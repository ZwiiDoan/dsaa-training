package per.duyd.training.dsaa.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NumsSameConsecDiff {
  public int[] numsSameConsecDiff(int n, int k) {
    List<String> ans = new ArrayList<>();
    StringBuilder current = new StringBuilder();
    for (int i = 1; i < 10; i++) {
      current.append(i);
      backtrack(i, current, ans, n, k);
      current.deleteCharAt(0);
    }
    return ans.stream().map(Integer::parseInt).mapToInt(Integer::intValue).toArray();
  }

  private void backtrack(int lastDigit, StringBuilder current, List<String> ans, int n, int k) {
    if (current.length() == n) {
      ans.add(current.toString());
      return;
    }

    if (lastDigit + k < 10) {
      current.append(lastDigit + k);
      backtrack(lastDigit + k, current, ans, n, k);
      current.deleteCharAt(current.length() - 1);
    }

    if (lastDigit - k >= 0) {
      current.append(lastDigit - k);
      backtrack(lastDigit - k, current, ans, n, k);
      current.deleteCharAt(current.length() - 1);
    }
  }
}
