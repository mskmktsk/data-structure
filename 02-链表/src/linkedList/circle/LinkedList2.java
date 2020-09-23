package linkedList.circle;

import linkedList.AbstractList;

import java.util.Objects;

public class LinkedList2<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    public void add(int index, E element) {
        if (index == size) {
            last = new Node<>(last, element, first);
            if (Objects.nonNull(last.prev)) {
                last.prev.next = last;
                last.next.prev = last;
            } else {
                first = last;
                first.next = first;
                first.prev = first;
            }
        } else {
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> node = new Node<>(prev, element, next);
            prev.next = node;
            next.prev = node;
            if (index == 0) {
                first = node;
            }
        }
        size++;
    }

    public E get(int index) {
        return node(index).element;
    }

    public E set(int index, E element) {
        Node<E> node = node(index);
        E old = node.element;
        node.element = element;
        return old;
    }

    public E remove(int index) {
        indexOfBounds(index);
        Node<E> node = node(index);
        if (index == 0) {
            first = node.next;
            first.prev = last;
        } else {
            node.prev.next = node.next;
        }
        if (index == (size - 1)) {
            if (size == 1) {
                first = null;
                last = null;
            } else {
                last = node.prev;
                last.next = first;
            }
        } else {
            node.next.prev = node.prev;
        }
        size--;
        return node.element;
    }

    public int indexOf(E element) {
        if (Objects.nonNull(first)) {
            Node<E> node = first;
            if (Objects.nonNull(element)) {
                for (int i = 0; i < size; i++) {
                    if (element.equals(node.element)) {
                        return i;
                    }
                    node = node.next;
                }
            } else {
                for (int i = 0; i < size; i++) {
                    if (Objects.isNull(node.element)) {
                        return i;
                    }
                    node = node.next;
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * 获取 index 对应的节点
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        indexOfBounds(index);
        if (index > (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
            return node;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size);
        if (Objects.nonNull(first)) {
            sb.append(", [");
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    sb.append(", ");
                }
                sb.append(node(i).element);
            }
            sb.append("]");
        }
        return sb.toString();
    }
}
