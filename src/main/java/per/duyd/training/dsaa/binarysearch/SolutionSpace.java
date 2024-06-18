package per.duyd.training.dsaa.binarysearch;

import java.util.Arrays;

public class SolutionSpace {
  public int maxMinSweetnessPiece(int[] sweetness, int k) {
    /*
      ans must be in [0, sum(sweetness) / (k + 1)]
     */
    if (k > sweetness.length) {
      return 0;
    }

    int total = 0;
    for (int s : sweetness) {
      total += s;
    }

    int left = 0, right = total / (k + 1), ans = 0;

    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (isValidMinSweetness(mid, sweetness, k)) {
        ans = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return ans;
  }

  private boolean isValidMinSweetness(int maxSweetness, int[] sweetness, int k) {
    int pieces = 0, currSweetness = 0;

    for (int i = 0; i < sweetness.length && pieces < k + 1; i++) {
      currSweetness += sweetness[i];
      if (currSweetness >= maxSweetness) {
        pieces++;
        currSweetness = 0;
      }
    }

    return pieces == k + 1;
  }

  public int minLargestSubArraySum(int[] nums, int k) {
    /*
    ans must be in [max(num), sum(num)]
     */
    int right = Arrays.stream(nums).sum();
    int left = Arrays.stream(nums).max().orElse(0);

    int ans = left;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (isValidLargestSubArraySum(mid, nums, k)) {
        ans = mid;
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }

    return ans;
  }

  private boolean isValidLargestSubArraySum(int largestSubArraySum, int[] nums, int k) {
    int splitSum = 0, splitCount = 0;
    for (int num : nums) {
      splitSum += num;

      if (splitSum > largestSubArraySum) {
        splitCount++;
        splitSum = num;
      }
    }

    return splitCount + 1 <= k;
  }
}
