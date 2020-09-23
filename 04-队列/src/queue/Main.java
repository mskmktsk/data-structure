package queue;

public class Main {
    public static void main(String[] args) {
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
        System.out.println(queue);
    }
}
