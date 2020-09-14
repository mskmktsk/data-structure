package com.osozos.learn._1_线性结构._1_动态数组_Dynamic_Array;

import java.util.Objects;

public class ArrayList<E> {
    /**
     * 内部数组
     */
    private E[] elements;

    /**
     * 元素数量
     */
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 元素的数量
     * @return size
     */
    public int size() {
        return size;
    };

    /**
     * 是否为空
     * @return 为空: true, 不为空: false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 是否包含某个元素
     * @param element 元素
     * @return 包含: true, 不包含: false
     */
    public boolean contains(E element) {
        return indexOf(element) != -1;
    }

    /**
     * 添加元素到最后面
     * @param element 元素
     */
    public void add(E element) {
        add(size, element);
    }

    /**
     * 往 index 位置添加元素
     * @param index 索引
     * @param element 元素
     */
    public void add(int index, E element) {
        size++;
        indexOfBounds(index);
        elements[index] = element;
    }

    /**
     * 返回 index 位置对应的元素
     * @param index 索引
     * @return 对应索引的元素
     */
    public E get(int index) {
        indexOfBounds(index);
        return elements[index];
    }

    /**
     * 设置 index 位置的元素
     * @param index 索引
     * @param element 新元素
     * @return 旧元素
     */
    public E set(int index, E element) {
        indexOfBounds(index);
        E old = elements[index];
        elements[index] = element;
        return old;
    }

    /**
     * 删除 index 位置对应的元素
     * @param index 索引
     * @return 被删除的元素
     */
    public E remove(int index) {
        indexOfBounds(index);
        E old = elements[index];
        for (int i = index + 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }
        return old;
    }

    /**
     * 查看元素的位置
     * @param element 元素
     * @return 如果有则返回对应元素的位置, 如果没有, 则返回 -1
     */
    public int indexOf(E element) {
        if (Objects.isNull(element)){ return -1; }
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        size = 0;
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    };

    private void indexOfBounds(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }
}
