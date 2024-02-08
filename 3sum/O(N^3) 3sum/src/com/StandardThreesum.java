package com;

import java.util.*;

public class StandardThreesum {

  // New Instance of StandardThreesum

  // Takes in a list of ints and returns a list of lists of ints of numbers that add up to 0
  public StandardThreesum() {}

  public ArrayList<int[]> Threesum(int[] l) {
    Arrays.sort(l);
    int len = l.length;
    ArrayList<int[]> res = new ArrayList<int[]>();

    // Iterate through the array, with three nested loops
    for (int ix = 0; ix < len; ix++) {
      for (int jx = ix + 1; jx < len; jx++) {
        for (int kx = jx + 1; kx < len; kx++) {
            
          // Check if the sum of the three numbers is equal to 0
          if (l[ix] + l[jx] + l[kx] == 0) {
            int[] tsum = { l[ix], l[jx], l[kx] };
            res.add(tsum);
          }
        }
      }
    }

    return res;
  }
}
