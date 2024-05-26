package per.duyd.training.dsaa.dynamicprogramming;

public class MinFallingPathSum {
  public int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;

    if (n == 1) {
      return matrix[0][0];
    }

    //Top-Down
    int[][] cache = new int[n][n];
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        if (row == 0) {
          cache[row][col] = matrix[row][col];
        } else {
          cache[row][col] = -101;
        }
      }
    }


    int minSum = Integer.MAX_VALUE;
    for (int col = 0; col < n; col++) {
      minSum = Math.min(minSum, minPath(n - 1, col, matrix, cache));
    }

    return minSum;
  }

  private int minPath(int row, int col, int[][] matrix, int[][] cache) {
    if (cache[row][col] != -101) {
      return cache[row][col];
    }

    int minSum = Integer.MAX_VALUE;

    if (col > 0) {
      minSum = Math.min(minSum, minPath(row - 1, col - 1, matrix, cache));
    }

    minSum = Math.min(minSum, minPath(row - 1, col, matrix, cache));

    if (col < matrix.length - 1) {
      minSum = Math.min(minSum, minPath(row - 1, col + 1, matrix, cache));
    }

    cache[row][col] = minSum + matrix[row][col];
    return cache[row][col];
  }
}
