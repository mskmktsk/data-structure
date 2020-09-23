package com.osozos.ArraryList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        // 添加元素测试
        arrayList.add(1);
        arrayList.add(20);
        arrayList.add(3);
        System.out.println(arrayList);
        // 包含测试
        System.out.println("com.osozos.learn.ArrayList 包含了20, " + arrayList.contains(Integer.valueOf(20)));
        // 添加指定元素位置测试
        arrayList.add(1, 4);
        arrayList.add(2, 5);
        arrayList.add(0, 6);
        arrayList.add(0, 7);
        arrayList.add(3, 8);
        System.out.println(arrayList);
        // 修改指定元素测试
        arrayList.set(0, 9);
        arrayList.set(3, 10);
        System.out.println(arrayList);
        // 删除测试
        arrayList.remove(0);
        arrayList.remove(5);
        arrayList.remove(1);
        arrayList.remove(Integer.valueOf(4));
        arrayList.remove(arrayList.size() - 1);
        System.out.println(arrayList);
        // 清理测试
        arrayList.clear();
        System.out.println(arrayList);
        // 测试扩容
        for (int i = 1; i <= 100; i++) {
            arrayList.add(i);
        }
        System.out.println(arrayList);
    }
}
