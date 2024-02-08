import com.AVL;
import com.BST;
import com.InOrderIterator;
import com.Node;
import com.PostOrderIterator;
import com.PreOrderIterator;
import com.Timer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {

  public static void main(String[] args) {
    // Timing Additions and Heights (change variable to AVL/BST to test)
    ArrayList<Double> times = new ArrayList<>();
    ArrayList<Integer> heights = new ArrayList<>();
    ArrayList<Double> del_times = new ArrayList<>();

    int inputSize = 10000;

    for (int i = 0; i < 100; i++) {
      ArrayList<Integer> numbers = new ArrayList<>();
      for (int j = 0; j < inputSize; j++) {
        numbers.add(j);
      }
      Collections.shuffle(numbers);

      AVL tree = new AVL();
      Long time = 0L;

      // Additions
      for (int j = 0; j < inputSize; j++) {
        int num = numbers.get(j);
        int value = num;

        long startTime = System.nanoTime();
        tree.add(value);
        long endTime = System.nanoTime();

        time += endTime - startTime;
      }
      times.add(time / 1000000.0);
      heights.add(tree.height());

      // Deletions
      Long delTime = 0L;
      for (int j = 0; j < inputSize; j++) {
        int num = numbers.get(j);
        int value = num;

        long startTime = System.nanoTime();
        tree.remove(value);
        long endTime = System.nanoTime();

        delTime += endTime - startTime;
      }
      del_times.add(delTime / 1000000.0);

      inputSize += 10000;
    }

    // print out
    System.out.println("Tree Times: " + Arrays.toString(times.toArray()));
    System.out.println("Tree Heights: " + Arrays.toString(heights.toArray()));
    System.out.println(
      "Tree Deletion Times: " + Arrays.toString(del_times.toArray())
    );
   
  }
}
