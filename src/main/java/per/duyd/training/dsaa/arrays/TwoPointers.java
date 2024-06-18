package per.duyd.training.dsaa.arrays;

public class TwoPointers {
  public String reverseWords(String s) {
    char[] chars = s.toCharArray();

    int wordStart = 0, wordEnd;
    for (int i = 1; i <= chars.length; i++) {
      if (i == chars.length || chars[i] == ' ') {
        wordEnd = i - 1;
        reverseChars(chars, wordStart, wordEnd);
        wordStart = i + 1;
      }
    }

    return new String(chars);
  }

  private void reverseChars(char[] word, int left, int right) {
    while (left < right) {
      char temp = word[left];
      word[left] = word[right];
      word[right] = temp;
      left++;
      right--;
    }
  }

  /**
   * <p>Given a string s, reverse the string according to the following rules:<br/>
   * All the characters that are not English letters remain in the same position.<br/>
   * All the English letters (lowercase or uppercase) should be reversed.<br/>
   * Return s after reversing it.
   * </p>
   * <p>Example 1:<br/>
   * Input: s = "ab-cd"<br/>
   * Output: "dc-ba"
   * </p>
   * <p>Example 2:<br/>
   * Input: s = "a-bC-dEf-ghIj"<br/>
   * Output: "j-Ih-gfE-dCba"
   * </p>
   * <p>
   * Example 3:<br/>
   * Input: s = "Test1ng-Leet=code-Q!"<br/>
   * Output: "Qedo1ct-eeLg=ntse-T!"
   * </p>
   * <p>
   * Constraints:<br/>
   * 1 <= s.length <= 100<br/>
   * s consists of characters with ASCII values in the range [33, 122].<br/>
   * s does not contain '\"' or '\\'.
   * </p>
   */
  public String reverseEnglishLetters(String input) {
    char[] inputChars = input.toCharArray();
    int left = 0, right = inputChars.length - 1;

    while (left < right) {
      if (isEnglishLetter(inputChars[left]) && isEnglishLetter(inputChars[right])) {
        swapChars(inputChars, left, right);
        left++;
        right--;
      }

      if (isNotEnglishLetter(inputChars[left])) {
        left++;
      }

      if (isNotEnglishLetter(inputChars[right])) {
        right--;
      }
    }

    return new String(inputChars);
  }

  private void swapChars(char[] inputChars, int left, int right) {
    char tmp = inputChars[left];
    inputChars[left] = inputChars[right];
    inputChars[right] = tmp;
  }

  private boolean isEnglishLetter(char input) {
    return (input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z');
  }

  private boolean isNotEnglishLetter(char input) {
    return !isEnglishLetter(input);
  }

  /**
   * <p>Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the
   * minimum integer common to both arrays. If there is no common integer amongst nums1 and nums2, return -1.</p>
   *
   * <p>Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that integer.</p>
   *
   * <p>Example 1:<br/>
   * Input: nums1 = [1,2,3], nums2 = [2,4]<br/>
   * Output: 2<br/>
   * Explanation: The smallest element common to both arrays is 2, so we return 2.
   * </p>
   *
   * <p>
   * Example 2:<br/>
   * Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]<br/>
   * Output: 2<br/>
   * Explanation: There are two common elements in the array 2 and 3 out of which 2 is the smallest, so 2 is returned.
   * </p>
   *
   * <p>
   * Constraints:<br/>
   * 1 <= nums1.length, nums2.length <= 105<br/>
   * 1 <= nums1[i], nums2[j] <= 109<br/>
   * Both nums1 and nums2 are sorted in non-decreasing order.
   * </p>
   */
  public int minCommonNumber(int[] nums1, int[] nums2) {
    int i = 0, j = 0, n = nums1.length - 1, m = nums2.length - 1;

    while (i <= n && j <= m) {
      if (nums1[i] == nums2[j]) {
        return nums1[i];
      } else if (nums1[i] < nums2[j]) {
        i++;
      } else if (nums1[i] > nums2[j]) {
        j++;
      }
    }

    return -1;
  }

  /**
   * <p>Given an integer array nums, move all 0's to the end of it while maintaining the relative
   * order of the non-zero elements.</p>
   * <p>Note that you must do this in-place without making a copy of the array.</p>
   * <p>
   * Example 1:<br/>
   * Input: nums = [0,1,0,3,12]<br/>
   * Output: [1,3,12,0,0]
   * </p>
   * <p>
   * Example 2:<br/>
   * Input: nums = [0]<br/>
   * Output: [0]
   * </p>
   * <p>
   * Constraints: <br/>
   * 1 <= nums.length <= 104<br/>
   * -231 <= nums[i] <= 231 - 1
   * </p>
   */
  public void moveZeros(int[] nums) {
    int z = -1;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0 && z == -1) {
        z = i;
      }

      if (nums[i] != 0 && z != -1) {
        swap(nums, z, i);
        z++;
      }
    }
  }

  private void swap(int[] nums, int i, int j) {
    int tmp = nums[i];
    nums[i] = nums[j];
    nums[j] = tmp;
  }

  /**
   * <p>Given a 0-indexed string word and a character ch, reverse the segment of word that starts
   * at index 0 and ends at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.</p>
   * <p>For example, if word = "abcdefd" and ch = "d", then you should reverse the segment that
   * starts at 0 and ends at 3 (inclusive). The resulting string will be "dcbaefd".</p>
   * <p>Return the resulting string.</p>
   * <p>
   * Example 1:<br/>
   * Input: word = "abcdefd", ch = "d"<br/>
   * Output: "dcbaefd"<br/>
   * Explanation: The first occurrence of "d" is at index 3.<br/>
   * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd".
   * </p>
   * <p>
   * Example 2:<br/>
   * Input: word = "xyxzxe", ch = "z"<br/>
   * Output: "zxyxxe"<br/>
   * Explanation: The first and only occurrence of "z" is at index 3.<br/>
   * Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe".
   * </p>
   * <p>
   * Example 3:<br/>
   * Input: word = "abcd", ch = "z"<br/>
   * Output: "abcd"<br/>
   * Explanation: "z" does not exist in word.<br/>
   * You should not do any reverse operation, the resulting string is "abcd".
   */
  public String reversePrefix(String word, char ch) {
    char[] inputChars = word.toCharArray();

    for (int i = 0; i < inputChars.length; i++) {
      char c = inputChars[i];
      if (c == ch) {
        reverseChars(inputChars, 0, i);
        break;
      }
    }

    return new String(inputChars);
  }
}
