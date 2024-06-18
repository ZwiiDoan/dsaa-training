package per.duyd.training.dsaa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GeneralTest {

  private General general;

  public static Stream<Arguments> isIsomorphicParams() {
    return Stream.of(
        Arguments.of("2 Strings are isomorphic", "egg", "add", true),
        Arguments.of("2 Strings are NOT isomorphic", "foo", "bar", false),
        Arguments.of("2 Strings are isomorphic 2", "paper", "title", true),
        Arguments.of("2 Strings are NOT isomorphic 2", "ggee", "adad", false),
        Arguments.of("2 Strings are NOT isomorphic 3", "badc", "baba", false)
    );
  }

  @BeforeEach
  void setUp() {
    general = new General();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isIsomorphicParams")
  void isIsomorphic(String description, String s, String t, boolean expected) {
    assertEquals(expected, general.isIsomorphic(s, t));
  }
}