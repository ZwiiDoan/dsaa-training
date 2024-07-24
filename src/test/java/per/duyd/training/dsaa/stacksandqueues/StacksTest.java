package per.duyd.training.dsaa.stacksandqueues;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StacksTest {

  private Stacks stacks;

  public static Stream<Arguments> simplifyPathParams() {
    return Stream.of(
        Arguments.of("should remove trailing slash", "/home/", "/home"),
        Arguments.of("should replace multiple consecutive slashes by a single one", "/home//foo",
            "/home/foo"),
        Arguments.of("should use parent directory for double period ..",
            "/home/user/Documents/../Pictures", "/home/user/Pictures"),
        Arguments.of("should not go one level up from root directory", "/../", "/"),
        Arguments.of("should treat ... as a valid directory", "/.../a/../b/c/../d/./", "/.../b/d")
    );
  }

  public static Stream<Arguments> removeStarsParams() {
    return Stream.of(
        Arguments.of("should remove stars in the middle of the string", "leet**cod*e", "lecoe"),
        Arguments.of("should remove stars at the end of the string", "erase*****", "")
    );
  }

  public static Stream<Arguments> robotWithStringParams() {
    return Stream.of(
        Arguments.of("Example 1", "zza", "azz"),
        Arguments.of("Example 2", "bac", "abc"),
        Arguments.of("Example 3", "bdda", "addb"),
        Arguments.of("Example 4", "bydizfve", "bdevfziy"),
        Arguments.of("Example 5", "mmuqezwmomeplrtskz", "eekstrlpmomwzqummz")
    );
  }

  @BeforeEach
  void setUp() {
    stacks = new Stacks();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("simplifyPathParams")
  void simplifyPath(String description, String path, String expected) {
    assertEquals(expected, stacks.simplifyPath(path));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("removeStarsParams")
  void removeStars(String description, String s, String expected) {
    assertEquals(expected, stacks.removeStars(s));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("robotWithStringParams")
  void robotWithString(String description, String s, String expected) {
    assertEquals(expected, stacks.robotWithString(s));
  }
}