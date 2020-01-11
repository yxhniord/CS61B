public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < (items.length/4) && items.length > 8) {
            reduce();
        }
    }

    public void expand() {
        int length = items.length * 2;
        resizeHelper(length);
    }

    public void reduce() {
        int length = items.length / 2;
        resizeHelper(length);
    }

    public void resizeHelper(int capacity) {
        T[] temp = items;
        int begin = plusOne(nextFirst);
        int end = minusOne(nextLast);
        items = (T[]) new Object[capacity];
        nextFirst = 0;
        nextLast = 1;
        for (int i = begin; i != end; i = plusOne(i, temp.length)) {
            items[nextLast] = temp[i];
            nextLast = plusOne(nextLast);
        }
        items[nextLast] = temp[end];
        nextLast = plusOne(nextLast);
    }

    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public T removeFirst() {
        resize();
        T res = getFirst();
        nextFirst = plusOne(nextFirst);
        items[nextFirst] = null;
        size--;
        return res;
    }

    public T getFirst() {
        return items[plusOne(nextFirst)];
    }

    public T removeLast() {
        resize();
        T res = getLast();
        nextLast = minusOne(nextLast);
        items[nextLast] = null;
        size--;
        return res;
    }

    public T getLast() {
        return items[minusOne(nextLast)];
    }

    public int minusOne(int index) {
        return Math.floorMod(index-1, items.length);
    }

    public int plusOne(int index) {
        return Math.floorMod(index+1, items.length);
    }

    public int plusOne(int index, int length) {
        return Math.floorMod(index+1, length);
    }

    public void printDeque() {
        for (int index = plusOne(nextFirst); index != nextLast; index = plusOne(index)){
            System.out.print(items[index]);
            System.out.print(" ");
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        index = Math.floorMod(nextFirst+index+1, items.length);
        return(items[index]);
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 30; i++) {
            arrayDeque.addLast(i);
        }
        for (int i = 0; i < 24; i++){
            arrayDeque.addFirst(i);
        }
        arrayDeque.printDeque();
        System.out.println("/r");
        System.out.println(arrayDeque.get(30));

    }
}
