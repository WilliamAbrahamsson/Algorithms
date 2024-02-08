package com;

import java.util.Stack;

public class BST {

  // Root node
  public Node root;

  // initialize tree with root node
  public BST() {
    this.root = null;
  }

  // add function
  public void add(int key) {
    this.root = add(this.root, key);
  }

  public Node add(Node node, int key) {
    if (node == null) {
      return new Node(key);
    }

    if (node.key > key) {
      node.left = add(node.left, key);
    } else if (node.key < key) {
      node.right = add(node.right, key);
    }

    return node;
  }

  // remove function
  public void remove(int key) {
    this.root = this.remove(this.root, key);
  }

  private Node remove(Node node, int key) {
    if (node == null) {
      return null;
    }
    if (key < node.key) {
      node.left = this.remove(node.left, key);
    } else if (key > node.key) {
      node.right = this.remove(node.right, key);
    } else {
      // if the node has no children, return null
      if (node.left == null && node.right == null) {
        return null;
      }

      // if the node has one child, return the child
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }

      Node smallest = this.smallest(node.right);
      node.key = smallest.key;
      node.right = this.remove(node.right, smallest.key);
    }

    // return the node
    return node;
  }

  // smallest function
  private Node smallest(Node node) {
    if (node == null) {
      return null;
    }

    if (node.left == null) {
      return node;
    }

    // return the smallest node
    return this.smallest(node.left);
  }

  // size function
  public int size() {
    return this.size(this.root);
  }

  private int size(Node node) {
    if (node == null) {
      return 0;
    }

    return this.size(node.left) + this.size(node.right) + 1;
  }

  // height function
  public int height() {
    return this.height(this.root);
  }

  public int height(Node node) {
    if (node == null) {
      return -1;
    } else {
      return 1 + Math.max(height(node.left), height(node.right));
    }
  }

  // contains function
  public boolean contains(int key) {
    return this.contains(this.root, key);
  }

  private boolean contains(Node node, int key) {
    if (node == null) {
      return false;
    }

    if (key < node.key) {
      return this.contains(node.left, key);
    } else if (key > node.key) {
      return this.contains(node.right, key);
    }

    // if the key is equal to the node key, return true
    return true;
  }

  public int kthLargest(int k) {
    return kthLargestHelper(root, k);
  }

  private int kthLargestHelper(Node current, int k) {
    if (current == null) {
      throw new IllegalArgumentException("No kth largest element in tree");
    }

    int rightCount = countNodes(current.right);
    if (rightCount == k - 1) {
      return current.key;
    } else if (rightCount > k - 1) {
      return kthLargestHelper(current.right, k);
    } else {
      return kthLargestHelper(current.left, k - rightCount - 1);
    }
  }

  private int countNodes(Node current) {
    if (current == null) {
      return 0;
    }
    return 1 + countNodes(current.left) + countNodes(current.right);
  }

  // remove largest k function
  public int removeLargestK(int k) {
    int largest = kthLargest(k);
    this.remove(largest);
    return largest;
  }
}
