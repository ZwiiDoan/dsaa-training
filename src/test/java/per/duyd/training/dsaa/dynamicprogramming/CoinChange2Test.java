package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoinChange2Test {

  private CoinChange2 coinChange2;

  public static Stream<Arguments> changeParams() {
    return Stream.of(
        Arguments.of("Example 1", 5, new int[] {1, 2, 5}, 4),
        Arguments.of("Example 2", 3, new int[] {2}, 0),
        Arguments.of("Example 3", 10, new int[] {10}, 1)
    );
  }

  @BeforeEach
  void setUp() {
    coinChange2 = new CoinChange2();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("changeParams")
  void change(String description, int amount, int[] coins, int expected) {
    assertEquals(expected, coinChange2.change(amount, coins));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("changeParams")
  void changeBottomUp(String description, int amount, int[] coins, int expected) {
    assertEquals(expected, coinChange2.changeBottomUp(amount, coins));
  }
}