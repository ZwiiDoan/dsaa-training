package per.duyd.training.dsaa.linkedlist;

public class ReversingLinkedList {
  /**
   * Definition for singly-linked list.
   */
  public static class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
      this.val = val;
    }

    ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public boolean isPalindrome(ListNode head) {
    ListNode headCopy = copyLinkedList(head);
    ListNode reversedHead = reverseLinkedList(headCopy);
    return compareLinkedList(head, reversedHead);

    /*
    [1,2,3,4,5]
    curr = 1, prev = null => next = curr.next = 2, curr.next = prev = null, prev = curr = 1, curr = next = 2
    curr = 2, prev = 1 => next = curr.next = 3, curr.next = prev = 1, prev = curr = 2, curr = next = 3
    curr = 3, prev = 2 => next = curr.next = 4, curr.next = prev = 2, prev = curr = 3, curr = next = 4
    curr = 4, prev = 3 => next = curr.next = 5, curr.next = prev = 3, prev = curr = 4, curr = next = 5
    curr = 5, prev = 4 => next = curr.next = null, curr.next = prev = 4, prev = curr = 5, curr = next = null
    curr = null => end
     */
  }

  public boolean isPalindrome2(ListNode head) {
    if (head == null) {
      return true;
    }
    ListNode firstHalfEnd = endOfFirstHalf(head);
    ListNode secondHalfStart = reverseLinkedList(firstHalfEnd.next);
    boolean ans = compareLinkedList(head, secondHalfStart);
    firstHalfEnd.next = reverseLinkedList(secondHalfStart);
    return ans;
  }

  private ListNode copyLinkedList(ListNode head) {
    ListNode headCopy = new ListNode(head.val), curr = head, currCopy = headCopy;

    while (curr.next != null) {
      currCopy.next = new ListNode(curr.next.val);
      currCopy = currCopy.next;
      curr = curr.next;
    }

    return headCopy;
  }

  private ListNode reverseLinkedList(ListNode head) {
    ListNode curr = head, prev = null;

    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  private boolean compareLinkedList(ListNode head1, ListNode head2) {
    ListNode curr1 = head1, curr2 = head2;

    while (curr2 != null) {
      if (curr1 == null || curr1.val != curr2.val) {
        return false;
      }

      curr1 = curr1.next;
      curr2 = curr2.next;
    }

    return true;
  }

  private ListNode endOfFirstHalf(ListNode head) {
    ListNode fast = head;
    ListNode slow = head;
    while (fast.next != null && fast.next.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }
    return slow;
  }
}
