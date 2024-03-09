package per.duyd.training.dsaa.boggle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EngTrieSETTest {

  private EngTrieSET trie;

  @BeforeEach
  void setup() {
    trie = new EngTrieSET();
  }

  @Test
  void testAddAndContains() {
    trie.add("HELLO");
    trie.add("WORLD");

    assertTrue(trie.contains("HELLO"));
    assertTrue(trie.contains("WORLD"));
    assertFalse(trie.contains(""));
  }

  @Test
  void testKeysWithPrefix() {
    trie.add("CAT");
    trie.add("CATS");
    trie.add("CAR");
    trie.add("APPLE");

    Iterable<String> results = trie.keysWithPrefix("CA");
    Iterator<String> iterator = results.iterator();

    assertEquals("CAR", iterator.next());
    assertEquals("CAT", iterator.next());
    assertEquals("CATS", iterator.next());
    assertFalse(iterator.hasNext());
  }

  @ParameterizedTest
  @CsvSource({"CA,true,false", "AP,true,false", "CAR,true,true", "APPLE,true,true"})
  void testGetNode(String key, boolean exist, boolean isString) {
    trie.add("CAT");
    trie.add("CATS");
    trie.add("CAR");
    trie.add("APPLE");

    EngTrieSET.Node node = trie.get(key);

    if (exist) {
      assertNotNull(node);
      assertEquals(isString, node.isString());
    } else {
      assertNull(node);
    }
  }

  @Test
  void testKeysThatMatch() {
    trie.add("SHELLS");
    trie.add("SHORE");
    trie.add("THE"); // Should not be included due to lowercase

    Iterable<String> results = trie.keysThatMatch("SHORE");
    Iterator<String> iterator = results.iterator();

    assertEquals("SHORE", iterator.next());
    assertFalse(iterator.hasNext());
  }

  @Test
  void testLongestPrefixOf() {
    trie.add("SHELLS");
    trie.add("SHELL");
    trie.add("SHORE");

    assertEquals("SHELL", trie.longestPrefixOf("SHELLFISH"));
    assertEquals("SHORE", trie.longestPrefixOf("SHORESIDE"));
    assertNull(trie.longestPrefixOf("HELLO"));
    assertNull(trie.longestPrefixOf(""));
  }

  @Test
  void testDelete() {
    trie.add("HELLO");
    trie.add("HELP");

    trie.delete("HELLO");
    assertFalse(trie.contains("HELLO"));
    assertTrue(trie.contains("HELP"));

    trie.delete("HELP");
    assertFalse(trie.contains("HELP"));
  }

  @Test
  void testIsEmptyAndSize() {
    assertTrue(trie.isEmpty());
    assertEquals(0, trie.size());

    trie.add("HELLO");
    assertFalse(trie.isEmpty());
    assertEquals(1, trie.size());
  }

}