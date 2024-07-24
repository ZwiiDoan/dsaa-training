package per.duyd.training.dsaa.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrefixSumTest {

  private PrefixSum prefixSum;

  public static Stream<Arguments> testHighestAltitudeParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {-5, 1, 5, 0, -7}, 1),
        Arguments.of("Example 2", new int[] {-4, -3, -2, -1, 4, 3, 2}, 0));
  }

  public static Stream<Arguments> testPivotIndexParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 7, 3, 6, 5, 6}, 3),
        Arguments.of("Example 2", new int[] {1, 2, 3}, -1),
        Arguments.of("Example 3", new int[] {2, 1, -1}, 0),
        Arguments.of("Example 4", new int[] {-1, -1, -1, -1, -1, -1}, -1),
        Arguments.of("Example 5", new int[] {-1, -1, -1, -1, -1, 0}, 2),
        Arguments.of("Example 6", new int[] {-1, -1, -1, 1, 1, 1}, -1));
  }

  public static Stream<Arguments> testNumArrayParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {-2, 0, 3, -5, 2, -1}, 0, 2, 1),
        Arguments.of("Example 2", new int[] {-2, 0, 3, -5, 2, -1}, 2, 5, -1),
        Arguments.of("Example 3", new int[] {-2, 0, 3, -5, 2, -1}, 0, 5, -3));
  }

  @BeforeEach
  void setUp() {
    prefixSum = new PrefixSum();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testHighestAltitudeParams")
  void testHighestAltitude(String description, int[] gain, int expected) {
    assertEquals(expected, prefixSum.highestAltitude(gain));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testPivotIndexParams")
  void testPivotIndex(String description, int[] nums, int expected) {
    assertEquals(expected, prefixSum.pivotIndex(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testNumArrayParams")
  void testNumArray(String description, int[] nums, int left, int right, int expected) {
    assertEquals(expected, new PrefixSum.NumArray(nums).sumRange(left, right));
  }
}