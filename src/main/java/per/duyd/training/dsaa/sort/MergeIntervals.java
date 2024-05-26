package per.duyd.training.dsaa.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

public class MergeIntervals {
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));

    LinkedList<int[]> output = new LinkedList<>();

    for (int[] interval : intervals) {
      if (output.isEmpty() || output.getLast()[1] < interval[0]) {
        output.add(interval);
      } else {
        output.getLast()[1] = Math.max(output.getLast()[1], interval[1]);
      }
    }

    return output.toArray(new int[output.size()][2]);
  }
}
