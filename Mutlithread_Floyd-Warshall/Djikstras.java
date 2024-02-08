import java.util.*;
import java.util.concurrent.Semaphore;

public class Djikstras implements Runnable {

  private DirectedGraph DG;
  private int vertex_count;
  private Heapy queue; // min-heap used as priority queue.
  public Edge[] edgeTo;
  public double[] weightTo;
  private static final double INFINITY = Double.POSITIVE_INFINITY;
  private int start_vertex;
  private static Semaphore semaphore = new Semaphore(1);

  public Djikstras(DirectedGraph DG, int start_vertex) {
    this.DG = DG;
    this.vertex_count = DG.vertex_count;
    this.start_vertex = start_vertex;
  }

  @Override
  public void run() {
    // init both arrays to have the size of the graph vertex count.
    this.edgeTo = new Edge[vertex_count];
    this.weightTo = new double[vertex_count];

    Arrays.fill(this.weightTo, INFINITY); // infinity in all positions
    this.weightTo[start_vertex] = 0.0; // change first pos to 0.0 from infinity.

    // inititalize min heap as priority queue.
    queue = new Heapy(vertex_count);

    // push the first vertex index to the queue.
    queue.push(0.0, start_vertex); // [0.0, 0]

    // while the queue is not empty...
    while (!queue.isEmpty()) {
      // remove smallest (pop)
      int poped = queue.pop().getVertex(); // []
      // get adjency list for that particular vertex.
      ArrayList<Edge> adj_edges = DG.getAdjecentEdges(poped);

      // iterate edges.
      for (Edge edge : adj_edges) {
        relax(edge);
      }
    }
    /* try {
      semaphore.acquire();
      System.out.println("Starting at: " + start_vertex);
      printAllPaths(start_vertex);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    } */
  }

  public void relax(Edge edge) {
    // get the data from the edge.
    int from = edge.from;
    int to = edge.to;
    double weight = edge.weight;

    if (this.weightTo[to] > this.weightTo[from] + weight) {
      this.weightTo[to] = this.weightTo[from] + weight;
      this.edgeTo[to] = edge;

      boolean found = false;

      // iterate min heap queue.
      for (int ix = 0; ix < this.queue.getSize(); ix++) {
        if (this.queue.getHeapEntry(ix).getVertex() == to) {
          this.queue.getHeapEntry(ix).setWeightTo(this.weightTo[to]);
          found = true;
          break;
        }
      }

      if (!found) {
        // if to vertex is not already in the queue, add it.
        this.queue.push(this.weightTo[to], to);
      }
    }
  }

  public synchronized void printAllPaths(int source) {
    for (int i = 0; i < edgeTo.length; i++) {
      if (i == source) {
        continue;
      }
      System.out.print(i + ": <");
      printPath(source, i);
      System.out.println("> (=" + weightTo[i] + ")");
    }
  }

  private void printPath(int source, int destination) {
    if (destination == source) {
      System.out.print(source);
      return;
    }
    if (this.edgeTo[destination] == null) {
      return;
    }
    printPath(source, this.edgeTo[destination].from);
    System.out.print(", " + destination);
  }
}
