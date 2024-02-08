public class binarySearch {
    
    public static int search(Integer[] arr, int key) {
        int low = 0;
        int high = arr.length;

        while (low <= high) {
            int mid = (low + high) / 2;
            
            if (arr[mid] == key) {
                return arr[mid]; // key is found at root
            }
            else if (key < arr[mid]) {
                high = mid - 1; // key is to the left of root
            }
            else {
                low = mid + 1; // key is to the right of root
            }
        }

        return -1;
    }
}
