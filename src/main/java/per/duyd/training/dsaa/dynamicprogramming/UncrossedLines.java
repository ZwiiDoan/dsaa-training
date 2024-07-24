package per.duyd.training.dsaa.dynamicprogramming;

import java.util.Arrays;

public class UncrossedLines {
  public int maxUncrossedLines(int[] nums1, int[] nums2) {
    /*
      - state: i, j => function: maxUL(i, j) => ans = maxUL(0, 0)
      - maxUL(i, j):
          if (i == nums1.length || j == nums2.length) => return 0
          else if (nums1[i] == nums2[j]) => return 1 + maxUL(i + 1, j + 1)
          else => return Math.max(maxUL(i + 1, j), maxUL(i, j + 1))
      - cache = int[nums1.length + 1][nums2.length + 1] => int[i][j] = maxUL(i, j)
     */
    int[][] cache = new int[nums1.length + 1][nums2.length + 1];
    for (int[] row : cache) {
      Arrays.fill(row, -1);
    }
    return maxUL(cache, nums1, nums2, 0, 0);
  }

  private int maxUL(int[][] cache, int[] nums1, int[] nums2, int i, int j) {
    if (i == nums1.length || j == nums2.length) {
      return 0;
    } else if (cache[i][j] != -1) {
      return cache[i][j];
    } else if (nums1[i] == nums2[j]) {
      cache[i][j] = 1 + maxUL(cache, nums1, nums2, i + 1, j + 1);
    } else {
      cache[i][j] =
          Math.max(maxUL(cache, nums1, nums2, i, j + 1), maxUL(cache, nums1, nums2, i + 1, j));
    }

    return cache[i][j];
  }

  public int maxUncrossedLinesBottomUp(int[] nums1, int[] nums2) {
    int[][] maxUL = new int[nums1.length + 1][nums2.length + 1];

    for (int i = nums1.length - 1; i >= 0; i--) {
      for (int j = nums2.length - 1; j >= 0; j--) {
        if (nums1[i] == nums2[j]) {
          maxUL[i][j] = maxUL[i + 1][j + 1] + 1;
        } else {
          maxUL[i][j] = Math.max(maxUL[i][j + 1], maxUL[i + 1][j]);
        }
      }
    }

    return maxUL[0][0];
  }
}
