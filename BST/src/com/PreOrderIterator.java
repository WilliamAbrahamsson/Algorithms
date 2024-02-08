package com;

import java.util.Iterator;

// PreOrderIterator class
public class PreOrderIterator implements Iterator<Node> {

  private Node current;
  private Iterator<Node> leftIterator;
  private Iterator<Node> rightIterator;

  public PreOrderIterator(Node root) {
    current = root;
    if (root != null) {
      leftIterator = new PreOrderIterator(root.left);
      rightIterator = new PreOrderIterator(root.right);
    }
  }

  @Override
  public boolean hasNext() {
    return current != null;
  }

  @Override
  public Node next() {
    Node result = current;
    if (leftIterator.hasNext()) {
      current = leftIterator.next();
    } else if (rightIterator.hasNext()) {
      current = rightIterator.next();
    } else {
      current = null;
    }
    return result;
  }
}
