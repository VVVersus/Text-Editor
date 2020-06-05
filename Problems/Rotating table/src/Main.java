import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        scanner.nextLine();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(scanner.nextLine());
        }
        Collections.rotate(list, scanner.nextInt());
        for (String s : list) {
            System.out.println(s);
        }
    }
}