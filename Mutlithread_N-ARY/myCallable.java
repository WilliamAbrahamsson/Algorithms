import java.util.concurrent.Callable;

public class myCallable implements Callable<Integer>{
    
    private Integer[] mid;
    private String[] locate;
    private Integer[] A;
    private int pos;
    private int key ;
    private int lo;
    private int intv;
    private int step;
    private int hi;

    public myCallable (Integer[] mid, String[] locate, int pos, Integer[] A, int key, int lo, int intv, int step, int hi) {
        
        this.A = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            this.A[i] = A[i];
        }
        this.locate = new String[locate.length];
        for (int i = 0; i < locate.length; i++) {
            this.locate[i] = locate[i];
        }
        this.mid = new Integer[mid.length];
        for (int i = 0; i < mid.length; i++) {
            this.mid[i] = mid[i];
        }

        this.pos = pos;
        this.key = key;
        this.lo = lo;
        this.intv = intv;
        this.step = step;
        this.hi = hi;
    }


    @Override
    public Integer call() throws Exception {
        for (int i = 1; i <= intv; i++) {
            int offs = step * i + (i - 1);
            mid[i] = lo + offs;
            int lmid = lo + + offs;
            
            if (lmid <= hi) {
                if (A[lmid] > key) {
                    locate[i] = "L";
                }
                else if (A[lmid] < key) {
                    locate[i] = "R";
                }
                else {
                    locate[i] = "E";
                    pos = lmid;
                    return pos;
                }
            }
            else {
                mid[i] = hi + 1;
                locate[i] = "L";
            }
        }
        return -1;
    }
}

