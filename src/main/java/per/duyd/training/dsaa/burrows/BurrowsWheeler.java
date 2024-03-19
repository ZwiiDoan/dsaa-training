package per.duyd.training.dsaa.burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class BurrowsWheeler {
  // apply Burrows-Wheeler transform,
  // reading from standard input and writing to standard output
  public static void transform() {
    String input = BinaryStdIn.readString();
    CircularSuffixArray circularSuffixArray = new CircularSuffixArray(input);
    int first;

    for (int i = 0; i < input.length(); i++) {
      if (circularSuffixArray.index(i) == 0) {
        first = i;
        BinaryStdOut.write(first);
        break;
      }
    }

    for (int i = 0; i < input.length(); i++) {
      int index = circularSuffixArray.index(i);
      if (index == 0) {
        BinaryStdOut.write(input.charAt(input.length() - 1));
      } else {
        BinaryStdOut.write(input.charAt(index - 1));
      }
    }

    BinaryStdOut.close();
  }

  // apply Burrows-Wheeler inverse transform,
  // reading from standard input and writing to standard output
  public static void inverseTransform() {
    int first = BinaryStdIn.readInt();
    String t = BinaryStdIn.readString();

    char[] tArray = t.toCharArray();
    char[] hArray = new char[tArray.length];
    int[] next = new int[tArray.length];

    //Key-indexed Counting Sort
    int[] count = new int[257];
    for (char c : tArray) {
      count[c + 1]++;
    }

    for (int r = 0; r < 256; r++) {
      count[r + 1] += count[r];
    }

    for (int i = 0; i < tArray.length; i++) {
      int hIndex = count[tArray[i]]++;
      hArray[hIndex] = tArray[i];
      next[hIndex] = i;
    }

    reconstructOriginalString(hArray, first, tArray, next);
  }

  private static void reconstructOriginalString(char[] hArray, int first, char[] tArray,
                                                int[] next) {
    BinaryStdOut.write(hArray[first]);

    if (hArray.length > 1) {
      int currentIndex = first;
      for (int i = 1; i < tArray.length - 1; i++) {
        currentIndex = next[currentIndex];
        BinaryStdOut.write(hArray[currentIndex]);
      }
      BinaryStdOut.write(tArray[first]);
    }

    BinaryStdOut.close();
  }

  // if args[0] is "-", apply Burrows-Wheeler transform
  // if args[0] is "+", apply Burrows-Wheeler inverse transform
  public static void main(String[] args) {
    if (args[0].equals("-")) {
      transform();
    } else if (args[0].equals("+")) {
      inverseTransform();
    } else {
      throw new IllegalArgumentException("Illegal command line argument");
    }
  }
}
