package per.duyd.training.dsaa.sliderpuzzle;

import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;

public class Board {

  private final int[][] tiles;
  private final int N;
  private boolean isGoal;
  private Board twin;

  // create a board from an n-by-n array of tiles,
  // where tiles[row][col] = tile at (row, col)
  public Board(int[][] tiles) {
    validateTiles(tiles);
    N = tiles.length;
    this.tiles = new int[N][N];
    isGoal = true;
    int maxTile = (int) Math.pow(N, 2) - 1;

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        int tile = tiles[row][col];
        this.tiles[row][col] = tile;
        validateTile(tile, maxTile);
        if (tile != N * row + col + 1 && tile != 0) {
          isGoal = false;
        }
      }
    }
  }

  private Board getTwin() {
    int swapRow = 0;
    for (int col = 0; col < N; col++) {
      if (this.tiles[swapRow][col] == 0) {
        swapRow = N - 1;
        break;
      }
    }

    swap(swapRow, 0, swapRow, 1);
    Board twin = new Board(this.tiles);
    swap(swapRow, 1, swapRow, 0);

    return twin;
  }

  private void validateTiles(int[][] tiles) {
    if (tiles == null || tiles.length < 2 || tiles.length > 128) {
      throw new IllegalArgumentException("Invalid board length");
    }
  }

  private void validateTile(int tile, int maxTile) {
    if (tile < 0 || tile > maxTile) {
      throw new IllegalArgumentException("Invalid tile: " + tile);
    }
  }

  // string representation of this board
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(N);

    for (int[] row : this.tiles) {
      stringBuilder.append("\n");
      for (int tile : row) {
        stringBuilder.append(" ").append(tile);
      }
    }

    return stringBuilder.toString();
  }

  // board dimension n
  public int dimension() {
    return N;
  }

  // number of tiles out of place
  public int hamming() {
    int hamming = 0;

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (this.tiles[row][col] != N * row + col + 1 && this.tiles[row][col] != 0) {
          hamming++;
        }
      }
    }

    return hamming;
  }

  // sum of Manhattan distances between tiles and goal
  public int manhattan() {
    int manhattan = 0;

    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        int tile = this.tiles[row][col];
        if (tile != 0) {
          int goalRow = (tile - 1) / N;
          int goalCol = (tile - 1) % N;
          manhattan += Math.abs(goalRow - row) + Math.abs(goalCol - col);
        }
      }
    }

    return manhattan;
  }

  // is this board the goal board?
  public boolean isGoal() {
    return this.isGoal;
  }

  // does this board equal y?
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Board board = (Board) o;

    if (board.tiles.length != N) {
      return false;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (board.tiles[i][j] != this.tiles[i][j]) {
          return false;
        }
      }
    }

    return true;
  }

  // all neighboring boards
  public Iterable<Board> neighbors() {
    List<Board> neighbors = new ArrayList<>();

    int blankCol = 0, blankRow = 0;
    for (int row = 0; row < N; row++) {
      for (int col = 0; col < N; col++) {
        if (this.tiles[row][col] == 0) {
          blankCol = col;
          blankRow = row;
        }
      }
    }

    if (blankCol > 0) {
      neighbors.add(neighbor(blankRow, blankCol, blankRow, blankCol - 1));
    }

    if (blankCol < N - 1) {
      neighbors.add(neighbor(blankRow, blankCol, blankRow, blankCol + 1));
    }

    if (blankRow > 0) {
      neighbors.add(neighbor(blankRow, blankCol, blankRow - 1, blankCol));
    }

    if (blankRow < N - 1) {
      neighbors.add(neighbor(blankRow, blankCol, blankRow + 1, blankCol));
    }

    return neighbors;
  }

  private Board neighbor(int blankRow, int blankCol, int swapRow, int swapCol) {
    swap(blankRow, blankCol, swapRow, swapCol);
    Board neighbor = new Board(this.tiles);
    swap(swapRow, swapCol, blankRow, blankCol);
    return neighbor;
  }

  // a board that is obtained by exchanging any pair of tiles
  public Board twin() {
    if (this.twin == null) {
      this.twin = getTwin();
    }
    return this.twin;
  }

  private void swap(int row1, int col1, int row2, int col2) {
    int tmp = this.tiles[row1][col1];
    this.tiles[row1][col1] = this.tiles[row2][col2];
    this.tiles[row2][col2] = tmp;
  }

  // unit testing (not graded)
  public static void main(String[] args) {
    Board board1 = new Board(new int[][] {
        {8, 1, 3}, {4, 0, 2}, {7, 6, 5}
    });
    StdOut.println("board1: " + board1);

    Board board2 = new Board(new int[][] {
        {1, 2, 3}, {4, 5, 6}, {7, 8, 0}
    });
    StdOut.println("board2: " + board2);

    Board board3 = new Board(new int[][] {
        {8, 1, 3}, {4, 0, 2}, {7, 6, 5}
    });
    StdOut.println("board3: " + board3);

    StdOut.println("board1.equals(board3): " + board1.equals(board3));
    StdOut.println("board1.equals(board2): " + board1.equals(board2));
    StdOut.println("board1.isGoal(): " + board1.isGoal());
    StdOut.println("board2.isGoal(): " + board2.isGoal());
    StdOut.println("board1.twin(): " + board1.twin());
    StdOut.println("board2.twin(): " + board2.twin());
    StdOut.println("board1.hamming(): " + board1.hamming());
    StdOut.println("board2.hamming(): " + board2.hamming());
    StdOut.println("board1.manhattan(): " + board1.manhattan());
    StdOut.println("board2.manhattan(): " + board2.manhattan());
    StdOut.println("board1.neighbors(): " + board1.neighbors());
  }

}