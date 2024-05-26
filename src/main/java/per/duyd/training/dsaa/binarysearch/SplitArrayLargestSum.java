package per.duyd.training.dsaa.binarysearch;

import java.util.Arrays;

public class SplitArrayLargestSum {
  public int splitArray(int[] nums, int k) {
    int right = Arrays.stream(nums).sum();
    int left = Arrays.stream(nums).max().orElse(0);

    int ans = left;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (isValidSplit(mid, nums, k)) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return ans;
  }

  private boolean isValidSplit(int maxSum, int[] nums, int k) {
    int splitSum = 0;
    int splitCount = 0;
    for (int num : nums) {
      if (splitSum + num <= maxSum) {
        splitSum += num;
      } else {
        splitCount++;
        splitSum = num;
      }
    }

    return splitCount + 1 <= k;
  }
}
