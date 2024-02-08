public class Edge {
    double weight;
    int from;
    int to;

    public Edge(int from, int to, double weight) {
        this.from = from; // only because it makes edge iterator look nice :)
        this.to = to;
        this.weight = weight;
    }
}
