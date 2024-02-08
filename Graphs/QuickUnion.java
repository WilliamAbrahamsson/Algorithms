import java.util.stream.IntStream;

public class QuickUnion {

  private int[] id;

  // Initialize array [0, 1, ... N-1].
  public QuickUnion(int N) {
    id = IntStream.rangeClosed(0, N - 1).toArray();
  }

  // Find the root of the element a.
  public int root(int a) {
    while (a != id[a]) {
      a = id[a];
    }
    return a;
  }

  // Check if the elements a and b are connected.
  public boolean areConnected(int a, int b) {
    if (root(a) == root(b)) {
      return true;
    }
    return false;
  }

  // Union the elements a and b.
  public void union(int a, int b) {
    int ra = root(a);
    int rb = root(b);
    id[ra] = rb;
  }
}
