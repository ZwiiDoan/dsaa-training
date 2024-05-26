package per.duyd.training.dsaa.intervals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class InsertIntervalTest {
  @Test
  void shouldInsertInterval() {
    int[][] expected = new int[][] {
        {1, 8}
    };

    int[][] actual = new InsertInterval().insert(new int[][] {
        {1, 5},
        {6, 8}
    }, new int[] {5, 6});

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(expected[i][j], actual[i][j]);
      }
    }
  }
}