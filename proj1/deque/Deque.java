package deque;

import java.util.Iterator;

public interface Deque<T> {
    public void addFirst(T item);

    public void addLast(T item);

    public T removeFirst();

    public T removeLast();

    public T getFirst();

    public T getLast();

    public int size();

    public boolean isEmpty();

    public Iterator<T> iterator();

    public boolean equals(Object o);

    public void printDeque();

    public T get(int index);
}
