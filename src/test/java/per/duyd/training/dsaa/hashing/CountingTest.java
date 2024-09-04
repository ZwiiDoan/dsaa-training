package per.duyd.training.dsaa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CountingTest {

  private Counting counting;

  public static Stream<Arguments> sumOfUniqueParams() {
    return Stream.of(Arguments.of("2 unique elements, sum is 4", new int[] {1, 2, 3, 2}, 4),
        Arguments.of("No unique elements, sum is 0", new int[] {1, 1, 1, 1, 1}, 0),
        Arguments.of("All elements are, sum is 15", new int[] {1, 2, 3, 4, 5}, 15));
  }

  public static Stream<Arguments> totalMaxFrequenciesParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 2, 3, 1, 4}, 4),
        Arguments.of("Example 2", new int[] {1, 2, 3, 4, 5}, 5));
  }

  public static Stream<Arguments> findLuckyIntegerParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {2, 2, 3, 4}, 2),
        Arguments.of("Example 2", new int[] {1, 2, 2, 3, 3, 3}, 3),
        Arguments.of("Example 3", new int[] {2, 2, 2, 3, 3}, -1));
  }

  public static Stream<Arguments> haveUniqueOccurrencesParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 2, 1, 1, 3}, true),
        Arguments.of("Example 2", new int[] {1, 2}, false),
        Arguments.of("Example 3", new int[] {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}, true));
  }

  public static Stream<Arguments> frequencyShortParams() {
    return Stream.of(Arguments.of("Example 1", "tree", "eert"),
        Arguments.of("Example 2", "cccaaa", "aaaccc"), Arguments.of("Example 3", "Aabb", "bbaA"));
  }

  public static Stream<Arguments> maxSubArrayLengthParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 3, 1, 2, 3, 1, 2}, 2, 6),
        Arguments.of("Example 2", new int[] {1, 2, 1, 2, 1, 2, 1, 2}, 1, 2),
        Arguments.of("Example 3", new int[] {5, 5, 5, 5, 5, 5, 5}, 4, 4),
        Arguments.of("Example 4", new int[] {1}, 1, 1),
        Arguments.of("Example 5", new int[] {1, 11}, 2, 2),
        Arguments.of("Example 6", new int[] {1, 2, 2, 1, 3}, 1, 3));
  }

  public static Stream<Arguments> numberOfGoodPairsParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 3, 1, 1, 3}, 4),
        Arguments.of("Example 2", new int[] {1, 1, 1, 1}, 6),
        Arguments.of("Example 3", new int[] {1, 2, 3}, 0));
  }

  public static Stream<Arguments> subArraysWithSumParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 0, 1, 0, 1}, 2, 4),
        Arguments.of("Example 2", new int[] {0, 0, 0, 0, 0}, 0, 15));
  }

  public static Stream<Arguments> maximumUniqueSubarrayParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {4, 2, 4, 5, 6}, 17),
        Arguments.of("Example 2", new int[] {5, 2, 1, 2, 5, 2, 1, 2, 5}, 8));
  }

  public static Stream<Arguments> containsPermutationParams() {
    return Stream.of(
        Arguments.of("Example 1", "ab", "eidbaooo", true),
        Arguments.of("Example 2", "ab", "eidboaoo", false),
        Arguments.of("Example 3", "adc", "dcda", true),
        Arguments.of("Example 4", "abc", "bbbca", true)
    );
  }

  @BeforeEach
  void setUp() {
    counting = new Counting();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("sumOfUniqueParams")
  void testSumOfUnique(String description, int[] nums, int expected) {
    assertEquals(expected, counting.sumOfUnique(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("totalMaxFrequenciesParams")
  void testTotalMaxFrequencies(String description, int[] nums, int expected) {
    assertEquals(expected, counting.totalMaxFrequencies(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("findLuckyIntegerParams")
  void testFindLuckyInteger(String description, int[] nums, int expected) {
    assertEquals(expected, counting.findLuckyInteger(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("haveUniqueOccurrencesParams")
  void testHaveUniqueOccurrences(String description, int[] nums, boolean expected) {
    assertEquals(expected, counting.haveUniqueOccurrences(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("frequencyShortParams")
  void testFrequencyShort(String description, String s, String expected) {
    assertEquals(expected, counting.frequencyShort(s));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxSubArrayLengthParams")
  void testMaxSubArrayLength(String description, int[] nums, int k, int expected) {
    assertEquals(expected, counting.maxSubArrayLength(nums, k));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("numberOfGoodPairsParams")
  void testNumberOfGoodPairs(String description, int[] nums, int expected) {
    assertEquals(expected, counting.numberOfGoodPairs(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("subArraysWithSumParams")
  void testSubArraysWithSum(String description, int[] nums, int goal, int expected) {
    assertEquals(expected, counting.subArraysWithSum(nums, goal));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maximumUniqueSubarrayParams")
  void testMaximumUniqueSubarray(String description, int[] nums, int expected) {
    assertEquals(expected, counting.maximumUniqueSubarray(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("containsPermutationParams")
  void testContainsPermutation(String description, String s1, String s2, boolean expected) {
    assertEquals(expected, counting.containsPermutation(s1, s2));
  }
}