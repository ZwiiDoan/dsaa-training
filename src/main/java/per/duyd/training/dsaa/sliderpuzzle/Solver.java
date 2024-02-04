package per.duyd.training.dsaa.sliderpuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Solver {
  private final List<Board> solution;
  private final boolean isSolvable;
  private final int moves;

  // find a solution to the initial board (using the A* algorithm)
  public Solver(Board initial) {
    if (initial == null) {
      throw new IllegalArgumentException("Initial board cannot be null");
    }

    BoardNode initialNode = new BoardNode(null, initial, 0);
    BoardNode twinNode = new BoardNode(null, initial.twin(), 0);

//    MinPQ<BoardNode> minPQ = new MinPQ<>(new BoardNodeComparator());
//    minPQ.insert(initialNode);
//    MinPQ<BoardNode> twinMinPQ = new MinPQ<>(new BoardNodeComparator());
//    twinMinPQ.insert(twinNode);

    PriorityQueue<BoardNode> minPQ = new PriorityQueue<>(new BoardNodeComparator());
    minPQ.add(initialNode);
    PriorityQueue<BoardNode> twinMinPQ = new PriorityQueue<>(new BoardNodeComparator());
    twinMinPQ.add(twinNode);

    BoardNode minNode, twinMinNode;
    while (true) {
      minNode = minPQ.remove();
      if (minNode.board.isGoal()) {
        this.isSolvable = true;
        break;
      }

      twinMinNode = twinMinPQ.remove();
      if (twinMinNode.board.isGoal()) {
        this.isSolvable = false;
        break;
      }

      for (Board neighbor : minNode.board.neighbors()) {
        if (boardHasNotBeenProcessed(neighbor, minNode)) {
          minPQ.add(new BoardNode(minNode, neighbor, minNode.moves + 1));
        }
      }

      for (Board twinNeighbor : twinMinNode.board.neighbors()) {
        if (boardHasNotBeenProcessed(twinNeighbor, twinMinNode)) {
          twinMinPQ.add(new BoardNode(twinMinNode, twinNeighbor, twinMinNode.moves + 1));
        }
      }
    }

    if (this.isSolvable) {
      this.moves = minNode.moves;
      this.solution = new ArrayList<>();
      while (minNode != null) {
        this.solution.add(minNode.board);
        minNode = minNode.previousNode;
      }
      Collections.reverse(this.solution);
    } else {
      this.moves = -1;
      this.solution = null;
    }
  }

  private boolean boardHasNotBeenProcessed(Board neighbor, BoardNode minNode) {
    return minNode.previousNode == null || !minNode.previousNode.board.equals(neighbor);
  }

  private static class BoardNode {
    private final BoardNode previousNode;
    private final int priority;
    private final Board board;
    private final int moves;

    public BoardNode(BoardNode previousNode, Board board, int moves) {
      this.moves = moves;
      this.board = board;
      this.priority = board.manhattan() + moves;
      this.previousNode = previousNode;
    }
  }

  private static class BoardNodeComparator implements Comparator<BoardNode>, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public int compare(BoardNode o1, BoardNode o2) {
      return Integer.compare(o1.priority, o2.priority);
    }
  }

  // is the initial board solvable? (see below)
  public boolean isSolvable() {
    return this.isSolvable;
  }

  // min number of moves to solve initial board; -1 if unsolvable
  public int moves() {
    return this.moves;
  }

  // sequence of boards in a shortest solution; null if unsolvable
  public Iterable<Board> solution() {
    return this.solution;
  }

  // test client (see below)
  public static void main(String[] args) {
    // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        tiles[i][j] = in.readInt();
      }
    }
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable()) {
      StdOut.println("No solution possible");
    } else {
      StdOut.println("Minimum number of moves = " + solver.moves());
      for (Board board : solver.solution()) {
        StdOut.println(board);
      }
    }
  }
}
