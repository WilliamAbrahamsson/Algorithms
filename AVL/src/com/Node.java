package com;

// Node class
public class Node {

  public int key;
  public Node left;
  public Node right;
  public Node next;

  // height variable for AVL (Not used in BST)
  public int height;

  public Node(int key) {
    this.key = key;
    this.left = null;
    this.right = null;
    this.height = 0;
  }
}
