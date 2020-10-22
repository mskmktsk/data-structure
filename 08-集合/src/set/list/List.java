package set.list;

public interface List<E> {

    static final int ELEMENT_NOT_FOUND = -1;

    /**
     * 元素的数量
     * @return size
     */
    int size();

    /**
     * 是否为空
     * @return 为空: true, 不为空: false
     */
    boolean isEmpty();

    /**
     * 是否包含某个元素
     * @param element 元素
     * @return 包含: true, 不包含: false
     */
    boolean contains(E element);

    /**
     * 添加元素到最后面
     * @param element 元素
     */
    void add(E element);

    /**
     * 往 index 位置添加元素
     * @param index 索引
     * @param element 元素
     */
    void add(int index, E element);

    /**
     * 返回 index 位置对应的元素
     * @param index 索引
     * @return 对应索引的元素
     */
    E get(int index);

    /**
     * 设置 index 位置的元素
     * @param index 索引
     * @param element 新元素
     * @return 旧元素
     */
    E set(int index, E element);

    /**
     * 删除 index 位置对应的元素
     * @param index 索引
     * @return 被删除的元素
     */
    E remove(int index);

    E remove(E element);

    /**
     * 查看元素的位置
     * @param element 元素
     * @return 如果有则返回对应元素的位置, 如果没有, 则返回 ELEMENT_NOT_FOUND
     */
    int indexOf(E element);

    /**
     * 清除所有元素
     */
    void clear();
}
