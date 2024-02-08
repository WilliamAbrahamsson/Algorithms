public class Main {

  public static void main(String[] args) {
    try {
      // initialize peterson
      Peterson _peterson = new Peterson();

      System.out.println("Starting 2 threads ...");

      // initialize threads
      Thread p0 = new Thread(() -> _peterson.access_region(0));
      Thread p1 = new Thread(() -> _peterson.access_region(1));

      // start threads
      p0.start();
      p1.start();

      // wait for threads to finish (join)
      p0.join();
      p1.join();
    } catch (InterruptedException e) {}
  }
}
