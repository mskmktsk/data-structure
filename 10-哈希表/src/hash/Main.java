package hash;

import hash.map.*;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        Map<Object, Integer> map = new HashMap<>();
        test(map);
    }

    public static void test(Map<Object, Integer> map) {
        Person p1 = new Person("jack", 10, 1.71f);
        Person p2 = new Person("jack", 10, 1.71f);
        System.out.println("============= size/isEmpty/put/get =============");
        System.out.println("map is empty: " + map.isEmpty());
        map.put("jack", 12);
        map.put("rose", 16);
        map.put("rose", 18);
        map.put("jack", 27);
        map.put(null, 36);
        map.put(p1, 1);
        map.put(p2, 1);
        System.out.println("map contains 12: " + map.containsValue(12));
        System.out.println("map contains 18: " + map.containsValue(18));
        System.out.println("map contains 1: " + map.containsValue(1));
        System.out.println("map contains a: " + map.containsKey("jack"));
        System.out.println("map contains p1: " + map.containsKey(p1));
        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean visit(Object key, Integer value) {
                System.out.println("Key: " + key + ", Value: " + value);
                return false;
            }
        });
    }

    public static void test2(Map<Object, Integer> map) {
        Person p1 = new Person("jack", 10, 1.71f);
        Person p2 = new Person("jack", 10, 1.71f);
        System.out.println("============= size/isEmpty/put/get =============");
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
        System.out.println("a = " + map.get("a"));
        System.out.println("b = " + map.get("b"));
        System.out.println("c = " + map.get("c"));
        System.out.println("p1 == p2 : " + Objects.equals(p1, p2));
        System.out.println("p1 = " + map.get(p1));
        System.out.println("p2 = " + map.get(p2));
        System.out.println("============= remove =============");
        System.out.println("remove p1: " + map.remove(p1));
        System.out.println("remove a: " + map.remove("a"));
        System.out.println("a = " + map.get("a"));
        System.out.println("p2 = " + map.get(p2));
    }
}
