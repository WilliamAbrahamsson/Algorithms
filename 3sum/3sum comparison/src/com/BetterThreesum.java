package com;

import java.util.*;
import java.util.Arrays;
import java.util.Random;

public class BetterThreesum {

  public double threeSum(int arraySize) {
    Random rand = new Random();

    int[] l = new int[arraySize];
    Timer timer = new Timer();
    int threesums = 0;

    // Fill the array with random numbers
    for (int i = 0; i < arraySize / 2; i++) {
      l[i] = i;
    }

    for (int i = 0; i < arraySize / 2; i++) {
      l[i] = -i;
    }

    // Sort the input array
    Arrays.sort(l);
    int len = l.length;
    ArrayList<int[]> res = new ArrayList<int[]>();

    timer.startTimer();
    for (int ix = 0; ix < len; ix++) {
      // Initialize left and right pointers
      int leftNode = ix + 1;
      int rightNode = len - 1;

      while (leftNode < rightNode) {
        // Check if the current triplet sums to 0
        if (l[ix] + l[leftNode] + l[rightNode] == 0) {
          // If so, add the triplet to the list of results
          int[] tsum = { l[ix], l[leftNode], l[rightNode] };
          res.add(tsum);

          // Move the left and right pointers inwards
          leftNode += 1;
          rightNode -= 1;
          // If the sum is less than 0, move the left pointer inwards
        } else if (l[ix] + l[leftNode] + l[rightNode] < 0) {
          leftNode += 1;
        }
        // If the sum is greater than 0, move the right pointer inwards
        else {
          rightNode -= 1;
        }
      }
    }
    return timer.endTimer();
  }
}
