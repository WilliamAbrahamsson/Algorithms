public class TestHeap {

  public static void main(String[] args) {
    // test array
    int[] arr = { 4, 10, 3, 5, 1, 6, 12, 5, 2, 1, 32, 12, 1, 8, 3, 4, 7, 0 };

    // sort array
    HeapSort heapSort = new HeapSort();

    System.out.println("Unsorted array: ");
    HeapSort.printArray(arr);

    System.out.println();

    heapSort.sort(arr, 0, arr.length);
    System.out.println("Sorted array with heapSort:");
    HeapSort.printArray(arr);
  }
}
