package learn;

public class Stack<E> {
    private ArrayList<E> list;

    public Stack() {
        list = new ArrayList<>();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E peek() {
        return list.get(list.size() - 1);
    }

    public void clear() {
        list.clear();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("size = ").append(size());
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                sb.append(i == 0 ? ", [" : " <- ");
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
}
