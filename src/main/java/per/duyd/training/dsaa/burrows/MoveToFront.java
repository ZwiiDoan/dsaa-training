package per.duyd.training.dsaa.burrows;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

public class MoveToFront {

  // apply move-to-front encoding, reading from standard input and writing to standard output
  public static void encode() {
    char[] ascii = getAsciiChars();

    while (!BinaryStdIn.isEmpty()) {
      char c = BinaryStdIn.readChar();

      for (int i = 0; i < 256; i++) {
        if (ascii[i] == c) {
          BinaryStdOut.write(i, 8);
          ascii = moveToFront(ascii, i);
          break;
        }
      }
    }

    BinaryStdOut.close();
  }

  // apply move-to-front decoding, reading from standard input and writing to standard output
  public static void decode() {
    char[] ascii = getAsciiChars();

    while (!BinaryStdIn.isEmpty()) {
      int i = BinaryStdIn.readChar();
      BinaryStdOut.write(ascii[i]);
      ascii = moveToFront(ascii, i);
    }

    BinaryStdOut.close();
  }

  private static char[] getAsciiChars() {
    char[] ascii = new char[256];
    for (int i = 0; i < 256; i++) {
      ascii[i] = (char) i;
    }
    return ascii;
  }

  private static char[] moveToFront(char[] ascii, int i) {
    char[] updated = new char[256];
    updated[0] = ascii[i];
    System.arraycopy(ascii, 0, updated, 1, i);
    System.arraycopy(ascii, i + 1, updated, i + 1, 255 - i);
    return updated;
  }

  // if args[0] is "-", apply move-to-front encoding
  // if args[0] is "+", apply move-to-front decoding
  public static void main(String[] args) {
    if (args[0].equals("-")) {
      encode();
    } else if (args[0].equals("+")) {
      decode();
    } else {
      throw new IllegalArgumentException("Illegal command line argument");
    }
  }
}
