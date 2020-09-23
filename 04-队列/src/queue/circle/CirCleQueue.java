package queue.circle;

public class CirCleQueue<E> {
    private int size;
    private int front;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CirCleQueue() {
        size = 0;
        front = 0;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[current(size)] = element;
        size++;
    }

    public E deQueue() {
        E element = elements[front++];
        elements[front - 1] = null;
        front = front == elements.length ? 0 : front;
        size--;
        return element;
    }

    public E front() {
        return elements[front];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[current(i)] = null;
        }
        size = 0;
        front = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ")
                .append(size)
                .append(", front_index = ")
                .append(front)
                .append(", rear_index = ")
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

    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;

        E[] newElements = (E[]) new Object[oldCapacity + oldCapacity >> 1];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[current(i)];
        }
        elements = newElements;
        front = 0;
    }

    private int current(int i) {
        return (front + i) % elements.length;
    }
}
