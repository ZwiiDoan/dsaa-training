package per.duyd.training.dsaa.arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TwoPointersTest {

  private TwoPointers twoPointers;

  public static Stream<Arguments> testReverseWordsParams() {
    return Stream.of(Arguments.of(
        "Given a long sentence, should reverse every words in the sentence while still preserving whitespace and initial word order",
        "Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc"), Arguments.of(
        "Given a full name, should reverse every words in the name while still preserving whitespace and initial word order",
        "Mr Ding", "rM gniD"));
  }

  public static Stream<Arguments> testReverseEnglishLettersParams() {
    return Stream.of(Arguments.of(
        "Given a short word with 1 non English char and lowercase English chars, " +
            "should reverse English chars", "ab-cd", "dc-ba"), Arguments.of(
        "Given a word with multiple non English and English chars 1, should reverse " +
            "English chars", "a-bC-dEf-ghIj", "j-Ih-gfE-dCba"), Arguments.of(
        "Given a word with multiple non English and English chars 2, should reverse " +
            "English chars", "Test1ng-Leet=code-Q!", "Qedo1ct-eeLg=ntse-T!"));
  }

  public static Stream<Arguments> testMinCommonNumberParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 2, 3}, new int[] {2, 4}, 2),
        Arguments.of("Example 2", new int[] {1, 2, 3, 6}, new int[] {2, 3, 4, 5}, 2),
        Arguments.of("Early Termination", new int[] {1, 3, 4}, new int[] {2, 5, 6, 7}, -1)
    );
  }

  public static Stream<Arguments> testMoveZerosParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {0, 1, 0, 3, 12}, new int[] {1, 3, 12, 0, 0}),
        Arguments.of("Example 2", new int[] {0}, new int[] {0})
    );
  }

  public static Stream<Arguments> testReversePrefixParams() {
    return Stream.of(
        Arguments.of("Example 1: Reverse part of word from 0 to 3", "abcdefd", 'd', "dcbaefd"),
        Arguments.of("Example 2: Reverse part of word from 0 to 3", "xyxzxe", 'z', "zxyxxe"),
        Arguments.of("Example 3: Do nothing", "abcd", 'z', "abcd")
    );
  }

  @BeforeEach
  void setUp() {
    twoPointers = new TwoPointers();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testReverseWordsParams")
  void testReverseWords(String description, String input, String expected) {
    assertEquals(expected, twoPointers.reverseWords(input));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testReverseEnglishLettersParams")
  void testReverseEnglishLetters(String description, String input, String expected) {
    assertEquals(expected, twoPointers.reverseEnglishLetters(input));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testMinCommonNumberParams")
  void testMinCommonNumber(String description, int[] nums1, int[] nums2, int expected) {
    assertEquals(expected, twoPointers.minCommonNumber(nums1, nums2));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testMoveZerosParams")
  void testMoveZeros(String description, int[] input, int[] expected) {
    twoPointers.moveZeros(input);
    assertArrayEquals(expected, input);
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testReversePrefixParams")
  void testReversePrefix(String description, String input, char ch, String expected) {
    assertEquals(expected, twoPointers.reversePrefix(input, ch));
  }
}