package version_3;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;

class Heapy {

  // variable declarations
  private int[] heap;
  private int sz;
  private int capacity;
  private static final int top = 1;  
  private Semaphore mutex = new Semaphore(1);

  // constructor
  public Heapy(int capacity) {
    this.capacity = capacity;
    this.sz = 0;
    heap = new int[this.capacity + 1];
    heap[0] = Integer.MIN_VALUE;
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
    int temp = heap[a];
    heap[a] = heap[b];
    heap[b] = temp;
  }

  // swim up
  public void swim(int pos) {
    while (pos > 1 && heap[pos] < heap[getParent(pos)]) {
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
      
      if (right <= sz && heap[right] < heap[left]) {
        smallest = right;
      }

      if (heap[pos] > heap[smallest]) {
        swap(pos, smallest);
        pos = smallest;
      } else {
        break;
      }
    }
  }

  public void insert(int element) {
    try {
      mutex.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    };
    try {
      if (sz == capacity) {
        System.out.println("The heap is full!");
        return;
      }

      heap[++sz] = element;
      swim(sz);
    } finally {
      mutex.release();
    }
  }

  public int peek() {
    try {
      mutex.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      if (sz == 0) {
        System.out.println("The heap is empty!");
        throw new NoSuchElementException();
      }
      return heap[top];
    } finally {
      mutex.release();
    }
  }

  
  public int pop() {
    try {
      mutex.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    try {
      if (sz == 0) {
        System.out.println("The heap is empty!");
        throw new NoSuchElementException();
      }
      int removedElement = heap[top];
      heap[top] = heap[sz--];
      sink(top);
      return removedElement;
    } finally {
      mutex.release();
    }
  }
}
