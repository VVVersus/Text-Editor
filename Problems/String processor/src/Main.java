import java.util.Locale;

class StringProcessor extends Thread {

    final Scanner scanner = new Scanner(System.in); // use it to read string from the standard input

    @Override
    public void run() {
        String s = null;
        while (!(s = scanner.nextLine()).equals(s.toUpperCase(Locale.US))) {
            System.out.println(s.toUpperCase(Locale.US));
        }
        System.out.println("FINISHED");
    }
}