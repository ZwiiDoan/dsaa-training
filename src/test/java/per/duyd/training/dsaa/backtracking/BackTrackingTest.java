package per.duyd.training.dsaa.backtracking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
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

  public static Stream<Arguments> combinationSum2Params() {
    return Stream.of(Arguments.of("Example 1", new int[] {10, 1, 2, 7, 6, 1, 5}, 8,
            List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6))),
        Arguments.of("Example 2", new int[] {2, 5, 2, 1, 2}, 5,
            List.of(List.of(1, 2, 2), List.of(5))));
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

  @ParameterizedTest(name = "{0}")
  @MethodSource("combinationSum2Params")
  void combinationSum2(String description, int[] candidates, int target,
                       List<List<Integer>> expected) {
    assertIterableEquals(expected, backTracking.combinationSum2(candidates, target));
  }
}