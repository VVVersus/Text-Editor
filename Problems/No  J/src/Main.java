import java.util.*;

public class Main {

    public static void processIterator(String[] array) {
        List<String> strings = new ArrayList<>(List.of(array));
        ListIterator<String> iter = strings.listIterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.toUpperCase(Locale.US).charAt(0) == 'J') {
                iter.set(item.substring(1));
            } else {
                iter.remove();
            }
        }
        while (iter.hasPrevious()) {
            System.out.println(iter.previous());
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processIterator(scanner.nextLine().split(" "));
    }
}