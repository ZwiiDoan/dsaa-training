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

}
