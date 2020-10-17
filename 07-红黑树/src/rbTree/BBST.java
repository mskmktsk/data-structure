package rbTree;

import com.sun.istack.internal.Nullable;

import java.util.Comparator;
import java.util.Objects;

public class BBST<E> extends BST<E> {

    public BBST() {
        this(null);
    }

    public BBST(@Nullable Comparator comparator) {
        this.comparator = comparator;
    }

    /**
     * 统一旋转
     */
    protected void rotate(Node<E> r,
                          Node<E> a, Node<E> b, Node<E> c,
                          Node<E> d,
                          Node<E> e, Node<E> f, Node<E> g) {
        // 让 d 成为子树的根节点
        d.parent = r.parent;
        if (r.isLeftChild()) {
            r.parent.left = d;
        } else if (r.isRightChild()) {
            r.parent.right = d;
        } else {
            root = d;
        }

        // a-b-c
        b.left = a;
        if (Objects.nonNull(a)) {
            a.parent = b;
        }
        b.right = c;
        if (Objects.nonNull(c)) {
            c.parent = b;
        }

        // e-f-g
        f.left = e;
        if (Objects.nonNull(e)) {
            e.parent = f;
        }
        f.right = g;
        if (Objects.nonNull(g)) {
            g.parent = f;
        }

        d.left = b;
        d.right = f;
        b.parent = d;
        f.parent = d;
    }

    protected void rotateLeft(Node<E> grand) {
        Node<E> parent = grand.right;
        Node<E> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(child, parent, grand);
    }

    protected void rotateRight(Node<E> grand) {
        Node<E> parent = grand.left;
        Node<E> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(child, parent, grand);
    }

    protected void afterRotate(Node<E> child, Node<E> parent, Node<E> grand) {
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
    }
}
