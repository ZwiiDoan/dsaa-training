package per.duyd.training.dsaa.stacksandqueues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MonotonicTest {

  private Monotonic monotonic;

  public static Stream<Arguments> validSubArraysParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 4, 2, 5, 3}, 11),
        Arguments.of("Example 2", new int[] {3, 2, 1}, 3),
        Arguments.of("Example 3", new int[] {2, 2, 2}, 6));
  }

  public static Stream<Arguments> mostCompetitiveSubsequenceParams() {
    return Stream.of(
        Arguments.of("Example 3", new int[] {71, 18, 52, 29, 55, 73, 24, 42, 66, 8, 80, 2}, 3,
            new int[] {8, 80, 2}),
        Arguments.of("Example 1", new int[] {3, 5, 2, 6}, 2, new int[] {2, 6}),
        Arguments.of("Example 2", new int[] {2, 4, 3, 3, 5, 4, 9, 6}, 4, new int[] {2, 3, 3, 4}));
  }

  public static Stream<Arguments> visiblePeopleCountsParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {10, 6, 8, 5, 11, 9}, new int[] {3, 1, 2, 1, 1, 0}),
        Arguments.of("Example 2", new int[] {5, 1, 2, 3, 10}, new int[] {4, 1, 1, 1, 0}));
  }

  public static Stream<Arguments> sumSubarrayMinsParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {3, 1, 2, 4}, 17),
        Arguments.of("Example 2", new int[] {11, 81, 94, 43, 3}, 444));
  }

  public static Stream<Arguments> subArrayRangesParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 3}, 4),
        Arguments.of("Example 2", new int[] {1, 3, 3}, 4),
        Arguments.of("Example 3", new int[] {4, -2, -3, 4, 1}, 59));
  }

  public static Stream<Arguments> maximumRobotsParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {3, 6, 1, 3, 4}, new int[] {2, 1, 3, 4, 5}, 25, 3),
        Arguments.of("Example 2", new int[] {11, 12, 19}, new int[] {10, 8, 7}, 19, 0));
  }

  @BeforeEach
  void setUp() {
    monotonic = new Monotonic();
  }

  public static Stream<Arguments> finalPricesParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {8, 4, 6, 2, 3}, new int[] {4, 2, 4, 2, 3}),
        Arguments.of("Example 2", new int[] {1, 2, 3, 4, 5}, new int[] {1, 2, 3, 4, 5}),
        Arguments.of("Example 3", new int[] {10, 1, 1, 6}, new int[] {9, 0, 1, 6}));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("finalPricesParams")
  void finalPrices(String description, int[] prices, int[] expected) {
    assertIterableEquals(Arrays.stream(expected).boxed().toList(),
        Arrays.stream(monotonic.finalPrices(prices)).boxed().toList());
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("validSubArraysParams")
  void validSubArrays(String description, int[] nums, int expected) {
    assertEquals(expected, monotonic.validSubArrays(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("mostCompetitiveSubsequenceParams")
  void mostCompetitiveSubsequence(String description, int[] nums, int k, int[] expected) {
    assertIterableEquals(Arrays.stream(expected).boxed().toList(),
        Arrays.stream(monotonic.mostCompetitiveSubsequence(nums, k)).boxed().toList());
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("visiblePeopleCountsParams")
  void visiblePeopleCounts(String description, int[] heights, int[] expected) {
    assertIterableEquals(Arrays.stream(expected).boxed().toList(),
        Arrays.stream(monotonic.visiblePeopleCounts(heights)).boxed().toList());
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("sumSubarrayMinsParams")
  void sumSubarrayMins(String description, int[] arr, int expected) {
    assertEquals(expected, monotonic.sumSubarrayMins(arr));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("subArrayRangesParams")
  void subArrayRanges(String description, int[] arr, int expected) {
    assertEquals(expected, monotonic.subArrayRanges(arr));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maximumRobotsParams")
  void maximumRobots(String description, int[] chargeTimes, int[] runningCosts, long budget,
                     int expected) {
    assertEquals(expected, monotonic.maximumRobots(chargeTimes, runningCosts, budget));
  }
}