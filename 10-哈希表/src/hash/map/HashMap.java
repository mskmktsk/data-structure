package hash.map;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    private int size;
    private Node<K, V>[] table;
    private static final int DEFAULT_CAPACITY = 1 << 4;

    public HashMap() {
        table = new Node[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        if (isEmpty()) return;
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
    }

    @Override
    public V put(K key, V value) {
        int index = index(key);
        // 取出 index 索引上的红黑树根节点
        Node<K, V> root = table[index];
        if (Objects.isNull(root)) {
            root = new Node<>(key, value, null);
            table[index] = root;
            size++;
            afterPut(root);
            return null;
        }

        // 添加新的节点到红黑树上

        Node<K, V> parent = root;
        Node<K, V> node = root;
        int h1 = Objects.isNull(key) ? 0 : key.hashCode();
        int cmp = 0;
        do {
            cmp = compareTo(key, node.key, h1, node.hash);
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                node.key = key;
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
        } while (node != null);
        Node<K, V> cNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = cNode;
        } else {
            parent.left = cNode;
        }
        size++;
        // 新添加之后的处理
        afterPut(cNode);
        return null;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return Objects.nonNull(node) ? node.value : null;
    }

    @Override
    public V remove(K key) {
        return remove(node(key));
    }

    @Override
    public boolean containsKey(K key) {
        return Objects.nonNull(node(key));
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {

    }
    
    private V remove(Node<K, V> node) {

        if (Objects.isNull(node)) {
            return null;
        }

        if (node.hasTwoChildren()) {
            Node<K, V> s = successor(node);
            node.key = s.key;
            V value = node.value;
            node.value = s.value;
            s.value = value;
            node = s;
        }

        Node<K, V> replacement = Objects.nonNull(node.left) ? node.left : node.right;
        int index = index(node);

        if (Objects.nonNull(replacement)) {
            replacement.parent = node.parent;
            if (Objects.isNull(node.parent)) {
                table[index] = replacement;
            }
            else if (node == node.parent.left) {
                node.parent.left = replacement;
            } else {
                node.parent.right = replacement;
            }
            // 删除 node 后的处理
            afterRemove(replacement);
        } else if (Objects.isNull(node.parent)) {
            table[index] = null;
            // 删除 node 后的处理
            afterRemove(node);
        } else {
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                node.parent.right = null;
            }
            // 删除 node 后的处理
            afterRemove(node);
        }
        return node.value;
    }

    private void afterRemove(Node<K, V> node) {
        // 用以取代 node 的子节点是红节点
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是跟节点
        if (Objects.isNull(parent)) {
            return;
        }

        // 删除的是黑色叶子节点
        // 判断被删除的 node 是左还是右
        boolean left = Objects.isNull(parent.left) || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
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

    private Node<K, V> node(K key) {
        Node<K, V> node = table[index(key)];
        int h1 = Objects.nonNull(key) ? key.hashCode() : 0;
        int cmp;
        while (Objects.nonNull(node)) {
            cmp = compareTo(key, node.key, h1, node.hash);
            if (cmp == 0) {
                return node;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    /**
     * 根据 key 生成对应的索引
     */
    private int index(K key) {
        if (Objects.isNull(key)) return 0;
        int hash = key.hashCode();
        hash = hash ^ (hash >>> 16);
        return hash & (table.length - 1);
    }

    private int index(Node<K, V> node) {
        return (node.hash ^ (node.hash >>> 16)) & (table.length - 1);
    }

    private int compareTo(K k1, K k2, int h1, int h2) {
        // 比较哈希
        int result = h1 - h2;
        if (result != 0) return result;

        // 比较equals
        if (Objects.equals(k1, k2)) return 0;

        // 哈希值相等，但是不equals
        if (Objects.nonNull(k1) && Objects.nonNull(k2)) {
            String k1Cls = k1.getClass().getName();
            String k2Cls = k1.getClass().getName();
            // 比较类名
            result = k1Cls.compareTo(k2Cls);
            if (result != 0) return result;

            // 同一类型并且具有可比较性
            if (k1 instanceof Comparable) {
                return ((Comparable) k1).compareTo(k2);
            }
        }

        // 同一类型不具有比较性
        // key1不为null，key2为null
        // key1为null，key2不为null
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    private void afterPut(Node<K, V> node) {

        Node<K, V> parent = node.parent;

        if (Objects.isNull(parent)) {
            black(node);
            return;
        }

        if (isBlack(parent)) {
            return;
        }

        Node<K, V> uncle = parent.sibling();
        Node<K, V> grand = red(parent.parent);

        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            afterPut(grand);
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

    private void rotateLeft(Node<K, V> grand) {
        Node<K, V> parent = grand.right;
        Node<K, V> child = parent.left;
        grand.right = child;
        parent.left = grand;

        afterRotate(child, parent, grand);
    }

    private void rotateRight(Node<K, V> grand) {
        Node<K, V> parent = grand.left;
        Node<K, V> child = parent.right;
        grand.left = child;
        parent.right = grand;

        afterRotate(child, parent, grand);
    }

    private void afterRotate(Node<K, V> child, Node<K, V> parent, Node<K, V> grand) {
        // 让 parent 成为子树的根节点
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else { // node 是 root 节点
            table[index(grand)] = parent;
        }

        // 更新 child 的 parent
        if (Objects.nonNull(child)) {
            child.parent = grand;
        }

        // 更新 grand 的 parent
        grand.parent = parent;
    }

    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (Objects.nonNull(node)) {
            node.color = color;
        }
        return node;
    }

    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    private boolean colorOf(Node<K, V> node) {
        return Objects.isNull(node) ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }

    private Node<K, V> successor(Node<K, V> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        // 后继节点在右子树中
        Node<K, V> p = node.right;
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

    private static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        Node<K, V> left;
        Node<K, V> right;
        Node<K, V> parent;
        int hash;

        Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.hash = Objects.isNull(key) ? 0 : key.hashCode();
        }

        boolean isLeaf() {
            return Objects.isNull(left)&& Objects.isNull(right);
        }

        boolean hasTwoChildren() {
            return Objects.nonNull(left) && Objects.nonNull(right);
        }

        boolean isLeftChild() {
            return Objects.nonNull(parent) && this == parent.left;
        }

        boolean isRightChild() {
            return Objects.nonNull(parent) && this == parent.right;
        }

        Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            return null;
        }
    }
}
