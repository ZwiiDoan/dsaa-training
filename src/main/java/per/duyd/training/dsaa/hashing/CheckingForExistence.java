package per.duyd.training.dsaa.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckingForExistence {
  public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int num : nums) {
      if (!seen.add(num)) {
        return true;
      }
    }

    return false;
  }

  public String destinationCity(String[][] paths) {
    Map<String, Integer> outDegree = new HashMap<>();

    for (String[] path : paths) {
      outDegree.put(path[0], outDegree.getOrDefault(path[0], 0) + 1);
      outDegree.put(path[1], outDegree.getOrDefault(path[1], 0));
    }

    return outDegree.entrySet().stream().filter(entry -> entry.getValue() == 0).findFirst()
        .map(Map.Entry::getKey).orElse(null);
  }
}
