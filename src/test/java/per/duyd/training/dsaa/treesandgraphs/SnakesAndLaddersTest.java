package per.duyd.training.dsaa.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SnakesAndLaddersTest {
  @Test
  void shouldFindMinMoves() {
    assertEquals(4, new SnakesAndLadders().snakesAndLadders(
        new int[][] {
            {-1, -1, -1, 46, 47, -1, -1, -1},
            {51, -1, -1, 63, -1, 31, 21, -1},
            {-1, -1, 26, -1, -1, 38, -1, -1},
            {-1, -1, 11, -1, 14, 23, 56, 57},
            {11, -1, -1, -1, 49, 36, -1, 48},
            {-1, -1, -1, 33, 56, -1, 57, 21},
            {-1, -1, -1, -1, -1, -1, 2, -1},
            {-1, -1, -1, 8, 3, -1, 6, 56}
        }
    ));
  }
}