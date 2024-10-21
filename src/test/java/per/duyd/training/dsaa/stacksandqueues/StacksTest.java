package per.duyd.training.dsaa.stacksandqueues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class StacksTest {

  private Stacks stacks;

  public static Stream<Arguments> simplifyPathParams() {
    return Stream.of(Arguments.of("should remove trailing slash", "/home/", "/home"),
        Arguments.of("should replace multiple consecutive slashes by a single one", "/home//foo",
            "/home/foo"), Arguments.of("should use parent directory for double period ..",
            "/home/user/Documents/../Pictures", "/home/user/Pictures"),
        Arguments.of("should not go one level up from root directory", "/../", "/"),
        Arguments.of("should treat ... as a valid directory", "/.../a/../b/c/../d/./", "/.../b/d"));
  }

  public static Stream<Arguments> removeStarsParams() {
    return Stream.of(
        Arguments.of("should remove stars in the middle of the string", "leet**cod*e", "lecoe"),
        Arguments.of("should remove stars at the end of the string", "erase*****", ""));
  }

  public static Stream<Arguments> robotWithStringParams() {
    return Stream.of(Arguments.of("Example 1", "zza", "azz"),
        Arguments.of("Example 2", "bac", "abc"), Arguments.of("Example 3", "bdda", "addb"),
        Arguments.of("Example 4", "bydizfve", "bdevfziy"),
        Arguments.of("Example 5", "mmuqezwmomeplrtskz", "eekstrlpmomwzqummz"));
  }

  public static Stream<Arguments> validateStackSequencesParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {1, 2, 3, 4, 5}, new int[] {4, 5, 3, 2, 1}, true),
        Arguments.of("Example 1", new int[] {1, 2, 3, 4, 5}, new int[] {4, 3, 5, 1, 2}, false));
  }

  public static Stream<Arguments> asteroidCollisionParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {5, 10, -5}, new int[] {5, 10}),
        Arguments.of("Example 2", new int[] {8, -8}, new int[] {}),
        Arguments.of("Example 3", new int[] {10, 2, -5}, new int[] {10}));
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

  @ParameterizedTest(name = "{0}")
  @MethodSource("validateStackSequencesParams")
  void validateStackSequences(String description, int[] pushed, int[] popped, boolean expected) {
    assertEquals(expected, stacks.validateStackSequences(pushed, popped));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("asteroidCollisionParams")
  void asteroidCollision(String description, int[] asteroids, int[] expected) {
    assertIterableEquals(Arrays.stream(expected).boxed().toList(),
        Arrays.stream(stacks.asteroidCollision(asteroids)).boxed().toList());
  }

  @Test
  void minStackTest() {
    Stacks.MinStack minStack = new Stacks.MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    assertEquals(-3, minStack.getMin());
    minStack.pop();
    assertEquals(0, minStack.top());
    assertEquals(-2, minStack.getMin());
  }

  @Test
  void maxStackTest() {
    Stacks.MaxStack maxStack = new Stacks.MaxStack();
    maxStack.push(-2);
    maxStack.push(0);
    maxStack.push(-3);
    assertEquals(0, maxStack.getMax());
    maxStack.pop();
    assertEquals(0, maxStack.top());
    assertEquals(0, maxStack.getMax());
  }
}