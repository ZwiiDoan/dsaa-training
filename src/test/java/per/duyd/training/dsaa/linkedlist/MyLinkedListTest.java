package per.duyd.training.dsaa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MyLinkedListTest {

  @Test
  void testMyLinkedList1() {
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(1);
    myLinkedList.addAtTail(3);
    myLinkedList.addAtIndex(1, 2);
    assertEquals(2, myLinkedList.get(1));
    myLinkedList.deleteAtIndex(1);
    assertEquals(3, myLinkedList.get(1));
  }

  @Test
  void testMyLinkedList2() {
    MyLinkedList myLinkedList = new MyLinkedList();
    myLinkedList.addAtHead(1);
    myLinkedList.addAtTail(3);
    myLinkedList.addAtIndex(1, 2);
    assertEquals(2, myLinkedList.get(1));
    myLinkedList.deleteAtIndex(0);
    assertEquals(2, myLinkedList.get(0));
  }
}