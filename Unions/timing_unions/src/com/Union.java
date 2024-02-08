package com;

import java.util.*;
import java.util.stream.IntStream;

public class Union {

  public Union() {
    // new instance of Union.
  }

  public static int[] init(int N) {
    // initialize array [0, 1, ... N].
    return IntStream.rangeClosed(0, N - 1).toArray();
  }

  public static void union(int[] d, int a, int b) {
    int a_id = d[a];
    int b_id = d[b];

    // if the loop through value equals a_id, set that value to b_id.
    for (int ix = 0; ix < d.length; ix++) {
      if (d[ix] == a_id) {
        d[ix] = b_id;
      }
    }
  }

  public static boolean connected(int[] d, int a, int b) {
    // returns true if condition true.
    return d[a] == d[b];
  }
}
