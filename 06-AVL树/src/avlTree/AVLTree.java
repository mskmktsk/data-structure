package avlTree;

import avlTree.binarySearchTree.BinarySearchTree;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree {
    public AVLTree() {
        this(null);
    }

    public AVLTree(Comparator comparator) {
        super(comparator);
    }
}
