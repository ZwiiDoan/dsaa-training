package per.duyd.training.dsaa.hashing;

import java.util.HashSet;
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
}
