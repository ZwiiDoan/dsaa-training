package per.duyd.training.dsaa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GeneralTest {

  private General general;

  public static Stream<Arguments> testIsomorphicParams() {
    return Stream.of(
        Arguments.of("2 Strings are isomorphic", "egg", "add", true),
        Arguments.of("2 Strings are NOT isomorphic", "foo", "bar", false),
        Arguments.of("2 Strings are isomorphic 2", "paper", "title", true),
        Arguments.of("2 Strings are NOT isomorphic 2", "ggee", "adad", false),
        Arguments.of("2 Strings are NOT isomorphic 3", "badc", "baba", false)
    );
  }

  public static Stream<Arguments> testFollowsPatternParams() {
    return Stream.of(
        Arguments.of("String s follows pattern 1", "abba", "dog cat cat dog", true),
        Arguments.of("String s does not follow pattern 1", "abba", "dog cat cat fish", false),
        Arguments.of("String s does not follow pattern 2", "aaaa", "dog cat cat dog", false),
        Arguments.of("String s does not follow pattern 3", "abba", "dog dog dog dog", false),
        Arguments.of("String s follows pattern 2",
            "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccdd",
            "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t",
            true)
    );
  }

  public static Stream<Arguments> testCustomSortStringParams() {
    return Stream.of(
        Arguments.of("Example 1", "cba", "abcd", List.of("cbad", "dcba", "cdba", "cbda")),
        Arguments.of("Example 2", "bcafg", "abcd", List.of("bcad", "dbca", "bdca", "bcda"))
    );
  }

  public static Stream<Arguments> testCloseStringsParams() {
    return Stream.of(
        Arguments.of("Example 1", "abc", "bca", true),
        Arguments.of("Example 2", "a", "aa", false),
        Arguments.of("Example 3", "cabbba", "abbccc", true)
    );
  }

  @BeforeEach
  void setUp() {
    general = new General();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testIsomorphicParams")
  void testIsIsomorphic(String description, String s, String t, boolean expected) {
    assertEquals(expected, general.isIsomorphic(s, t));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testFollowsPatternParams")
  void testFollowsPattern(String description, String pattern, String s, boolean expected) {
    assertEquals(expected, general.followsPattern(pattern, s));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testCustomSortStringParams")
  void testCustomSortString(String description, String order, String s,
                            List<String> expectedValues) {
    assertTrue(expectedValues.contains(general.customSortString(order, s)));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testCloseStringsParams")
  void testCloseStrings(String description, String word1, String word2, boolean expected) {
    assertEquals(expected, general.closeString(word1, word2));
  }
}