public class Main {
    public static void main(String[] args){

        // testing diferent arraysizes (10k - 100k)
        for (int arrSize = 10; arrSize <= arrSize*10; arrSize+= 10) {
            System.out.println("Arrsize: " + arrSize);
            Integer[] arr = arrayConstructor(arrSize); // returns a list containing ints from 1-10
            System.out.println( "Size " + arrSize + ": " + nanoTimer(arr, 0, arr.length - 1, 8, 2, 2) + " ns");
            sleep();
            if ( arrSize == 100) {
                break;
            }
        }
        //System.out.println( "Size " + arrSize + ": " + nanoTimer(arr, 0, arr.length - 1, 5, 2, 2) + " ns");
    
        // testing different intervall

        // Testing different startpoints and endpoints

        // Testing different amounts of threads

    }

    static long nanoTimer (Integer[] arr, int low, int high, int key, int intv, int amountThreads) {
        long startTime = System.currentTimeMillis();
        Integer pos = NarySearchThreads.nary(arr, low, high, key, intv, amountThreads);
        long stopTime = System.currentTimeMillis();
        if (pos == -1) {
            System.out.println("Key not found.");
        } else {
            System.out.println("Key found at position " + pos);
        }
        return (stopTime - startTime) * 1000000;
    }

    static Integer[] arrayConstructor (int length) {
        Integer[] arr = new Integer[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    static void sleep () {
        try {
            Thread.sleep(2000); // 2 second sleep
        } catch (Exception e) {
            System.out.println("Sleep is interuppted");
        }
    }
}
