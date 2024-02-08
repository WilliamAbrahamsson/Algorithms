import java.util.NoSuchElementException;

class Heapy {

  public class HeapEntry {

    public double weight_to;
    public int vertex;

    public HeapEntry(double weight_to, int vertex) {
      this.weight_to = weight_to;
      this.vertex = vertex;
    }

    // getters and setters
    public double getWeightTo() {
      return this.weight_to;
    }

    public void setWeightTo(double new_weight) {
      this.weight_to = new_weight;
    }

    public int getVertex() {
      return this.vertex;
    }

    public void setVertex(int new_vertex_number) {
      this.vertex = new_vertex_number;
    }
  }

  // variable declarations
  private HeapEntry[] heap;
  private int sz;
  private int capacity;
  private static final int top = 1;

  // constructor
  public Heapy(int capacity) {
    this.capacity = capacity;
    this.sz = 0;
    heap = new HeapEntry[this.capacity + 1];
    heap[0] = new HeapEntry(Double.MIN_VALUE, -1);
  }

  public HeapEntry getHeapEntry(int position) {
    return heap[position];
  }

  // get parent of node
  public int getParent(int pos) {
    return pos / 2;
  }

  // check if the node is a leaf
  public boolean isLeaf(int pos) {
    return pos > sz / 2;
  }

  // get left child of node
  public int getLeftChild(int pos) {
    return pos * 2;
  }

  // get right child of node
  public int getRightChild(int pos) {
    return pos * 2 + 1;
  }

  // swap two nodes
  public void swap(int a, int b) {
    HeapEntry temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
  }

  // swim up
  public void swim(int pos) {
    while (
      pos > 1 && heap[pos].getWeightTo() < heap[getParent(pos)].getWeightTo()
    ) {
      swap(pos, getParent(pos));
      pos = getParent(pos);
    }
  }

  // sink down
  public void sink(int pos) {
    while (!isLeaf(pos)) {
      int left = getLeftChild(pos);
      int right = getRightChild(pos);
      int smallest = left;

      if (right <= sz && heap[right].getWeightTo() < heap[left].getWeightTo()) {
        smallest = right;
      }

      if (heap[pos].getWeightTo() > heap[smallest].getWeightTo()) {
        swap(pos, smallest);
        pos = smallest;
      } else {
        break;
      }
    }
  }

  public void push(double weightTo, int vertex) {
    HeapEntry element = new HeapEntry(weightTo, vertex);

    if (sz == capacity) {
      System.out.println("The heap is full!");
      return;
    }

    heap[++sz] = element;
    swim(sz);
  }

  public HeapEntry peek() {
    if (sz == 0) {
      System.out.println("The heap is empty!");
      throw new NoSuchElementException();
    }
    return heap[top];
  }

  public HeapEntry pop() {
    if (sz == 0) {
      System.out.println("The heap is empty!");
      throw new NoSuchElementException();
    }
    HeapEntry removedElement = heap[top];
    heap[top] = heap[sz--];
    sink(top);
    return removedElement;
  }

  public int getSize() {
    return this.sz;
  }

  public boolean isEmpty() {
    if (this.sz == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    String s = "";
    for (int i = 1; i <= sz; i++) {
      s += heap[i] + " ";
    }
    return s;
  }
}
