package com;

import java.io.File;
import java.io.IOException;

public class LCRS {

  public File file;
  public LCRS leftMostChild;
  public LCRS rightSibling;
  public LCRS parent;

  public LCRS(File file) {
    this.file = file;
    this.parent = null;
    this.leftMostChild = null;
    this.rightSibling = null;
  }

  // add node
  public LCRS addNode(String path, String fileName) throws IOException {
    // paths matches
    if (file.getPath().equals(path)) {
      return this;
    }

    // Search the leftmost child and its siblings for a matching node
    LCRS child = leftMostChild;
    while (child != null) {
      LCRS result = child.addNode(path, fileName);
      if (result != null) {
        return result;
      }
      child = child.rightSibling;
    }

    // If file dosent exsist, create it
    File newFile = new File(path, fileName);
    newFile.createNewFile();

    // Rebuild the tree
    return buildTree(new File(path));
  }

  // build tree
  public static LCRS buildTree(File directory) {
    // create the root node for the tree
    LCRS root = new LCRS(directory);

    // call the recursive helper function to build the rest of the tree
    buildTreeHelper(root);
    return root;
  }

  // Recursive helper function to build the tree
  private static void buildTreeHelper(LCRS node) {
    File file = node.file;

    if (file.isDirectory()) {
      // get list of files in directory
      File[] children = file.listFiles();

      // for each child file, create a node and add it to the tree
      for (File child : children) {
        LCRS childNode = new LCRS(child);
        childNode.parent = node;
        buildTreeHelper(childNode);

        // add the child node as the leftmost child if the node doesn't have any
        if (node.leftMostChild == null) {
          node.leftMostChild = childNode;
        } else {
          // otherwise, add it as a right sibling of the previous child
          LCRS sibling = node.leftMostChild;
          while (sibling.rightSibling != null) {
            sibling = sibling.rightSibling;
          }
          sibling.rightSibling = childNode;
        }
      }
    }
  }
}
