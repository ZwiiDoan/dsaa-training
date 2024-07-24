package per.duyd.training.dsaa.linkedlist;

public class FastAndSlowPointers {
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

  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummyHead = new ListNode(-1);
    dummyHead.next = head;

    ListNode first = dummyHead;
    ListNode second = dummyHead;
    int i = 0;

    while (i <= n && second != null) {
      i++;
      second = second.next;
    }

    while (second != null) {
      first = first.next;
      second = second.next;
    }

    ListNode removed = first.next;
    first.next = first.next.next;
    removed.next = null;

    return dummyHead.next;
  }
}
