package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoinChangeTest {
  private CoinChange coinChange;

  public static Stream<Arguments> testCoinChangeParams() {
    return Stream.of(
        Arguments.of("Given 3 coins and a valid amount, should return fewest number of coins",
            new int[] {1, 2, 5}, 11, 3),
        Arguments.of("Given 1 coin and an invalid amount, should return -1", new int[] {2}, 3, -1),
        Arguments.of("Given 1 coin and amount = 0, should return 0", new int[] {1}, 0, 0),
        Arguments.of("Given 5 same coins and amount = 5 x coin, should return 5", new int[] {9, 9,
            9, 9, 9}, 45, 5)
    );
  }

  @BeforeEach
  void setUp() {
    coinChange = new CoinChange();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testCoinChangeParams")
  void testCoinChangeTopDown(String description, int[] coins, int amount, int expected) {
    assertEquals(expected, coinChange.coinChangeTopDown(coins, amount));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testCoinChangeParams")
  void testCoinChangeBottomUp(String description, int[] coins, int amount, int expected) {
    assertEquals(expected, coinChange.coinChangeBottomUp(coins, amount));
  }
}