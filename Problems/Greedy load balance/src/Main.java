import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int counter = scanner.nextInt();

        Queue<Task> queue1 = new ArrayDeque<>();
        Queue<Task> queue2 = new ArrayDeque<>();
        int load1 = 0;
        int load2 = 0;
        int id;
        int load;
        for (int i = 0; i < counter; i++) {
            id = scanner.nextInt();
            load = scanner.nextInt();

            if (load2 < load1) {
                load2 += load;
                queue2.offer(new Task(id, load));
            } else {
                load1 += load;
                queue1.offer(new Task(id, load));
            }
        }

        for (Task task : queue1) {
            System.out.print(task.getId() + " ");
        }
        System.out.println();
        for (Task task : queue2) {
            System.out.print(task.getId() + " ");
        }
    }
}

class Task {
    int id;
    int load;

    public Task(int id, int load) {
        this.id = id;
        this.load = load;
    }

    public int getId() {
        return id;
    }

    public int getLoad() {
        return load;
    }
}
