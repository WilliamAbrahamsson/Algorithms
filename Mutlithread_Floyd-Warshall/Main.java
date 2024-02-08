import java.util.Random;
import java.util.concurrent.*;

public class Main {

  static DirectedGraph DG;
  static int nThreads;

  public static void main(String[] args) {
    // Experiment
    DG = generateGraph(100, 100);
    nThreads = 8; // 8 cores on my M1 macbook pro. (change to 1 for single threaded).

    double total_djikstra_time = runDjikstras(DG, nThreads);
    double floyd_time = runFloyd(DG, nThreads);

    //print times
    System.out.println(
      "Vertex count: " +
      DG.vertex_count +
      ", Edge count: " +
      DG.vertex_count *
      10 +
      ", Threads: " +
      nThreads
    );
    System.out.println("Floyd-Warshall Time: " + floyd_time + "ms");
    System.out.println("Djikstra Time: " + total_djikstra_time + "ms");
    /* 
    // Morgans test graph for dijkstras :)
    DG = new DirectedGraph(8);
    DG.add_edge(0, 1, 5.0);
    DG.add_edge(0, 3, 8.0);
    DG.add_edge(0, 6, 9.0);
    DG.add_edge(1, 2, 15.0);
    DG.add_edge(1, 3, 4.0);
    DG.add_edge(1, 4, 12.0);
    DG.add_edge(2, 7, 9.0);
    DG.add_edge(3, 4, 7.0);
    DG.add_edge(3, 5, 6.0);
    DG.add_edge(4, 2, 3.0);
    DG.add_edge(4, 7, 11.0);
    DG.add_edge(5, 4, 1.0);
    DG.add_edge(5, 7, 13.0);
    DG.add_edge(6, 3, 5.0);
    DG.add_edge(6, 5, 4.0);
    DG.add_edge(6, 7, 20.0);
    
    System.out.println("Dijkstra's");
    // Djikstras d = new Djikstras(DG, 6);
    runDjikstras(DG, 8); */

    /* // Floyd warshall graph from youtube video for testing that it works :)
    DirectedGraph DG = new DirectedGraph(5);
    DG.add_edge(0, 1, 3); // A -> B
    DG.add_edge(0, 2, 2); // A -> C
    DG.add_edge(1, 3, 7); // B -> D
    DG.add_edge(2, 4, 4); // C -> E
    DG.add_edge(4, 2, 5); // E -> C
    DG.add_edge(3, 4, 6); // D -> E
    DG.add_edge(4, 1, 4); // E -> B  

    System.out.println("Floyd-Warshall's");
    runFloyd(DG, 8);  */

  }

  // run floyd
  public static double runFloyd(DirectedGraph DG, int nThreads) {
    // start timer
    double startTime = System.nanoTime();

    FloydWarshall fw = new FloydWarshall(DG, nThreads);

    // stop timer
    double endTime = System.nanoTime();
    double floyd_time = (endTime - startTime) / 100_000;

    return floyd_time;
  }

  public static double runDjikstras(DirectedGraph DG, int nThreads) {
    // start timer
    double startTime = System.nanoTime();

    // create a thread pool
    ExecutorService executor = Executors.newFixedThreadPool(nThreads);

    // submit a task for each vertex in the graph
    for (int ix = 0; ix < DG.vertex_count; ix++) {
      final int startVertex = ix;
      executor.submit(new Djikstras(DG, startVertex));
    }

    executor.shutdown();
    while (!executor.isTerminated()) {
      // wait for all threads to finish.
    }

    // stop timer
    double endTime = System.nanoTime();
    double djikstra_time = (endTime - startTime) / 100_000;

    return djikstra_time;
  }

  // generate graph of specified size and edge count. Weights are random doubles between 1 and 10.
  public static DirectedGraph generateGraph(int vertex_count, int edge_count) {
    DirectedGraph DG = new DirectedGraph(vertex_count);
    Random rand = new Random();

    for (int ix = 0; ix < edge_count; ix++) {
      int from = rand.nextInt(vertex_count);
      int to = rand.nextInt(vertex_count);
      double weight = rand.nextDouble(10.0) + 1.0;
      DG.add_edge(from, to, weight);
    }

    return DG;
  }
}
