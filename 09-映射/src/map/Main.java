package map;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Map<String, Integer> map = new TreeMap<>();
        System.out.println("map is empty: " + map.isEmpty());
        map.put("b", 12);
        map.put("c", 16);
        map.put("a", 18);
        map.put("c", 21);
        map.put("b", 27);
        System.out.println("map is empty: " + map.isEmpty());
        System.out.println("map size: " + map.size());
        printer(map);

        System.out.println("map get value by b key: " + map.get("b"));
        System.out.println("map remove b: " + map.remove("b"));
        printer(map);

        System.out.println("map contains key b: " + map.containsKey("b"));
        System.out.println("map contains key c: " + map.containsKey("c"));
        System.out.println("map contains value 27: " + map.containsValue(27));
        System.out.println("map contains value 18: " + map.containsValue(18));

        map.clear();
        System.out.println("map clear");
        System.out.println("map is empty: " + map.isEmpty());
        System.out.println("map size: " + map.size());
        printer(map);
    }

    public static void printer(Map<String, Integer> map) {
        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean visit(String key, Integer value) {
                System.out.println("key: " + key + ", value: " + value);
                return false;
            }
        });
    }
}
