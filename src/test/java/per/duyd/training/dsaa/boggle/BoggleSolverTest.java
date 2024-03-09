package per.duyd.training.dsaa.boggle;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BoggleSolverTest {

  @ParameterizedTest
  @CsvSource({
      "boggle/dictionary-algs4.txt,boggle/board4x4.txt,29,33",
      "boggle/dictionary-algs4.txt,boggle/board-q.txt,29,84",
      "boggle/dictionary-common.txt,boggle/board5x10.txt,350,572"
  })
  void shouldGetAllValidWordsAndScore(String dictionaryFile, String boardFile, int expectedWords,
                                      int expectedScore) {
    BoggleSolver boggleSolver =
        new BoggleSolver(new In(dictionaryFile).readAllStrings());
    BoggleBoard board = new BoggleBoard(boardFile);
    int actualWords = 0;
    int actualScore = 0;
    for (String word : boggleSolver.getAllValidWords(board)) {
      StdOut.println(word);
      actualScore += boggleSolver.scoreOf(word);
      actualWords++;
    }

    assertEquals(expectedWords, actualWords);
    assertEquals(expectedScore, actualScore);
  }

  @ParameterizedTest
  @CsvSource({
      "boggle/board-dichlorodiphenyltrichloroethanes.txt,27,68",
      "boggle/board4x4.txt,204,281"
  })
  void shouldHandleBigDictionary(String boardFile, int expectedWords, int expectedScore) {
    BoggleSolver boggleSolver =
        new BoggleSolver(new In("boggle/dictionary-yawl.txt").readAllStrings());
    BoggleBoard board = new BoggleBoard(boardFile);
    int actualWords = 0;
    int actualScore = 0;
    for (String word : boggleSolver.getAllValidWords(board)) {
      StdOut.println(word);
      actualScore += boggleSolver.scoreOf(word);
      actualWords++;
    }

    assertEquals(expectedWords, actualWords);
    assertEquals(expectedScore, actualScore);
  }
}