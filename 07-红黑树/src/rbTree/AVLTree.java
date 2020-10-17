package rbTree;

import com.sun.istack.internal.Nullable;

import java.util.Comparator;
import java.util.Objects;

public class AVLTree<E> extends BBST<E> {
    public AVLTree() {
        this(null);
    }

    public AVLTree(@Nullable Comparator comparator) {
        super(comparator);
    }

    protected void afterAdd(Node<E> node) {
        while (Objects.nonNull((node = node.parent))) {
            if (isBalance(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
                break;
            }
        }
    }

    protected void afterRemove(Node<E> node) {
        while (Objects.nonNull((node = node.parent))) {
            if (isBalance(node)) {
                // 更新高度
                updateHeight(node);
            } else {
                // 恢复平衡
                rebalance(node);
            }
        }
    }

    private void rebalance(Node<E> grand) {
        Node<E> parent = ((AVLNode)grand).tallerChild();
        Node<E> node = ((AVLNode)parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotateRight(grand);
            } else { // LR
                rotateLeft(parent);
                rotateRight(grand);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotateRight(parent);
                rotateLeft(grand);
            } else { // RR
                rotateLeft(grand);
            }
        }
    }

    private void rebalance2(Node<E> grand) {
        Node<E> parent = ((AVLNode)grand).tallerChild();
        Node<E> node = ((AVLNode)parent).tallerChild();
        if (parent.isLeftChild()) { // L
            if (node.isLeftChild()) { // LL
                rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);
            } else { // LR
                rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
            }
        } else { // R
            if (node.isLeftChild()) { // RL
                rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);
            } else { // RR
                rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);
            }
        }
    }

    @Override
    protected void rotate(Node<E> r, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
        super.rotate(r, a, b, c, d, e, f, g);
        // 更新高度
        updateHeight(b);
        updateHeight(f);
        updateHeight(d);
    }

    @Override
    protected void afterRotate(Node<E> child, Node<E> parent, Node<E> grand) {
        super.afterRotate(child, parent, grand);
        // 更新高度
        updateHeight(grand);
        updateHeight(parent);
    }

    private void updateHeight(Node<E> node) {
        ((AVLNode)node).updateHeight();
    }

    @Override
    protected Node<E> createNode(E element, Node<E> parent) {
        return new AVLNode<>(element, parent);
    }

    private boolean isBalance(Node<E> node) {
        return Math.abs(((AVLNode<E>)node).balanceFactor()) <= 1;
    }

    private static class AVLNode<E> extends Node<E> {
        int height = 1;

        public AVLNode(E element, Node<E> parent) {
            super(element, parent);
        }

        public int balanceFactor() {
            int leftHeight = Objects.nonNull(left) ? ((AVLNode<E>)left).height : 0;
            int rightHeight = Objects.nonNull(right) ? ((AVLNode<E>)right).height : 0;
            return leftHeight - rightHeight;
        }

        public void updateHeight() {
            int leftHeight = Objects.nonNull(left) ? ((AVLNode<E>)left).height : 0;
            int rightHeight = Objects.nonNull(right) ? ((AVLNode<E>)right).height : 0;
            height = 1 + Math.max(leftHeight, rightHeight);
        }

        public Node<E> tallerChild() {
            int leftHeight = Objects.nonNull(left) ? ((AVLNode<E>)left).height : 0;
            int rightHeight = Objects.nonNull(right) ? ((AVLNode<E>)right).height : 0;
            if (leftHeight > rightHeight) return left;
            if (leftHeight < rightHeight) return right;
            return isLeftChild() ? left : right;
        }

        @Override
        public String toString() {
            return element + "_p(" +
                    (Objects.nonNull(parent) ? parent.element : "null") + ")" +
                    "_h(" + height + ")";
        }
    }
}
