public class Main {

    public static void main(String[] args) {

        Thread t1 = new WorkerThread();
        t1.setName("worker-1");
        t1.start();

        Thread t2 = new WorkerThread();
        t2.setName("worker-2");
        t2.start();
    }
}

// Don't change the code below
class WorkerThread extends Thread {

    private static final int numberOfLines = 3;

    @Override
    public void run() {
        final String name = Thread.currentThread().getName();

        if (!name.startsWith("worker-")) {
            return;
        }

        for (int i = 0; i < numberOfLines; i++) {
            System.out.println("do something...");
        }
    }
}