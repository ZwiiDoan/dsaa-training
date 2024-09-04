package per.duyd.training.dsaa.hashing;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Counting {

  public int sumOfUnique(int[] nums) {
    Map<Integer, Integer> numCounts = new HashMap<>();

    for (int num : nums) {
      numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
    }

    return numCounts.entrySet().stream().filter(entry -> entry.getValue() == 1)
        .mapToInt(Map.Entry::getKey).sum();
  }

  public int totalMaxFrequencies(int[] nums) {
    int maxFrequency = 0;

    int[] frequencies = new int[101];

    for (int num : nums) {
      frequencies[num]++;
      maxFrequency = Math.max(maxFrequency, frequencies[num]);
    }

    int totalMaxFrequencies = 0;
    for (int frequency : frequencies) {
      totalMaxFrequencies += frequency == maxFrequency ? frequency : 0;
    }

    return totalMaxFrequencies;
  }

  public int findLuckyInteger(int[] arr) {
    int[] frequencies = new int[501];

    for (int num : arr) {
      frequencies[num]++;
    }

    for (int i = 500; i >= 1; i--) {
      if (frequencies[i] == i) {
        return i;
      }
    }

    return -1;
  }

  public boolean haveUniqueOccurrences(int[] arr) {
    Map<Integer, Integer> occurrenceMap = new HashMap<>();

    for (int num : arr) {
      occurrenceMap.put(num, occurrenceMap.getOrDefault(num, 0) + 1);
    }

    Set<Integer> occurrenceSet = new HashSet<>();
    for (Map.Entry<Integer, Integer> entry : occurrenceMap.entrySet()) {
      if (!occurrenceSet.add(entry.getValue())) {
        return false;
      }
    }

    return true;
  }

  public String frequencyShort(String s) {
    return s.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
        .flatMap(entry -> Collections.nCopies(entry.getValue().intValue(),
            String.valueOf(entry.getKey())).stream())
        .collect(Collectors.joining());
  }

  public int maxSubArrayLength(int[] nums, int k) {
    int left = -1, right, n = nums.length, max = 1;
    Map<Integer, Integer> numCount = new HashMap<>();

    for (right = 0; right < n; right++) {
      numCount.put(nums[right], numCount.getOrDefault(nums[right], 0) + 1);

      while (numCount.get(nums[right]) > k) {
        left++;
        numCount.put(nums[left], numCount.get(nums[left]) - 1);
      }

      max = Math.max(max, right - left);
    }

    return max;
  }

  public int numberOfGoodPairs(int[] nums) {
    int goodPairs = 0;
    int[] occurrences = new int[101];

    for (int num : nums) {
      if (occurrences[num] > 0) {
        goodPairs += occurrences[num];
      }
      occurrences[num]++;
    }

    return goodPairs;
  }

  public int subArraysWithSum(int[] nums, int goal) {
    int start = 0;
    int prefixZeros = 0;
    int currentSum = 0;
    int totalCount = 0;

    // Loop through the array using end pointer
    for (int end = 0; end < nums.length; end++) {
      // Add current element to the sum
      currentSum += nums[end];

      // Slide the window while condition is met
      while (start < end && (nums[start] == 0 || currentSum > goal)) {
        if (nums[start] == 1) {
          prefixZeros = 0;
        } else {
          prefixZeros++;
        }

        currentSum -= nums[start];
        start++;
      }

      // Count subarrays when window sum matches the goal
      if (currentSum == goal) {
        totalCount += 1 + prefixZeros;
      }
    }

    return totalCount;
  }

  public int maximumUniqueSubarray(int[] nums) {
    int left = 0, sum = 0, max = 0;
    boolean[] numExists = new boolean[10001];

    for (int currentNum : nums) {
      while (numExists[currentNum]) {
        sum -= nums[left];
        numExists[nums[left]] = false;
        left++;
      }

      numExists[currentNum] = true;
      sum += currentNum;
      max = Math.max(max, sum);
    }

    return max;
  }

  public boolean containsPermutation(String s1, String s2) {
    if (s2.length() < s1.length()) {
      return false;
    }

    int[] s1Counts = new int[26];
    for (char c : s1.toCharArray()) {
      s1Counts[c - 'a']++;
    }

    int[] s2Counts = new int[26];
    for (int i = 0; i < s1.length(); i++) {
      s2Counts[s2.charAt(i) - 'a']++;
    }

    if (arrayEquals(s1Counts, s2Counts)) {
      return true;
    }

    for (int start = 1; start <= s2.length() - s1.length(); start++) {
      s2Counts[s2.charAt(start - 1) - 'a']--;
      s2Counts[s2.charAt(start + s1.length() - 1) - 'a']++;

      if (arrayEquals(s1Counts, s2Counts)) {
        return true;
      }
    }

    return false;
  }

  private boolean arrayEquals(int[] a, int[] b) {
    if (a.length != b.length) {
      return false;
    }

    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }

    return true;
  }
}
