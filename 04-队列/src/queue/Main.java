package queue;

import queue.circle.CirCleDeque;
import queue.circle.CirCleQueue;

public class Main {
    public static void main(String[] args) {
        printQueue();
        printDeque();
        printCircleQueue();
        printCircleDeque();
    }

    public static void printCircleDeque() {
        System.out.println("双端循环队列:");
        CirCleDeque<Integer> deque = new CirCleDeque<>();
        deque.enQueueFront(1);
        deque.enQueueFront(2);
        deque.enQueueFront(3);
        deque.enQueueRear(4);
        deque.enQueueRear(5);
        deque.enQueueRear(6);
        System.out.println(deque);
        System.out.println("Deque front: " + deque.front());
        System.out.println("Deque  rear: " + deque.rear());
        while (!deque.isEmpty()) {
            if (deque.size() % 2 == 0) {
                System.out.println("deQueue front: " + deque.deQueueFront());
            } else {
                System.out.println("deQueue  rear: " + deque.deQueueRear());
                System.out.println(deque);
            }
        }
    }

    public static void printCircleQueue() {
        System.out.println("循环队列:");
        CirCleQueue<Integer> queue = new CirCleQueue<>();
        for (int i = 1; i <= 10; i++) {
            queue.enQueue(i);
        }
        System.out.println("front: " + queue.front());
        System.out.println(queue);
        while (queue.size() > 1) {
            queue.deQueue();
            System.out.println(queue);
        }
        queue.enQueue(11);
        queue.enQueue(12);
        System.out.println(queue);
    }

    public static void printDeque() {
        System.out.println("双端队列:");
        Deque<Integer> deque = new Deque<>();
        deque.enQueueFront(1);
        deque.enQueueFront(2);
        deque.enQueueFront(3);
        deque.enQueueRear(4);
        deque.enQueueRear(5);
        deque.enQueueRear(6);
        System.out.println(deque);
        System.out.println("Deque front: " + deque.front());
        System.out.println("Deque  rear: " + deque.rear());
        while (!deque.isEmpty()) {
            if (deque.size() % 2 == 0) {
                System.out.println("deQueue front: " + deque.deQueueFront());
            } else {
                System.out.println("deQueue  rear: " + deque.deQueueRear());
                System.out.println(deque);
            }
        }
    }

    public static void printQueue() {
        System.out.println("队列:");
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        System.out.println(queue);
        System.out.println("front: " + queue.front());
        while (!queue.isEmpty()) {
            System.out.println("deQueue: " + queue.deQueue());
        }
    }
}
