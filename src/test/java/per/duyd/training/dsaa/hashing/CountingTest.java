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
    return Stream.of(
        Arguments.of("Example 1", new int[] {1, 2, 2, 3, 1, 4}, 4),
        Arguments.of("Example 2", new int[] {1, 2, 3, 4, 5}, 5)
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
}