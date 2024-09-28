package per.duyd.training.dsaa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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
    ListNode expected1 = new ListNode(1,
        new ListNode(3, new ListNode(4, new ListNode(1, new ListNode(2, new ListNode(6))))));

    ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
    ListNode expected2 = new ListNode(1, new ListNode(2, new ListNode(4)));

    ListNode head3 = new ListNode(2, new ListNode(1));
    ListNode expected3 = new ListNode(2);

    return Stream.of(Arguments.of("LinkedList has odd number of nodes", head1, expected1),
        Arguments.of("LinkedList has even number of nodes", head2, expected2),
        Arguments.of("LinkedList has 2 nodes", head3, expected3),
        Arguments.of("LinkedList has 1 node", new ListNode(1), null));
  }

  public static Stream<Arguments> removeNthFromEndParams() {
    return Stream.of(Arguments.of("Example 1",
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2,
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(5))))),
        Arguments.of("Example 2", new ListNode(1), 1, null),
        Arguments.of("Example 3", new ListNode(1, new ListNode(2)), 1, new ListNode(1)));
  }

  public static Stream<Arguments> deleteDuplicatesParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new ListNode(1, new ListNode(2, new ListNode(3,
                new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5))))))),
            new ListNode(1, new ListNode(2, new ListNode(5)))
        ),
        Arguments.of("Example 2",
            new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3))))),
            new ListNode(2, new ListNode(3))
        )
    );
  }

  public static Stream<Arguments> swapNodesParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))),
            2,
            new ListNode(1, new ListNode(4, new ListNode(3, new ListNode(2, new ListNode(5)))))
        ),
        Arguments.of("Example 2",
            new ListNode(7, new ListNode(9, new ListNode(6, new ListNode(6,
                new ListNode(7, new ListNode(8, new ListNode(3, new ListNode(0,
                    new ListNode(9, new ListNode(5)))))))))),
            5,
            new ListNode(7, new ListNode(9, new ListNode(6, new ListNode(6,
                new ListNode(8, new ListNode(7, new ListNode(3, new ListNode(0,
                    new ListNode(9, new ListNode(5))))))))))
        )
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


  @ParameterizedTest(name = "{0}")
  @MethodSource("removeNthFromEndParams")
  void removeNthFromEnd(String description, ListNode head, int n, ListNode expected) {
    ListNode actual = fastAndSlowPointers.removeNthFromEnd(head, n);

    while (expected != null) {
      assertEquals(expected.val, actual.val);
      expected = expected.next;
      actual = actual.next;
    }

    assertNull(actual);
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("deleteDuplicatesParams")
  void deleteDuplicates(String description, ListNode head, ListNode expected) {
    ListNode actual = fastAndSlowPointers.deleteDuplicates(head);

    while (expected != null) {
      assertEquals(expected.val, actual.val);
      expected = expected.next;
      actual = actual.next;
    }

    assertNull(actual);
  }


  @ParameterizedTest(name = "{0}")
  @MethodSource("swapNodesParams")
  void swapNodes(String description, ListNode head, int k, ListNode expected) {
    ListNode actual = fastAndSlowPointers.swapNodes(head, k);

    while (expected != null) {
      assertEquals(expected.val, actual.val);
      expected = expected.next;
      actual = actual.next;
    }

    assertNull(actual);
  }
}