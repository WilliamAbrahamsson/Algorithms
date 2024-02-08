import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class NarySearchThreads {

  private static Integer[] mid;
  // private static String[] locate;
  private static AtomicReferenceArray<String> locate;
  private static AtomicInteger pos;
  private static Executor exe;

  public static Integer nary(
    Integer[] A,
    int lo,
    int hi,
    int key,
    int intv,
    int amountThreads
  ) {
    mid = new Integer[intv + 1];
    Arrays.fill(mid, 0);

    locate = new AtomicReferenceArray<>(new String[intv + 2]);
    for (int i = 0; i < intv + 2; i++) {
      locate.set(i, "U");
    }

    pos = new AtomicInteger(-1);
    exe = Executors.newFixedThreadPool(amountThreads);

    locate.set(0, "R");
    locate.set(intv + 1, "L");

    while (lo <= hi && pos.get() == -1) {
      mid[0] = lo - 1;
      int step = (hi - lo + 1) / (intv + 1);

      // making the variable lo and hi effectively final so they work inside the runnable
      int final_lo = lo;
      int final_hi = hi;

      Runnable task = () -> {
        markLoc(A, key, final_lo, intv, step, final_hi);
      };

      Future<?> future = ((ExecutorService) exe).submit(task);
      try {
        future.get();
      } catch (InterruptedException | ExecutionException e) {
        e.printStackTrace();
      } // wait for the task to complete

      for (int i = 1; i <= intv; i++) {
        if (!locate.get(i).equals(locate.get(i - 1))) {
          lo = mid[i - 1] + 1;
          hi = mid[i] - 1;
        }
      }

      if (!locate.get(intv).equals(locate.get(intv + 1))) {
        lo = mid[intv] + 1;
      }
    }

    ((ExecutorService) exe).shutdown();
    return pos.get();
  }

  static void markLoc(
    Integer[] A,
    int key,
    int lo,
    int intv,
    int step,
    int hi
  ) {
    for (int i = 1; i <= intv; i++) {
      int offs = step * i + (i - 1);
      mid[i] = lo + offs;
      int lmid = lo + offs;
      if (lmid <= hi) {
        if (A[lmid] > key) {
          locate.set(i, "L");
        } else if (A[lmid] < key) {
          locate.set(i, "R");
        } else {
          locate.set(i, "E");
          pos.compareAndSet(-1, lmid);
        }
      } else {
        mid[i] = hi + 1;
        locate.set(i, "L");
      }
    }
  }
}
