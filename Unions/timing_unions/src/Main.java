import com.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    double[] unionResults = new double[10];
    double[] quickUnionResults = new double[10];

    int index = 0;
    for (int unionCount = 10000; unionCount <= 100000; unionCount += 10000) {
      // for loop from 1 to 10
      double unionSum = 0;
      double quickUnionSum = 0;

      for (int i = 1; i <= 10; i++) {
        double unionTime = timingUnion(unionCount);
        double quickUnionTime = timingQuickUnion(unionCount);

        unionSum += unionTime;
        quickUnionSum += quickUnionTime;
      }

      // average
      unionResults[index] = unionSum / 10;
      quickUnionResults[index] = quickUnionSum / 10;

      index++;
    }

    // print
    System.out.println("Union: " + Arrays.toString(unionResults));
    System.out.println("QuickUnion: " + Arrays.toString(quickUnionResults));
  }

  public static double timingUnion(int times) {
    ArrayList<Double> unionData = new ArrayList<Double>();

    Timer timer = new Timer();
    timer.startTimer();

    // New instance of Union.
    Union unionInstance = new Union();

    int[] uf = unionInstance.init(times + 1);

    int ix = 0;
    while (ix < times) {
      unionInstance.union(uf, ix, (ix + 1));

      ix += 1;
    }

    double passed = timer.endTimer();
    unionData.add(passed);

    return passed;
  }

  public static double timingQuickUnion(int times) {
    ArrayList<Double> unionData = new ArrayList<Double>();
    Timer timer = new Timer();

    timer.startTimer();

    // New instance of Union.
    QuickUnion unionInstance = new QuickUnion();

    int[] uf = unionInstance.init(times + 1);

    int ix = 0;
    while (ix < times) {
      unionInstance.union(uf, ix, (ix + 1));

      ix += 1;
    }

    double passed = timer.endTimer();
    unionData.add(passed);

    return passed;
  }
}
