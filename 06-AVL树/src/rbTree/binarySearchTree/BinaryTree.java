package rbTree.binarySearchTree;

import rbTree.binarySearchTree.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BinaryTree<E> implements BinaryTreeInfo {
    protected int size;
    protected Node<E> root;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        root = null;
        size = 0;
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

    public int height() {
        if (Objects.isNull(root)) {
            return 0;
        }
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        int levelSize = queue.size();
        while (!queue.isEmpty()) {
            Node<E> node = queue.poll();
            levelSize--;
            if (Objects.nonNull(node.left)) {
                queue.offer(node.left);
            }
            if (Objects.nonNull(node.right)) {
                queue.offer(node.right);
            }
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    public int height2() {
        return height2(root);
    }

    private int height2(Node<E> node) {
        if (Objects.isNull(node)) {
            return 0;
        }
        return 1 + Math.max(height2(node.left), height2(node.right));
    }

    /**
     * 创建节点
     * @param element 节点内容
     * @param parent 父节点
     * @return 新创建的节点
     */
    protected Node<E> createNode(E element, Node<E> parent) {
        return new Node<>(element, parent);
    }

    protected Node<E> predecessor(Node<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        // 前驱节点在左子树中
        Node<E> p = node.left;
        if (Objects.nonNull(p)) {
            while (Objects.nonNull(p.right)) {
                p = p.right;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (Objects.nonNull(node.parent) && node == node.parent.left) {
            node = node.parent;
        }

        return node.parent;
    }

    protected Node<E> successor(Node<E> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        // 后继节点在右子树中
        Node<E> p = node.right;
        if (Objects.nonNull(p)) {
            while (Objects.nonNull(p.left)) {
                p = p.left;
            }
            return p;
        }

        // 从父节点、祖父节点中寻找前驱节点
        while (Objects.nonNull(node.parent) && node == node.parent.right) {
            node = node.parent;
        }

        return node.parent;
    }

    public static interface Visitor<E> {
        void visit(E element);
    }

    protected static class Node<E> {
        public E element;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return Objects.isNull(left)&& Objects.isNull(right);
        }

        public boolean hasTwoChildren() {
            return Objects.nonNull(left) && Objects.nonNull(right);
        }

        public boolean isLeftChild() {
            return Objects.nonNull(parent) && this == parent.left;
        }

        public boolean isRightChild() {
            return Objects.nonNull(parent) && this == parent.right;
        }
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
}
