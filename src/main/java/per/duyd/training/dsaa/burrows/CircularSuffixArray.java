package per.duyd.training.dsaa.burrows;

import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Optional;

public class CircularSuffixArray {

  private final int[] index;
  private final int length;

  private static class CircularSuffix implements Comparable<CircularSuffix> {
    private final int startIndex;
    private final String originalString;

    public CircularSuffix(int startIndex, String originalString) {
      this.startIndex = startIndex;
      this.originalString = originalString;
    }

    @Override
    public int compareTo(CircularSuffix other) {
      int i = this.startIndex;
      int j = other.startIndex;
      int l = this.originalString.length();
      int k = 0;

      while (k < l) {
        if (i >= l) {
          i = 0;
        }
        char thisChar = this.originalString.charAt(i);

        if (j >= l) {
          j = 0;
        }
        char otherChar = this.originalString.charAt(j);

        int cmp = Character.compare(thisChar, otherChar);
        if (cmp != 0) {
          return cmp;
        }

        i++;
        j++;
        k++;
      }

      return 0;
    }
  }

  // circular suffix array of s
  public CircularSuffixArray(String s) {
    Optional.ofNullable(s).orElseThrow(IllegalArgumentException::new);

    this.length = s.length();
    this.index = new int[this.length];

    CircularSuffix[] circularSuffixes = new CircularSuffix[this.length];
    for (int i = 0; i < this.length; i++) {
      circularSuffixes[i] = new CircularSuffix(i, s);
    }
    Arrays.sort(circularSuffixes);

    for (int i = 0; i < this.length; i++) {
      this.index[i] = circularSuffixes[i].startIndex;
    }
  }

  // length of s
  public int length() {
    return this.length;
  }

  // returns index of ith sorted suffix
  public int index(int i) {
    if (i >= this.length || i < 0) {
      throw new IllegalArgumentException();
    }

    return this.index[i];
  }


  // unit testing (required)
  public static void main(String[] args) {
    CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABRACADABRA!");
    StdOut.println(circularSuffixArray.length);
    for (int i = 0; i < circularSuffixArray.length; i++) {
      StdOut.println(circularSuffixArray.index[i]);
    }
  }
}
