package deque;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        ArrayDeque<String> lld1 = new ArrayDeque<>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterward. */
    public void addRemoveTest() {

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeLastEmptyTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.removeLast();
        assertEquals(0, lld1.size());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void addLargeTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            lld1.addLast(i);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, (int) lld1.removeFirst());
        }
    }

    @Test
    /* Test equals method */
    public void equalsTest() {
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        ArrayDeque<Integer> lld2 = new ArrayDeque<>();
        ArrayDeque<Integer> lld3 = new ArrayDeque<>();
        for (int i = 0; i < 1000; i++) {
            lld1.addLast(i);
            lld2.addLast(i);
            lld3.addLast(i + 1);
        }
        assertEquals(lld1, lld2);
        assertNotEquals(lld1, lld3);
    }

    @Test
    /* Test get method */
    public void getTest() {
        /*
            - sequence of ArrayDeque operations was:
         ArrayDeque.addFirst(0)
         ArrayDeque.removeFirst()     ==> 0
         ArrayDeque.addLast(2)
         ArrayDeque.addLast(3)
         ArrayDeque.addLast(4)
         ArrayDeque.addFirst(5)
         ArrayDeque.removeFirst()     ==> 5
         ArrayDeque.addLast(7)
         ArrayDeque.removeFirst()     ==> 2
         ArrayDeque.addLast(9)
         ArrayDeque.get(2)      ==> 7
         ArrayDeque.removeFirst()     ==> 3
         ArrayDeque.removeLast()      ==> 9
         ArrayDeque.addLast(13)
         ArrayDeque.removeFirst()     ==> 4
         ArrayDeque.removeFirst()     ==> 7
         ArrayDeque.addFirst(16)
         ArrayDeque.removeFirst()     ==> 16
         ArrayDeque.addLast(18)
         */
        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(0);
        lld1.removeFirst();
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addFirst(5);
        lld1.removeFirst();
        lld1.addLast(7);
        lld1.removeFirst();
        lld1.addLast(9);
        assertEquals(7, (int) lld1.get(2));
        lld1.removeFirst();
        lld1.removeLast();
        lld1.addLast(13);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.addFirst(16);
        assertEquals(16, (int) lld1.removeFirst());
        lld1.addLast(18);
    }
}
