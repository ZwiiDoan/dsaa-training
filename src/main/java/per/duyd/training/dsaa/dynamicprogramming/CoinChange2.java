package per.duyd.training.dsaa.dynamicprogramming;

import java.util.Arrays;

public class CoinChange2 {
  public int change(int amount, int[] coins) {
    /*
    - function: noOfCombinations(i,reAmount) with reAmount in [0,amount], i in [0, coins.length - 1] => ans = noCombinations(0,amount)
    - noOfCombinations(i, reAmount):
        if (i == coins.length) return 0;
        if (reAmount == amount) return 1;

        if (reAmount - coin[i] < 0) {
          return noOfCombinations(i + 1, reAmount)
        } else {
          return noOfCombinations(i, reAmount - coin[i)) + noOfCombinations(i + 1, reAmount);
        }

    - cache = int[coins.length + 1][amount + 1];

     */
    int[][] cache = new int[coins.length + 1][amount + 1];
    for (int[] row : cache) {
      Arrays.fill(row, -1);
    }

    return noOfCombinations(coins, 0, amount, cache);
  }

  private int noOfCombinations(int[] coins, int i, int reAmount, int[][] cache) {
    if (i == coins.length) {
      return 0;
    }

    if (reAmount == 0) {
      return 1;
    }

    if (cache[i][reAmount] != -1) {
      return cache[i][reAmount];
    }

    if (reAmount - coins[i] < 0) {
      cache[i][reAmount] = noOfCombinations(coins, i + 1, reAmount, cache);
    } else {
      cache[i][reAmount] = noOfCombinations(coins, i, reAmount - coins[i], cache) +
          noOfCombinations(coins, i + 1, reAmount, cache);
    }

    return cache[i][reAmount];
  }

  public int changeBottomUp(int amount, int[] coins) {
    int[][] noOfCombinations = new int[coins.length + 1][amount + 1];
    for (int[] row : noOfCombinations) {
      Arrays.fill(row, -1);
    }

    for (int i = coins.length; i >= 0; i--) {
      for (int j = 0; j <= amount; j++) {
        if (i == coins.length) {
          noOfCombinations[i][j] = 0;
        } else if (j == 0) {
          noOfCombinations[i][j] = 1;
        } else if (j - coins[i] < 0) {
          noOfCombinations[i][j] = noOfCombinations[i + 1][j];
        } else {
          noOfCombinations[i][j] = noOfCombinations[i + 1][j] + noOfCombinations[i][j - coins[i]];
        }
      }
    }

    return noOfCombinations[0][amount];
  }
}
