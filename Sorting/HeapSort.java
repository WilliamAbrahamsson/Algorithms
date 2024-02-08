public class HeapSort {

  public int[] sort(int[] arr, int start, int end) {
    int size = end - start;

    // build MAX heap
    for (int ix = size / 2 - 1; ix >= 0; ix--) {
      buildMaxHeap(arr, size, ix, start);
    }

    // extract elements from the heap
    for (int ix = size - 1; ix >= 0; ix--) {
      // swap
      int temp = arr[start];
      arr[start] = arr[start + ix];
      arr[start + ix] = temp;

      // rebuild the heap
      buildMaxHeap(arr, ix, 0, start);
    }

    return arr;
  }

  // builds the max heap from the root down
  void buildMaxHeap(int[] arr, int size, int root, int start) {
    int root_index = root;
    int right_index = (root * 2) + 2;
    int left_index = (root * 2) + 1;

    // if right_index larger than root_index
    if (
      right_index < size &&
      arr[start + right_index] > arr[start + root_index]
    ) {
      root_index = right_index;
    }

    // if left_index larger than root_index
    if (
      left_index < size && arr[start + left_index] > arr[start + root_index]
    ) {
      root_index = left_index;
    }

    // if root_index is not the same as the original root_index
    if (root_index != root) {
      // swap
      int temp = arr[start + root];
      arr[start + root] = arr[start + root_index];
      arr[start + root_index] = temp;
      buildMaxHeap(arr, size, root_index, start);
    }
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
