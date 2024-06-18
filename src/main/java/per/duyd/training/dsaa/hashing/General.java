package per.duyd.training.dsaa.hashing;

import java.util.HashMap;
import java.util.Map;

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

    /*
    Example 3:
    Input: s = "paper", t = "title"
    Output: true
    stCharsMap: [p -> t, a -> i, e -> l, r -> e]
    tsCharsMap: [t -> p, i -> a, l -> e, e -> r]

    Example 2:
    Input: s = "foo", t = "bar"
    Output: false
    stCharsMap: [f -> b, o -> a,r] => false
    stCharsMap: [b -> f, a -> o, r -> o]
     */
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
}
