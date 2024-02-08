package com;

import java.util.Iterator;

// PostOrderIterator class
public class PostOrderIterator implements Iterator<Node> {

  private Node current;
  private Iterator<Node> leftIterator;
  private Iterator<Node> rightIterator;

  public PostOrderIterator(Node root) {
    current = root;
    if (root != null) {
      leftIterator = new PostOrderIterator(root.left);
      rightIterator = new PostOrderIterator(root.right);
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
    } else if (rightIterator.hasNext()) {
      result = rightIterator.next();
    } else {
      result = current;
      current = null;
    }
    return result;
  }
}
