import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println(isBalanced());
    }

    public static boolean isBalanced() {
        String[] strings  = new Scanner(System.in).nextLine().split("");
        Deque<String> stack = new ArrayDeque<>();

        for (String str : strings) {
            if ("{".equals(str) || "(".equals(str) || "[".equals(str)) {
                stack.offerLast(str);
            } else {
                switch (str) {
                    case "}":
                        if ("{".equals(stack.peekLast())) {
                            stack.pollLast();
                        } else {
                            return false;
                        }
                        break;
                    case ")":
                        if ("(".equals(stack.peekLast())) {
                            stack.pollLast();
                        } else {
                            return false;
                        }
                        break;
                    case "]":
                        if ("[".equals(stack.peekLast())) {
                            stack.pollLast();
                        } else {
                            return false;
                        }
                        break;
                    default:
                        System.out.println("Unsupported option");
                        return false;

                }
            }
        }
        return stack.size() == 0;
    }
}