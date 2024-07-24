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
}
