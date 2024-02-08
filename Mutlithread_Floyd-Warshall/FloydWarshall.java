import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FloydWarshall {

  private int[][] successorsMatrix;
  private double[][] weightsMatrix;
  private static final int INFINITY = Integer.MAX_VALUE;
  private DirectedGraph DG;

  public FloydWarshall(DirectedGraph DG, int nThreads) {
    this.DG = DG;
    run(nThreads);
  }

  public void run(int nThreads) {
    // start timer.
    double start = System.nanoTime();

    // init.
    int n = DG.vertex_count;

    // init weights matrix.
    weightsMatrix = new double[n][n];
    for (int ix = 0; ix < n; ix++) {
      Arrays.fill(weightsMatrix[ix], INFINITY);
      weightsMatrix[ix][ix] = 0;
    }

    // fill weights matrix.
    for (int ix = 0; ix < n; ix++) {
      for (Edge e : DG.adjacency_list.get(ix)) {
        weightsMatrix[ix][e.to] = e.weight;
      }
    }

    // init successors matrix.
    successorsMatrix = new int[n][n];
    for (int ix = 0; ix < n; ix++) {
      for (int jx = 0; jx < n; jx++) {
        if (ix == jx || weightsMatrix[ix][jx] == INFINITY) {
          successorsMatrix[ix][jx] = -1;
        } else {
          successorsMatrix[ix][jx] = jx;
        }
      }
    }

    // init executor service with maximum available threads.
    ExecutorService executor = Executors.newFixedThreadPool(nThreads);
    for (int kx = 0; kx < n; kx++) {
      final int k = kx;
      executor.execute(() -> {
        for (int ix = 0; ix < n; ix++) {
          final int i = ix;
          for (int jx = 0; jx < n; jx++) {
            final int j = jx;
            if (
              // if there is a shorter path from i to j via k.
              (weightsMatrix[i][k] + weightsMatrix[k][j]) < weightsMatrix[i][j]
            ) {
              // update weights.
              weightsMatrix[i][j] = (weightsMatrix[i][k] + weightsMatrix[k][j]);

              // update successors.
              successorsMatrix[i][j] = successorsMatrix[i][k];
            }
          }
        }
      });
    }
    executor.shutdown();
    while (!executor.isTerminated()) {
      // wait for all threads to finish.
    }
    // display (lots of time!).
    /* printWeightsMatrix();
    printSuccessorsMatrix(); */
  }

  public ArrayList<Integer> shortestPath(int dest) {
    ArrayList<Integer> path = new ArrayList<Integer>();

    // current
    int current = 0;

    // add source to path.
    path.add(current);

    // if there is no path to the destination, return an empty path.
    if (successorsMatrix[current][dest] == INFINITY) {
      return path;
    }

    while (successorsMatrix[current][dest] != dest) {
      // add next node to path.
      path.add(successorsMatrix[current][dest]);

      // update current.
      current = successorsMatrix[current][dest];
    }

    // add destination to path.
    path.add(dest);

    // print path
    // System.out.println(path);

    return path;
  }

  // print successors matrix.
  private void printSuccessorsMatrix() {
    System.out.println("Successors Matrix:");
    for (int ix = 0; ix < successorsMatrix.length; ix++) {
      for (int jx = 0; jx < successorsMatrix[ix].length; jx++) {
        if (successorsMatrix[ix][jx] == -1) {
          System.out.print("- ");
        } else {
          System.out.print(successorsMatrix[ix][jx] + " ");
        }
      }
      System.out.println();
    }
  }

  // print weights matrix.
  public void printWeightsMatrix() {
    System.out.println("Weights Matrix:");
    for (int ix = 0; ix < weightsMatrix.length; ix++) {
      for (int jx = 0; jx < weightsMatrix[ix].length; jx++) {
        if (weightsMatrix[ix][jx] == INFINITY) {
          System.out.print("INF ");
        } else {
          System.out.print(weightsMatrix[ix][jx] + " ");
        }
      }
      System.out.println();
    }
  }
}
