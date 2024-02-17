package per.duyd.training.dsaa.wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SAPCacheTest {

  private SAPCache cache;

  @BeforeEach
  void setUp() {
    cache = new SAPCache();
  }

  @Test
  void testGetLengthFromEmptyCache() {
    List<Integer> listA = Arrays.asList(1, 2);
    List<Integer> listB = Arrays.asList(3);
    Integer result = cache.getLength(listA, listB);
    assertNull(result);
  }

  @Test
  void testPutAndGetLength() {
    List<Integer> listA = Arrays.asList(5, 7);
    List<Integer> listB = Arrays.asList(10);
    int expectedLength = 8;

    cache.putLength(listA, listB, expectedLength);

    // Test both directions (order shouldn't matter)
    Integer length1 = cache.getLength(listA, listB);
    Integer length2 = cache.getLength(listB, listA);

    assertEquals(expectedLength, length1);
    assertEquals(expectedLength, length2);
  }

  @Test
  void testGetAncestorFromEmptyCache() {
    List<Integer> listA = Arrays.asList(1, 2);
    List<Integer> listB = Arrays.asList(3);
    Integer result = cache.getAncestor(listA, listB);
    assertNull(result);
  }

  @Test
  void testPutAndGetAncestor() {
    List<Integer> listA = Arrays.asList(5, 7);
    List<Integer> listB = Arrays.asList(10);
    int expectedAncestor = 20; // Replace with an appropriate value

    cache.putAncestor(listA, listB, expectedAncestor);

    // Test both directions (order shouldn't matter)
    Integer ancestor1 = cache.getAncestor(listA, listB);
    Integer ancestor2 = cache.getAncestor(listB, listA);

    assertEquals(expectedAncestor, ancestor1);
    assertEquals(expectedAncestor, ancestor2);
  }

  @Test
  void testCacheKeyGeneration() {
    List<Integer> listA = Arrays.asList(2, 3, 1);
    List<Integer> listB = Arrays.asList(8, 5);
    String expectedKey = "2-3-1|8-5";
    String generatedKey = cache.getCacheKey(listA, listB);
    assertEquals(expectedKey, generatedKey);
  }

  // Tests for the overloaded methods:
  @Test
  void testGetLengthSingleIntegers() {
    int v = 10;
    int w = 15;
    Integer result = cache.getLength(v, w);
    assertNull(result);
  }

  @Test
  void testPutAndGetLengthSingleIntegers() {
    int v = 10;
    int w = 15;
    int expectedLength = 5;
    cache.putLength(v, w, expectedLength);
    assertEquals(expectedLength, cache.getLength(v, w));
    assertEquals(expectedLength, cache.getLength(w, v));
  }

  @Test
  void testGetAncestorSingleIntegers() {
    int v = 2;
    int w = 8;
    Integer result = cache.getAncestor(v, w);
    assertNull(result);
  }

  @Test
  void testPutAndGetAncestorSingleIntegers() {
    int v = 2;
    int w = 8;
    int expectedAncestor = 5; // Assume this is a plausible ancestor

    cache.putAncestor(v, w, expectedAncestor);

    // Test symmetry
    assertEquals(expectedAncestor, cache.getAncestor(v, w));
    assertEquals(expectedAncestor, cache.getAncestor(w, v));
  }
}
