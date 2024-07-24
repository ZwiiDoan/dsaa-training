package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UncrossedLinesTest {

  private UncrossedLines uncrossedLines;

  public static Stream<Arguments> maxUncrossedLinesParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {1, 4, 2}, new int[] {1, 2, 4}, 2),
        Arguments.of("Example 2", new int[] {2, 5, 1, 2, 5}, new int[] {10, 5, 2, 1, 5, 2}, 3),
        Arguments.of("Example 3", new int[] {1, 3, 7, 1, 7, 5}, new int[] {1, 9, 2, 5, 1}, 2)
    );
  }

  @BeforeEach
  void setUp() {
    uncrossedLines = new UncrossedLines();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxUncrossedLinesParams")
  void maxUncrossedLines(String description, int[] nums1, int[] nums2, int expected) {
    assertEquals(expected, uncrossedLines.maxUncrossedLines(nums1, nums2));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxUncrossedLinesParams")
  void maxUncrossedLinesBottomUp(String description, int[] nums1, int[] nums2, int expected) {
    assertEquals(expected, uncrossedLines.maxUncrossedLinesBottomUp(nums1, nums2));
  }
}