import java.util.ArrayList;

// depth first extension of path.
public class DFSPaths extends Paths {

  public DFSPaths(Graph graph, int start_vertex) {
    super(graph, start_vertex);
    dfs(start_vertex);
  }

  void dfs(int search_vertex) {
    // mark the vertex as visited.
    this.marked[search_vertex] = true;

    // get the adjencies of the vertex.
    ArrayList<Integer> adjList = graph.getAdjecentVertices(search_vertex);

    for (Integer vertex : adjList) {
      if (!this.marked[vertex]) {
        dfs(vertex);
        this.edgeTo[vertex] = search_vertex;
      }
    }
  }
}
