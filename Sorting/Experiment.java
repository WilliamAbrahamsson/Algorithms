import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Experiment {

  public static void main(String[] args) {
    // set the array size
    int array_size = 10_000;

    // set the max depth
    int max_depth = 40;

    // set the number of times to average the duration
    int average_of = 1_000;

    // sort the array with different depths
    long[] insert_durations = sortArraysWithDepths(
      array_size,
      max_depth,
      "insert",
      average_of
    );
    long[] heap_durations = sortArraysWithDepths(
      array_size,
      max_depth,
      "heap",
      average_of
    );

    // write the durations to files
    writeArrayToFile(insert_durations, "insert_times.py", "insert_times");
    writeArrayToFile(heap_durations, "heap_times.py", "heap_times");
  }

  // write an array to a file
  public static void writeArrayToFile(
    long[] durations,
    String filename,
    String arrayName
  ) {
    try {
      FileWriter writer = new FileWriter(filename);
      StringBuilder sb = new StringBuilder();
      sb.append(arrayName + " = [");
      for (int ix = 0; ix < durations.length; ix++) {
        sb.append(durations[ix]);
        if (ix < durations.length - 1) {
          sb.append(", ");
        }
      }
      sb.append("]");
      writer.write(sb.toString());
      writer.close();
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  // sorts with different depths and returns the durations in an array
  public static long[] sortArraysWithDepths(
    int array_size,
    int max_depth,
    String switcher,
    int average_of
  ) {
    long[] durations = new long[max_depth];
    for (int ix = 0; ix < max_depth; ix++) {
      durations[ix] = sortArray(array_size, ix, switcher, average_of);
    }
    return durations;
  }

  // sorts the array and returns the duration
  public static long sortArray(
    int array_size,
    int max_depth,
    String switcher,
    int average_of
  ) {
    int[] arr;
    QuickSort quickSort = new QuickSort();
    arr = generateArray(array_size);
    long total_duration = 0;

    for (int ix = 0; ix < average_of; ix++) {
      // generate a new array for each iteration
      arr = generateArray(array_size);

      // sort the array using the specified method and get the duration
      long duration = quickSort.sort(arr, 0, arr.length, max_depth, switcher);

      // add the duration to the total duration
      total_duration += duration;
    }

    // return the average duration
    return total_duration / average_of;
  }

  // generates array
  public static int[] generateArray(int size) {
    int[] arr = new int[size];
    for (int ix = 0; ix < size; ix++) {
      arr[ix] = ix + 1;
    }
    return shuffleArray(arr);
  }

  // shuffeles the array.
  public static int[] shuffleArray(int[] arr) {
    Random rand = new Random();
    for (int ix = arr.length - 1; ix > 0; ix--) {
      int jx = rand.nextInt(ix + 1);
      int temp = arr[ix];
      arr[ix] = arr[jx];
      arr[jx] = temp;
    }
    return arr;
  }

  // prints the array.
  public static void printArray(int[] arr) {
    int lastIndex = arr.length - 1;
    for (int ix = 0; ix < lastIndex; ix++) {
      System.out.print(arr[ix] + ", ");
    }
    if (arr.length > 0) {
      System.out.print(arr[lastIndex]);
    }
    System.out.println();
  }
}
