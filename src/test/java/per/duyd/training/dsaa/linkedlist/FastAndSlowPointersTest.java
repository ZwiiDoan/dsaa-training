package per.duyd.training.dsaa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static per.duyd.training.dsaa.linkedlist.FastAndSlowPointers.ListNode;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FastAndSlowPointersTest {

  private FastAndSlowPointers fastAndSlowPointers;

  public static Stream<Arguments> deleteMiddleParams() {
    ListNode head1 = new ListNode(1, new ListNode(3,
        new ListNode(4, new ListNode(7, new ListNode(1, new ListNode(2, new ListNode(6)))))));
    ListNode expected1 = new ListNode(1, new ListNode(3,
        new ListNode(4, new ListNode(1, new ListNode(2, new ListNode(6))))));

    ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
    ListNode expected2 = new ListNode(1, new ListNode(2, new ListNode(4)));

    ListNode head3 = new ListNode(2, new ListNode(1));
    ListNode expected3 = new ListNode(2);

    return Stream.of(
        Arguments.of("LinkedList has odd number of nodes", head1, expected1),
        Arguments.of("LinkedList has even number of nodes", head2, expected2),
        Arguments.of("LinkedList has 2 nodes", head3, expected3),
        Arguments.of("LinkedList has 1 node", new ListNode(1), null)
    );
  }

  @BeforeEach
  void setUp() {
    fastAndSlowPointers = new FastAndSlowPointers();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("deleteMiddleParams")
  void deleteMiddle(String description, ListNode head, ListNode expected) {
    ListNode actual = fastAndSlowPointers.deleteMiddle(head);

    while (expected != null) {
      assertNotNull(actual);
      assertEquals(expected.val, actual.val);
      actual = actual.next;
      expected = expected.next;
    }

    assertNull(actual);
  }
}