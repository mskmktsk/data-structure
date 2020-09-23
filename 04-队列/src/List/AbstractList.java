package List;

public abstract class AbstractList<E> implements List<E> {
    /**
     * 元素数量
     */
    protected int size;

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
     * 删除 element 元素
     * @param element 元素
     * @return 被删除的元素
     */
    public E remove(E element) {
        return remove(indexOf(element));
    }

    /**
     * 判断索引是否越界
     * @param index 索引
     */
    protected void indexOfBounds(int index) {
        if (index >= size || index < 0) {
            outOfBounds(index);
        }
    }

    /**
     * 判断索引是否越界
     * @param index 索引
     */
    protected void indexOfBoundsForAdd(int index) {
        if (index > size || index < 0) {
            outOfBounds(index);
        }
    }

    protected void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
