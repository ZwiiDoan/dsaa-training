package per.duyd.training.dsaa.intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InsertInterval {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> ans = new ArrayList<>();

    if (intervals.length == 0) {
      return new int[][] {newInterval};
    } else if (intervals[0][0] > newInterval[1]) {
      ans.add(newInterval);
      Collections.addAll(ans, intervals);
      return ans.toArray(new int[ans.size()][2]);
    } else if (intervals[intervals.length - 1][1] < newInterval[0]) {
      Collections.addAll(ans, intervals);
      ans.add(newInterval);
      return ans.toArray(new int[ans.size()][2]);
    }

    int[] mergedInterval = newInterval;

    for (int i = 0; i < intervals.length; i++) {
      if (intervals[i][1] < mergedInterval[0]) {
        ans.add(intervals[i]);
      } else if (intervals[i][0] > mergedInterval[1]) {
        ans.add(intervals[i]);
      } else {
        mergedInterval = new int[] {Math.min(intervals[i][0], newInterval[0]),
            Math.max(intervals[i][1], newInterval[1])};

        for (int j = i + 1; j < intervals.length; j++) {
          if (mergedInterval[1] < intervals[j][0]) {
            i = j - 1;
            break;
          } else {
            mergedInterval[1] = Math.max(mergedInterval[1], intervals[j][1]);
            i = j;
          }
        }

        ans.add(mergedInterval);
      }
    }

    return ans.toArray(new int[ans.size()][2]);
  }
}
