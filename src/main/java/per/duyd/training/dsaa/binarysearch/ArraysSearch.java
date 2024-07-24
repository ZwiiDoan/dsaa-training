package per.duyd.training.dsaa.binarysearch;

public class ArraysSearch {

  private int pick;

  public ArraysSearch() {
  }

  public ArraysSearch(int pick) {
    this.pick = pick;
  }

  public int guessNumber(int n) {
    int left = 0, right = n;

    while (left <= right) {
      int mid = left + (right - left) / 2;
      int result = guess(mid);

      if (result == 0) {
        return mid;
      } else if (result > 0) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return -1;
  }

  private int guess(int num) {
    return Integer.compare(this.pick, num);
  }

  public int maxDistanceBinarySearch(int[] nums1, int[] nums2) {
    int j = 0, maxDistance = 0;

    for (int i = 0; i < nums1.length && j < nums2.length; i++) {
      int left = j, right = nums2.length - 1;

      while (left <= right) {
        int mid = left + (right - left) / 2;

        if (nums1[i] <= nums2[mid]) {
          j = mid + 1;
          left = mid + 1;
          maxDistance = Math.max(maxDistance, mid - i);
        } else {
          right = mid - 1;
        }
      }
    }

    return maxDistance;
  }

  public int maxDistance2Pointers(int[] nums1, int[] nums2) {
    int p1 = 0, p2 = 0, maxDistance = 0;

    while (p1 < nums1.length && p2 < nums2.length) {
      if (nums1[p1] <= nums2[p2]) {
        maxDistance = Math.max(maxDistance, p2 - p1);
        p2++;
      } else {
        p1++;
      }
    }

    return maxDistance;
  }
}
