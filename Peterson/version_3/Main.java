package version_3;
public class Main {

  public static void main(String[] args) {
    try {
      int numThreads = 420_69;
      Peterson3 _peterson = new Peterson3(numThreads + 1);
      System.out.println("Starting " + numThreads + " threads ...");

      // loop through threads
      for (int i = 0; i <= numThreads; i++) {
        int j = i;
        Thread p = new Thread(() -> _peterson.access_region(j));
        p.start();

        // wait for threads to finish (join)
        p.join();
      }
    } catch (InterruptedException e) {}
  }
}
