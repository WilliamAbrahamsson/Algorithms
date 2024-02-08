package com;

// AVL class
public class AVL {

  // Root node
  public Node root;

  // initialize tree with root node
  public AVL() {
    this.root = null;
  }

  // add function
  public void add(int key) {
    this.root = add(this.root, key);
  }

  public Node add(Node n, int key) {
    if (n == null) {
      return new Node(key);
    }

    if (n.key > key) {
      n.left = add(n.left, key);
    } else if (n.key < key) {
      n.right = add(n.right, key);
    }

    return balance(n);
  }

  // remove function
  public void remove(int key) {
    this.root = remove(this.root, key);
  }

  public Node remove(Node node, int key) {
    if (node == null) {
      return node;
    }

    if (key < node.key) {
      node.left = this.remove(node.left, key);
    } else if (key > node.key) {
      node.right = this.remove(node.right, key);
    } else {
      if (node.left == null) {
        return node.right;
      } else if (node.right == null) {
        return node.left;
      }

      node.key = minValue(node.right);
      node.right = remove(node.right, node.key);
    }

    // return the balanced node
    return balance(node);
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

  // balance function
  public Node balance(Node node) {
    if (node == null) {
      return node;
    }

    if (height(node.left) - height(node.right) > 1) {
      if (height(node.left.left) >= height(node.left.right)) {
        node = rotateLeft(node);
      } else {
        node = doubleLeft(node);
      }
    } else if (height(node.right) - height(node.left) > 1) {
      if (height(node.right.right) >= height(node.right.left)) {
        node = rotateRight(node);
      } else {
        node = doubleRight(node);
      }
    }

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    return node;
  }

  // height function
  public int height() {
    return this.height(this.root);
  }

  public int height(Node node) {
    if (node == null) {
      return -1;
    }
    return node.height;
  }

  // left rotation function
  public Node rotateLeft(Node node) {
    Node newNode = node.left;
    node.left = newNode.right;
    newNode.right = node;

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    newNode.height = Math.max(height(newNode.left), node.height) + 1;

    return newNode;
  }

  // right rotation function
  public Node rotateRight(Node node) {
    Node newNode = node.right;
    node.right = newNode.left;
    newNode.left = node;

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    newNode.height = Math.max(height(newNode.left), node.height) + 1;

    return newNode;
  }

  // double right rotation function
  public Node doubleRight(Node node) {
    node.right = rotateLeft(node.right);
    return rotateRight(node);
  }

  // double left rotation function
  public Node doubleLeft(Node node) {
    node.left = rotateRight(node.left);
    return rotateLeft(node);
  }

  // min value function
  public int minValue(Node node) {
    int minv = node.key;

    while (node.left != null) {
      minv = node.left.key;
      node = node.left;
    }
    return minv;
  }
}
