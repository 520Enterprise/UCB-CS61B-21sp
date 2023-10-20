package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst, nextLast;

    public ArrayDeque() {
        int initCapacity = 8;
        items = (T[]) new Object[initCapacity];
        size = 0;
        nextFirst = initCapacity / 2 - 1;
        nextLast = initCapacity / 2;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, nextFirst + 1, newItems, capacity / 2 - size / 2, size);
        items = newItems;
        nextFirst = capacity / 2 - size / 2 - 1;
        nextLast = nextFirst + size + 1;
    }

    @Override
    public void addFirst(T item) {
        if (nextFirst == -1) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst--;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (nextLast == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast++;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst++;
        size--;
        if (size < items.length / 4) {
            if (items.length >= 16) {
                resize(items.length / 2);
            } else if (nextFirst >= 4) {
                resize(8);
            }
        }
        return first;
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        size--;
        if (size < items.length / 4) {
            if (items.length >= 16) {
                resize(items.length / 2);
            } else if (nextLast <= 3) {
                resize(8);
            }
        }
        return last;
    }

    @Override
    public int size() {
        return size;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = items[nextFirst + 1 + index];
            index++;
            return item;
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public void printDeque() {
        System.out.print("[ ");
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.print("]");
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[nextFirst + 1 + index];
    }

    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (o == this) {
            return true;

        }
        Deque<T> other = (Deque<T>) o;
        if (size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
