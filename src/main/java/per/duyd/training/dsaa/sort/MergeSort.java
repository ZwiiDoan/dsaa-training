package per.duyd.training.dsaa.sort;

public class MergeSort {
  private void merge(int[] array, int[] temp, int start, int mid, int end) {
    for (int i = start; i <= end; i++) {
      temp[i] = array[i];
    }

    int i = start, j = mid + 1;

    for (int k = start; k <= end; k++) {
      if (i > mid) {
        array[k] = temp[j++];
      } else if (j > end) {
        array[k] = temp[i++];
      } else if (temp[i] > temp[j]) {
        array[k] = temp[j++];
      } else {
        array[k] = temp[i++];
      }
    }
  }

//  private void mergeWithLessSpace(int[] a, int[] temp, int low, int mid, int high) {
//    Insertion.sort(a, Comparator.comparingInt(Integer::intValue));
//  }


  public void sort(int[] array) {
    int[] temp = new int[array.length];
    for (int size = 1; size < array.length; size += size) {
      for (int start = 0; start < array.length - size; start += size * 2) {
        merge(array, temp, start, start + size - 1,
            Math.min(array.length - 1, start + 2 * size - 1));
      }
    }
  }
}
