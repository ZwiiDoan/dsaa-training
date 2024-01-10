package per.duyd.training.dsaa.uinionfind;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

  private final WeightedQuickUnionUF wquf;
  private final WeightedQuickUnionUF fullnessWquf;
  private final int gridSize;
  private final int bottomVirtualSiteIndex;
  private final int topVirtualSiteIndex;
  private final boolean[] openSites;

  private int openSitesCount;

  // creates n-by-n grid, with all sites initially blocked
  public Percolation(int n) {
    if (n <= 0) {
      throw new IllegalArgumentException("Invalid grid size");
    }

    int sitesCount = n * n;
    gridSize = n;
    bottomVirtualSiteIndex = sitesCount;
    topVirtualSiteIndex = sitesCount + 1;
    openSites = new boolean[sitesCount];
    openSitesCount = 0;
    wquf = new WeightedQuickUnionUF(sitesCount + 2);
    fullnessWquf = new WeightedQuickUnionUF(sitesCount + 2);
  }

  // opens the site (row, col) if it is not open already
  public void open(int row, int col) {
    validate(row, col);

    if (isOpen(row, col)) {
      return;
    }

    int currentSiteIndex = getSiteIndex(row, col);
    openSites[currentSiteIndex] = true;
    openSitesCount++;

    if (row == 1) { // connect top sites with top virtual site
      wquf.union(topVirtualSiteIndex, currentSiteIndex);
      fullnessWquf.union(topVirtualSiteIndex, currentSiteIndex);
    }

    if (row == gridSize) { // connect bottom sites with bottom virtual site
      wquf.union(bottomVirtualSiteIndex, currentSiteIndex);
    }

    // connect to top open neighbor site
    unionOpenNeighborSite(currentSiteIndex, row - 1, col);

    // connect to bottom open neighbor site
    unionOpenNeighborSite(currentSiteIndex, row + 1, col);

    // connect to right open neighbor site
    unionOpenNeighborSite(currentSiteIndex, row, col + 1);

    // connect to left open neighbor site
    unionOpenNeighborSite(currentSiteIndex, row, col - 1);
  }

  private int getSiteIndex(int row, int col) {
    return (row - 1) * gridSize + (col - 1);
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validate(row, col);
    return openSites[getSiteIndex(row, col)];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validate(row, col);
    int siteIndex = getSiteIndex(row, col);
    return fullnessWquf.find(siteIndex) == fullnessWquf.find(topVirtualSiteIndex);
  }

  // returns the number of open sites
  public int numberOfOpenSites() {
    return openSitesCount;
  }

  // does the system percolate?
  public boolean percolates() {
    return wquf.find(bottomVirtualSiteIndex) == wquf.find(topVirtualSiteIndex);
  }

  private void validate(int row, int col) {
    if (row > gridSize || col > gridSize || row < 1 || col < 1) {
      throw new IllegalArgumentException("Invalid row and/or column numbers");
    }
  }

  private void unionOpenNeighborSite(int currentSiteIndex, int neighborRow, int neighborCol) {
    if (neighborRow >= 1 && neighborRow <= gridSize
        && neighborCol >= 1 && neighborCol <= gridSize
        && isOpen(neighborRow, neighborCol)) {
      wquf.union(currentSiteIndex, getSiteIndex(neighborRow, neighborCol));
      fullnessWquf.union(currentSiteIndex, getSiteIndex(neighborRow, neighborCol));
    }
  }
}
