import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> deq = new ArrayDeque<>();

        int number;
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            number = scanner.nextInt();
            if (number % 2 == 0) {
                deq.offerFirst(number);
            } else {
                deq.offerLast(number);
            }
        }
        for (Integer item : deq) {
            System.out.println(item);
        }
    }
}