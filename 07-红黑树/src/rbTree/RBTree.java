package rbTree;

import rbTree.binarySearchTree.BST;

import java.util.Comparator;
import java.util.Objects;

public class RBTree<E> extends BST<E> {
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (Objects.nonNull(node)) {
            ((RBNode<E>)node).color = color;
        }
        return node;
    }

    private Node<E> black(Node<E> node) {
        return color(node, BLACK);
    }

    private Node<E> red(Node<E> node) {
        return color(node, RED);
    }

    private boolean colorOf(Node<E> node) {
        return Objects.isNull(node) ? BLACK : ((RBNode<E>)node).color;
    }

    private boolean isBlack(Node<E> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<E> node) {
        return colorOf(node) == RED;
    }

    private static class RBNode<E> extends Node<E> {
        boolean color;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }
    }
}
