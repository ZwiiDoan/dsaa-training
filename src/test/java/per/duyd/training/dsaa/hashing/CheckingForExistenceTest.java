package per.duyd.training.dsaa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CheckingForExistenceTest {
  private CheckingForExistence checkingForExistence;

  public static Stream<Arguments> testContainsDuplicateParams() {
    return Stream.of(Arguments.of("Array contains duplicate", new int[] {1, 2, 3, 1}, true),
        Arguments.of("Array does NOT contain duplicate", new int[] {1, 2, 3, 4}, false),
        Arguments.of("Array contains duplicate 2", new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true));
  }

  public static Stream<Arguments> testDestinationCityParams() {
    return Stream.of(Arguments.of("Example 1",
            new String[][] {{"London", "New York"}, {"New York", "Lima"}, {"Lima", "Sao Paulo"}},
            "Sao Paulo"),
        Arguments.of("Example 2", new String[][] {{"B", "C"}, {"D", "B"}, {"C", "A"}}, "A"),
        Arguments.of("Example 3", new String[][] {{"A", "Z"}}, "Z"));
  }

  public static Stream<Arguments> testIsPathCrossingParams() {
    return Stream.of(
        Arguments.of("Example 1", "NES", false),
        Arguments.of("Example 2", "NESWW", true)
    );
  }

  @BeforeEach
  void setUp() {
    checkingForExistence = new CheckingForExistence();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testContainsDuplicateParams")
  void testContainsDuplicate(String description, int[] nums, boolean expected) {
    assertEquals(expected, checkingForExistence.containsDuplicate(nums));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testDestinationCityParams")
  void testDestinationCity(String description, String[][] paths, String expected) {
    assertEquals(expected, checkingForExistence.destinationCity(paths));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testIsPathCrossingParams")
  void testIsPathCrossing(String description, String path, boolean expected) {
    assertEquals(expected, checkingForExistence.isPathCrossing(path));
  }
}