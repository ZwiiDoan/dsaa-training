package per.duyd.training.dsaa.dynamicprogramming;

import java.util.Arrays;

/**
 * <p>
 * You are given an integer array coins representing coins of different denominations and an
 * integer amount representing a total amount of money.<br/>
 * Return the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.<br/>
 * You may assume that you have an infinite number of each kind of coin.
 * </p>
 * <p>
 * Example 1:<br/>
 * Input: coins = [1,2,5], amount = 11<br/>
 * Output: 3<br/>
 * Explanation: 11 = 5 + 5 + 1
 * </p>
 * <p>
 * Example 2:<br/>
 * Input: coins = [2], amount = 3<br/>
 * Output: -1<br/>
 * </p>
 * <p>
 * Example 3:<br/>
 * Input: coins = [1], amount = 0<br/>
 * Output: 0<br/>
 * </p>
 */
public class CoinChange {
  public int coinChangeTopDown(int[] coins, int amount) {
    /*
      minCoins(x) = min(minCoins(x - c) + 1) | with c in coins, x in [0,amount]
      minCoins(0) = 0
      cache = int[amount];
     */

    int[] cache = new int[amount + 1];
    Arrays.fill(cache, -1);
    cache[0] = 0;

    return minCoins(coins, amount, cache);
  }

  private int minCoins(int[] coins, int amount, int[] cache) {
    if (cache[amount] != -1) {
      return cache[amount];
    }

    int currMinCoins = Integer.MAX_VALUE;

    for (int c : coins) {
      if (amount - c >= 0) {
        int prevMinCoins = minCoins(coins, amount - c, cache);
        if (prevMinCoins != -1) {
          currMinCoins = Math.min(currMinCoins, prevMinCoins + 1);
        }
      }
    }

    cache[amount] = currMinCoins == Integer.MAX_VALUE ? -1 : currMinCoins;

    return cache[amount];
  }

  public int coinChangeBottomUp(int[] coins, int amount) {
    int[] minCoins = new int[amount + 1];
    Arrays.fill(minCoins, -1);
    minCoins[0] = 0;

    for (int i = 1; i <= amount; i++) {
      minCoins[i] = Integer.MAX_VALUE;

      for (int c : coins) {
        if (i - c >= 0 && minCoins[i - c] != -1) {
          minCoins[i] = Math.min(minCoins[i], minCoins[i - c] + 1);
        }
      }

      if (minCoins[i] == Integer.MAX_VALUE) {
        minCoins[i] = -1;
      }
    }

    return minCoins[amount];
  }
}
