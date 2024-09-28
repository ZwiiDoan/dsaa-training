package per.duyd.training.dsaa.linkedlist;

public class General {

  public ListNode removeElements(ListNode head, int val) {
    while (head != null && head.val == val) {
      head = head.next;
    }

    if (head == null) {
      return null;
    }

    ListNode prev = head, curr = head.next;
    while (curr != null) {
      if (curr.val == val) {
        prev.next = curr.next;
      } else {
        prev = curr;
      }

      curr = curr.next;
    }

    return head;
  }

  public int getDecimalValue(ListNode head) {
    int sum = 0, count = 0;

    ListNode curr = head, prev = null;
    while (curr != null) {
      ListNode next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    while (prev != null) {
      sum += (prev.val == 1) ? (int) Math.pow(2, count) : 0;
      prev = prev.next;
      count++;
    }

    return sum;
  }

  public ListNode oddEvenList(ListNode head) {
    if (head == null || head.next == null) {
      return head;
    }

    ListNode evenHead = head.next, oddCurr = head, evenCurr = evenHead;

    while (evenCurr != null && evenCurr.next != null) {
      ListNode oddNext = evenCurr.next;
      oddCurr.next = evenCurr.next;
      oddCurr = oddNext;

      ListNode evenNext = evenCurr.next.next;
      evenCurr.next = evenCurr.next.next;
      evenCurr = evenNext;
    }

    oddCurr.next = evenHead;

    return head;
  }
}
