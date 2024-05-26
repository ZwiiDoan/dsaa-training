package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FindTargetSumWaysTest {

  @Test
  void shouldFindTargetSumWays() {
    assertEquals(4, new FindTargetSumWays().findTargetSumWays(new int[] {
        0, 0, 1
    }, 1));

    assertEquals(5, new FindTargetSumWays().findTargetSumWays(new int[] {
        1, 1, 1, 1, 1
    }, 3));
  }
}