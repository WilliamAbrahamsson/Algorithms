import java.util.ArrayList;
import java.util.LinkedList;

// breath first extnesion of path.
public class BFSPaths extends Paths {

  public BFSPaths(Graph graph, int start_vertex) {
    super(graph, start_vertex);
    bfs(start_vertex);
  }

  private void bfs(int search_vertex) {
    // linked list works as a queue.
    LinkedList<Integer> queue = new LinkedList<Integer>();
    queue.add(search_vertex);
    this.marked[search_vertex] = true;

    // while the queue is not empty
    while (!queue.isEmpty()) {
      int removed_vertex = queue.remove();
      ArrayList<Integer> adjList = graph.getAdjecentVertices(removed_vertex);

      // for each vertex in the adjList.
      for (Integer vertex : adjList) {
        // if the vertex is not marked, mark it and add it to the queue.
        if (!this.marked[vertex]) {
          queue.add(vertex);
          this.marked[vertex] = true;
          this.edgeTo[vertex] = removed_vertex;
        }
      }
    }
  }
}
