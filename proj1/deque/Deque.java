package deque;

import java.util.Iterator;

public interface Deque<T> {
    void addFirst(T item);

    void addLast(T item);

    T removeFirst();

    T removeLast();

    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    Iterator<T> iterator();

    boolean equals(Object o);

    void printDeque();

    T get(int index);
}
