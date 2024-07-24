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

  List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> ans = new ArrayList<>();

    List<int[]> candidateCounters = Arrays.stream(candidates).boxed()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream().map(entry -> new int[] {entry.getKey(), entry.getValue().intValue()})
        .collect(Collectors.toList());

    combinationSum2(candidateCounters, target, 0, ans, new ArrayList<>());
    return ans;
  }

  private void combinationSum2(List<int[]> candidateCounters, int remSum, int currIndex,
                               List<List<Integer>> ans, List<Integer> currList) {
    if (remSum == 0) {
      ans.add(new ArrayList<>(currList));
    } else {
      for (int nextIndex = currIndex; nextIndex < candidateCounters.size(); nextIndex++) {
        int candidate = candidateCounters.get(nextIndex)[0];
        int count = candidateCounters.get(nextIndex)[1];

        if (candidate <= remSum && count > 0) {
          currList.add(candidate);
          candidateCounters.set(nextIndex, new int[] {candidate, count - 1});

          combinationSum2(candidateCounters, remSum - candidate, nextIndex, ans, currList);

          candidateCounters.set(nextIndex, new int[] {candidate, count});
          currList.remove(currList.size() - 1);
        }
      }
    }
  }
}
