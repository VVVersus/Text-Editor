import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<String> numbers = List.of(new Scanner(System.in).nextLine().split("\\s+"));
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (i % 2 != 0) {
                System.out.print(numbers.get(i) + " ");
            }
        }
    }
}