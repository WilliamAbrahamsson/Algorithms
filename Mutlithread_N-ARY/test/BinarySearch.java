public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid; // Target found at index mid
            }

            if (arr[mid] < target) {
                left = mid + 1; // Eliminate left half of the array
            } else {
                right = mid - 1; // Eliminate right half of the array
            }
        }

        return -1; // Target not found
    }
}
