import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> arr = new ArrayList(Arrays.asList(scanner.nextLine().split("\\s+")));
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            Collections.swap(arr, a, b);
        }
        for (String item : arr) {
            System.out.print(item + " ");
        }
    }
}