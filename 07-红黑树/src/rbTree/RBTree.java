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

    @Override
    protected void afterRemove(Node<E> node) {
        // 用以取代 node 的子节点是红节点
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<E> parent = node.parent;
        // 删除的是跟节点
        if (Objects.isNull(parent)) {
            return;
        }

        // 删除的是黑色叶子节点
        // 判断被删除的 node 是左还是右
        boolean left = Objects.isNull(parent.left) || node.isLeftChild();
        Node<E> sibling = left ? parent.right : parent.left;
        if (left) {
            if (isRed(sibling)) {
                black(parent);
                red(sibling);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟没有一个红色子节点, 父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少一个红色子节点
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }
                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) {
                red(sibling);
                black(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }
            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟没有一个红色子节点, 父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少一个红色子节点
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }
                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
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
        boolean color = RED;

        RBNode(E element, Node<E> parent) {
            super(element, parent);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (color == RED) {
                sb.append("R").append("_");
            }
            sb.append(element);
            return sb.toString();
        }
    }
}
