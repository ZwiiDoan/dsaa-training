package per.duyd.training.dsaa.treesandgraphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graphs {
  public int findJudge(int n, int[][] trust) {
    if (n == 1 && (trust == null || trust.length == 0)) {
      return n;
    }

    int[] inDegrees = new int[n + 1];
    int[] outDegrees = new int[n + 1];

    for (int[] t : trust) {
      inDegrees[t[1]]++;
      outDegrees[t[0]]++;
    }

    for (int i = 1; i < n + 1; i++) {
      if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
        return i;
      }
    }

    return -1;
  }

  public int maximalNetworkRank(int n, int[][] roads) {
    if (roads == null || roads.length == 0) {
      return 0;
    }

    Map<Integer, Set<Integer>> citiesMap = new HashMap<>();

    for (int[] r : roads) {
      citiesMap.computeIfAbsent(r[0], key -> new HashSet<>()).add(r[1]);
      citiesMap.computeIfAbsent(r[1], key -> new HashSet<>()).add(r[0]);
    }

    int maxRank = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        int totalRank =
            citiesMap.getOrDefault(i, Set.of()).size() + citiesMap.getOrDefault(j, Set.of()).size();
        if (citiesMap.getOrDefault(i, Set.of()).contains(j)) {
          totalRank--;
        }

        if (totalRank > maxRank) {
          maxRank = totalRank;
        }
      }
    }

    return maxRank;
  }

  private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

  public int islandPerimeter(int[][] grid) {
    Queue<int[]> queue = new ArrayDeque<>();
    boolean[][] seen = new boolean[grid.length][grid[0].length];
    boolean foundFirstLand = false;
    int perimeter = 0;

    for (int row = 0; row < grid.length && !foundFirstLand; row++) {
      for (int col = 0; col < grid[0].length && !foundFirstLand; col++) {
        if (grid[row][col] == 1) {
          queue.offer(new int[] {row, col});
          seen[row][col] = true;
          foundFirstLand = true;
          perimeter = 4;
        }
      }
    }

    while (!queue.isEmpty()) {
      int[] cell = queue.poll();

      for (int[] d : DIRECTIONS) {
        int nextRow = cell[0] + d[0];
        int nextCol = cell[1] + d[1];

        if (isValidCell(nextRow, nextCol, grid.length, grid[0].length) && !seen[nextRow][nextCol] &&
            grid[nextRow][nextCol] == 1) {
          perimeter = perimeter - 1 + 3;
          seen[nextRow][nextCol] = true;
          queue.offer(new int[] {nextRow, nextCol});
        }
      }
    }

    return perimeter;
  }

  private boolean isValidCell(int row, int col, int maxRow, int maxCol) {
    return row >= 0 && row < maxRow && col >= 0 && col < maxCol;
  }

  public int numEnclaves(int[][] grid) {
    int maxRow = grid.length, maxCol = grid[0].length;
    boolean[][] seen = new boolean[maxRow][maxCol];
    Stack<int[]> stack = new Stack<>();

    int ans = 0;

    for (int i = 1; i < maxRow; i++) {
      for (int j = 1; j < maxCol; j++) {
        if (!seen[i][j] && grid[i][j] == 1) {
          seen[i][j] = true;
          stack.push(new int[] {i, j});
          ans += dfsEnclaves(seen, stack, grid);
        }
      }
    }

    return ans;
  }

  private int dfsEnclaves(boolean[][] seen, Stack<int[]> stack, int[][] grid) {
    int maxRow = grid.length, maxCol = grid[0].length;
    boolean isValidEnclaves = true;
    int enclaves = 1;

    while (!stack.isEmpty()) {
      int[] cell = stack.pop();
      int row = cell[0];
      int col = cell[1];

      for (int[] direction : DIRECTIONS) {
        int nextRow = row + direction[0];
        int nextCol = col + direction[1];

        if (!isValidCell(nextRow, nextCol, maxRow, maxCol)) {
          isValidEnclaves = false;
        } else if (!seen[nextRow][nextCol] && grid[nextRow][nextCol] == 1) {
          seen[nextRow][nextCol] = true;
          stack.push(new int[] {nextRow, nextCol});
          enclaves++;
        }
      }
    }

    return isValidEnclaves ? enclaves : 0;
  }
}
