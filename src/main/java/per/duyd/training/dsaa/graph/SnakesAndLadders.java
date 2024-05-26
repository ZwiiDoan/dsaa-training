package per.duyd.training.dsaa.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
  public int snakesAndLadders(int[][] board) {
    int boardSize = board.length;
    int endIndex = boardSize * boardSize;
    boolean[] seen = new boolean[endIndex + 1];

    Queue<int[]> queue = new LinkedList<>();
    seen[1] = true;
    queue.offer(new int[] {1, 0});//{index, steps}

    while (!queue.isEmpty()) {
      int[] square = queue.poll();

      if (square[0] == endIndex) {
        return square[1];
      }

      int maxIndex = Math.min(square[0] + 6, endIndex);

      for (int nextIndex = square[0] + 1; nextIndex <= maxIndex; nextIndex++) {
        int[] nextRowCol = getRowCol(nextIndex, boardSize);
        int followIndex = board[nextRowCol[0]][nextRowCol[1]];
        int destIndex = followIndex == -1 ? nextIndex : followIndex;

        if (!seen[destIndex]) {
          seen[destIndex] = true;
          queue.offer(new int[] {destIndex, square[1] + 1});
        }
      }
    }

    return -1;
  }

  private int[] getRowCol(int index, int boardSize) {
    int rowOffset = (index - 1) / boardSize;
    int colOffset = (index - 1) % boardSize;

    int row = boardSize - rowOffset - 1;
    int col = rowOffset % 2 == 0 ? colOffset : boardSize - 1 - colOffset;

    return new int[] {row, col};
  }
}
