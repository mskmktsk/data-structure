package binarySearchTree;

import binarySearchTree.printer.BinaryTreeInfo;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.*;

public class BinarySearchTree<E> extends BinaryTree {
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(@Nullable Comparator comparator) {
        this.comparator = comparator;
    }

    public void add(@NotNull E element) {
        // 添加第一个节点
        if (Objects.isNull(root)) {
            root = new Node<>(element, null);
            size++;
            return;
        }
        // 添加其他节点
        Node<E> parent = null;
        Node<E> node = root;
        int cmp = 0;
        while (node != null) {
            cmp = compareTo(element, node.element);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.element = element;
                return;
            }
        }
        Node<E> cNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = cNode;
        } else {
            parent.left = cNode;
        }
        size++;
    }

    public void remove(E element) {
        remove(node(element));
    }

    private void remove(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        if (node.hasTwoChildren()) {
            Node<E> s = successor(node);
            node.element = s.element;
            node = s;
        }

        Node<E> replacement = Objects.nonNull(node.left) ? node.left : node.right;

        if (Objects.nonNull(replacement)) {
            if (Objects.isNull(node.parent)) {
                root = replacement;
            }
            else if (replacement == node.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
        } else if (Objects.isNull(node.parent)) {
            root = null;
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
        }
    }

    public boolean contains(E element) {
        return Objects.nonNull(node(element));
    }

    public void levelOrder(Visitor<E> visitor) {
        if (Objects.isNull(visitor)) {
            return;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            visitor.visit(node.element);
            if (Objects.nonNull(node.left)) {
                queue.offer(node.left);
            }
            if (Objects.nonNull(node.right)) {
                queue.offer(node.right);
            }
        }
    }

    public void preorder(Visitor<E> visitor) {
        preorder(root, visitor);
    }

    private void preorder(Node<E> node, Visitor<E> visitor) {
        if (Objects.isNull(node) || Objects.isNull(visitor)) {
            return;
        }
        visitor.visit(node.element);
        preorder(node.left, visitor);
        preorder(node.right, visitor);
    }

    public void inorder(Visitor<E> visitor) {
        inorder(root, visitor);
    }

    private void inorder(Node<E> node, Visitor<E> visitor) {
        if (Objects.isNull(node) || Objects.isNull(visitor)) {
            return;
        }
        inorder(node.left, visitor);
        visitor.visit(node.element);
        inorder(node.right, visitor);
    }

    public void postorder(Visitor<E> visitor) {
        postorder(root, visitor);
    }

    private void postorder(Node<E> node, Visitor<E> visitor) {
        if (Objects.isNull(node) || Objects.isNull(visitor)) {
            return;
        }
        postorder(node.left, visitor);
        postorder(node.right, visitor);
        visitor.visit(node.element);
    }

    private Node<E> node(E element) {
        Node<E> node = root;
        int cmp;
        while (Objects.nonNull(node)) {
            cmp = compareTo(node.element, element);
            if (cmp == 0) {
                return node;
            }
            else if (cmp > 0) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return null;
    }

    private int compareTo(E e1, E e2) {
        if (Objects.nonNull(comparator)) {
            comparator.compare(e1, e2);
        }
        return ((Comparable)e1).compareTo(e2);
    }

    public static interface Visitor<E> {
        void visit(E element);
    }
}
