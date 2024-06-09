package per.duyd.training.dsaa.dynamicprogramming;

/**
 * <p>
 * Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 * </p>
 * <p>
 * Example 1:
 * Input: matrix = [
 * ["1","0","1","0","0"],
 * ["1","0","1","1","1"],
 * ["1","1","1","1","1"],
 * ["1","0","0","1","0"]
 * ]
 * Output: 4
 * </p>
 * <p>
 * Example 2:
 * Input: matrix = [["0","1"],["1","0"]]
 * Output: 1
 * </p>
 * <p>
 * Example 3:
 * Input: matrix = [["0"]]
 * Output: 0
 * </p>
 * <p>Constraints:
 * <ul>
 * <li>m == matrix.length</li>
 * <li>n == matrix[i].length</li>
 * <li>1 <= m, n <= 300</li>
 * <li>matrix[i][j] is '0' or '1'.</li>
 * </ul>
 * </p>
 */
public class MaximumSquare {
  public int maximalSquare(char[][] matrix) {
    int maxSquareLength = 0;

    int[][] squareLengthMatrix = new int[matrix.length][matrix[0].length];
    for (int row = 0; row < matrix.length; row++) {
      for (int col = 0; col < matrix[0].length; col++) {
        if (matrix[row][col] == '1') {
          int minSquareLength = getSquareLength(row - 1, col, squareLengthMatrix);
          minSquareLength =
              Math.min(minSquareLength, getSquareLength(row - 1, col - 1, squareLengthMatrix));
          minSquareLength =
              Math.min(minSquareLength, getSquareLength(row, col - 1, squareLengthMatrix));
          squareLengthMatrix[row][col] = minSquareLength + 1;
        } else {
          squareLengthMatrix[row][col] = 0;
        }

        maxSquareLength = Math.max(maxSquareLength, squareLengthMatrix[row][col]);
      }
    }

    return maxSquareLength * maxSquareLength;
  }

  private int getSquareLength(int row, int col, int[][] squareLengthMatrix) {
    if (row < 0 || col < 0) {
      return 0;
    } else {
      return squareLengthMatrix[row][col];
    }
  }
}
