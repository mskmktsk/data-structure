package queue.circle;

public class CirCleDeque<E> {
    private int size;
    private int front;
    private int rear;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CirCleDeque() {
        size = 0;
        front = 0;
        rear = 0;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueueRear(E element) {
        ensureCapacity(size + 1);
        rear = current(size);
        elements[rear] = element;
        size++;
    }

    public E deQueueFront() {
        E element = elements[front++];
        elements[front - 1] = null;
        front = front == elements.length ? 0 : front;
        size--;
        if (size == 0) {
            rear = 0;
        }
        return element;
    }

    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        if (size != 0) {
            front = (front == 0 ? elements.length : front) - 1;
        }
        elements[front] = element;
        size++;
    }

    public E deQueueRear() {
        E element = elements[rear--];
        rear = rear == -1 ? elements.length - 1 : rear;
        size--;
        if (size == 0) {
            front = 0;
        }
        return element;
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[rear];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[current(i)] = null;
        }
        size = 0;
        front = 0;
        rear = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ")
                .append(size)
                .append(", front_index = ")
                .append(front)
                .append(", rear_index = ")
                .append(rear);
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
        rear = size - 1;
    }

    private int current(int i) {
        return (front + i) % elements.length;
    }
}
