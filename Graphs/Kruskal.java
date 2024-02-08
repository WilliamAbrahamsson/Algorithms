import java.util.ArrayList;

public class Kruskal {

  private MinHeap heap;
  private QuickUnion uf;

  public Kruskal(UndirectedGraph G) {
    printMSTs(getMSTs(G));
  }

  public ArrayList<ArrayList<Edge>> getMSTs(UndirectedGraph G) {
    ArrayList<ArrayList<Integer>> connectedComponents = getComponents(G);
    ArrayList<ArrayList<Edge>> msts = new ArrayList<>();

    for (ArrayList<Integer> component : connectedComponents) {
      ArrayList<Edge> mst = new ArrayList<>();
      heap = new MinHeap(); // priority queue.
      uf = new QuickUnion(component.size()); //

      // add all edges in the component to the priority queue (heap).
      for (int v : component) {
        for (Edge e : G.adjacency_list.get(v)) {
          // add only one copy of each edge in the component.
          if (e.to > v && component.contains(e.to)) {
            heap.push(e);
          }
        }
      }

      // process edges in decreasing order of weight.
      while (!heap.isEmpty() && mst.size() < component.size() - 1) {
        Edge e = heap.pop();

        // parse in index 0 because to adjust for union-find offset.
        int from = e.from - component.get(0);
        int to = e.to - component.get(0);

        if (!uf.areConnected(from, to)) {
          uf.union(from, to);
          mst.add(e);
        }
      }

      msts.add(mst);
    }
    return msts;
  }

  // get all components.
  public static ArrayList<ArrayList<Integer>> getComponents(Graph G) {
    boolean[] marked = new boolean[G.vertex_count];
    ArrayList<ArrayList<Integer>> connectedComponents = new ArrayList<>();
    for (int ix = 0; ix < G.vertex_count; ix++) {
      if (!marked[ix]) {
        // use depth-first search to find all vertices connected to v.
        DFSPaths paths = new DFSPaths(G, ix);
        ArrayList<Integer> component = new ArrayList<>();
        for (int jx = 0; jx < G.vertex_count; jx++) {
          if (paths.has_path_to(jx)) {
            component.add(jx);
            marked[jx] = true;
          }
        }
        connectedComponents.add(component);
      }
    }
    return connectedComponents;
  }

  // print out
  public static void printMSTs(ArrayList<ArrayList<Edge>> msts) {
    for (int ix = 0; ix < msts.size(); ix++) {
      System.out.println("MST " + (ix + 1) + ":");
      for (Edge e : msts.get(ix)) {
        System.out.println(e.from + " - " + e.to + " (" + e.weight + ")");
      }
      System.out.println();
    }
  }
}
