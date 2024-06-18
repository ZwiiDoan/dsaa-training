package per.duyd.training.dsaa.arrays;

public class PrefixSum {
  public int highestAltitude(int[] gain) {
    int[] altitudes = new int[gain.length + 1];
    altitudes[0] = 0;

    int ans = 0;
    for (int i = 1; i < altitudes.length; i++) {
      altitudes[i] = altitudes[i - 1] + gain[i - 1];
      ans = Math.max(ans, altitudes[i]);
    }

    return ans;
  }
}
