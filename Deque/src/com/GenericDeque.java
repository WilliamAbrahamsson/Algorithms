package com;

public class GenericDeque {

  // init head
  public Node head;

  // add first function
  public void addFirst(int data) {
    Node newNode = new Node(data);
    newNode.next = head;
    head = newNode;
  }

  // add last function
  public void addLast(int data) {
    Node newNode = new Node(data);

    if (head == null) {
      head = newNode;
      return;
    }

    // Find the last node.
    Node current = head;
    while (current.next != null) {
      current = current.next;
    }

    current.next = newNode;
  }

  // Check if the list is empty.
  public boolean isEmpty() {
    return head == null;
  }

  // size function
  public int size() {
    int count = 0;
    Node current = head;
    while (current != null) {
      count += 1;

      // Go to the next node.
      current = current.next;
    }
    return count;
  }

  // remove from front of the list
  public void removeFirst() {
    if (head == null) {
      throw new IllegalStateException("Can't remove from empty list!");
    }

    // Change the head one step to the right
    head = head.next;
  }

  // Remove the last node from the list.
  public void removeLast() {
    if (head == null) {
      throw new IllegalStateException("Can't remove from empty list");
    }

    Node current = head;
    while (current.next.next != null) {
      current = current.next;
    }

    current.next = null;
  }
}
