import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testArrayDequeGold() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        //addFirst
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = sad.get(i);
            int expected = ads.get(i);
            assertEquals("Oh noooo!\nThis is bad in addFirst:\n   Random number " + actual
                    + " not equal to " + expected + "!", actual, expected);
        }

        //addLast
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = sad.get(i);
            int expected = ads.get(i);
            assertEquals("Oh noooo!\nThis is bad in addLast:\n   Random number " + actual
                    + " not equal to " + expected + "!", actual, expected);
        }

        //removeFirst
        ArrayList<Integer> actualList = new ArrayList<>();
        ArrayList<Integer> expectedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualList.add(sad.removeFirst());
            expectedList.add(ads.removeFirst());
        }
        for (int i = 0; i < 10; i++) {
            Integer actual = sad.get(i);
            Integer expected = ads.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst:\n   Random number " + actual
                    + " not equal to " + expected + "!", actual, expected);
        }
        for (int i = 0; i < 10; i++) {
            Integer actual = actualList.get(i);
            Integer expected = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeFirst:\n   Random number " + actual
                    + " not equal to " + expected + "!", actual, expected);
        }

        //removeLast
        actualList.clear();
        expectedList.clear();
        for (int i = 0; i < 10; i++) {
            actualList.add(sad.removeLast());
            expectedList.add(ads.removeLast());
        }
        int actual = sad.size();
        int expected = ads.size();
        assertEquals("Oh noooo!\nThis is bad in removeLast:\n   actual size " + actual
                + " not equal to " + expected + "!", actual, expected);
        for (int i = 0; i < 10; i++) {
            Integer actual1 = actualList.get(i);
            Integer expected1 = expectedList.get(i);
            assertEquals("Oh noooo!\nThis is bad in removeLast:\n   Random number " + actual1
                    + " not equal to " + expected1 + "!", actual1, expected1);
        }
    }

    @Test
    public void testArrayGold2() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();

        //addFirst
        int random = StdRandom.uniform(100);
        ads.addFirst(random);
        sad.addFirst(random);
        assertEquals("addFirst(" + random + ")", sad.get(0), ads.get(0));
        System.out.println("addFirst(" + random + ")");

        //addLast
        random = StdRandom.uniform(100);
        ads.addLast(random);
        sad.addLast(random);
        assertEquals("addLast(" + random + ")", sad.get(1), ads.get(1));
        System.out.println("addLast(" + random + ")");

        //removeFirst
        Integer actual = sad.removeFirst();
        Integer expected = ads.removeFirst();
        assertEquals("removeFirst()", actual, expected);
        System.out.println("removeFirst()");

        //removeLast
        Integer actual1 = sad.removeLast();
        Integer expected1 = ads.removeLast();
        assertEquals("removeLast()", actual1, expected1);
        System.out.println("removeLast()");
    }
}
