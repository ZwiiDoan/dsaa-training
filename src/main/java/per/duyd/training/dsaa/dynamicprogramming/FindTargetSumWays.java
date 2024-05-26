package per.duyd.training.dsaa.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class FindTargetSumWays {
  public int findTargetSumWays(int[] nums, int target) {
    /*
     * Input: nums = [1,1,1,1,1], target = 3
     * ways([1,1,1,1,1], 3) = ways([1,1,1,1], 2) + ways([1,1,1,1], 4)
     * ways([1,1,1,1], 2) = ways([1,1,1], 1) + sways([1,1,1], 3)
     * ways([1,1,1], 1) = ways([1,1], 0) + ways([1,1], 2)
     * ways([1,1], 0) = ways([1], -1) + ways([1], 1)
     * ways([1], -1) = 1, ways([1], 1) = 1
     */

    /*
     * ways(nums, target) = ways(nums.removeLast(), target - numsLast) + ways(nums.removeLast(), target + numsLast)
     * ways([X], Y) = Math.abs(X) == Math.abs(Y) ? 1 : 0
     */

    return findWays(nums, target, new HashMap<>());
  }

  private int findWays(int[] nums, int target, Map<String, Integer> cache) {
    String cacheKey = hashCacheKey(nums, target);
    if (cache.containsKey(cacheKey)) {
      return cache.get(cacheKey);
    }

    int ways;
    if (nums.length == 1) {
      if (target == 0 && nums[0] == 0) {
        ways = 2;
      } else {
        ways = Math.abs(nums[0]) == Math.abs(target) ? 1 : 0;
      }
    } else {
      int[] subNums = new int[nums.length - 1];
      int lastNum = nums[nums.length - 1];
      System.arraycopy(nums, 0, subNums, 0, nums.length - 1);

      int firstWays = findWays(subNums, target - lastNum, cache);
      int secondWays = findWays(subNums, target + lastNum, cache);
      ways = (Math.max(firstWays, 0)) + (Math.max(secondWays, 0));
    }

    cache.put(cacheKey, ways);
    return ways;
  }

  private String hashCacheKey(int[] nums, int target) {
    StringBuilder cacheKey = new StringBuilder();
    for (int num : nums) {
      cacheKey.append(num).append(",");
    }
    return cacheKey.append("=").append(target).toString();
  }
}
