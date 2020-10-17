package avlTree;

import avlTree.printer.BinaryTrees;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Integer[] data = new Integer[] {
                80, 90, 5, 97, 73, 61, 35, 9, 1, 67, 65, 84, 39, 56, 43, 23, 27, 16, 60, 87
        };
        AVLTree<Integer> avl = new AVLTree<>();
        for (Integer d : data) {
            avl.add(d);
        }
        BinaryTrees.println(avl);
        System.out.println("--------------------------");
        avl.remove(1);
        avl.remove(5);
        BinaryTrees.println(avl);
    }
}
