package per.duyd.training.dsaa.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BackTrackingTest {

  private BackTracking backTracking;

  public static Stream<Arguments> permuteUniqueParams() {
    return Stream.of(Arguments.of("Example 1", new int[] {1, 1, 2},
            List.of(List.of(1, 1, 2), List.of(1, 2, 1), List.of(2, 1, 1))),
        Arguments.of("Example 2", new int[] {1, 2, 3},
            List.of(List.of(1, 2, 3), List.of(1, 3, 2), List.of(2, 1, 3), List.of(2, 3, 1),
                List.of(3, 1, 2), List.of(3, 2, 1))));
  }

  @BeforeEach
  void setUp() {
    backTracking = new BackTracking();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("permuteUniqueParams")
  void permuteUnique(String description, int[] nums, List<List<Integer>> expected) {
    List<List<Integer>> actual = backTracking.permuteUnique(nums);
    assertEquals(expected.size(), actual.size());
    expected.forEach(e -> assertTrue(actual.stream().anyMatch(a -> a.containsAll(e))));
  }
}