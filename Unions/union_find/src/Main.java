import com.*;

public class Main {

  public static void main(String[] args) {
    // Problem 1
    Union union = new Union();
    int[] df = union.init(8);

    union.union(df, 0, 1);
    System.out.println(union.connected(df, 0, 1));
  }
}
