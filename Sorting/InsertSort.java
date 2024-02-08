public class InsertSort {

  public void sort(int[] arr, int start, int end) {
    // loop through the arr
    for (int ix = start; ix < end; ix++) {
      // find the correct position for the current element
      for (int jx = ix; jx > start; jx--) {
        // swap if the current element is smaller than the previous element
        if (arr[jx] < arr[jx - 1]) {
          // swap
          int temp = arr[jx];
          arr[jx] = arr[jx - 1];
          arr[jx - 1] = temp;
        } else {
          break;
        }
      }
    }
  }

  // prints the array
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
