import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Graph {

  public int vertex_count;
  public ArrayList<ArrayList<Edge>> adjacency_list;

  // constructor.
  public Graph(int vertex_count) {
    this.vertex_count = vertex_count;

    // initialize adjacency list of size vertex_count.
    this.adjacency_list = new ArrayList<ArrayList<Edge>>(vertex_count);

    // initialize each vertex of the adjacency list.
    for (int ix = 0; ix < vertex_count; ix++) {
      adjacency_list.add(new ArrayList<Edge>());
    }
  }

  // iterate through adjecent vertices of a vertex, then add the "to value".
  public ArrayList<Integer> getAdjecentVertices(int vertex) {
    ArrayList<Integer> adjList = new ArrayList<>();
    Iterator<Edge> iterate_adjacencies = iterate_adjacencies(vertex);
    while (iterate_adjacencies.hasNext()) {
      Edge edge = iterate_adjacencies.next();
      adjList.add(edge.to);
    }
    return adjList;
  }

  public ArrayList<Edge> getAdjecentEdges(int vertex) {
    ArrayList<Edge> adjList = new ArrayList<>();
    Iterator<Edge> iterate_adjacencies = iterate_adjacencies(vertex);
    while (iterate_adjacencies.hasNext()) {
      Edge edge = iterate_adjacencies.next();
      adjList.add(edge);
    }
    return adjList;
  }

  // iterate through the adjacency list of a vertex.
  public Iterator<Edge> iterate_adjacencies(int vertex) {
    return new AdjacencyIterator(vertex);
  }

  // iterate through all edges in the graph.
  public Iterator<Edge> iterate_edges() {
    return new EdgeIterator();
  }

  // iterate through all vertices in the graph.
  public Iterator<Integer> iterate_vertices() {
    return new VertexIterator();
  }

  // returns false (overridden by directed graph).
  public boolean is_directed() {
    return false;
  }

  private class AdjacencyIterator implements Iterator<Edge> {

    private Iterator<Edge> iterator;

    public AdjacencyIterator(int vertex) {
      iterator = adjacency_list.get(vertex).iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Edge next() {
      return iterator.next();
    }
  }

  // uses the adjacency iterator to iterate through all edges in the graph.
  private class EdgeIterator implements Iterator<Edge> {

    private int currentVertex;
    private Iterator<Edge> adjacencyIterator;

    public EdgeIterator() {
      currentVertex = 0;
      adjacencyIterator = new AdjacencyIterator(currentVertex);
    }

    @Override
    public boolean hasNext() {
      if (adjacencyIterator.hasNext()) {
        return true;
      }

      while (currentVertex < vertex_count - 1) {
        currentVertex++;
        adjacencyIterator = new AdjacencyIterator(currentVertex);

        if (adjacencyIterator.hasNext()) {
          return true;
        }
      }
      return false;
    }

    @Override
    public Edge next() {
      return adjacencyIterator.next();
    }
  }

  // all vertices in graph iterator.
  private class VertexIterator implements Iterator<Integer> {

    private int current_vertex;

    public VertexIterator() {
      current_vertex = 0;
    }

    @Override
    public boolean hasNext() {
      return current_vertex < vertex_count;
    }

    @Override
    public Integer next() {
      return current_vertex++;
    }
  }

  // writes to python array that is later printed in graph.py.
  public void writeGraph(String data, String filename, String arrayName) {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
      writer.write(arrayName + " = " + data);
      writer.close();
    } catch (IOException e) {
      System.out.println("Error writing to file: " + e.getMessage());
    }
  }

  // to string method is used in the writer aswell as for debugging.
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    for (int ix = 0; ix < vertex_count; ix++) {
      sb.append(ix).append(": [");
      Iterator<Edge> iterate_adjacencies = iterate_adjacencies(ix);
      while (iterate_adjacencies.hasNext()) {
        Edge edge = iterate_adjacencies.next();
        sb.append(edge.to);
        if (iterate_adjacencies.hasNext()) {
          sb.append(", ");
        }
      }
      sb.append("]");
      if (ix < vertex_count - 1) {
        sb.append(", ");
      }
    }
    sb.append("}");
    return sb.toString();
  }
}
