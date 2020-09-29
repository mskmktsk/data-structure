package binarySearchTree;

import binarySearchTree.printer.BinaryTrees;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        printer();
    }

    public static void printer() {
        Integer data[] = new Integer[] {
                7, 4, 9, 2, 5, 8, 11, 3
        };

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        Arrays.stream(data).forEach(bst::add);

        BinaryTrees.println(bst);
    }

}
