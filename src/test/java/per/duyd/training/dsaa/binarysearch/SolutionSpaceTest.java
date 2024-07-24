package per.duyd.training.dsaa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SolutionSpaceTest {

  private SolutionSpace solutionSpace;

  public static Stream<Arguments> maxMinSweetnessPieceParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, 5, 6),
        Arguments.of("Example 2", new int[] {5, 6, 7, 8, 9, 1, 2, 3, 4}, 8, 1),
        Arguments.of("Example 3", new int[] {1, 2, 2, 1, 2, 2, 1, 2, 2}, 2, 5));
  }

  public static Stream<Arguments> minLargestSubArraySumParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {7, 2, 5, 10, 8}, 2, 18),
        Arguments.of("Example 2", new int[] {1, 2, 3, 4, 5}, 2, 9),
        Arguments.of("Example 3", new int[] {1, 4, 4}, 3, 4),
        Arguments.of("Example 4", new int[] {10, 5, 13, 4, 8, 4, 5, 11, 14, 9, 16, 10, 20, 8}, 8,
            25)
    );
  }

  public static Stream<Arguments> minimumTimeParams() {
    int[] time = new int[100000];
    Arrays.fill(time, 1);

    return Stream.of(
        Arguments.of("Example 1", new int[] {1, 2, 3}, 5, 3),
        Arguments.of("Example 2", new int[] {2}, 1, 2),
        Arguments.of("Example 3", new int[] {10000}, 10000000, 100000000000L),
        Arguments.of("Example 4", time, 10000000, 100)
    );
  }

  @BeforeEach
  void setUp() {
    solutionSpace = new SolutionSpace();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxMinSweetnessPieceParams")
  void maxMinSweetnessPiece(String description, int[] sweetness, int k, long expected) {
    assertEquals(expected, solutionSpace.maxMinSweetnessPiece(sweetness, k));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("minLargestSubArraySumParams")
  void minLargestSubArraySum(String description, int[] nums, int k, long expected) {
    assertEquals(expected, solutionSpace.minLargestSubArraySum(nums, k));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("minimumTimeParams")
  void minimumTime(String description, int[] time, int totalTrips, long expected) {
    assertEquals(expected, solutionSpace.minimumTime(time, totalTrips));
  }
}