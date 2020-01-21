package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(4);
        assertTrue(arb.isEmpty());
        arb.enqueue(33.1);
        arb.enqueue(44.8);
        arb.enqueue(62.3);
        arb.enqueue(-3.4);
        assertEquals(arb.peek(), new Double(33.1));
        assertEquals(arb.capacity, 4);
        assertEquals(arb.capacity(), 4);
        assertEquals(arb.fillCount, 4);
        assertEquals(arb.fillCount(), 4);
        assertTrue(arb.isFull());
        assertEquals(arb.dequeue(), new Double(33.1));
        assertFalse(arb.isFull());
        assertEquals(arb.peek(), new Double(44.8));
        arb.enqueue(28.1);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
