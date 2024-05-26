package per.duyd.training.dsaa.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SplitArrayLargestSumTest {

  @Test
  void splitArray() {
    assertEquals(18, new SplitArrayLargestSum().splitArray(new int[] {7, 2, 5, 10, 8}, 2));
    assertEquals(9, new SplitArrayLargestSum().splitArray(new int[] {1, 2, 3, 4, 5}, 2));
  }
}