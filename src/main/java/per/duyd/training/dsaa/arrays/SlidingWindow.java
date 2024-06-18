package per.duyd.training.dsaa.arrays;

import java.util.Set;

public class SlidingWindow {
  public int minSubArrayLen(int target, int[] nums) {
    /*
    Example 1:
      Input: target = 7, nums = [2,3,1,2,4,3]
      Output: 2
      Explanation: The subarray [4,3] has the minimal length under the problem constraint.

    Constraints:
      1 <= target <= 10^9
      1 <= nums.length <= 10^5
      1 <= nums[i] <= 10^4

    Walkthrough: Sliding Window
      [2] = 2 < 7
      [2,3] = 5 < 7
      [2,3,1] = 6 < 7
      [2,3,1,2] = 8 > 7
      [3,1,2] = 6 < 7
      [3,1,2,4] = 10 > 7 => currMin = 4
      [1,2,4] = 7 == 7 => currMin = 3
      [1,2,4,3] = 9 > 7
      [2,4,3] = 9 > 7
      [4,3] = 7 == 7 => currMin = 2
      [3] = 3 < 7
     */

    int start = 0, ans = Integer.MAX_VALUE, currSum = 0;

    for (int end = 0; end < nums.length; end++) {
      currSum += nums[end];

      while (currSum >= target) {
        ans = Math.min(ans, end - start + 1);
        currSum -= nums[start];
        start++;
      }
    }

    return ans == Integer.MAX_VALUE ? 0 : ans;
  }

  public int maxVowels(String s, int k) {
    /*
      Example 1:
        Input: s = "abciiidef", k = 3
        Output: 3
        Explanation: The substring "iii" contains 3 vowel letters.
        Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
      Constraints:
        1 <= s.length <= 105
        s consists of lowercase English letters.
        1 <= k <= s.length
      Walkthrough: Sliding Window
        [a,b,c] => ans = 1
        [b,c,i]
        ...
        [i,i,i] => ans = 3
        ...
        [d,e,f]
     */

    int currVowels = 0;
    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    for (int end = 0; end < k; end++) {
      if (vowels.contains(s.charAt(end))) {
        currVowels++;
      }
    }

    int start = 0, ans = currVowels;

    for (int end = k; end < s.length(); end++) {
      if (vowels.contains(s.charAt(start))) {
        currVowels--;
      }

      start++;

      if (vowels.contains(s.charAt(end))) {
        currVowels++;
      }

      ans = Math.max(ans, currVowels);
    }

    return ans;
  }

  public int equalSubstring(String s, String t, int maxCost) {
    /*
    Example 1:
      Input: s = "abcd", t = "bcdf", maxCost = 3
      Output: 3
      Explanation: "abc" of s can change to "bcd".
      That costs 3, so the maximum length is 3.
    Constraints:
      1 <= s.length <= 10^5
      t.length == s.length
      0 <= maxCost <= 10^6
      s and t consist of only lowercase English letters.
    Walkthrough:
      - Go from left to right and build a sliding window restricted by maxCost
      - Track the window length for new max length everytime the window increase in size
     */

    int start = 0, maxLength = 0, currCost = 0;

    for (int end = 0; end < t.length(); end++) {
      currCost += getCost(s.charAt(end), t.charAt(end));

      while (currCost > maxCost) {
        currCost -= getCost(s.charAt(start), t.charAt(start));
        start++;
      }

      maxLength = Math.max(maxLength, end - start + 1);
    }

    return maxLength;
  }

  private int getCost(char x, char y) {
    return Math.abs(x - y);
  }
}
