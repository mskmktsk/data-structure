package com.osozos.learn;

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

    }

    public E get(int index) {
        return null;
    }

    public E set(int index, E element) {
        return null;
    }

    public E remove(int index) {
        return null;
    }

    public E remove(E element) {
        return null;
    }

    public int indexOf(E element) {
        return 0;
    }

    public void clear() {

    }

    public String toString() {
        return super.toString();
    }
}
