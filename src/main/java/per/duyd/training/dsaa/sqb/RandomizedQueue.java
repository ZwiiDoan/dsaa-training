package per.duyd.training.dsaa.sqb;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

  private Item[] items;
  private int size;

  // construct an empty randomized queue
  public RandomizedQueue() {
    items = (Item[]) new Object[1];
  }

  // is the randomized queue empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the randomized queue
  public int size() {
    return size;
  }

  // add the item
  public void enqueue(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }

    items[size] = item;
    size++;

    if (size == items.length) {
      resize(items.length * 2);
    }
  }

  private void resize(int capacity) {
    Item[] newItems = (Item[]) new Object[capacity];
    int maxIndex = Math.min(items.length, capacity);

    for (int i = 0; i < maxIndex; i++) {
      newItems[i] = items[i];
    }

    items = newItems;
  }

  // remove and return a random item
  public Item dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("RandomizedQueue is empty");
    }

    int randomIndex = StdRandom.uniformInt(size);
    Item randomItem = items[randomIndex];
    items[randomIndex] = items[size - 1];
    items[size - 1] = null;
    size--;

    if (size == items.length / 4) {
      resize(items.length / 2);
    }

    return randomItem;
  }

  // return a random item (but do not remove it)
  public Item sample() {
    if (isEmpty()) {
      throw new NoSuchElementException("RandomizedQueue is empty");
    }
    return items[StdRandom.uniformInt(size)];
  }

  // return an independent iterator over items in random order
  public Iterator<Item> iterator() {
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {

    private final Item[] randomItems;
    private int current;

    public RandomizedQueueIterator() {
      current = 0;
      randomItems = (Item[]) new Object[size];

      for (int i = 0; i < size; i++) {
        randomItems[i] = items[i];
      }

      for (int i = 0; i < size; i++) {
        int randomIndex = StdRandom.uniformInt(size);
        Item randomItem = randomItems[randomIndex];
        Item currentItem = randomItems[i];
        randomItems[i] = randomItem;
        randomItems[randomIndex] = currentItem;
      }
    }

    @Override
    public boolean hasNext() {
      return current < size;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      return randomItems[current++];
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();

    randomizedQueue.enqueue(1);
    randomizedQueue.enqueue(2);
    randomizedQueue.enqueue(3);
    randomizedQueue.enqueue(4);
    randomizedQueue.enqueue(5);
    randomizedQueue.enqueue(6);
    randomizedQueue.enqueue(7);
    randomizedQueue.enqueue(8);
    randomizedQueue.enqueue(9);
    randomizedQueue.enqueue(10);

    System.out.println("RandomizedQueue has " + randomizedQueue.size() + " items: ");
    for (int item : randomizedQueue) {
      System.out.print(item + " ");
    }

    System.out.println();

    randomizedQueue.dequeue();
    randomizedQueue.dequeue();
    randomizedQueue.dequeue();

    System.out.println("RandomizedQueue has " + randomizedQueue.size() + " items: ");
    for (int item : randomizedQueue) {
      System.out.print(item + " ");
    }
  }

}