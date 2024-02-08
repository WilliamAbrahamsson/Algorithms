import java.util.ArrayList;
import java.util.Iterator;

// undirected extension of graph.
public class UndirectedGraph extends Graph {

  public UndirectedGraph(int vertex_count) {
    super(vertex_count);
  }

  // adds undirected edge with weight 1.0.
  public void add_edge(int a, int b) {
    add_edge(a, b, 1.0);
  }

  // adds undirected edge with weight param.
  public void add_edge(int a, int b, double weight) {
    // add edge to a that points to b.
    Edge to_b = new Edge(a, b, weight);
    adjacency_list.get(a).add(to_b);

    // add edge to b that points to a.
    Edge to_a = new Edge(b, a, weight);
    adjacency_list.get(b).add(to_a);
  }

  // removes undirected edge.
  public void remove_edge(int a, int b) {
    // Remove the edge from a to b.
    Iterator<Edge> iterate_a = iterate_adjacencies(a);
    while (iterate_a.hasNext()) {
      Edge edge = iterate_a.next();
      if (edge.from == a && edge.to == b) {
        adjacency_list.get(a).remove(edge);
        break;
      }
    }

    // Remove the edge from b to a.
    Iterator<Edge> iterate_b = iterate_adjacencies(b);
    while (iterate_b.hasNext()) {
      Edge edge = iterate_b.next();
      if (edge.from == b && edge.to == a) {
        adjacency_list.get(b).remove(edge);
        break;
      }
    }
  }

  // get degree (number of edges connected to spec vertex).
  public int get_degree(int vertex) {
    return adjacency_list.get(vertex).size();
  }

  // get all edge weights.
  public double[] get_edge_weights() {
    ArrayList<Double> edge_weights = new ArrayList<Double>();
    Iterator<Edge> iterate_edges = iterate_edges();
    while (iterate_edges.hasNext()) {
      Edge edge = iterate_edges.next();
      edge_weights.add(edge.weight);
    }
    double[] edge_weights_array = new double[edge_weights.size()];
    for (int i = 0; i < edge_weights.size(); i++) {
      edge_weights_array[i] = edge_weights.get(i);
    }
    return edge_weights_array;
  }

  // get all edges with specified weight.
  public ArrayList<Edge> get_edges_with_weight(double weight) {
    ArrayList<Edge> edges_with_weight = new ArrayList<Edge>();
    Iterator<Edge> iterate_edges = iterate_edges();
    while (iterate_edges.hasNext()) {
      Edge edge = iterate_edges.next();
      if (edge.weight == weight) {
        edges_with_weight.add(edge);
      }
    }
    return edges_with_weight;
  }

  // returns false
  @Override
  public boolean is_directed() {
    return false;
  }
}
