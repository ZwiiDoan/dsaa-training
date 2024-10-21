package per.duyd.training.dsaa.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Monotonic {

  public int[] finalPrices(int[] prices) {
    int[] finalPrices = new int[prices.length];
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < prices.length; i++) {
      while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
        int j = stack.pop();
        finalPrices[j] = prices[j] - prices[i];
      }

      stack.push(i);
    }

    while (!stack.isEmpty()) {
      int j = stack.pop();
      finalPrices[j] = prices[j];
    }

    return finalPrices;
  }

  public int validSubArrays(int[] nums) {
    Stack<Integer> stack = new Stack<>();
    int ans = 0;

    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        ans += i - stack.pop();
      }

      stack.push(i);
    }

    while (!stack.isEmpty()) {
      ans += nums.length - stack.pop();
    }

    return ans;
  }

  public int[] mostCompetitiveSubsequence(int[] nums, int k) {
    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < nums.length; i++) {
      while (!stack.isEmpty() && stack.peek() > nums[i] && nums.length - i > k - stack.size()) {
        stack.pop();
      }

      if (stack.size() < k) {
        stack.push(nums[i]);
      }
    }

    return stack.stream().mapToInt(Integer::intValue).toArray();
  }

  public int[] visiblePeopleCounts(int[] heights) {
    Stack<Integer> stack = new Stack<>();
    int[] ans = new int[heights.length];

    for (int i = 0; i < heights.length; i++) {
      while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
        ans[stack.pop()]++;
      }

      if (!stack.isEmpty()) {
        ans[stack.peek()]++;
      }

      stack.push(i);
    }

    return ans;
  }

  /*
  [10,6,8,5,11,9]
  []              - [0,0,0,0,0,0]
  [10]            - [0,0,0,0,0,0]
  [10,6]          - [1,0,0,0,0,0] => ans[stack.peek()]++
  [10,8]          - [2,1,0,0,0,0] => ans[stack.pop()]++; ans[stack.peek()]++
  [10,8,5]        - [2,1,1,0,0,0] => ans[stack.peek()]++
  [11]            - [3,1,2,1,0,0] => ans[stack.pop()]++; ans[stack.pop()]++; ans[stack.pop()]++;
  [11,9]          - [3,1,2,1,1,0] => ans[stack.peek()]++
   */

  public int sumSubarrayMins(int[] arr) {
    int n = arr.length;
    int answer = 0;
    int mod = 100000007;

    for (int left = 0; left < n; ++left) {
      int minVal = arr[left];
      for (int right = left; right < n; ++right) {
        minVal = Math.min(minVal, arr[right]);
        answer = (answer + minVal) % mod;
      }
    }

    return answer;
  }

  public long subArrayRanges(int[] nums) {
    int n = nums.length;
    long answer = 0;

    for (int left = 0; left < n; ++left) {
      int minVal = nums[left], maxVal = nums[left];
      for (int right = left; right < n; ++right) {
        minVal = Math.min(minVal, nums[right]);
        maxVal = Math.max(maxVal, nums[right]);
        answer += maxVal - minVal;
      }
    }

    return answer;
  }

  public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
    int left = 0, right = 0;
    long sum = 0;

    Deque<Integer> deque = new ArrayDeque<>();
    while (right < runningCosts.length) {
      while (!deque.isEmpty() && deque.peekLast() < chargeTimes[right]) {
        deque.pollLast();
      }
      deque.addLast(chargeTimes[right]);
      sum += runningCosts[right];
      right++;

      //"if" keep the max length
      if (!deque.isEmpty() && ((right - left) * sum > (budget - deque.peekFirst()))) {
        sum -= runningCosts[left];
        if (deque.peekFirst() == chargeTimes[left]) {
          deque.pollFirst();
        }
        left++;
      }
    }
    
    return runningCosts.length - left;
  }

}
