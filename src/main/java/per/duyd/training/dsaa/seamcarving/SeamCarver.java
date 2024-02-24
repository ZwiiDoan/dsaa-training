package per.duyd.training.dsaa.seamcarving;

import edu.princeton.cs.algs4.Picture;
import java.util.Arrays;
import java.util.Optional;

public class SeamCarver {
  private static final boolean HORIZONTAL = true;
  private static final boolean VERTICAL = false;
  private static final double BORDER_ENERGY = 1000;
  private static final int NULL_EDGE_TO = -1;

  private final Picture picture;
  private int width;
  private int height;
  private final double[][] energy;

  // create a seam carver object based on the given picture
  public SeamCarver(Picture picture) {
    this.picture =
        Optional.ofNullable(picture).map(Picture::new).orElseThrow(IllegalArgumentException::new);
    this.width = this.picture.width();
    this.height = this.picture.height();

    this.energy = new double[this.height][this.width];
    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        this.energy[row][col] = calculateEnergy(row, col);
      }
    }
  }

  // current picture
  public Picture picture() {
    Picture current = new Picture(this.width, this.height);

    for (int row = 0; row < this.height; row++) {
      for (int col = 0; col < this.width; col++) {
        current.setRGB(col, row, this.picture.getRGB(col, row));
      }
    }

    return current;
  }

  // width of current picture
  public int width() {
    return this.width;
  }

  // height of current picture
  public int height() {
    return this.height;
  }

  // energy of pixel at column x and row y
  public double energy(int x, int y) {
    validatePixel(x, y);

    return this.energy[y][x];
  }

  // sequence of indices for horizontal seam
  public int[] findHorizontalSeam() {
    double[] distTo = new double[this.width * this.height];
    for (int v = 0; v < distTo.length; v++) {
      distTo[v] = v % this.width == 0 ? BORDER_ENERGY : Double.POSITIVE_INFINITY;
    }
    int[] edgeTo = new int[this.width * this.height];
    Arrays.fill(edgeTo, NULL_EDGE_TO);

    relaxHorizontalGraph(distTo, edgeTo);

    return getHorizontalSeam(distTo, edgeTo);
  }

  // sequence of indices for vertical seam
  public int[] findVerticalSeam() {
    double[] distTo = new double[this.width * this.height];
    for (int v = 0; v < distTo.length; v++) {
      distTo[v] = v / this.width == 0 ? BORDER_ENERGY : Double.POSITIVE_INFINITY;
    }
    int[] edgeTo = new int[this.width * this.height];
    Arrays.fill(edgeTo, NULL_EDGE_TO);

    relaxVerticalGraph(distTo, edgeTo);

    return getVerticalSeam(distTo, edgeTo);
  }

  // remove horizontal seam from current picture
  public void removeHorizontalSeam(int[] seam) {
    validateSeam(seam, HORIZONTAL);

    for (int col = 0; col < seam.length; col++) {
      int delRow = seam[col];
      for (int row = delRow; row < this.height - 1; row++) {
        energy[row][col] = energy[row + 1][col];
        this.picture.setRGB(col, row, this.picture.getRGB(col, row + 1));
      }
    }

    this.height--;

    recalculateEnergy(seam, HORIZONTAL);
  }

  // remove vertical seam from current picture
  public void removeVerticalSeam(int[] seam) {
    validateSeam(seam, VERTICAL);

    for (int row = 0; row < seam.length; row++) {
      int delCol = seam[row];
      for (int col = delCol; col < this.width - 1; col++) {
        energy[row][col] = energy[row][col + 1];
        this.picture.setRGB(col, row, this.picture.getRGB(col + 1, row));
      }
    }

    this.width--;
    recalculateEnergy(seam, VERTICAL);
  }

  private void recalculateEnergy(int[] seam, boolean seamDirection) {
    if (seamDirection == HORIZONTAL) {
      for (int col = 0; col < this.width; col++) {
        int delRow = seam[col];
        if (delRow > 0) {
          this.energy[delRow - 1][col] = calculateEnergy(delRow - 1, col);
        }

        this.energy[delRow][col] = calculateEnergy(delRow, col);
      }
    } else {
      for (int row = 0; row < this.height; row++) {
        int delCol = seam[row];
        if (delCol > 0) {
          this.energy[row][delCol - 1] = calculateEnergy(row, delCol - 1);
        }

        this.energy[row][delCol] = calculateEnergy(row, delCol);
      }
    }
  }

  private int[] getVerticalSeam(double[] distTo, int[] edgeTo) {
    int[] seam = new int[this.height];
    int w = getVerticalMinW(distTo);
    int i = seam.length - 1;
    for (int v = w; v != NULL_EDGE_TO && i >= 0; v = edgeTo[v]) {
      seam[i] = v % this.width;
      i--;
    }

    return seam;
  }

  private int[] getHorizontalSeam(double[] distTo, int[] edgeTo) {
    int[] seam = new int[this.width];
    int w = getHorizontalMinW(distTo);
    int i = seam.length - 1;
    for (int v = w; v != NULL_EDGE_TO && i >= 0; v = edgeTo[v]) {
      seam[i] = v / this.width;
      i--;
    }

    return seam;
  }

  private int getIndex(int row, int col) {
    return row * this.width + col;
  }

  private double calculateEnergy(int row, int col) {
    if (isBorderPixel(row, col)) {
      return BORDER_ENERGY;
    } else {
      int rgbPrevRow = this.picture.getRGB(col, row - 1);
      int rgbNextRow = this.picture.getRGB(col, row + 1);
      int rgbPrevCol = this.picture.getRGB(col - 1, row);
      int rgbNextCol = this.picture.getRGB(col + 1, row);
      double rowGradient = Math.pow(getR(rgbNextRow) - getR(rgbPrevRow), 2) +
          Math.pow(getG(rgbNextRow) - getG(rgbPrevRow), 2) +
          Math.pow(getB(rgbNextRow) - getB(rgbPrevRow), 2);
      double colGradient = Math.pow(getR(rgbNextCol) - getR(rgbPrevCol), 2) +
          Math.pow(getG(rgbNextCol) - getG(rgbPrevCol), 2) +
          Math.pow(getB(rgbNextCol) - getB(rgbPrevCol), 2);
      return Math.sqrt(colGradient + rowGradient);
    }
  }

  private int getR(int rgb) {
    return (rgb >> 16) & 0xFF;
  }

  private int getG(int rgb) {
    return (rgb >> 8) & 0xFF;
  }

  private int getB(int rgb) {
    return rgb & 0xFF;
  }

  private boolean isBorderPixel(int row, int col) {
    return col == 0 || col >= this.width - 1 || row == 0 || row >= this.height - 1;
  }

  private void relaxVerticalGraph(double[] distTo, int[] edgeTo) {
    for (int row = 0; row < this.height - 1; row++) {
      for (int col = 0; col < this.width; col++) {
        int nextRow = row + 1;
        if (col > 0) {
          relax(row, col, nextRow, col - 1, distTo, edgeTo);
        }

        relax(row, col, nextRow, col, distTo, edgeTo);

        if (col < this.width - 1) {
          relax(row, col, nextRow, col + 1, distTo, edgeTo);
        }
      }
    }
  }

  private void relaxHorizontalGraph(double[] distTo, int[] edgeTo) {
    for (int col = 0; col < this.width - 1; col++) {
      for (int row = 0; row < this.height; row++) {
        int nextCol = col + 1;
        if (row > 0) {
          relax(row, col, row - 1, nextCol, distTo, edgeTo);
        }

        relax(row, col, row, nextCol, distTo, edgeTo);

        if (row < this.height - 1) {
          relax(row, col, row + 1, nextCol, distTo, edgeTo);
        }
      }
    }
  }

  private void relax(int row1, int col1, int row2, int col2, double[] distTo, int[] edgeTo) {
    int v = getIndex(row1, col1);
    int w = getIndex(row2, col2);

    double newDistToW = distTo[v] + this.energy[row2][col2];
    if (distTo[w] > newDistToW) {
      distTo[w] = newDistToW;
      edgeTo[w] = v;
    }
  }

  private int getVerticalMinW(double[] distTo) {
    int minIndex = getIndex(this.height - 1, 0);
    double minDist = distTo[minIndex];
    int minW = minIndex;
    for (int col = 1; col < this.width; col++) {
      int w = getIndex(this.height - 1, col);
      double distToW = distTo[w];
      if (distToW < minDist) {
        minDist = distToW;
        minW = w;
      }
    }

    return minW;
  }

  private int getHorizontalMinW(double[] distTo) {
    int minIndex = getIndex(0, this.width - 1);
    double minDist = distTo[minIndex];
    int minW = minIndex;
    for (int row = 1; row < this.height; row++) {
      int w = getIndex(row, this.width - 1);
      double distToW = distTo[w];
      if (distToW < minDist) {
        minDist = distToW;
        minW = w;
      }
    }

    return minW;
  }

  private void validatePixel(int col, int row) {
    if (col < 0 || col > this.width - 1) {
      throw new IllegalArgumentException();
    }

    if (row < 0 || row > this.height - 1) {
      throw new IllegalArgumentException();
    }
  }

  private void validateSeam(int[] seam, boolean seamDirection) {
    if (seam == null) {
      throw new IllegalArgumentException();
    }

    if (seamDirection == HORIZONTAL) {
      if (this.height <= 1) {
        throw new IllegalArgumentException();
      }

      if (seam.length != this.width) {
        throw new IllegalArgumentException();
      }

      for (int col = 0; col < this.width; col++) {
        validatePixel(col, seam[col]);

        if (col > 0 && Math.abs(seam[col] - seam[col - 1]) > 1) {
          throw new IllegalArgumentException();
        }
      }
    }

    if (seamDirection == VERTICAL) {
      if (this.width <= 1) {
        throw new IllegalArgumentException();
      }

      if (seam.length != this.height) {
        throw new IllegalArgumentException();
      }

      for (int row = 0; row < this.height; row++) {
        validatePixel(seam[row], row);
        if (row > 0 && Math.abs(seam[row] - seam[row - 1]) > 1) {
          throw new IllegalArgumentException();
        }
      }
    }
  }
}
