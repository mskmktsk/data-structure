package LinkedList;

public class Main {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        // 添加元素测试
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);
        // 包含测试
        System.out.println("包含了 c: " + list.contains("c"));
        // 添加指定元素位置测试
        list.add(list.size(), "d");
        list.add(0, "e");
        list.add(0, "f");
        System.out.println(list);
        // 修改指定元素测试
        list.set(0, "g");
        list.set(3, "h");
        System.out.println(list);
        // 删除测试
        list.remove(0);
        list.remove(1);
        list.remove("d");
        System.out.println(list);
        // 清理测试
        list.clear();
        System.out.println(list);
        for (int i = 0; i < 10; i++) {
            list.add(String.valueOf(i));
        }
        System.out.println(list);
    }
}
