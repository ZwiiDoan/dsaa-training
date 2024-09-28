package per.duyd.training.dsaa.linkedlist;

public class MyLinkedList {

  private MyNode head;
  private MyNode tail;
  private int size = 0;

  public MyLinkedList() {
  }

  public int get(int index) {
    if (index < 0 || index >= size) {
      return -1;
    }
    MyNode node = getMyNode(index);
    return node == null ? -1 : node.val;
  }

  public void addAtHead(int val) {
    MyNode newHead = new MyNode();
    newHead.val = val;

    if (head == null) {
      head = newHead;
      tail = head;
    } else {
      MyNode temp = head;
      head = newHead;
      head.next = temp;
      temp.prev = head;
    }

    size++;
  }

  public void addAtTail(int val) {
    MyNode newTail = new MyNode();
    newTail.val = val;

    if (tail == null) {
      tail = newTail;
      head = tail;
    } else {
      MyNode temp = tail;
      tail = newTail;
      tail.prev = temp;
      temp.next = tail;
    }

    size++;
  }

  public void addAtIndex(int index, int val) {
    if (index == size) {
      addAtTail(val);
    } else if (index == 0) {
      addAtHead(val);
    } else {
      MyNode node = getMyNode(index);
      if (node == null) {
        return;
      }

      MyNode newNode = new MyNode();
      newNode.val = val;

      MyNode prev = node.prev;

      if (prev != null) {
        prev.next = newNode;
      }

      newNode.prev = prev;
      newNode.next = node;
      node.prev = newNode;

      size++;
    }
  }

  public void deleteAtIndex(int index) {
    if (size == 0) {
      return;
    } else if (index == 0) {
      head = head.next;
    } else if (index == size - 1) {
      tail = tail.prev;
    } else {
      MyNode node = getMyNode(index);
      if (node == null) {
        return;
      }

      MyNode next = node.next;
      MyNode prev = node.prev;

      if (prev != null) {
        prev.next = next;
      }

      if (next != null) {
        next.prev = prev;
      }
    }

    size--;
  }

  private MyNode getMyNode(int index) {
    if (index < 0 || index >= size) {
      return null;
    }

    int current = 0;
    MyNode node = head;

    while (current != index) {
      node = node.next;
      current++;
    }

    return node;
  }

  private static class MyNode {
    int val;
    MyNode next;
    MyNode prev;
  }
}
