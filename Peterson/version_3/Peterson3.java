package version_3;
public class Peterson3 {
    private int N;  
    private boolean[] interested;

    private Heapy queue;

    public Peterson3(int numThreads) {
        N = numThreads;  
        interested = new boolean[N];
        queue = new Heapy(N);
    }

    public synchronized void enter_region(int process) {
        int other = (process + 1) % N; 
        interested[process] = true; 
        
        queue.insert(process);

        while (queue.peek() != process || interested[other]) {
            // Wait
        }
        
        System.out.println(process + " Entered critical region");
    }

    public synchronized void leave_region(int process) {
        System.out.println(process + " Left critical region");
        interested[process] = false;
        
        queue.pop();
    }

    public synchronized void access_region(int process) {
        enter_region(process);
        
        System.out.println("Critical region: " + process + "");

        leave_region(process);
    }
}
