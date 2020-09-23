package arraryList;

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
    /**
     * 第一个元素索引
     */
    private int first;
    /**
     * 默认容量
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 找不到元素的返回值
     */
    private static final int ELEMENT_NOT_FOUND = -1;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int capacity) {
        capacity = (capacity < DEFAULT_CAPACITY) ? DEFAULT_CAPACITY : capacity;
        elements = (E[]) new Object[capacity];
        first = 0;
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
        return indexOf(element) != ELEMENT_NOT_FOUND;
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
        indexOfBoundsForAdd(index);
        rangeOfBounds(size + 1);
        if (index >= (size >> 1)) {
            for (int i = size - 1; i >= index; i--) {
                elements[getCurrentIndex(i + 1)] = elements[getCurrentIndex(i)];
            }
        } else {
            for (int i = 0; i <= index; i++) {
                E current = elements[getCurrentIndex(i)];
                if (first == 0) {
                    elements[elements.length - 1] = current;
                } else {
                    elements[getCurrentIndex(i - 1)] = current;
                }
            }
            first -= 1;
            if (first == -1) {
                first = elements.length - 1;
            }
        }
        size++;
        elements[getCurrentIndex(index)] = element;
    }

    /**
     * 返回 index 位置对应的元素
     * @param index 索引
     * @return 对应索引的元素
     */
    public E get(int index) {
        indexOfBounds(index);
        return elements[getCurrentIndex(index)];
    }

    /**
     * 设置 index 位置的元素
     * @param index 索引
     * @param element 新元素
     * @return 旧元素
     */
    public E set(int index, E element) {
        indexOfBounds(index);
        int currentIndex = getCurrentIndex(index);
        E old = elements[currentIndex];
        elements[currentIndex] = element;
        return old;
    }

    /**
     * 删除 index 位置对应的元素
     * @param index 索引
     * @return 被删除的元素
     */
    public E remove(int index) {
        indexOfBounds(index);
        int currentIndex = getCurrentIndex(index);
        E old = elements[currentIndex];
        if (index >= (size >> 1)) {
            for (int i = index; i < size; i++) {
                elements[getCurrentIndex(i)] = elements[getCurrentIndex(i + 1)];
            }
            elements[getCurrentIndex(size - 1)] = null;
        } else {
            for (int i = index; i > 0; i--) {
                elements[getCurrentIndex(i)] = elements[getCurrentIndex(i - 1)];
            }
            elements[getCurrentIndex(first)] = null;
            first += 1;
            if (first == elements.length) {
                first = 0;
            }
        }
        size--;
        trim();
        return old;
    }

    /**
     * 删除指定元素（如果存在的话）
     * @param element 被删除元素
     * @return 被删除的元素
     */
    public E remove(E element) {
        return remove(indexOf(element));
    }

    /**
     * 查看元素的位置
     * @param element 元素
     * @return 如果有则返回对应元素的位置, 如果没有, 则返回 ELEMENT_NOT_FOUND
     */
    public int indexOf(E element) {
        if (Objects.isNull(element)) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return getCurrentIndex(i);
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(elements[i])) {
                    return getCurrentIndex(i);
                }
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /**
     * 清除所有元素
     */
    public void clear() {
        while (--size >= 0) {
            elements[getCurrentIndex(size)] = null;
        }
        first = 0;
        size++;
    }

    /**
     * 模版化 print
     * @return 模版字符串
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size).append(", elements = [");
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(elements[getCurrentIndex(i)]);
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * 获取 index 真实下标
     * @param index 索引
     * @return 真实下标
     */
    private int getCurrentIndex(int index) {
        return (first + index) % elements.length;
    }

    /**
     * 缩容操作
     */
    private void trim() {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity >> 1;
        if (size >= newCapacity || size <= DEFAULT_CAPACITY) {
            return;
        }
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        first = 0;
        elements = newElements;
    }

    /**
     * 判断索引是否越界
     * @param index 索引
     */
    private void indexOfBounds(int index) {
        if (index >= size || index < 0) {
            outOfBounds(index);
        }
    }

    /**
     * 判断索引是否越界
     * @param index 索引
     */
    private void indexOfBoundsForAdd(int index) {
        if (index > size || index < 0) {
            outOfBounds(index);
        }
    }

    /**
     * 抛出异常下标
     * @param index 出现异常的下标
     */
    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 扩容边界检查
     * @param size 当前 ArrayList 所需容量
     */
    private void rangeOfBounds(int size) {
        if (size > elements.length) {
            E[] newElement = (E[]) new Object[size + (size >> 1)];
            for (int i = 0; i < elements.length; i++) {
                newElement[i] = elements[i];
            }
            first = 0;
            elements = newElement;
        }
    }
}
