import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NarySearchThreads1 {

    private static Integer[] mid;
    private static String[] locate;
    private static int pos;
    private static Executor exe;
    
    public static Integer nary(Integer[] A, int lo, int hi, int key, int intv, int amountThreads) {
        
        mid = new Integer[intv + 1];
        Arrays.fill(mid, 0);
        locate = new String[intv + 2];
        Arrays.fill(locate, "N");

        pos = -1;

        locate[0] = "R";
        locate[intv + 1] = "L";

        exe = Executors.newFixedThreadPool(amountThreads);
    
        while (lo <= hi && pos == -1) {
            mid[0] = lo - 1;
            int step = (hi - lo + 1) / (intv + 1);
            
            // making the variable lo and hi effectively final so they work inside the callable
            int final_lo = lo; 
            int final_hi = hi;

            Callable<Integer> task = new myCallable(mid, locate, pos, A, key, final_lo, intv, step, final_hi);
            Future<Integer> res = ((ExecutorService) exe).submit(task);
           
            try {
                pos = res.get();
            } 
            catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        
            for (int i = 1; i <= intv; i++) {
                if (!locate[i].equals(locate[i - 1])) {
                    lo = mid[i - 1] + 1;
                    hi = mid[i] - 1;
                }
            }
    
            if (!locate[intv].equals(locate[intv + 1])) {
                lo = mid[intv] + 1;
            }
        }

        ((ExecutorService) exe).shutdown();
        while(!((ExecutorService) exe).isTerminated()) {
            // System.out.println("ALL REQUESTS COMPLETED SUCCESSFULLY");
        }
        return pos;
    }
}
