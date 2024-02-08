public class QuickSort {

  // initialize other sorting algorithms
  HeapSort heapSort = new HeapSort();
  InsertSort insertSort = new InsertSort();

  // sort an array
  public long sort(
    int[] arr,
    int start,
    int end,
    int max_depth,
    String switcher
  ) {
    long startTime = System.nanoTime();
    quickSort(arr, start, end, max_depth, switcher);
    long endTime = System.nanoTime();
    return (endTime - startTime) / 1_000;
  }

  // median of three pivot selection
  private int choosePivot(int[] arr, int start, int end) {
    int arr_length = arr.length;
    int pivot_index;

    // check that the arr is not empty, if it is just return 0
    if (arr_length > 0) {
      // adjust end index
      end--;

      // get mid index
      int mid = (start + end) / 2;

      // in the even case, select the smaller of the two middle values
      if (arr_length % 2 == 0) {
        int right_mid = mid + 1;
        if (arr[right_mid] < arr[mid]) {
          mid = right_mid;
        }
      }

      // get values from indexes
      int startValue = arr[start];
      int midValue = arr[mid];
      int endValue = arr[end];

      // if start is the median
      if (
        startValue <= midValue &&
        startValue >= endValue ||
        startValue >= midValue &&
        startValue <= endValue
      ) {
        pivot_index = start;
      }
      // if mid is the median
      else if (
        midValue <= startValue &&
        midValue >= endValue ||
        midValue >= startValue &&
        midValue <= endValue
      ) {
        pivot_index = mid;
      }
      // otherwise, end is the median
      else {
        pivot_index = end;
      }

      // System.out.println(arr[pivot_index]);
      return pivot_index;
    } else {
      return 0;
    }
  }

  // swap two elements in an array
  private void swap(int[] arr, int ix, int jx) {
    int temp = arr[ix];
    arr[ix] = arr[jx];
    arr[jx] = temp;
  }

  // choose pivot and partition the array around the pivot
  private int partition(int[] arr, int start, int end) {
    int pivotIndex = choosePivot(arr, start, end);
    int pivotValue = arr[pivotIndex];
    end--;

    // swap pivot to end (temporarily)
    swap(arr, pivotIndex, end);

    int ix = start;
    for (int jx = start; jx < end; jx++) {
      if (arr[jx] <= pivotValue) {
        // swap the two elements
        swap(arr, ix, jx);
        ix++;
      }
    }

    // swap pivot into place
    swap(arr, ix, end);

    return ix;
  }

  // recursive quicksort
  public void quickSort(
    int[] arr,
    int start,
    int end,
    int max_depth,
    String switcher
  ) {
    // base case
    if (end - start <= 1) {
      return;
    }

    // check if max depth has been reached
    if (max_depth == 0) {
      if (switcher.equals("insert")) {
        insertSort.sort(arr, start, end);
      } else if (switcher.equals("heap")) {
        heapSort.sort(arr, start, end);
      }
      return;
    }

    // partition the array
    int pivotIndex = partition(arr, start, end);

    // sort the left side
    quickSort(arr, start, pivotIndex, max_depth--, switcher);

    // sort the right side
    quickSort(arr, pivotIndex + 1, end, max_depth--, switcher);
  }
}
