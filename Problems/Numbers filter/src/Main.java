import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        int number;
        while ((number = scanner.nextInt()) != 0) {
            if (number % 2 == 0) {
                System.out.println(number);
            }
        }
    }
}