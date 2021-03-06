package linkedList.single;

import linkedList.AbstractList;

import java.util.Objects;

public class LinkedList2<E> extends AbstractList<E> {
    private Node<E> first;

    public LinkedList2() {
        first = new Node<>(null, null);
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public void add(int index, E element) {
        Node<E> prev = index == 0 ? first : node(index - 1);
        prev.next = new Node<>(element, prev.next);;
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
        Node<E> prev = index == 0 ? first : node(index - 1);
        Node<E> node = prev.next;
        prev.next = node.next;
        size--;
        return node.element;
    }

    public int indexOf(E element) {
        if (Objects.nonNull(first.next)) {
            Node<E> node = first.next;
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
        first.next = null;
        size = 0;
    }

    /**
     * 获取 index 对应的节点
     * @param index
     * @return
     */
    private Node<E> node(int index) {
        indexOfBounds(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size);
        if (Objects.nonNull(first.next)) {
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
