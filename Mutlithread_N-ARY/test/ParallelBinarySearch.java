import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelBinarySearch extends RecursiveTask<Integer> {
    private int[] arr;
    private int target;
    private int left;
    private int right;

    public ParallelBinarySearch(int[] arr, int target, int left, int right) {
        this.arr = arr;
        this.target = target;
        this.left = left;
        this.right = right;
    }

    protected Integer compute() {
        if (left > right) {
            return -1; // Target not found
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == target) {
            return mid; // Target found at index mid
        }

        if (arr[mid] < target) {
            ParallelBinarySearch rightSearch = new ParallelBinarySearch(arr, target, mid + 1, right);
            rightSearch.fork();
            return rightSearch.join();
        } else {
            ParallelBinarySearch leftSearch = new ParallelBinarySearch(arr, target, left, mid - 1);
            leftSearch.fork();
            return leftSearch.join();
        }
    }
}
