import com.*;
// Import the BST and Node classes
import com.BST;
import com.Node;
import java.util.Iterator;

public class Main {

  public static void main(String[] args) {
    System.out.println("Initializing Binary Search Tree ...\n");
    BST bst = new BST();

    System.out.println("Adding elements: 38, 5, 45, 1, 9, 47, 8, 15, 46, 13");
    bst.add(38);
    bst.add(5);
    bst.add(45);
    bst.add(1);
    bst.add(9);
    bst.add(47);
    bst.add(8);
    bst.add(15);
    bst.add(46);
    bst.add(13);

    System.out.print("Height: ");
    System.out.println(bst.height()); // 4

    System.out.print("Size: ");
    System.out.println(bst.size()); // 10

    // Contains Function
    System.out.print("Contains 13: ");
    System.out.print(bst.contains(13)); // true
    System.out.print(", 14: ");
    System.out.println(bst.contains(14)); // false

    // print TRAVERSALS header
    System.out.println("\nTRAVERSALS\n");

    System.out.println("In order traversal:");
    InOrderIterator inIterator = new InOrderIterator(bst.root);

    // Iterate over the elements in the deque and print them to the console
    while (inIterator.hasNext()) {
      Node node = inIterator.next();
      System.out.println(node.key);
    }

    // Create a new pre order for the deque
    System.out.println("\nPre order traversal:");
    PreOrderIterator preIterator = new PreOrderIterator(bst.root);

    // Iterate over the elements in the deque and print them to the console
    while (preIterator.hasNext()) {
      Node node = preIterator.next();
      System.out.println(node.key);
    }

    // Create a new post order for the deque
    System.out.println(" \nPost order traversal:");
    PostOrderIterator postIterator = new PostOrderIterator(bst.root);

    // Iterate over the elements in the deque and print them to the console
    while (postIterator.hasNext()) {
      Node node = postIterator.next();
      System.out.println(node.key);
    }

    System.out.print("\nRemove k = 4 (th) largest k (node): ");
    int lk = bst.removeLargestK(4);
    System.out.println(lk); // 38

    // print TRAVERSALS header
    System.out.println("\nTRAVERSALS AGAIN\n");

    System.out.println("In order traversal:");
    inIterator = new InOrderIterator(bst.root);

    // Iterate over the elements in the deque and print them to the console
    while (inIterator.hasNext()) {
      Node node = inIterator.next();
      System.out.println(node.key);
    }

    // Create a new pre order for the deque
    System.out.println("\nPre order traversal:");
    preIterator = new PreOrderIterator(bst.root);

    // Iterate over the elements in the deque and print them to the console
    while (preIterator.hasNext()) {
      Node node = preIterator.next();
      System.out.println(node.key);
    }

    // Create a new post order for the deque
    System.out.println(" \nPost order traversal:");
    postIterator = new PostOrderIterator(bst.root);

    // Iterate over the elements in the deque and print them to the console
    while (postIterator.hasNext()) {
      Node node = postIterator.next();
      System.out.println(node.key);
    }
  }
}
