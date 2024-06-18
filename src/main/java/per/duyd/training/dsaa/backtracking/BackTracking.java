package per.duyd.training.dsaa.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BackTracking {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> ans = new ArrayList<>();
    Map<Integer, Long> counter = Arrays.stream(nums).boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    permuteUnique(new ArrayList<>(), counter, nums, ans);
    return ans;
  }

  private void permuteUnique(List<Integer> curr, Map<Integer, Long> counter, int[] nums,
                             List<List<Integer>> ans) {
    if (curr.size() == nums.length) {
      ans.add(new ArrayList<>(curr));
    } else {
      for (int number : counter.keySet()) {
        long count = counter.get(number);

        if (count > 0) {
          curr.add(number);
          counter.put(number, count - 1);
          permuteUnique(curr, counter, nums, ans);
          curr.remove(curr.size() - 1);
          counter.put(number, count);
        }
      }
    }
  }
}
