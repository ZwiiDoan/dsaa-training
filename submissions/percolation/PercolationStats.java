import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

  private static final double CONFIDENCE_95 = 1.96;

  private final int trials;
  private final double[] fractions;

  // perform independent trials on an n-by-n grid
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) {
      throw new IllegalArgumentException("Invalid grids size and/or trials");
    }

    this.trials = trials;
    this.fractions = new double[trials];
    int sitesCount = n * n;

    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);

      while (!percolation.percolates()) {
        percolation.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
      }

      fractions[i] = (double) percolation.numberOfOpenSites() / sitesCount;
    }
  }

  // sample mean of percolation threshold
  public double mean() {
    return StdStats.mean(fractions);
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    return StdStats.stddev(fractions);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLo() {
    return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(trials);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHi() {
    return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(trials);
  }

  private void printResults() {
    System.out.println("mean                    = " + mean());
    System.out.println("stddev                  = " + stddev());
    System.out.println(
        "95% confidence interval = [" + confidenceLo() + ", " + confidenceHi() + "]");
  }

  // test client (see below)
  public static void main(String[] args) {
    validate(args);
    new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1])).printResults();
  }

  private static void validate(String[] args) {
    if (args == null || args.length != 2) {
      throw new IllegalArgumentException("Invalid grids size and/or trials");
    }
  }
}
