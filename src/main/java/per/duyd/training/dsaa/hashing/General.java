package per.duyd.training.dsaa.hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class General {
  public boolean isIsomorphic(String s, String t) {
    Map<Character, Character> stCharsMap = new HashMap<>();
    Map<Character, Character> tsCharsMap = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
      if (hasMultipleMappings(s, t, i, stCharsMap) || hasMultipleMappings(t, s, i, tsCharsMap)) {
        return false;
      }
    }

    return true;
  }

  private boolean hasMultipleMappings(String s, String t, int i,
                                      Map<Character, Character> charsMap) {
    char sChar = s.charAt(i);
    char tChar = t.charAt(i);
    Character mappedChar = charsMap.get(sChar);

    if (mappedChar == null) {
      charsMap.put(sChar, tChar);
    } else {
      return mappedChar != tChar;
    }

    return false;
  }

  public boolean followsPattern(String pattern, String s) {
    String[] words = s.split(" ");

    if (words.length != pattern.length()) {
      return false;
    }

    Map<Character, Integer> charIndexMap = new HashMap<>();
    Map<String, Integer> wordIndexMap = new HashMap<>();

    for (int i = 0; i < words.length; i++) {
      if (!Objects.equals(wordIndexMap.put(words[i], i), charIndexMap.put(pattern.charAt(i), i))) {
        return false;
      }
    }

    return true;
  }

  public String customSortString(String order, String s) {
    int[] charCounts = new int[26];
    for (char c : s.toCharArray()) {
      charCounts[c - 'a']++;
    }

    StringBuilder sb = new StringBuilder();
    for (char c : order.toCharArray()) {
      while (charCounts[c - 'a'] > 0) {
        sb.append(c);
        charCounts[c - 'a']--;
      }
    }

    for (char c : s.toCharArray()) {
      while (charCounts[c - 'a'] > 0) {
        sb.append(c);
        charCounts[c - 'a']--;
      }
    }

    return sb.toString();
  }

  public boolean closeString(String word1, String word2) {
    int[] charCounts1 = new int[26];
    int[] charCounts2 = new int[26];

    for (char c : word1.toCharArray()) {
      charCounts1[c - 'a']++;
    }

    for (char c : word2.toCharArray()) {
      charCounts2[c - 'a']++;
    }

    Map<Integer, Integer> occurrences1 = new HashMap<>();
    Map<Integer, Integer> occurrences2 = new HashMap<>();
    for (int i = 0; i < 26; i++) {
      if (charCounts1[i] != 0) {
        occurrences1.put(charCounts1[i], occurrences1.getOrDefault(charCounts1[i], 0) + 1);
        if (charCounts2[i] == 0) {
          return false;
        }
      }

      if (charCounts2[i] != 0) {
        occurrences2.put(charCounts2[i], occurrences2.getOrDefault(charCounts2[i], 0) + 1);
        if (charCounts1[i] == 0) {
          return false;
        }
      }
    }

    if (occurrences1.size() != occurrences2.size()) {
      return false;
    }

    for (Map.Entry<Integer, Integer> entry : occurrences1.entrySet()) {
      if (!Objects.equals(entry.getValue(), occurrences2.get(entry.getKey()))) {
        return false;
      }
    }

    return true;
  }
}
