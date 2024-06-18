package per.duyd.training.dsaa.linkedlist;

public class FastAndSlowPointers {
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

  public ListNode deleteMiddle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }

    ListNode slow = head, fast = head.next.next;

    while (fast != null && fast.next != null) {
      fast = fast.next.next;
      slow = slow.next;
    }

    slow.next = slow.next.next;

    return head;

    /*
    Input: head = [1,3,4,7,1,2,6]
    Output: [1,3,4,1,2,6]
    fast = 4, slow = 1
    fast = 1, slow = 3
    fast = 6, slow = 4

    Input: head = [1,2,3,4]
    Output: [1,2,4]
    fast = 3, slow = 1
    fast = null, slow = 2

    Input: head = [2,1]
    Output: [2]
    fast = null, slow = 2
     */
  }
}
