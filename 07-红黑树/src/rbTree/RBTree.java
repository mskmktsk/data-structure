package rbTree;

import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class RBTree<E> extends BBST<E> {
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    public RBTree() {
        this(null);
    }

    public RBTree(Comparator<E> comparator) {
        super(comparator);
    }

    @Override
    protected void afterAdd(Node<E> node) {
        Node<E> parent = node.parent;

        if (Objects.isNull(parent)) {
            black(node);
            return;
        }

        if (isBlack(parent)) {
            return;
        }

        Node<E> uncle = parent.sibling();
        Node<E> grand = red(parent.parent);

        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterAdd(grand);
            return;
        }

        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { // R
            if (node.isRightChild()) { // RR
                black(parent);
            } else {
                black(node);
                rotateRight(parent);
            }
            rotateLeft(grand);
        }
    }

    private Node<E> color(Node<E> node, boolean color) {
        if (Objects.nonNull(node)) {
            ((RBNode<E>)node).color = color;
        }
        return node;
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new RBNode<>(element, parent);
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(color ? "B" : "R").append("_").append(element);
            return sb.toString();
        }
    }
}
