import com.*;

public class Main {

  public static void main(String[] args) {
    // Problem 3

    // init timer
    Timer timer = new Timer();

    // start timer
    timer.startTimer();

    // end timer
    double time = timer.endTimer();

    System.out.println("Time: " + time + " seconds.");
  }
}
