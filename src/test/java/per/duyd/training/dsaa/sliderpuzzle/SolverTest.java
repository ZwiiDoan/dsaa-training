package per.duyd.training.dsaa.sliderpuzzle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class SolverTest {

  @Test
  void testMain() {
    assertDoesNotThrow(() -> Solver.main(new String[] {"8puzzle/puzzle04.txt"}));
  }

  @Test
  void testSolvableBoard() {
    Board board = new Board(new int[][] {
        {0, 1, 3}, {4, 2, 5}, {7, 8, 6}
    });

    Solver solver = new Solver(board);

    assertTrue(solver.isSolvable());
    assertEquals(4, solver.moves());

    List<Board> solution = new ArrayList<>();
    solver.solution().forEach(solution::add);

    assertEquals(5, solution.size());
    assertTrue(solution.containsAll(new ArrayList<Board>() {{
      add(new Board(new int[][] {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}}));
      add(new Board(new int[][] {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}}));
      add(new Board(new int[][] {{1, 2, 3}, {4, 0, 5}, {7, 8, 6}}));
      add(new Board(new int[][] {{1, 2, 3}, {4, 5, 0}, {7, 8, 6}}));
      add(new Board(new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}));
    }}));
  }

  @Test
  void testNotSolvableBoard() {
    Board twin = new Board(new int[][] {
        {0, 3, 1}, {4, 2, 5}, {7, 8, 6}
    });

    Solver solver = new Solver(twin);

    assertFalse(solver.isSolvable());
    assertEquals(-1, solver.moves());
    assertNull(solver.solution());
  }
}