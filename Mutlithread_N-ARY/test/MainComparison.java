import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MainComparison {
    private static final int ARRAY_SIZE = 10000000;
    private static final int TARGET = 47321702;

    public static void main(String[] args) {
        int[] arr = generateArray(ARRAY_SIZE);

        long startTime, endTime;
        int sequentialResult, parallelResult;

        // Sequential Binary Search
        startTime = System.nanoTime();
        sequentialResult = BinarySearch.binarySearch(arr, TARGET);
        endTime = System.nanoTime();
        long sequentialTime = endTime - startTime;

        // Parallel Binary Search
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelBinarySearch parallelSearch = new ParallelBinarySearch(arr, TARGET, 0, arr.length - 1);
        startTime = System.nanoTime();
        parallelResult = forkJoinPool.invoke(parallelSearch);
        endTime = System.nanoTime();
        long parallelTime = endTime - startTime;

        // Print results
        if (sequentialResult == -1) {
            System.out.println("Sequential Binary Search: Target not found in the array.");
        } else {
            System.out.println("Sequential Binary Search: Target found at index " + sequentialResult);
        }

        if (parallelResult == -1) {
            System.out.println("Parallel Binary Search: Target not found in the array.");
        } else {
            System.out.println("Parallel Binary Search: Target found at index " + parallelResult);
        }

        System.out.println("Sequential Binary Search Time: " + sequentialTime + " ns");
        System.out.println("Parallel Binary Search Time: " + parallelTime + " ns");
    }

    private static int[] generateArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }

        Arrays.sort(arr);
        return arr;
    }
}
