import java.util.*;
import java.util.ArrayList;

public class Dijkstra {

  // store the edge that takes you to each vertex on the shortest path.
  private Edge[] edgeTo;
  // shortest distance to each vertex.
  private double[] distTo;
  // priority queue (heap impl) to keep track of the vertices to visit.
  private MinHeap pq;

  public Dijkstra(DirectedGraph G, int s) {
    edgeTo = new Edge[G.vertex_count];
    distTo = new double[G.vertex_count];
    pq = new MinHeap();

    // set all distances to infinity except the start vertex.
    for (int v = 0; v < G.vertex_count; v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    distTo[s] = 0.0;

    // add the start vertex to the priority queue.
    pq.push(new Edge(s, s, 0.0));

    // run dijkstra's algorithm.
    while (!pq.isEmpty()) {
      Edge e = pq.pop();
      int v = e.to;
      for (Edge edge : G.getAdjecentEdges(v)) {
        relax(edge);
      }
    }
  }

  // relax an edge.
  private void relax(Edge e) {
    int v = e.from, w = e.to;
    if (distTo[w] > distTo[v] + e.weight) {
      // update the shortest distance to w.
      distTo[w] = distTo[v] + e.weight;
      // update the edge that takes you to w on the shortest path.
      edgeTo[w] = e;
      // add w to the priority queue.
      pq.push(new Edge(v, w, distTo[w]));
    }
  }

  // get the shortest path to a destination vertex.
  public ArrayList<Integer> shortestPath(int dest) {
    ArrayList<Integer> path = new ArrayList<Integer>();
    for (Edge e = edgeTo[dest]; e != null; e = edgeTo[e.from]) {
      // add the vertices on the shortest path in reverse order.
      path.add(e.from);
    }
    
    // reverse the path so that it is in the correct order.
    Collections.reverse(path);

    // add the last vertex.
    path.add(dest);
    return path;
  }
}
