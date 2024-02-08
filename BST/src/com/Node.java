package com;

// Node class
public class Node {

  public int key;
  public Node left;
  public Node right;
  public Node next;

  public Node(int key) {
    this.key = key;
    this.left = null;
    this.right = null;
  }
}
