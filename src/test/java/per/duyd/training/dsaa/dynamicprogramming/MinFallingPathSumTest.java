package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinFallingPathSumTest {
  @Test
  void shouldReturnMinFallingPathSum() {
    int[][] matrix = new int[][] {
        {-1, 84, -36, 0, 0, 34},
        {-60, 22, -57, -32, 93, 80},
        {64, -93, -71, -49, 57, 69},
        {17, 44, 62, -87, -83, 74},
        {-93, -55, -92, -97, 69, -34},
        {-68, -71, 31, 94, 1, 81}
    };

    assertEquals(-414, new MinFallingPathSum().minFallingPathSum(matrix));
  }
}