package per.duyd.training.dsaa.boggle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BoggleSolver {
  private final EngTrieSET trieSET;

  // Initializes the data structure using the given array of strings as the dictionary.
  // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
  public BoggleSolver(String[] dictionary) {
    // add dictionary into a TrieSET
    this.trieSET = new EngTrieSET();
    Arrays.stream(dictionary).forEach(trieSET::add);
  }

  // Returns the set of all valid words in the given Boggle board, as an Iterable.
  public Iterable<String> getAllValidWords(BoggleBoard board) {
    Set<String> validWords = new HashSet<>();
    boolean[] visited = new boolean[board.rows() * board.cols()];
    StringBuilder prefix = new StringBuilder();

    for (int row = 0; row < board.rows(); row++) {
      for (int col = 0; col < board.cols(); col++) {
        dfs(validWords, board, prefix, visited, row, col);
      }
    }

    return validWords;
  }

  // Returns the score of the given word if it is in the dictionary, zero otherwise.
  // (You can assume the word contains only the uppercase letters A through Z.)
  public int scoreOf(String word) {
    return Optional.ofNullable(word).map(it -> {
      if (it.isEmpty() || !this.trieSET.contains(it)) {
        return 0;
      }

      switch (word.length()) {
        case 1:
        case 2:
          return 0;
        case 3:
        case 4:
          return 1;
        case 5:
          return 2;
        case 6:
          return 3;
        case 7:
          return 5;
        default:
          return 11;
      }
    }).orElse(0);
  }

  private void dfs(Set<String> validWords, BoggleBoard board, StringBuilder prefix,
                   boolean[] visited, int row, int col) {
    if (!isInsideBoard(board, row, col)) {
      return;
    }

    int index = getIndex(row, col, board.cols());

    if (visited[index]) {
      return;
    } else {
      visited[index] = true;
    }

    char currentChar = board.getLetter(row, col);
    prefix.append(currentChar == 'Q' ? "QU" : currentChar);
    String currentWord = prefix.toString();

    EngTrieSET.Node currentNode = this.trieSET.get(currentWord);

    if (currentNode != null) {
      if (currentNode.isString() && currentWord.length() >= 3) {
        validWords.add(currentWord);
      }

      dfs(validWords, board, prefix, visited, row - 1, col - 1);
      dfs(validWords, board, prefix, visited, row - 1, col);
      dfs(validWords, board, prefix, visited, row - 1, col + 1);
      dfs(validWords, board, prefix, visited, row, col - 1);
      dfs(validWords, board, prefix, visited, row, col + 1);
      dfs(validWords, board, prefix, visited, row + 1, col - 1);
      dfs(validWords, board, prefix, visited, row + 1, col);
      dfs(validWords, board, prefix, visited, row + 1, col + 1);
    }

    trackBack(prefix, visited, index);
  }

  private void trackBack(StringBuilder prefix, boolean[] visited, int index) {
    int prefixLength = prefix.length();
    if (prefixLength >= 2 && prefix.charAt(prefixLength - 1) == 'U' &&
        prefix.charAt(prefixLength - 2) == 'Q') {
      prefix.delete(prefixLength - 2, prefixLength);
    } else {
      prefix.deleteCharAt(prefixLength - 1);
    }

    visited[index] = false;
  }

  private int getIndex(int row, int col, int cols) {
    return row * cols + col;
  }

  private boolean isInsideBoard(BoggleBoard board, int row, int col) {
    return 0 <= row && row <= board.rows() - 1 && 0 <= col && col <= board.cols() - 1;
  }

}

