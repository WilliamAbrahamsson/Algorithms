import com.*;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) {
    // Problem 5

    StandardThreesum threeSum = new StandardThreesum();

    int[] l = { -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    // Print out the results
    ArrayList<int[]> res = threeSum.Threesum(l);
    for (int[] i : res) {
      System.out.println(i[0] + " " + i[1] + " " + i[2]);
    }
  }
}
