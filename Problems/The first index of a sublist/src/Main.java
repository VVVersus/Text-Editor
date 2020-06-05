import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> listA = new ArrayList<>(List.of(scanner.nextLine().split("\\s+")));
        List<String> listB = new ArrayList<>(List.of(scanner.nextLine().split("\\s+")));

        System.out.print(Collections.indexOfSubList(listA, listB) + " "
                + (Collections.lastIndexOfSubList(listA, listB)));
    }
}