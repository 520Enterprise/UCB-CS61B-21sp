package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newItems, 0, size);
        items = newItems;
    }
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items, 0, items, 1, size);
        items[0] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[size] = item;
        size++;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[0];
        System.arraycopy(items, 1, items, 0, size - 1);
        size--;
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[size - 1];
        items[size - 1] = null;
        size--;
        if (size < items.length / 4 && items.length >= 16) {
            resize(items.length / 2);
        }
        return last;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return items[0];
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return items[size - 1];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        public ArrayDequeIterator() {
            index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = items[index];
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
        return items[index];
    }
}
