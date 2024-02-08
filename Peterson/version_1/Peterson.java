public class Peterson {

  // interested at index
  static boolean[] interested = { false, false };
  static int turn = 0;

  // enter region
  static void enter_region(int process) {
    System.out.println(process + " Wants to enter critical region");

    // other process
    int other_process = 1 - process;

    // set interested to true
    interested[process] = true;

    // set turn to other process
    turn = other_process;

    // wait until other process has left CR
    while (turn == other_process && interested[other_process]) {}

    System.out.println(process + " Entered critical region");
  }

  // leave region
  static void leave_region(int process) {
    System.out.println(process + " Left critical region");

    // set interested to false
    interested[process] = false;
  }

  // enter and leave region
  void access_region(int process) {
    enter_region(process);
    leave_region(process);
  }
}
