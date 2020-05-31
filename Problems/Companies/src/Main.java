import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        List<String> companies = List.of(new Scanner(System.in).nextLine().split("\\s+"));
        System.out.println(companies);
    }
}