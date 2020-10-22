package set;

import java.time.Duration;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        Set<Integer> listSet = new ListSet<>();
        printer(listSet);
        System.out.println("========");
        Set<Integer> treeSet = new TreeSet<>();
        printer(treeSet);
    }

    public static void printer(Set<Integer> set) {
        Integer[] integers = new Integer[]{
                11, 11, 10, 12, 11, 12, 11, 10, 12, 12, 10, 11, 10
        };
        for (Integer integer : integers) {
            set.add(integer);
        }
        set.traversal(new Set.Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);
                return false;
            }
        });
    }
}
