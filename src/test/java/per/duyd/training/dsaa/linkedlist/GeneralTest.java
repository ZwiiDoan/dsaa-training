package per.duyd.training.dsaa.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GeneralTest {
  private General general;

  public static Stream<Arguments> removeElementsParams() {
    return Stream.of(
        Arguments.of("Should remove 2 elements from the list",
            new ListNode(1, new ListNode(2, new ListNode(6,
                new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))))),
            6,
            new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))
        ),
        Arguments.of("Should remove 0 element from an empty list", null, 1, null),
        Arguments.of("Should remove all elements from a list when all elements equal to val",
            new ListNode(7, new ListNode(7, new ListNode(7, new ListNode(7)))),
            7,
            null)
    );
  }

  @BeforeEach
  void setup() {
    general = new General();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("removeElementsParams")
  void removeElements(String description, ListNode head, int val, ListNode expected) {
    ListNode actual = general.removeElements(head, val);

    while (expected != null) {
      assertNotNull(actual);
      assertEquals(expected.val, actual.val);
      actual = actual.next;
      expected = expected.next;
    }

    assertNull(actual);
  }
}
