package per.duyd.training.dsaa.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MinDifficultyJobScheduleTest {

  public static Stream<Arguments> minDifficultyParams() {
    return Stream.of(
        Arguments.of("multiple jobs per day", new int[] {6, 5, 4, 3, 2, 1}, 2, 7),
        Arguments.of("invalid schedule", new int[] {9, 9, 9}, 4, -1),
        Arguments.of("1 job per day", new int[] {1, 1, 1}, 3, 3)
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("minDifficultyParams")
  void minDifficulty(String description, int[] jobDifficulty, int d, int expected) {
    assertEquals(expected, new MinDifficultyJobSchedule().minDifficulty(jobDifficulty, d));
  }
}