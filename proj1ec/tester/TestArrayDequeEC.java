package tester;

import static org.junit.Assert.*;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;
public class TestArrayDequeEC {
    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> studentSolution = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correctSolution = new ArrayDequeSolution<>();
        String message = "";
        int testSize = 1000;
        for (int i = 0; i < testSize; i++) {
            int random = StdRandom.uniform(4);
            if (random == 0) {
                studentSolution.addFirst(i);
                correctSolution.addFirst(i);
                message += "addFirst(" + i + ")\n";
            } else if (random == 1) {
                studentSolution.addLast(i);
                correctSolution.addLast(i);
                message += "addLast(" + i + ")\n";
            } else if (random == 2) {
                if (studentSolution.size() > 0) {
                    Integer actual = studentSolution.removeFirst();
                    Integer expected = correctSolution.removeFirst();
                    message += "removeFirst()\n";
                    assertEquals(message, expected, actual);
                }
            } else {
                if (studentSolution.size() > 0) {
                    Integer actual = studentSolution.removeLast();
                    Integer expected = correctSolution.removeLast();
                    message += "removeLast()\n";
                    assertEquals(message, expected, actual);
                }
            }
        }
    }
}
