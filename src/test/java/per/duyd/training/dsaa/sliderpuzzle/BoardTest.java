package per.duyd.training.dsaa.sliderpuzzle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {
  @ParameterizedTest
  @ValueSource(ints = {2, 3, 4})
  void testValidBoardConstruction(int n) {
    int[][] tiles = generateNonGoalTiles(n);
    Board board = new Board(tiles);
    assertEquals(n, board.dimension());
    assertFalse(board.isGoal());
    assertTrue(board.hamming() > 0);
    assertTrue(board.manhattan() > 0);
  }

  private int[][] generateNonGoalTiles(int n) {
    int[][] tiles = generateGoalTiles(n);
    tiles[0][0] = 0;
    tiles[n - 1][n - 1] = 1;
    return tiles;
  }

  private int[][] generateGoalTiles(int n) {
    int[][] tiles = new int[n][n];
    for (int row = 0; row < n; row++) {
      for (int col = 0; col < n; col++) {
        tiles[row][col] = row * n + col + 1;
      }
    }
    tiles[n - 1][n - 1] = 0;

    return tiles;
  }

  @Test
  void testInvalidBoardLength() {
    assertThrows(IllegalArgumentException.class, () -> new Board(new int[1][1]));
    assertThrows(IllegalArgumentException.class, () -> new Board(new int[129][129]));
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 13})
  void testInvalidTileValue(int invalidTile) {
    int n = 3;
    int[][] tiles = generateNonGoalTiles(n);
    tiles[0][0] = invalidTile;
    assertThrows(IllegalArgumentException.class, () -> new Board(tiles));
  }

  @Test
  void testGoalBoard() {
    Board board = new Board(generateGoalTiles(3));
    assertTrue(board.isGoal());
  }

  @Test
  void testNonGoalBoard() {
    Board board = new Board(generateNonGoalTiles(3));
    assertFalse(board.isGoal());
  }

  @ParameterizedTest
  @MethodSource("provideBoardsAndExpectedNeighbors")
  void testAllNeighboringBoardsGenerated(Board board, int expectedNeighbors) {
    List<Board> neighbors = new ArrayList<>();
    board.neighbors().forEach(neighbors::add); // Collect neighbors using method reference
    assertEquals(expectedNeighbors, neighbors.size());
  }

  static Stream<Arguments> provideBoardsAndExpectedNeighbors() {
    return Stream.of(
        Arguments.of(new Board(new int[][] {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}}), 3),
        Arguments.of(new Board(new int[][] {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}}), 4),
        Arguments.of(new Board(new int[][] {{1, 2, 0}, {4, 3, 5}, {7, 8, 6}}), 2)
    );
  }

  @Test
  void testTwinBoardDifferent() {
    Board board = new Board(new int[][] {{0, 1}, {2, 3}});
    Board twin = board.twin();
    StdOut.println("twin: " + twin);
    assertNotEquals(board, twin);
    assertEquals(twin.dimension(), 2);
    assertFalse(twin.isGoal());
  }

  @Test
  void testMain() {
    assertDoesNotThrow(() -> Board.main(null));
  }
}