package version_2;
public class Peterson2 {

    private int N = 2;  
    private boolean[] interested = new boolean[N];

    // The priority queue
    private Heapy queue = new Heapy(2);

    public synchronized void enter_region(int process) {
        // System.out.println(process + " Wants to enter critical region");
        int other = 1 - process; 
        interested[process] = true; 
        
        // Add process to priority queue
        queue.insert(process);

        // Wait until it is the process's turn (i.e. the process is at the front of the queue)

        // add freeze handeling
        while (queue.peek() != process || interested[other]) {
            // Wait
        }
        
        System.out.println(process + " Entered critical region");
    }

    public synchronized void leave_region(int process) {
        System.out.println(process + " Left critical region");
        interested[process] = false;
        
        // Remove process from priority queue
        queue.pop();
      
    }

    public synchronized void access_region(int process) {
        enter_region(process);
        leave_region(process);
    }
}

