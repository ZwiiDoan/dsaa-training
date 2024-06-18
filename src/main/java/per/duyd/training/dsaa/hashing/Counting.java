package per.duyd.training.dsaa.hashing;

import java.util.HashMap;
import java.util.Map;

public class Counting {
  public int sumOfUnique(int[] nums) {
    Map<Integer, Integer> numCounts = new HashMap<>();

    for (int num : nums) {
      numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
    }

    return numCounts.entrySet().stream().filter(entry -> entry.getValue() == 1)
        .mapToInt(Map.Entry::getKey).sum();
  }
}
