package per.duyd.training.dsaa.dynamicprogramming;

import java.util.Arrays;

/**
 * <p>
 * You want to schedule a list of jobs in d days.<br/>
 * Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j
 * < i).<br/>
 * You have to finish at least one task every day.<br/>
 * The difficulty of a job schedule is the sum of difficulties of each day of the d days.<br/>
 * The difficulty of a day is the maximum difficulty of a job done on that day.<br/>
 * You are given an integer array jobDifficulty and an integer d.<br/>
 * The difficulty of the ith job is jobDifficulty[i].<br/>
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1
 * </p>
 * <p>
 * Example 1:<br/>
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2<br/>
 * Output: 7<br/>
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.<br/>
 * Second day you can finish the last job, total difficulty = 1.<br/>
 * The difficulty of the schedule = 6 + 1 = 7
 * </p>
 * <p>
 * Example 2:<br/>
 * Input: jobDifficulty = [9,9,9], d = 4<br/>
 * Output: -1<br/>
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * </p>
 * <p>
 * Example 3:<br/>
 * Input: jobDifficulty = [1,1,1], d = 3<br/>
 * Output: 3<br/>
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 * </p>
 * <p>
 * Constraints:<br/>
 * 1 <= jobDifficulty.length <= 300<br/>
 * 0 <= jobDifficulty[i] <= 1000<br/>
 * 1 <= d <= 10
 * </p>
 */
public class MinDifficultyJobSchedule {
  public int minDifficulty(int[] jobDifficulty, int d) {
    /*
    - n = jobDifficulty.length;
    - minDifficulty(i,j) with i in [0, d - 1] and j in [0, n - 1]
    - ans = minDifficulty(1, 0)
    - At every day i with remaining jobs starting from j:
      minDifficulty(i,j) = min(
        maxDifficulty(j, j + k) + minDifficulty(i + 1, k + 1)
      ) with k in [0, n - d]
    - maxDifficulty(d, j) = max(j, n - 1 - (d - i))
    - cache = int[d + 1][n + 1]
     */

    /*
    jobDifficulty = [6,5,4,3,2,1], d = 3
    minDifficulty(1,0) = min(
      maxDifficulty(0,0) + minDifficulty(2,1),
      maxDifficulty(0,1) + minDifficulty(2,2),
      maxDifficulty(0,2) + minDifficulty(2,3),
      maxDifficulty(0,3) + minDifficulty(2,4)
    ) => i = 1, j = 0, k = [0,3]
    minDifficulty(2,1) = min (
      max(1,1) + minDifficulty(3,2),
      max(1,2) + minDifficulty(3,3),
      max(1,3) + minDifficulty(3,4),
      max(1,4) + minDifficulty(3,5)
    ) => i = 2, j = 1, k = [0,3]
    minDifficulty(2,2) = min (
      max(2,2) + minDifficulty(3,3),
      max(2,3) + minDifficulty(3,4),
      max(2,4) + minDifficulty(3,5)
    )
    ...
    minDifficulty(3,3) = max(3,5) + minDifficulty(4,6)
    minDifficulty(3,4) = max(4,5) + minDifficulty(4,6)
    minDifficulty(3,5) = max(5,5) + minDifficulty(4,6)
     */

    /*
    jobDifficulty = [1,1,1], d = 3
    minDifficulty(1,0) = maxDifficulty(0,0) + minDifficulty(1,1)
    minDifficulty(1,1) = maxDifficulty(1,1) + minDifficulty(2,2)
    minDifficulty(2,2) = maxDifficulty(2,2) + minDifficulty(3,3)
     */

    if (d > jobDifficulty.length) {
      return -1;
    }

    int[][] cache = new int[d + 1][jobDifficulty.length + 1];
    for (int i = 0; i <= d; i++) {
      Arrays.fill(cache[i], -1);
    }

    return minDifficulty(jobDifficulty, d, 1, 0, cache);
  }

  private int minDifficulty(int[] jobDifficulty, int d, int i, int j, int[][] cache) {
    if (i >= d) {
      cache[i][j] = maxDifficulty(jobDifficulty, j, jobDifficulty.length);
    } else if (cache[i][j] != -1) {
      return cache[i][j];
    } else {
      int dayDifficulty = Integer.MAX_VALUE;
      for (int k = j; k <= jobDifficulty.length - 1 - d + i; k++) {
        dayDifficulty = Math.min(dayDifficulty,
            maxDifficulty(jobDifficulty, j, k + 1) +
                minDifficulty(jobDifficulty, d, i + 1, k + 1, cache));
      }

      cache[i][j] = dayDifficulty;
    }

    return cache[i][j];
  }

  private int maxDifficulty(int[] jobDifficulty, int from, int to) {
    return Arrays.stream(jobDifficulty, from, to).max().orElseThrow();
  }
}
