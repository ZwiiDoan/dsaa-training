package per.duyd.training.dsaa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ArraysSearchTest {

  private ArraysSearch arraysSearch;

  public static Stream<Arguments> guessNumberParams() {
    return Stream.of(Arguments.of("Example 1", 10, 6, 6), Arguments.of("Example 2", 1, 1, 1),
        Arguments.of("Example 3", 2, 1, 1));
  }

  public static Stream<Arguments> maxDistanceParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {55, 30, 5, 4, 2}, new int[] {100, 20, 10, 10, 5}, 2),
        Arguments.of("Example 2", new int[] {2, 2, 2}, new int[] {10, 10, 1}, 1),
        Arguments.of("Example 3", new int[] {30, 29, 19, 5}, new int[] {25, 25, 25, 25, 25}, 2));
  }

  @BeforeEach
  void setUp() {
    arraysSearch = new ArraysSearch();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("guessNumberParams")
  void guessNumber(String description, int n, int pick, int expected) {
    assertEquals(expected, new ArraysSearch(pick).guessNumber(n));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxDistanceParams")
  void maxDistanceBinarySearch(String description, int[] nums1, int[] nums2, int expected) {
    assertEquals(expected, arraysSearch.maxDistanceBinarySearch(nums1, nums2));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxDistanceParams")
  void maxDistance2Pointers(String description, int[] nums1, int[] nums2, int expected) {
    assertEquals(expected, arraysSearch.maxDistance2Pointers(nums1, nums2));
  }
}