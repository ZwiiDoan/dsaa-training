package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MaximumSquareTest {
  public static Stream<Arguments> testMaximumSquareParams() {
    return Stream.of(
        Arguments.of("1x1 Matrix", 0, new char[][] {{'0'}}),
        Arguments.of("2x2 Matrix", 1, new char[][] {{'0', '1'}, {'1', '0'}}),
        Arguments.of("4x5 Matrix", 4, new char[][] {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        }),
        Arguments.of("5x5 Matrix", 16,
            new char[][] {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'1', '1', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1'}}
        )
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testMaximumSquareParams")
  void testMaximumSquare(String displayName, int expectedAns, char[][] inputMatrix) {
    assertEquals(expectedAns, new MaximumSquare().maximalSquare(inputMatrix));
  }
}