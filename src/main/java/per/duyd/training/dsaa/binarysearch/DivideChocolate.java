package per.duyd.training.dsaa.binarysearch;

public class DivideChocolate {
  public int maximizeSweetness(int[] sweetness, int k) {
    int left = 0, total = 0;
    for (int sweet : sweetness) {
      if (left > sweet) {
        left = sweet;
      }
      total += sweet;
    }

    int right = total / (k + 1);

    int ans = left;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      if (validCutPlan(mid, sweetness, k)) {
        left = mid + 1;
        ans = mid;
      } else {
        right = mid - 1;
      }
    }

    return ans;
  }

  private boolean validCutPlan(int targetSweet, int[] sweetness, int k) {
    int currSweet = 0, pieces = 0;

    for (int i = 0; i < sweetness.length && pieces < k + 1; i++) {
      currSweet += sweetness[i];
      if (currSweet >= targetSweet) {
        pieces++;
        currSweet = 0;
      }
    }

    return pieces >= k + 1;
  }
}
