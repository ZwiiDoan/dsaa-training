package per.duyd.training.dsaa.greedy;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Greedy {
  public int maxIceCream(int[] costs, int coins) {
    PriorityQueue<Integer> minPQ = new PriorityQueue<>();
    Arrays.stream(costs).forEach(minPQ::add);

    int ans = 0;

    while (coins > 0 && !minPQ.isEmpty()) {
      int cost = minPQ.remove();
      if (cost <= coins) {
        ans++;
      }
      coins -= cost;
    }

    return ans;
  }

  public int maxIceCream2(int[] costs, int coins) {
    int max = 0;
    for (int cost : costs) {
      if (cost > max) {
        max = cost;
      }
    }

    int[] counts = new int[max + 1];
    for (int cost : costs) {
      counts[cost]++;
    }

    for (int i = 1; i < counts.length; i++) {
      counts[i] += counts[i - 1];
    }

    int[] sortedCosts = new int[costs.length];

    for (int i = costs.length - 1; i >= 0; i--) {
      sortedCosts[counts[costs[i]] - 1] = costs[i];
      counts[costs[i]]--;
    }

    int ans = 0;
    for (int i = 0; i < sortedCosts.length && coins > 0; i++) {
      if (sortedCosts[i] <= coins) {
        ans++;
      }

      coins -= sortedCosts[i];
    }

    return ans;
  }

  public int longestPalindrome(String s) {
    Map<Character, Long> charMap = s.chars().mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    long ans = 0;
    boolean oddFound = false;
    for (Character c : charMap.keySet()) {
      long charCount = charMap.get(c);
      if (charCount % 2 != 0) {
        ans += charCount - 1;
        oddFound = true;
      } else {
        ans += charCount;
      }
    }

    return (int) (oddFound ? ans + 1 : ans);
  }
}
