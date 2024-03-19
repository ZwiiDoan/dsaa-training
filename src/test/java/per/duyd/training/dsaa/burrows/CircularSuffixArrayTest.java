package per.duyd.training.dsaa.burrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CircularSuffixArrayTest {

  @Test
  void testLength() {
    CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABCABC");
    assertEquals(6, circularSuffixArray.length());
  }

  @Test
  void testIndex() {
    CircularSuffixArray circularSuffixArray = new CircularSuffixArray("ABRACADABRA!");
    assertEquals(11, circularSuffixArray.index(0));
    assertEquals(10, circularSuffixArray.index(1));
    assertEquals(7, circularSuffixArray.index(2));
    assertEquals(0, circularSuffixArray.index(3));
    assertEquals(3, circularSuffixArray.index(4));
    assertEquals(5, circularSuffixArray.index(5));
    assertEquals(8, circularSuffixArray.index(6));
    assertEquals(1, circularSuffixArray.index(7));
    assertEquals(4, circularSuffixArray.index(8));
    assertEquals(6, circularSuffixArray.index(9));
    assertEquals(9, circularSuffixArray.index(10));
    assertEquals(2, circularSuffixArray.index(11));
  }

  @Test
  void testRepeatedCharacters() {
    CircularSuffixArray circularSuffixArray = new CircularSuffixArray("AAAAABBBBB");
    assertEquals(10, circularSuffixArray.length());
    assertEquals(0, circularSuffixArray.index(0));
    assertEquals(1, circularSuffixArray.index(1));
    assertEquals(2, circularSuffixArray.index(2));
    assertEquals(3, circularSuffixArray.index(3));
    assertEquals(4, circularSuffixArray.index(4));
    assertEquals(9, circularSuffixArray.index(5));
    assertEquals(8, circularSuffixArray.index(6));
    assertEquals(7, circularSuffixArray.index(7));
    assertEquals(6, circularSuffixArray.index(8));
    assertEquals(5, circularSuffixArray.index(9));
  }
}