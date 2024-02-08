import com.*;

public class Main {

  public static void main(String[] args) {
    // Create a new GenericDeque instance
    GenericDeque deque = new GenericDeque();

    System.out.println("Deque is empty: " + deque.isEmpty());
    System.out.println("Size: " + deque.size());

    System.out.println(
      "Adding 3 elements last, 2 elemets first then 1 element last"
    );

    deque.addLast(1);
    deque.addLast(2);
    deque.addLast(3);

    deque.addFirst(4);
    deque.addFirst(5);

    deque.addLast(6);

    System.out.println("Deque is empty: " + deque.isEmpty());
    System.out.println("Size: " + deque.size());

    System.out.println("Iterating over elements in deque:");

    // Create a new iterator for the deque
    DequeIterator iterator = new DequeIterator(deque.head);

    // Iterate over the elements in the deque and print them to the console
    while (iterator.hasNext()) {
      int element = iterator.next();
      System.out.println(element);
    }

    System.out.println("-----------------");

    deque.removeFirst();
    deque.removeLast();
    System.out.println("Size: " + deque.size());

    System.out.println("Iterating over elements in deque:");

    // Iterate over the elements in the deque and print them to the console

    // re initialize iterator
    iterator = new DequeIterator(deque.head);

    while (iterator.hasNext()) {
      int element = iterator.next();
      System.out.println(element);
    }

    // Print the size of the deque
    System.out.println("Size: " + deque.size());
  }
}
