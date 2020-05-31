import java.util.*;

public class Main {

    public static void main(String[] args) {  
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offerLast(1);
        queue.offerLast(7);
        queue.offerFirst(0);
        queue.offerFirst(2);
        System.out.println(queue);
    }
}