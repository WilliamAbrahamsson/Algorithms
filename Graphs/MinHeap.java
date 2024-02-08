import java.util.ArrayList;

public class MinHeap {

  private ArrayList<Edge> heap;

  public MinHeap() {
    heap = new ArrayList<>();
  }

  // add the new edge to the end of the heap.
  public void push(Edge edge) {
    heap.add(edge);

    // get the current and parent indexes.
    int currentIndex = heap.size() - 1;
    int parentIndex = (currentIndex - 1) / 2;

    while (
      currentIndex > 0 &&
      heap.get(parentIndex).weight > heap.get(currentIndex).weight
    ) {
      // swap the current with its parent if the parent is greater.
      Edge temp = heap.get(currentIndex);
      heap.set(currentIndex, heap.get(parentIndex));
      heap.set(parentIndex, temp);
      // update the indices.
      currentIndex = parentIndex;
      parentIndex = (currentIndex - 1) / 2;
    }
  }

  public Edge pop() {
    if (heap.isEmpty()) {
      return null;
    }

    // remove and return the root edge of the heap.
    Edge root = heap.get(0);

    int lastIndex = heap.size() - 1;
    heap.set(0, heap.get(lastIndex));
    heap.remove(lastIndex);
    lastIndex--;

    int currentIndex = 0;
    int leftIndex = 1;
    int rightIndex = 2;

    while (leftIndex <= lastIndex) {
      // find the index of the smallest child.
      int minIndex = currentIndex;

      // check if the left child is smaller than the current edge.
      if (heap.get(leftIndex).weight < heap.get(minIndex).weight) {
        minIndex = leftIndex;
      }

      // check if the right child is smaller than the current edge.
      if (
        rightIndex <= lastIndex &&
        heap.get(rightIndex).weight < heap.get(minIndex).weight
      ) {
        minIndex = rightIndex;
      }

      // swap the current edge with the smallest child.
      if (minIndex != currentIndex) {
        Edge temp = heap.get(currentIndex);
        heap.set(currentIndex, heap.get(minIndex));
        heap.set(minIndex, temp);

        // update the 3 indexes.
        currentIndex = minIndex;
        leftIndex = currentIndex * 2 + 1;
        rightIndex = currentIndex * 2 + 2;
      } else {
        break;
      }
    }

    return root;
  }

  // build a min-heap with heapify.
  public void buildMinHeap(ArrayList<Edge> arr) {
    heap = arr;
    int size = heap.size();
    for (int i = size / 2 - 1; i >= 0; i--) {
      heapify(i, size);
    }
  }

  // heapify the heap.
  private void heapify(int root, int size) {
    int left = 2 * root + 1;
    int right = 2 * root + 2;
    int smallest = root;

    // find the smallest edge between the root and its children.
    if (left < size && heap.get(left).weight < heap.get(smallest).weight) {
      smallest = left;
    }
    if (right < size && heap.get(right).weight < heap.get(smallest).weight) {
      smallest = right;
    }
    if (smallest != root) {
      // swap the root edge with the smallest child edge.
      Edge temp = heap.get(root);
      heap.set(root, heap.get(smallest));
      heap.set(smallest, temp);
      heapify(smallest, size);
    }
  }

  // return the root edge of the heap.
  public boolean isEmpty() {
    return heap.isEmpty();
  }
}
