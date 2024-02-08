package com;

import java.util.Iterator;

// InOrderIterator class
public class InOrderIterator implements Iterator<Node> {

  private Node current;
  private Iterator<Node> leftIterator;
  private Iterator<Node> rightIterator;

  public InOrderIterator(Node root) {
    current = root;
    if (root != null) {
      leftIterator = new InOrderIterator(root.left);
      rightIterator = new InOrderIterator(root.right);
    }
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public Node next() {
    Node result = null;
    if (leftIterator.hasNext()) {
      result = leftIterator.next();
    } else {
      result = current;
      if (rightIterator.hasNext()) {
        current = rightIterator.next();
      } else {
        current = null;
      }
    }
    return result;
  }
}
