import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

  private class Node {
    Item item;
    Node next;
    Node previous;
  }

  private Node head;
  private Node tail;
  private int size;

  // construct an empty deque
  public Deque() {
  }

  // is the deque empty?
  public boolean isEmpty() {
    return size == 0;
  }

  // return the number of items on the deque
  public int size() {
    return size;
  }

  // add the item to the front
  public void addFirst(Item item) {
    validateItem(item);

    Node newNode = new Node();
    newNode.item = item;

    if (isEmpty()) {
      tail = newNode;
    } else {
      newNode.next = head;
      head.previous = newNode;
    }

    head = newNode;
    size++;
  }

  // add the item to the back
  public void addLast(Item item) {
    validateItem(item);

    Node newNode = new Node();
    newNode.item = item;

    if (isEmpty()) {
      head = newNode;
    } else {
      newNode.previous = tail;
      tail.next = newNode;
    }

    tail = newNode;
    size++;
  }

  private void validateItem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("Item cannot be null");
    }
  }

  // remove and return the item from the front
  public Item removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty");
    }

    Item item = head.item;
    head = head.next;
    size--;

    if (isEmpty()) {
      tail = null;
    } else {
      head.previous = null;
    }

    return item;
  }

  // remove and return the item from the back
  public Item removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Deque is empty");
    }

    Item item = tail.item;
    tail = tail.previous;
    size--;

    if (isEmpty()) {
      head = null;
    } else {
      tail.next = null;
    }

    return item;
  }

  // return an iterator over items in order from front to back
  public Iterator<Item> iterator() {
    return new DequeIterator();
  }

  private class DequeIterator implements Iterator<Item> {

    private Node current;

    public DequeIterator() {
      current = head;
    }

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException("Deque is empty");
      }

      Item item = current.item;
      current = current.next;

      return item;
    }
  }

  // unit testing (required)
  public static void main(String[] args) {
    Deque<Integer> deque = new Deque<>();

    deque.addFirst(5);
    deque.addLast(6);
    deque.addFirst(4);
    deque.addLast(7);
    deque.addFirst(3);
    deque.addLast(8);
    deque.addFirst(2);
    deque.addLast(9);
    deque.addFirst(1);
    deque.addLast(10);

    System.out.println("Deque has " + deque.size() + " items: ");
    for (int item : deque) {
      System.out.print(item + " ");
    }

    System.out.println();

    deque.removeFirst();
    deque.removeLast();
    deque.removeFirst();
    deque.removeLast();

    System.out.println("Deque has " + deque.size() + " items: ");
    for (int item : deque) {
      System.out.print(item + " ");
    }
  }
}