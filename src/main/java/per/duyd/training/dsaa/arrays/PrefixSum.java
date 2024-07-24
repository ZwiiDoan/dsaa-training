package per.duyd.training.dsaa.arrays;

public class PrefixSum {
  public int highestAltitude(int[] gain) {
    int[] altitudes = new int[gain.length + 1];
    altitudes[0] = 0;

    int ans = 0;
    for (int i = 1; i < altitudes.length; i++) {
      altitudes[i] = altitudes[i - 1] + gain[i - 1];
      ans = Math.max(ans, altitudes[i]);
    }

    return ans;
  }

  public int pivotIndex(int[] nums) {
    /*
    [1,7,3,6,5,6]
    [1,8,11,17,22,28]
    T = 28;
    i = 3;
    leftSum = 11
    rightSum = T - nums[i] = 11
    => return i
     */

    int[] prefixSum = new int[nums.length];
    prefixSum[0] = nums[0];
    for (int i = 1; i < nums.length; i++) {
      prefixSum[i] = prefixSum[i - 1] + nums[i];
    }

    for (int i = 0; i < nums.length; i++) {
      int leftSum = i - 1 >= 0 ? prefixSum[i - 1] : 0;
      int rightSum = prefixSum[nums.length - 1] - prefixSum[i];
      if (leftSum == rightSum) {
        return i;
      }
    }

    return -1;
  }

  public static class NumArray {

    private final int[] prefixSum;

    public NumArray(int[] nums) {
      this.prefixSum = new int[nums.length];
      this.prefixSum[0] = nums[0];
      for (int i = 1; i < nums.length; i++) {
        prefixSum[i] = nums[i] + prefixSum[i - 1];
      }
    }

    public int sumRange(int left, int right) {
      if (left == 0) {
        return prefixSum[right];
      } else {
        return prefixSum[right] - prefixSum[left - 1];
      }
    }
  }
}
