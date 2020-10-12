package avlTree;

import avlTree.binarySearchTree.BinarySearchTree;
import com.sun.istack.internal.Nullable;

import java.util.Comparator;

public class AVLTree<E> extends BinarySearchTree {
    public AVLTree() {
        this(null);
    }

    public AVLTree(@Nullable Comparator comparator) {
        super(comparator);
    }
}
