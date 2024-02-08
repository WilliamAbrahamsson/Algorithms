package com;

import java.util.Iterator;
import java.util.Random;

@SuppressWarnings("unchecked")
public class RandomizedQueueIterator<Integer> implements Iterator<Integer> {

  private final Object[] queue;
  private int size;
  private final Random random;

  public RandomizedQueueIterator(Object[] queue, int size) {
    this.queue = queue;
    this.size = size;
    this.random = new Random();
  }

  @Override
  public boolean hasNext() {
    // Return true if the iterator has not yet reached the end of the queue
    return this.size > 0;
  }

  @Override
  public Integer next() {
    // Pick a random item from the queue
    int randomIndex = this.random.nextInt(this.size);
    Integer item = (Integer) this.queue[randomIndex];

    // Remove that item from the queue
    this.queue[randomIndex] = this.queue[this.size - 1];
    this.queue[this.size - 1] = null;
    this.size--;

    return item;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }
}
