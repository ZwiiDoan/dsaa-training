package per.duyd.training.dsaa.sort;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MergeSortTest {

  private final MergeSort mergeSort = new MergeSort();

  @Test
  public void testSortEmptyArray() {
    int[] array = {};
    mergeSort.sort(array);
    assertArrayEquals(new int[] {}, array);
  }

  @Test
  public void testSortSingleElementArray() {
    int[] array = {5};
    mergeSort.sort(array);
    assertArrayEquals(new int[] {5}, array);
  }

  @Test
  public void testSortAlreadySortedArray() {
    int[] array = {1, 2, 3, 4, 5};
    mergeSort.sort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }

  @Test
  public void testSortReverseSortedArray() {
    int[] array = {5, 4, 3, 2, 1};
    mergeSort.sort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }

  @Test
  public void testSortUnsortedArray() {
    int[] array = {3, 1, 5, 2, 4};
    mergeSort.sort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }

  @Test
  public void testSortArrayWithDuplicates() {
    int[] array = {2, 1, 3, 5, 4};
    mergeSort.sort(array);
    assertArrayEquals(new int[] {1, 2, 3, 4, 5}, array);
  }

  @Test
  public void testSortLargeArray() {
    int[] array = new int[1000];
    for (int i = 0; i < array.length; i++) {
      array[i] = array.length - i;
    }
    mergeSort.sort(array);
    for (int i = 0; i < array.length - 1; i++) {
      assertTrue(array[i] <= array[i + 1]);
    }
  }
}