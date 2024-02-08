import com.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) {
    ArrayList<Double> standard_times = new ArrayList<>();
    ArrayList<Double> better_times = new ArrayList<>();

    StandardThreesum standard_threesum = new StandardThreesum();
    BetterThreesum better_threesum = new BetterThreesum();

    int ArraySize_standard = 100;

    for (int i = 0; i < 20; i++) {
      // add to times
      standard_times.add(standard_threesum.threeSum(ArraySize_standard));
      ArraySize_standard += 100;
    }

    int ArraySize_better = 100;

    for (int i = 0; i < 20; i++) {
      double sum = 0;
      for (int j = 0; j < 100; j++) {
        sum += better_threesum.threeSum(ArraySize_better);
      }
      better_times.add(sum / 100);
      ArraySize_better += 100;
    }

    // print the arraylist
    System.out.println("Better: " + better_times);
  }
}
