package per.duyd.training.dsaa.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SlidingWindowTest {

  private SlidingWindow slidingWindow;

  public static Stream<Arguments> testMinSubArrayLenParams() {
    return Stream.of(
        Arguments.of("Example 1: Sum of min sub-array equals to target",
            7, new int[] {2, 3, 1, 2, 4, 3}, 2),
        Arguments.of("Example 2: The array contains the target number", 4, new int[] {1, 4, 4}, 1),
        Arguments.of("Example 3: No valid sub-array exists",
            11, new int[] {1, 1, 1, 1, 1, 1, 1, 1}, 0),
        Arguments.of("Example 4: Sum of min sub-array greater than target",
            11, new int[] {1, 2, 3, 4, 5}, 3)
    );
  }

  public static Stream<Arguments> testMaxVowelsParams() {
    return Stream.of(
        Arguments.of("One substring of length 3 contains 3 vowels", "abciiidef", 3, 3),
        Arguments.of("Any substring of length 3 contains 2 vowels", "leetcode", 3, 2),
        Arguments.of("Any substring of length 2 contains 2 vowels", "aeiou", 2, 2)
    );
  }

  public static Stream<Arguments> testEqualSubstringParams() {
    return Stream.of(
        Arguments.of("Use all maxCost to change a substring", "abcd", "bcdf", 3, 3),
        Arguments.of("Use all maxCost to change 1 character", "abcd", "cdef", 3, 1),
        Arguments.of("Cannot make any change, max substring length is 1", "abcd", "acde", 0, 1)
    );
  }

  @BeforeEach
  void setUp() {
    slidingWindow = new SlidingWindow();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testMinSubArrayLenParams")
  void testMinSubArrayLen(String description, int target, int[] nums, int expected) {
    assertEquals(expected, slidingWindow.minSubArrayLen(target, nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testMaxVowelsParams")
  void testMaxVowels(String description, String s, int k, int expected) {
    assertEquals(expected, slidingWindow.maxVowels(s, k));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testEqualSubstringParams")
  void testEqualSubstring(String description, String s, String t, int maxCost, int expected) {
    assertEquals(expected, slidingWindow.equalSubstring(s, t, maxCost));
  }
}