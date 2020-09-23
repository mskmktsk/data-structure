package queue.circle;

public class CirCleQueue<E> {
    private int size;
    private int first;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CirCleQueue() {
        size = 0;
        first = 0;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        elements[current(size)] = element;
        size++;
    }

    public E deQueue() {
        E element = elements[first++];
        elements[first - 1] = null;
        first = first == elements.length ? 0 : first;
        size--;
        return element;
    }

    public E front() {
        return elements[first];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[current(i)] = null;
        }
        size = 0;
        first = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ")
                .append(size)
                .append(", first = ")
                .append(first)
                .append(", last = ")
                .append(current(size - 1));
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                sb.append(i == 0 ? ", (front) < " : ", ");
                sb.append(elements[current(i)]);
            }
            sb.append(" < (rear)");
        }
        return sb.toString();
    }

    private int current(int i) {
        return (first + i) % elements.length;
    }
}
