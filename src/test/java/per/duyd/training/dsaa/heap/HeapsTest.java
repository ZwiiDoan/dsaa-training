package per.duyd.training.dsaa.heap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HeapsTest {

  private Heaps heaps;

  public static Stream<Arguments> topKFrequentParams() {
    return Stream.of(
        Arguments.of("Example 1", new String[] {"i", "love", "leetcode", "i", "love", "coding"}, 2,
            List.of("i", "love")), Arguments.of("Example 2",
            new String[] {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4,
            List.of("the", "is", "sunny", "day")));
  }

  @BeforeEach
  void setUp() {
    heaps = new Heaps();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("topKFrequentParams")
  void topKFrequent(String description, String[] words, int k, List<String> expected) {
    assertEquals(expected, heaps.topKFrequent(words, k));
  }
}