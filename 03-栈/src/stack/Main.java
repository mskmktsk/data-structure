package stack;

public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        /** 入栈 */
        stack.push(1);
        System.out.println(stack);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);
        /** 出栈 */
        System.out.println(stack.pop());
        System.out.println(stack);
        /** 栈顶 */
        System.out.println(stack.peek());
        /** 清空 */
        stack.clear();
        System.out.println(stack);
    }
}
