package per.duyd.training.dsaa.treesandgraphs;

import java.util.Stack;

public class MaxAreaOfIsland {
  private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

  public int maxAreaOfIsland(int[][] grid) {
    int height = grid.length;
    int width = grid[0].length;
    boolean[] seen = new boolean[width * height];
    int maxArea = 0;

    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        if (isLand(row, col, seen, grid)) {
          maxArea = Math.max(maxArea, dfs(row, col, grid, seen));
        }
      }
    }

    return maxArea;
  }

  private int dfs(int row, int col, int[][] grid, boolean[] seen) {
    Stack<Integer> stack = new Stack<>();
    int width = grid[0].length;

    stack.push(getCell(row, col, width));
    seen[getCell(row, col, width)] = true;
    int area = 1;

    while (!stack.isEmpty()) {
      int cell = stack.pop();

      for (int[] direction : DIRECTIONS) {
        int nextRow = cell / width + direction[0];
        int nextCol = cell % width + direction[1];

        if (isLand(nextRow, nextCol, seen, grid)) {
          stack.push(getCell(nextRow, nextCol, width));
          seen[getCell(nextRow, nextCol, width)] = true;
          area++;
        }
      }
    }

    return area;
  }

  private boolean isLand(int row, int col, boolean[] seen, int[][] grid) {
    return 0 <= row && row < grid.length && 0 <= col && col < grid[0].length &&
        grid[row][col] == 1 && !seen[getCell(row, col, grid[0].length)];
  }

  private int getCell(int row, int col, int width) {
    return row * width + col;
  }
}
