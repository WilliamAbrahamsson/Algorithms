package com;

import java.util.*;

public class StandardThreesum {

  public double threeSum(int arraySize) {
    Random rand = new Random();
    int[] nums = new int[arraySize];
    Timer timer = new Timer();

    for (int i = 0; i < nums.length; i++) {
      nums[i] = rand.nextInt(-100, 101);
    }
    String res = "";
    int threesums = 0;

    timer.startTimer();
    for (int i = 0; i < nums.length; i++) {
      for (int j = 0; j < nums.length; j++) {
        for (int k = 0; k < nums.length; k++) {
          if (i == j || i == k || j == k) {
            continue;
          }
          if (nums[i] + nums[j] + nums[k] == 0) {
            threesums++;
          }
        }
      }
    }
    double passed = timer.endTimer();

    return passed;
  }
}
