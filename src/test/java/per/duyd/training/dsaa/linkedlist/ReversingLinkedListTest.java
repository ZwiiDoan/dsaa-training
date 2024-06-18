package per.duyd.training.dsaa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static per.duyd.training.dsaa.linkedlist.ReversingLinkedList.ListNode;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ReversingLinkedListTest {

  private ReversingLinkedList reversingLinkedList;

  public static Stream<Arguments> isPalindromeParams() {
    return Stream.of(
        Arguments.of("Linked List is a Palindrome",
            new ListNode(1, new ListNode(2, new ListNode(2, new ListNode(1)))), true),
        Arguments.of("Linked List is NOT a Palindrome",
            new ListNode(1, new ListNode(2)), false)
    );
  }

  @BeforeEach
  void setUp() {
    reversingLinkedList = new ReversingLinkedList();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isPalindromeParams")
  void isPalindrome(String description, ListNode head, boolean expected) {
    assertEquals(expected, reversingLinkedList.isPalindrome(head));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isPalindromeParams")
  void isPalindrome2(String description, ListNode head, boolean expected) {
    assertEquals(expected, reversingLinkedList.isPalindrome2(head));
  }
}