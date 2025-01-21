package per.duyd.training.dsaa.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BinaryTreesTest {

  private BinaryTrees binaryTrees;

  public static Stream<Arguments> deepestLeavesSumParams() {
    return Stream.of(Arguments.of("Example 1",
            new TreeNode(1, new TreeNode(2, new TreeNode(4, new TreeNode(7), null), new TreeNode(5)),
                new TreeNode(3, null, new TreeNode(6, null, new TreeNode(8)))), 15),
        Arguments.of("Example 2", new TreeNode(6,
            new TreeNode(7, new TreeNode(2, new TreeNode(9), null),
                new TreeNode(7, new TreeNode(1), new TreeNode(4))),
            new TreeNode(8, new TreeNode(1), new TreeNode(3, null, new TreeNode(5)))), 19));
  }

  public static Stream<Arguments> leafSimilarParams() {
    return Stream.of(Arguments.of("Example 1", new TreeNode(3,
            new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
            new TreeNode(1, new TreeNode(9), new TreeNode(8))),
        new TreeNode(3, new TreeNode(5, new TreeNode(6), new TreeNode(7)),
            new TreeNode(1, new TreeNode(4), new TreeNode(2, new TreeNode(9), new TreeNode(8)))),
        true), Arguments.of("Example 2", new TreeNode(1, new TreeNode(2), new TreeNode(3)),
        new TreeNode(1, new TreeNode(3), new TreeNode(2)), false));
  }

  public static Stream<Arguments> invertTreeParams() {
    return Stream.of(Arguments.of("Example 1",
            new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7, new TreeNode(6), new TreeNode(9))),
            new TreeNode(4, new TreeNode(7, new TreeNode(9), new TreeNode(6)),
                new TreeNode(2, new TreeNode(3), new TreeNode(1)))),
        Arguments.of("Example 2", new TreeNode(2, new TreeNode(1), new TreeNode(3)),
            new TreeNode(2, new TreeNode(3), new TreeNode(1))));
  }

  public static Stream<Arguments> isSymmetricParams() {
    return Stream.of(Arguments.of("Example 1",
        new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3))), true), Arguments.of("Example 2",
        new TreeNode(1, new TreeNode(2, null, new TreeNode(3)),
            new TreeNode(2, null, new TreeNode(3))), false));
  }

  public static Stream<Arguments> pathSumParams() {
    return Stream.of(
        Arguments.of("Example 1", new TreeNode(5,
                new TreeNode(4,
                    new TreeNode(11,
                        new TreeNode(7), new TreeNode(2)),
                    null),
                new TreeNode(8,
                    new TreeNode(13),
                    new TreeNode(4,
                        new TreeNode(5),
                        new TreeNode(1)))),
            22,
            List.of(List.of(5, 4, 11, 2), List.of(5, 8, 4, 5))
        ),
        Arguments.of("Example 2", new TreeNode(1, new TreeNode(2), new TreeNode(3)), 5, List.of())
    );
  }

  public static Stream<Arguments> removeLeafNodesParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new TreeNode(1,
                new TreeNode(2, new TreeNode(2), null),
                new TreeNode(3, new TreeNode(2), new TreeNode(4))),
            2,
            new TreeNode(1, null, new TreeNode(3, null, new TreeNode(4)))),
        Arguments.of("Example 2",
            new TreeNode(1,
                new TreeNode(3, new TreeNode(3), new TreeNode(2)),
                new TreeNode(3)),
            3,
            new TreeNode(1, new TreeNode(3, null, new TreeNode(2)), null)),
        Arguments.of("Example 3",
            new TreeNode(1, new TreeNode(2, new TreeNode(2, new TreeNode(2), null), null), null), 2,
            new TreeNode(1)
        )
    );
  }

  public static Stream<Arguments> pathSumCountParams() {
    return Stream.of(
        Arguments.of("Example 1", new TreeNode(10,
                new TreeNode(5,
                    new TreeNode(3, new TreeNode(3), new TreeNode(-2)),
                    new TreeNode(2, null, new TreeNode(1))),
                new TreeNode(-3, null, new TreeNode(11))),
            8,
            3
        ),
        Arguments.of("Example 2", new TreeNode(5,
                new TreeNode(4,
                    new TreeNode(11, new TreeNode(7), new TreeNode(2)),
                    null),
                new TreeNode(8,
                    new TreeNode(13, new TreeNode(5), new TreeNode(1)),
                    new TreeNode(4))),
            22,
            3
        ),
        Arguments.of("Example 3", new TreeNode(1), 0, 0)
    );
  }

  public static Stream<Arguments> longestZigZagParams() {
    return Stream.of(
        Arguments.of(
            "Example 1",
            new TreeNode(1,
                null,
                new TreeNode(1,
                    new TreeNode(1),
                    new TreeNode(1,
                        new TreeNode(1,
                            null,
                            new TreeNode(1, null, new TreeNode(1))),
                        new TreeNode(1)
                    )
                )
            ), 3
        ),
        Arguments.of(
            "Example 2",
            new TreeNode(1,
                new TreeNode(1,
                    null,
                    new TreeNode(1,
                        new TreeNode(1,
                            null,
                            new TreeNode(1)),
                        new TreeNode(1))),
                new TreeNode(1)
            ), 4
        )
    );
  }

  public static Stream<Arguments> levelOrderParams() {
    return Stream.of(
        Arguments.of(
            "Example 1",
            new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20,
                    new TreeNode(15),
                    new TreeNode(7)
                )
            ), List.of(List.of(3), List.of(9, 20), List.of(15, 7))
        ),
        Arguments.of(
            "Example 2",
            new TreeNode(1),
            List.of(List.of(1))
        ),
        Arguments.of(
            "Example 3",
            null,
            List.of()
        )
    );
  }

  public static Stream<Arguments> maxLevelSumParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new TreeNode(1,
                new TreeNode(7, new TreeNode(7), new TreeNode(-8)),
                new TreeNode(0)
            ), 2
        ),
        Arguments.of("Example 2",
            new TreeNode(989,
                null,
                new TreeNode(10250,
                    new TreeNode(98693),
                    new TreeNode(-89388, null, new TreeNode(-32127)))
            ), 2
        )
    );
  }

  public static Stream<Arguments> averageOfLevelsParams() {
    return Stream.of(
        Arguments.of(
            "Example 1",
            new TreeNode(3,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7))),
            List.of(3d, 14.5d, 11d)
        ),
        Arguments.of(
            "Example 2",
            new TreeNode(3,
                new TreeNode(9, new TreeNode(15), new TreeNode(7)),
                new TreeNode(20)),
            List.of(3d, 14.5d, 11d)
        )
    );
  }

  public static Stream<Arguments> isEvenOddTreeParams() {
    return Stream.of(
        Arguments.of(
            "Example 1",
            new TreeNode(1,
                new TreeNode(10,
                    new TreeNode(3, new TreeNode(12), new TreeNode(8)),
                    null),
                new TreeNode(4,
                    new TreeNode(7, new TreeNode(6), null),
                    new TreeNode(9, null, new TreeNode(2)))),
            true
        ),
        Arguments.of(
            "Example 2",
            new TreeNode(5,
                new TreeNode(4, new TreeNode(3), new TreeNode(3)),
                new TreeNode(2, new TreeNode(7), null)),
            false
        ),
        Arguments.of(
            "Example 3",
            new TreeNode(5,
                new TreeNode(9, new TreeNode(3), new TreeNode(5)),
                new TreeNode(1, new TreeNode(7), null)),
            false
        )
    );
  }

  @BeforeEach
  void setUp() {
    binaryTrees = new BinaryTrees();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("deepestLeavesSumParams")
  void deepestLeavesSum(String description, TreeNode root, int expected) {
    assertEquals(expected, binaryTrees.deepestLeavesSum(root));
  }

  public static Stream<Arguments> diameterOfBinaryTreeParams() {
    return Stream.of(Arguments.of("Example 1",
            new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3)), 3),
        Arguments.of("Example 2", new TreeNode(1, new TreeNode(2), null), 1));
  }

  public static Stream<Arguments> isSameTreeParams() {
    return Stream.of(Arguments.of("Example 1", new TreeNode(1, new TreeNode(2), new TreeNode(3)),
            new TreeNode(1, new TreeNode(2), new TreeNode(3)), true),
        Arguments.of("Example 2", new TreeNode(1, new TreeNode(2), null),
            new TreeNode(1, null, new TreeNode(2)), false),
        Arguments.of("Example 3", new TreeNode(1, new TreeNode(2), new TreeNode(1)),
            new TreeNode(1, new TreeNode(1), new TreeNode(2)), false));
  }

  public static Stream<Arguments> searchBSTParams() {
    return Stream.of(Arguments.of("Search value exists in the tree",
            new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7)), 2,
            new TreeNode(2, new TreeNode(1), new TreeNode(3))),
        Arguments.of("Search value does NOT exist in the tree",
            new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7)), 5,
            null));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("diameterOfBinaryTreeParams")
  void diameterOfBinaryTree(String description, TreeNode root, int expected) {
    assertEquals(expected, binaryTrees.diameterOfBinaryTree(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isSameTreeParams")
  void isSameTree(String description, TreeNode p, TreeNode q, boolean expected) {
    assertEquals(expected, binaryTrees.isSameTree(p, q));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("searchBSTParams")
  void searchBST(String description, TreeNode root, int val, TreeNode expected) {
    assertTrue(binaryTrees.isSameTree(expected, binaryTrees.searchBST(root, val)));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("leafSimilarParams")
  void leafSimilar(String description, TreeNode root1, TreeNode root2, boolean expected) {
    assertEquals(expected, binaryTrees.leafSimilar(root1, root2));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("invertTreeParams")
  void invertTree(String description, TreeNode root, TreeNode expected) {
    assertTrue(binaryTrees.isSameTree(expected, binaryTrees.invertTree(root)));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isSymmetricParams")
  void isSymmetric(String description, TreeNode root, boolean expected) {
    assertEquals(expected, binaryTrees.isSymmetric(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("pathSumParams")
  void pathSum(String description, TreeNode root, int targetSum, List<List<Integer>> expected) {
    assertIterableEquals(expected, binaryTrees.pathSum(root, targetSum));
    assertIterableEquals(expected, binaryTrees.pathSumBackTracking(root, targetSum));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("removeLeafNodesParams")
  void removeLeafNodes(String description, TreeNode root, int target, TreeNode expected) {
    assertTrue(binaryTrees.isSameTree(expected, binaryTrees.removeLeafNodes(root, target)));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("pathSumCountParams")
  void pathSumCount(String description, TreeNode root, int targetSum, int expected) {
    assertEquals(expected, binaryTrees.pathSumCount(root, targetSum));
    assertEquals(expected, binaryTrees.pathSumCountIterative(root, targetSum));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("longestZigZagParams")
  void longestZigZag(String description, TreeNode root, int expected) {
    assertEquals(expected, binaryTrees.longestZigZag(root));
    assertEquals(expected, binaryTrees.longestZigZagIterative(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("levelOrderParams")
  void levelOrder(String description, TreeNode root, List<List<Integer>> expected) {
    assertIterableEquals(expected, binaryTrees.levelOrder(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("maxLevelSumParams")
  void maxLevelSum(String description, TreeNode root, int expected) {
    assertEquals(expected, binaryTrees.maxLevelSum(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("averageOfLevelsParams")
  void averageOfLevels(String description, TreeNode root, List<Double> expected) {
    assertIterableEquals(expected, binaryTrees.averageOfLevels(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isEvenOddTreeParams")
  void isEvenOddTree(String description, TreeNode root, boolean expected) {
    assertEquals(expected, binaryTrees.isEvenOddTree(root));
  }
}