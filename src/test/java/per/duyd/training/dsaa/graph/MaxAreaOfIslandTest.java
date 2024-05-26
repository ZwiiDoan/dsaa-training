package per.duyd.training.dsaa.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaxAreaOfIslandTest {

  private MaxAreaOfIsland maxAreaOfIsland;

  @BeforeEach
  void beforeEachTest() {
    maxAreaOfIsland = new MaxAreaOfIsland();
  }

  @Test
  void maxAreaOfIsland() {
    assertEquals(4, maxAreaOfIsland.maxAreaOfIsland(
        new int[][] {
            {1, 1, 0, 0, 0},
            {1, 1, 0, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}
        }));
  }
}