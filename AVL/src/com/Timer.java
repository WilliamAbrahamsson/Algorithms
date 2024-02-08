package com;

// Timer class
public class Timer {

  public static long start;

  public Timer() {
    // new instance of timer.
  }

  public static void startTimer() {
    start = System.nanoTime();
  }

  public static double endTimer() {
    long end = System.nanoTime();
    long diff = end - start;
    return (double) diff / 1_000_000_000.0; // in seconds.
  }
}
