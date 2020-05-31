import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> deq = new ArrayDeque<>();
        int counter = scanner.nextInt();
        for (int i = 0; i < counter; i++) {
            deq.offerFirst(scanner.nextInt());
        }

        while (deq.peekFirst() != null) {
            System.out.println(deq.pollFirst());
        }
    }
}