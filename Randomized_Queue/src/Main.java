import com.*;
import java.util.Iterator;

public class Main {

  public static void main(String[] args) {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();

    System.out.println("Size: " + queue.size());
    System.out.println("Queue is empty: " + queue.isEmpty());

    System.out.println("Enqueueing 6 elements");
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);

    System.out.println("Size: " + queue.size());
    System.out.println("Queue is empty: " + queue.isEmpty());

    // dequeue 1 element
    System.out.println("Dequeueing 1 element ...");
    // set variable to the dequeued element
    int ditem = queue.dequeue();
    System.out.println("Dequeued element: " + ditem);

    System.out.println("Iterating over elements in queue in random order:");
    RandomizedQueueIterator iterator = new RandomizedQueueIterator(
      queue.queue,
      queue.size
    );

    while (iterator.hasNext()) {
      int item = (int) iterator.next();
      System.out.println(item);
    }

    System.out.println("Size: " + queue.size());
    System.out.println("Queue is empty: " + queue.isEmpty());
  }
}
