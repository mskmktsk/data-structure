package hash;

import hash.map.*;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Map<Object, Integer> map = new HashMap<>();
        Person p1 = new Person("jack", 10, 1.71f);
        Person p2 = new Person("jack", 10, 1.71f);
        System.out.println("map is empty: " + map.isEmpty());
        map.put("b", 12);
        map.put("c", 16);
        map.put("a", 18);
        map.put("c", 21);
        map.put("b", 27);
        map.put(p1, 1);
        map.put(p2, 1);
        System.out.println("map is empty: " + map.isEmpty());
        System.out.println("map size: " + map.size());
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));
        System.out.println(map.get(p1));
        System.out.println(map.get(p2));
    }
}
