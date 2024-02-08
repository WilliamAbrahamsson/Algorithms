package com;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DequeIterator implements Iterator<Integer> {

  private Node current;

  public DequeIterator(Node head) {
    this.current = head;
  }

  // Check if there are elements to iterate over.
  @Override
  public boolean hasNext() {
    return current != null;
  }

  // Get the next element in the list.
  @Override
  public Integer next() {
    // Throw an exception if there are no more elements to iterate over.
    if (!hasNext()) {
      throw new NoSuchElementException();
    }

    // Get the data from the current node and move to the next node.
    int data = current.data;
    current = current.next;
    return data;
  }
}
