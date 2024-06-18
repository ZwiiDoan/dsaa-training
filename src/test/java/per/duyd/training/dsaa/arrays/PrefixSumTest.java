package per.duyd.training.dsaa.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PrefixSumTest {

  private PrefixSum prefixSum;

  public static Stream<Arguments> testHighestAltitudeParams() {
    return Stream.of(
        Arguments.of("Example 1", new int[] {-5, 1, 5, 0, -7}, 1),
        Arguments.of("Example 2", new int[] {-4, -3, -2, -1, 4, 3, 2}, 0)
    );
  }

  @BeforeEach
  void setUp() {
    prefixSum = new PrefixSum();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testHighestAltitudeParams")
  void testHighestAltitude(String description, int[] gain, int expected) {
    assertEquals(expected, prefixSum.highestAltitude(gain));
  }
}