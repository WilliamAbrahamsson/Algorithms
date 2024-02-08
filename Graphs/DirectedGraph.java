import java.util.Iterator;

// directed extension of graph.
public class DirectedGraph extends Graph {

  public DirectedGraph(int vertex_count) {
    super(vertex_count);
  }

  // adds directed edge with weight 1.0.
  public void add_edge(int from, int to) {
    add_edge(from, to, 1.0);
  }

  // adds directed edge with weight param.
  public void add_edge(int from, int to, double weight) {
    // init edges and add them to the adjacency list.
    Edge pointer_to = new Edge(from, to, weight);
    adjacency_list.get(from).add(pointer_to);
  }

  // removes directed edge.
  public void remove_edge(int from, int to) {
    // Remove the edge from a to b.
    Iterator<Edge> iterate_a = iterate_adjacencies(from);
    while (iterate_a.hasNext()) {
      Edge pointer_to = iterate_a.next();
      if (pointer_to.from == from && pointer_to.to == to) {
        adjacency_list.get(from).remove(pointer_to);
        break;
      }
    }
  }

  // returns true.
  @Override
  public boolean is_directed() {
    return true;
  }

  // get total degree (sum of in and out degree).
  public int get_degree(int vertex) {
    return get_in_degree(vertex) + get_out_degree(vertex);
  }

  // get out degree.
  private int get_out_degree(int vertex) {
    return adjacency_list.get(vertex).size(); // pointers out.
  }

  // get in degree.
  int get_in_degree(int vertex) {
    int in_degree = 0;

    // iterate through all edges in the graph to find edges that point into to vertex.
    Iterator<Edge> iterate_adjacencies = iterate_adjacencies(vertex);
    while (iterate_adjacencies.hasNext()) {
      Edge pointer_to = iterate_adjacencies.next();
      if (pointer_to.to == vertex) {
        in_degree++;
      }
    }
    return in_degree;
  }
}
