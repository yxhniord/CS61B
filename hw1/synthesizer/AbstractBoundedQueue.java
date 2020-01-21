package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;    // return number of items currently in the buffer
    protected int capacity;     // return size of the buffer
    // return size of the buffer
    public int capacity() {
        return capacity;
    }
    //return number of items currently in the buffer
    public int fillCount() {
        return fillCount;
    }
    /*// is the buffer empty (fillCount equals zero)?
    public boolean isEmpty() {
        return fillCount() == 0;
    }
    // is the buffer full (fillCount is same as capacity)?
    public boolean isFull() {
        return capacity() == fillCount();
    }*/
    // return (but do not delete) item from the front
    public abstract T peek();
    // delete and return item from the front
    public abstract T dequeue();
    // add item x to the end
    public abstract void enqueue(T x);
}
