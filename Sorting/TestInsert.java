public class TestInsert {

  public static void main(String[] args) {
    // test array
    int[] arr = { 4, 10, 3, 5, 1, 6, 12, 5, 2, 1, 32, 12, 1, 8, 3, 4, 7, 0 };

    // sort array
    InsertSort insertSort = new InsertSort();

    System.out.println("Unsorted array: ");
    InsertSort.printArray(arr);

    System.out.println();

    insertSort.sort(arr, 0, arr.length);
    System.out.println("Sorted array with InsertSort:");
    InsertSort.printArray(arr);
  }
}
