package binarySearchTree;

import binarySearchTree.printer.BinaryTrees;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3
        };
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Arrays.stream(data).forEach(bst::add);

        BinaryTrees.println(bst);
        printer(bst);
        System.out.println();

        System.out.println("Tree height：" + bst.height());

        System.out.println("Tree contains 9: " + bst.contains(9));
        bst.remove(9);
        System.out.println("remove 9");
        System.out.println("Tree contains 9: " + bst.contains(9));
        BinaryTrees.println(bst);
        printer(bst);
        System.out.println();

        bst.clear();
        BinaryTrees.println(bst);
        System.out.println("Tree clear.");
    }

    public static void printer(BinarySearchTree<Integer> bst) {
        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.print("_" + element + "_");
            }
        });
    }
}
