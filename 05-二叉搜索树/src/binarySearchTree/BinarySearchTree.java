package binarySearchTree;

import binarySearchTree.printer.BinaryTreeInfo;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.Comparator;
import java.util.Objects;

public class BinarySearchTree<E> implements BinaryTreeInfo {
    private int size;
    private Node<E> root;
    private Comparator<E> comparator;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(@Nullable Comparator comparator) {
        this.comparator = comparator;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
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
    }

    public boolean contains(E element) {
        return false;
    }

    public void preorderTraversal() {
        preorderTraversal(root);
    }

    private void preorderTraversal(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        System.out.print(node.element + ",");
        preorderTraversal(node.left);
        preorderTraversal(node.right);
    }
    
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    private void inorderTraversal(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        inorderTraversal(node.left);
        System.out.print(node.element + ",");
        inorderTraversal(node.right);
    }

    public void postorderTraversal() {
        postorderTraversal(root);
    }

    private void postorderTraversal(Node<E> node) {
        if (Objects.isNull(node)) {
            return;
        }
        postorderTraversal(node.left);
        postorderTraversal(node.right);
        System.out.print(node.element + ",");
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>)node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>)node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>)node).element;
    }

    private int compareTo(E e1, E e2) {
        if (Objects.nonNull(comparator)) {
            comparator.compare(e1, e2);
        }
        return ((Comparable)e1).compareTo(e2);
    }

    private static final class Node<E> {
        E element;
        Node<E> left;
        Node<E> right;
        Node<E> parent;

        public Node(E element, Node parent) {
            this.element = element;
            this.parent = parent;
        }
    }
}
