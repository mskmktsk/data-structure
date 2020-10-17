package rbTree;

import rbTree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer[] data = new Integer[] {
                80, 90, 5, 97, 73, 61, 35, 9, 1, 67, 65, 84, 39, 56, 43, 23, 27, 16, 60, 87
        };
        RBTree<Integer> rb = new RBTree<>();
        for (Integer d : data) {
            rb.add(d);
        }
        BinaryTrees.println(rb);
//        System.out.println("--------------------------");
//        rb.remove(1);
//        rb.remove(5);
//        BinaryTrees.println(rb);
    }
}
