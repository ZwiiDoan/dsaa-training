package per.duyd.training.dsaa.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GreedyTest {

  private Greedy greedy;

  public static Stream<Arguments> maxIceCreamParams() {
    return Stream.of(
        Arguments.of("Given coins is enough to buy 4 ice creams, should return 4",
            new int[] {1, 3, 2, 4, 1}, 7, 4),
        Arguments.of("Given coins is not enough to buy any ice cream, should return 0",
            new int[] {10, 6, 8, 7, 7, 8}, 5, 0),
        Arguments.of("Given coins is enough to buy all ice creams, should return costs.length",
            new int[] {1, 6, 3, 1, 2, 5}, 20, 6)
    );
  }

  @BeforeEach
  void setUp() {
    greedy = new Greedy();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxIceCreamParams")
  void maxIceCream(String description, int[] costs, int coin, int expected) {
    assertEquals(expected, greedy.maxIceCream(costs, coin));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxIceCreamParams")
  void maxIceCream2(String description, int[] costs, int coin, int expected) {
    assertEquals(expected, greedy.maxIceCream2(costs, coin));
  }
}