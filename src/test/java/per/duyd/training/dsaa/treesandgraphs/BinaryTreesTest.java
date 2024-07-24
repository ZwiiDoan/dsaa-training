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

  public static Stream<Arguments> getAllElementsParams() {
    return Stream.of(Arguments.of("Example 1", new TreeNode(2, new TreeNode(1), new TreeNode(4)),
            new TreeNode(1, new TreeNode(0), new TreeNode(3)), List.of(0, 1, 1, 2, 3, 4)),
        Arguments.of("Example 2", new TreeNode(1, null, new TreeNode(8)),
            new TreeNode(8, new TreeNode(1), null), List.of(1, 1, 8, 8)));
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
  @MethodSource("getAllElementsParams")
  void getAllElements(String description, TreeNode root1, TreeNode root2, List<Integer> expected) {
    assertIterableEquals(expected, binaryTrees.getAllElements(root1, root2));
  }
}