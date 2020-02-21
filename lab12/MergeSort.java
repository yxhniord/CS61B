import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> queues = new Queue<>();
        for (Item item : items) {
            Queue<Item> queue = new Queue<>();
            queue.enqueue(item);
            queues.enqueue(queue);
        }
        return queues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> mergeSortedQueues = new Queue<>();
        int mergeSortedQueuesSize = q1.size() + q2.size();
        for (int i = 0; i < mergeSortedQueuesSize; i++) {
            mergeSortedQueues.enqueue(getMin(q1, q2));
        }
        return mergeSortedQueues;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }
        Queue<Queue<Item>> queues = makeSingleItemQueues(items);
        Queue<Item> qLeft = new Queue<>();
        Queue<Item> qRight = new Queue<>();
        int leftSize = queues.size() / 2;
        int rightSize = queues.size() - queues.size() / 2;
        for (int i = 0; i < leftSize; i++) {
            qLeft.enqueue(queues.dequeue().dequeue());
        }
        for (int i = 0; i < rightSize; i++) {
            qRight.enqueue(queues.dequeue().dequeue());
        }
        /*Queue<Item> qLeft = new Queue<>();
        Queue<Item> qRight = new Queue<>();
        int leftSize = items.size() / 2;
        int rightSize = items.size() - items.size() / 2;
        for (int i = 0; i < leftSize; i++) {
            qLeft.enqueue(items.dequeue());
        }
        for (int i = 0; i < rightSize; i++) {
            qRight.enqueue(items.dequeue());
        }*/
        Queue<Item> q1 = mergeSort(qLeft);
        Queue<Item> q2 = mergeSort(qRight);
        return mergeSortedQueues(q1, q2);
    }

    public static void main(String[] args) {
        Queue<String> students = new Queue<>();
        students.enqueue("Alice");
        students.enqueue("Vanessa");
        students.enqueue("Ethan");
        students.enqueue("Benjamin");
        students.enqueue("Tomas");
        students.enqueue("Benjamin");
        System.out.println(students.toString());
        Queue<String> sortedStudents = mergeSort(students);
        System.out.println(sortedStudents.toString());
    }
}


