package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            int randVal = StdRandom.uniform(0, 100);
            correct.addLast(randVal);
            buggy.addLast(randVal);
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(correct.removeLast(), buggy.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                buggy.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correctSize = correct.size();
                int buggySize = buggy.size();
                System.out.println("size()");
                System.out.println("correctSize: " + correctSize);
                System.out.println("buggySize: " + buggySize);
                assertEquals(correctSize, buggySize);
            } else if (correct.size() == 0) {
                continue;
            } else if (operationNumber == 2) {
                // getLast
                int correctLast = correct.getLast();
                int buggyLast = buggy.getLast();
                System.out.println("getLast()");
                System.out.println("correctLast: " + correctLast);
                System.out.println("buggyLast: " + buggyLast);
                assertEquals(correctLast, buggyLast);
            } else if (operationNumber == 3) {
                // removeLast
                int correctLast = correct.removeLast();
                int buggyLast = buggy.removeLast();
                System.out.println("removeLast()");
                System.out.println("correctLast: " + correctLast);
                System.out.println("buggyLast: " + buggyLast);
                assertEquals(correctLast, buggyLast);
            }
            assertEquals(correct.size(), buggy.size());
        }
    }
}
