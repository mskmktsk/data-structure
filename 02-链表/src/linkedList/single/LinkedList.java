package linkedList.single;

import linkedList.AbstractList;

import java.util.Objects;

public class LinkedList<E> extends AbstractList<E> {
    private Node<E> first;

    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public void add(int index, E element) {
        if (index == 0) {
            Node<E> node = new Node<>(element, first);
            first = node;
        } else {
            Node<E> prev = node(index - 1);
            prev.next = new Node<>(element, prev.next);;
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
        Node<E> node = first;
        if (index == 0) {
            first = node.next;
        } else {
            Node<E> prev = node(index - 1);
            node = prev.next;
            prev.next = node.next;
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
        size = 0;
    }

    /**
     * 获取 index 对应的节点
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        indexOfBounds(index);
        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
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
