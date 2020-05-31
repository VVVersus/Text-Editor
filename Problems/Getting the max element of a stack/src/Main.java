import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> trackStack = new ArrayDeque<>();
        LinkedList
        int counter = scanner.nextInt();
        scanner.nextLine();
        String option = null;
        for (int i = 0; i < counter; i++) {
            option = scanner.next();
            switch (option) {
                case "push":
                    int number = scanner.nextInt();
                    stack.offerLast(number);
                    if (trackStack.isEmpty() || number > trackStack.peekLast()) {
                        trackStack.offerLast(number);
                    } else {
                        trackStack.offerLast(trackStack.peekLast());
                    }
                    break;
                case "pop":
                    stack.pollLast();
                    trackStack.pollLast();
                    break;
                case "max":
                    System.out.println(trackStack.peekLast());
                    break;
                default:
                    System.out.println("Unsupported option!");
            }
            scanner.nextLine();
        }
    }
}