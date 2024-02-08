package com;

import java.lang.Object;
import java.util.Random;

@SuppressWarnings("unchecked")
public class RandomizedQueue<Integer> {

  public Object[] queue;
  public int size;
  public Random random;

  public RandomizedQueue() {
    this.queue = new Object[1];
    this.size = 0;
    this.random = new Random();
  }

  // size function
  public int size() {
    return this.size;
  }

  // Check if the queue is empty
  public boolean isEmpty() {
    return this.size == 0;
  }

  // Add Integer to the queue (Resize if needed)
  public void enqueue(Integer item) {
    // resize the queue if it is full
    if (this.size == this.queue.length) {
      this.resize(this.queue.length * 2);
    }

    this.queue[this.size] = item;
    this.size++;
  }

  // Remove a random Integer from the queue (Resize if needed).
  public Integer dequeue() {
    if (this.isEmpty()) {
      return null;
    }

    // pick a random item from the queue
    int randomIndex = this.random.nextInt(this.size);
    Integer item = (Integer) this.queue[randomIndex];

    // remove that item from the queue
    this.queue[randomIndex] = this.queue[this.size - 1];
    this.queue[this.size - 1] = null;
    this.size--;

    // rezise the queue if it is 1/4 full
    if (this.size > 0 && this.size == this.queue.length / 4) {
      this.resize(this.queue.length / 2);
    }

    return item;
  }

  // Change the size
  private void resize(int newSize) {
    Integer[] newQueue = (Integer[]) new Object[newSize];

    // copy the elements from the old array to the new array
    for (int i = 0; i < this.size; i++) {
      newQueue[i] = (Integer) this.queue[i];
    }

    this.queue = newQueue;
  }
}
