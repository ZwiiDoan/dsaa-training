package per.duyd.training.dsaa.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GraphsTest {

  private Graphs graphs;

  public static Stream<Arguments> findJudgeParams() {
    return Stream.of(Arguments.of("Judge exists 1", 2, new int[][] {{1, 2}}, 2),
        Arguments.of("Judge exists 2", 3, new int[][] {{1, 3}, {2, 3}}, 3),
        Arguments.of("Judge does NOT exist", 3, new int[][] {{1, 3}, {2, 3}, {3, 1}}, -1),
        Arguments.of("Given only 1 people with no trust, should return that people as the judge", 1,
            new int[][] {}, 1));
  }

  public static Stream<Arguments> maximalNetworkRankParams() {
    return Stream.of(Arguments.of("Example 1", 4, new int[][] {{0, 1}, {0, 3}, {1, 2}, {1, 3}}, 4),
        Arguments.of("Example 2", 5, new int[][] {{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}},
            5),
        Arguments.of("Example 3", 8, new int[][] {{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}},
            5), Arguments.of("Given no roads, should return maxRank = 0", 2, new int[][] {}, 0),
        Arguments.of("Given not all cities are connected, should return maxRank", 6,
            new int[][] {{2, 4}}, 1));
  }

  public static Stream<Arguments> numEnclavesParams() {
    return Stream.of(Arguments.of("Example 1",
            new int[][] {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}}, 3),
        Arguments.of("Example 2",
            new int[][] {{0, 1, 1, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}}, 0),
        Arguments.of("Example 3",
            new int[][] {
                {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                {1, 1, 0, 0, 0, 1, 0, 1, 1, 1},
                {0, 0, 0, 1, 1, 1, 0, 1, 0, 0},
                {0, 1, 1, 0, 0, 0, 1, 0, 1, 0},
                {0, 1, 1, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                {0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 1}},
            3));
  }

  @BeforeEach
  void setUp() {
    graphs = new Graphs();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("findJudgeParams")
  void findJudge(String description, int n, int[][] trust, int expected) {
    assertEquals(expected, graphs.findJudge(n, trust));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maximalNetworkRankParams")
  void maximalNetworkRank(String description, int n, int[][] roads, int expected) {
    assertEquals(expected, graphs.maximalNetworkRank(n, roads));
  }

  public static Stream<Arguments> islandPerimeterParams() {
    return Stream.of(Arguments.of("Example 1",
            new int[][] {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}}, 16),
        Arguments.of("Example 2", new int[][] {{1}}, 4),
        Arguments.of("Example 3", new int[][] {{1, 0}}, 4),
        Arguments.of("Example 4", new int[][] {{0, 1}}, 4));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("islandPerimeterParams")
  void islandPerimeter(String description, int[][] grid, int expected) {
    assertEquals(expected, graphs.islandPerimeter(grid));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("numEnclavesParams")
  void numEnclaves(String description, int[][] grid, int expected) {
    assertEquals(expected, graphs.numEnclaves(grid));
  }
}