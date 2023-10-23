package bstmap;

import edu.neu.ccs.quick.Pair;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;             // root of BST

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
            this.left = null;
            this.right = null;
        }
    }

    BSTMap() {
        root = null;
    }

    @Override
    public void clear() {
        root = null;
    }

    private class ReturnValue {
        private boolean found;
        private Node node;

        public ReturnValue(boolean found,@Nullable Node node) {
            this.found = found;
            this.node = node;
        }

        public boolean getFound() {
            return found;
        }

        public Node getNode() {
            return node;
        }
    }

    private ReturnValue get(Node x, K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (x == null) {
            return new ReturnValue(false, null);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return new ReturnValue(true, x);
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(root, key).getFound();
    }
    @Override
    public V get(K key) {
        ReturnValue returnValue = get(root, key);
        if (returnValue.getFound()) {
            return returnValue.getNode().val;
        } else {
            return null;
        }
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else return x.size;
    }
    @Override
    public int size() {
        return size(root);
    }

    private Node put(Node x, K key, V value) {
        if (x == null) {
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, value);
    }

    /* An iterator class for BSTMap. */
    //TODO: Implement the BSTMapIterator class here!
    private class BSTMapIterator implements Iterator<K> {
        private Node curr;
        private int index;

        public BSTMapIterator() {
            curr = root;
            index = 0;
        }

        public boolean hasNext() {
            return index < size();
        }

        public K next() {
            if (!hasNext()) {
                throw new IllegalArgumentException("No next element");
            }
            index++;
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        printInOrder(x.left);
        System.out.println("Key: " + x.key + " | Value: " + x.val);
        printInOrder(x.right);
    }
    public void printInOrder() {
        printInOrder(root);
    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}
