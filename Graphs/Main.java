public class Main {

  public static void main(String[] args) {
    /*
    // TEST COURSEPATH
    System.out.println("TEST COURSEPATH");
    CoursePath p1 = new CoursePath("2DV702");
    CoursePath p2 = new CoursePath("1DV506");
    */
    /*
    // TEST DJIKSTRA
    System.out.println("");
    System.out.println("TEST DJIKSTRA");
    DirectedGraph DG = new DirectedGraph(8);

    DG.add_edge(0, 3, 7.0);
    DG.add_edge(3, 1, 7.0);
    DG.add_edge(5, 3, 5.0);
    DG.add_edge(5, 7, 2.0);
    DG.add_edge(1, 6, 1.0);
    DG.add_edge(5, 3, 5.0);
    DG.add_edge(1, 6, 1.0);
    DG.add_edge(6, 2, 8.0);
    DG.add_edge(0, 2, 5.0);
    DG.add_edge(2, 4, 6.0);
    DG.add_edge(4, 6, 2.0);

    Dijkstra dijkstra = new Dijkstra(DG, 0);
    System.out.println(dijkstra.shortestPath(6));

    DG.writeGraph(DG.toString(), "directed_data.py", "directed_data");
    */
    /*
    // TEST KRUSKALS
    System.out.println("");
    System.out.println("TEST KRUSKALS");
    UndirectedGraph UDG = new UndirectedGraph(13);
    UDG.add_edge(0, 1, 7.0);
    UDG.add_edge(0, 3, 6.0);
    UDG.add_edge(0, 6, 9.0);
    UDG.add_edge(1, 2, 5.0);
    UDG.add_edge(1, 3, 22.0);
    UDG.add_edge(1, 4, 10.0);
    UDG.add_edge(2, 4, 4.0);
    UDG.add_edge(2, 7, 13.0);
    UDG.add_edge(3, 5, 1.0);
    UDG.add_edge(3, 6, 13.0);
    UDG.add_edge(4, 5, 3.0);
    UDG.add_edge(4, 7, 8.0);
    UDG.add_edge(5, 6, 14.0);
    UDG.add_edge(5, 7, 16.0);
    UDG.add_edge(6, 7, 2.0);

    UDG.add_edge(8, 9, 11.0);
    UDG.add_edge(9, 10, 9.0);
    UDG.add_edge(8, 10, 7.0);

    UDG.add_edge(11, 12, 6.0);

    Kruskal kruskal = new Kruskal(UDG);

    UDG.writeGraph(UDG.toString(), "undirected_data.py", "undirected_data");
    */
    /*
     // TEST DFS
     System.out.println("TEST DFS");
     DirectedGraph G = new DirectedGraph(15);
     G.add_edge(2, 0);
     G.add_edge(2, 3);
     G.add_edge(3, 1);
     G.add_edge(3, 4);
     G.add_edge(4, 3);
     G.add_edge(5, 4);
     G.add_edge(5, 6);
     G.add_edge(6, 4);
     G.add_edge(6, 10);
     G.add_edge(7, 6);
     G.add_edge(7, 8);
     G.add_edge(8, 9);
     G.add_edge(10, 9);
 
     G.add_edge(12, 11);
     G.add_edge(12, 13);
     G.add_edge(13, 14);
     G.add_edge(14, 12);
 
     DFSPaths search = new DFSPaths(G, 6);
 
     search.set_path_to(1);
     search.iterate_path();
    */
    /*
    // TEST BFS
    System.out.println("TEST BFS");
    DirectedGraph G = new DirectedGraph(15);
    G.add_edge(2, 0);
    G.add_edge(2, 3);
    G.add_edge(3, 1);
    G.add_edge(3, 4);
    G.add_edge(4, 3);
    G.add_edge(5, 4);
    G.add_edge(5, 6);
    G.add_edge(6, 4);
    G.add_edge(6, 10);
    G.add_edge(7, 6);
    G.add_edge(7, 8);
    G.add_edge(8, 9);
    G.add_edge(10, 9);

    G.add_edge(12, 11);
    G.add_edge(12, 13);
    G.add_edge(13, 14);
    G.add_edge(14, 12);

    DFSPaths search = new DFSPaths(G, 6);

    search.set_path_to(1);
    search.iterate_path();
    */
  }
}
