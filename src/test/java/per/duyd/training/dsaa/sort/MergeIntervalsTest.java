package per.duyd.training.dsaa.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MergeIntervalsTest {
  @Test
  public void shouldMergeIntervals() {
    int[][] intervals = new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
    int[][] expected = new int[][] {{1, 6}, {8, 10}, {15, 18}};

    int[][] actual = new MergeIntervals().merge(intervals);

    for (int i = 0; i < expected.length; i++) {
      assertEquals(expected[i][0], actual[i][0]);
      assertEquals(expected[i][1], actual[i][1]);
    }
  }
}