package avlTree;

import avlTree.binarySearchTree.BST;
import com.sun.istack.internal.Nullable;

import java.util.Comparator;
import java.util.Objects;

public class AVLTree<E> extends BST<E> {
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

    private void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(child, parent, grand);
    }

    private void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(child, parent, grand);
    }

    private void afterRotate(Node<E> child, Node<E> parent, Node<E> grand) {
        // 让 parent 成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // node 是 root 节点
            root = parent;
        }

        // 更新 child 的 parent
        if (Objects.nonNull(child)) {
            child.parent = grand;
        }

        // 更新 grand 的 parent
        grand.parent = parent;

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
