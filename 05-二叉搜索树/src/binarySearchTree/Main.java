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
    }

    public static void printer(BinarySearchTree<Integer> bst) {
        bst.inorder(new BinarySearchTree.Visitor<Integer>() {
            @Override
            public void visit(Integer element) {
                System.out.print("_" + element + "_");
            }
        });
    }

    public static void printer2(BinarySearchTree<Integer> bst) {
        bst.preorderTraversal();
        System.out.println();
        bst.inorderTraversal();
        System.out.println();
        bst.postorderTraversal();
        System.out.println();
        bst.levelOrderTraversal();
    }
}
