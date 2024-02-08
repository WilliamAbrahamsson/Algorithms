package com;

import java.util.*;
import java.util.stream.IntStream;

// Quick union
public class QuickUnion {

  // New instance of QuickUnion.
  public QuickUnion() {}

  // Initialize array [0, 1, ... N-1].
  public static int[] init(int N) {
    return IntStream.rangeClosed(0, N - 1).toArray();
  }

  // Find the root of the element a.
  public static int root(int[] d, int a) {
    while (a != d[a]) {
      a = d[a];
    }
    return a;
  }

  // Check if the elements a and b are connected.
  public static boolean connected(int[] d, int a, int b) {
    if (root(d, a) == root(d, b)) {
      return true;
    }
    return false;
  }

  // Union the elements a and b.
  public static void union(int[] d, int a, int b) {
    int ra = root(d, a);
    int rb = root(d, b);
    d[ra] = rb;
  }
}
