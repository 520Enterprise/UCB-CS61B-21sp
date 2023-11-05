package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MyHashMap.Node)) {
                return false;
            }
            MyHashMap.Node other = (MyHashMap.Node) o;
            return key.equals(other.key) && value.equals(other.value);
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private int itemSize; // number of items stored in the hash table
    private int bucketSize; // number of buckets
    private final double loadFactor; // max load factor
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = createTable(initialSize);
        loadFactor = maxLoad;
        bucketSize = initialSize;
        itemSize = 0;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new java.util.LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
       return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % bucketSize;
    }

    @Override
    public void clear() {
        itemSize = 0;
        buckets = createTable(bucketSize);
    }

    @Override
    /* You can assume null keys will never be inserted.
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to containsKey() is null");
        }
        return get(key) != null;
    }

    @Override
    /* You can assume null keys will never be inserted.
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        int index = hash(key);
        if (buckets[index] == null) {
            return null;
        }
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return itemSize;
    }

    @Override
    /*
      If the same key is inserted more than once, the value should be updated each time.
      You can assume null keys will never be inserted.
     */
    public void put(K key, V value) {
        if ((double) itemSize / bucketSize > loadFactor) {
            resize(bucketSize * 2);
        }
        int index = hash(key);
        if (containsKey(key)) {
            for (Node node : buckets[index]) {
                if (node.key.equals(key)) {
                    node.value = value;
                }
            }
        } else {
            if (buckets[index] == null) {
                buckets[index] = createBucket();
            }
            buckets[index].add(createNode(key, value));
            itemSize += 1;
        }
    }

    private void resize(int newSize) {
        Collection<Node>[] newBuckets = createTable(newSize);
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    int index = hash(node.key);
                    if (newBuckets[index] == null) {
                        newBuckets[index] = createBucket();
                    }
                    newBuckets[index].add(node);
                }
            }
        }
        buckets = newBuckets;
        bucketSize = newSize;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new java.util.HashSet<>();
        for (Collection<Node> bucket : buckets) {
            if (bucket != null) {
                for (Node node : bucket) {
                    set.add(node.key);
                }
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to remove() is null");
        }
        if (!containsKey(key)) {
            return null;
        }
        V value = get(key);
        int index = hash(key);
        buckets[index].remove(createNode(key, value));
        itemSize -= 1;
        return value;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to remove() is null");
        }
        if (!containsKey(key) || !get(key).equals(value)) {
            return null;
        }
        int index = hash(key);
        buckets[index].remove(createNode(key, value));
        itemSize -= 1;
        return value;
    }

    protected class MyHashMapIterator implements Iterator<K> {
        private int bucketIndex;
        private int itemIndex;
        private Iterator<Node> bucketIterator;

        public MyHashMapIterator() {
            bucketIndex = 0;
            itemIndex = 0;
            bucketIterator = buckets[bucketIndex].iterator();
        }

        @Override
        public boolean hasNext() {
            return bucketIndex < bucketSize && itemIndex < itemSize;
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            if (!bucketIterator.hasNext()) {
                bucketIndex += 1;
                bucketIterator = buckets[bucketIndex].iterator();
            }
            itemIndex += 1;
            return bucketIterator.next().key;
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }
}
