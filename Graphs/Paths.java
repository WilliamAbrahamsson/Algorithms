import java.util.ArrayList;
import java.util.Iterator;

// parent class to both breadth first and depth first search algs.
public class Paths {

  public Graph graph;
  public int start_vertex;
  public boolean[] marked;
  public int[] edgeTo;
  public ArrayList<Integer> path;

  public Paths(Graph graph, int start_vertex) {
    this.graph = graph;
    this.start_vertex = start_vertex;
    this.marked = new boolean[graph.vertex_count];
    this.edgeTo = new int[graph.vertex_count];
  }

  // returns true if its marked searched in the array of booleans.
  public boolean has_path_to(int search_vertex) {
    return marked[search_vertex];
  }

  // returns the path from the start vertex to the vertex.
  public void set_path_to(int vertex) {
    if (!has_path_to(vertex)) {
      this.path = null;
    }

    int x = vertex;
    ArrayList<Integer> path = new ArrayList<>();

    while (x != start_vertex) {
      path.add(0, x);
      x = edgeTo[x];
    }
    path.add(0, start_vertex);
    this.path = path;
  }

  // uses the iterator to walk the path.
  public void iterate_path() {
    Iterator<Integer> iterator = new pathIterator();
    while (iterator.hasNext()) {
      System.out.print(iterator.next() + " ");
    }
    System.out.println();
  }

  // path iterator
  private class pathIterator implements Iterator<Integer> {

    private int index;

    public pathIterator() {
      index = 0;
    }

    public boolean hasNext() {
      return index < path.size();
    }

    public Integer next() {
      return path.get(index++);
    }
  }

  // get visited vertices.
  public ArrayList<Integer> get_visited_vertices() {
    ArrayList<Integer> visited_vertices = new ArrayList<>();
    for (int i = 0; i < marked.length; i++) {
      if (marked[i]) {
        visited_vertices.add(i);
      }
    }
    return visited_vertices;
  }
}
