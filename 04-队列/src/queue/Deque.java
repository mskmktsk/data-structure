package queue;

import List.*;

public class Deque<E> {
    List<E> list = new LinkedList<E>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void enQueueRear(E element) {
        list.add(element);
    }

    public E deQueueFront() {
        return list.remove(0);
    }

    public void enQueueFront(E element) {
        list.add(0, element);
    }

    public E deQueueRear() {
        return list.remove(size() - 1);
    }

    public E front() {
        return list.get(0);
    }

    public E rear() {
        return list.get(size() - 1);
    }

    public void clear() {
        list.clear();
    }

    public String toString() {
        int size = size();
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size);
        if (!isEmpty()) {
            for (int i = 0; i < size; i++ ) {
                sb.append(i == 0 ? ", front < " : ", ");
                sb.append(list.get(i));
                if (i == (size - 1)) {
                    sb.append(" < rear");
                }
            }
        }
        return sb.toString();
    }
}
