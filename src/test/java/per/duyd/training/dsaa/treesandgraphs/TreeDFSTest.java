package per.duyd.training.dsaa.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TreeDFSTest {

  private TreeDFS treeDfs;

  public static Stream<Arguments> diameterOfBinaryTreeParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new TreeNode(1,
                new TreeNode(2,
                    new TreeNode(4),
                    new TreeNode(5)),
                new TreeNode(3)),
            3),
        Arguments.of("Example 2",
            new TreeNode(1,
                new TreeNode(2),
                null),
            1)
    );
  }

  public static Stream<Arguments> isSameTreeParams() {
    return Stream.of(
        Arguments.of(
            "Example 1",
            new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3)),
            new TreeNode(1,
                new TreeNode(2),
                new TreeNode(3)),
            true
        ),
        Arguments.of(
            "Example 2",
            new TreeNode(1,
                new TreeNode(2),
                null),
            new TreeNode(1,
                null,
                new TreeNode(2)),
            false
        ),
        Arguments.of(
            "Example 3",
            new TreeNode(1,
                new TreeNode(2),
                new TreeNode(1)),
            new TreeNode(1,
                new TreeNode(1),
                new TreeNode(2)),
            false
        )
    );
  }

  public static Stream<Arguments> searchBSTParams() {
    return Stream.of(
        Arguments.of(
            "Search value exists in the tree",
            new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7)),
            2,
            new TreeNode(2, new TreeNode(1), new TreeNode(3))
        ),
        Arguments.of(
            "Search value does NOT exist in the tree",
            new TreeNode(4,
                new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                new TreeNode(7)),
            5,
            null
        )
    );
  }

  @BeforeEach
  void setUp() {
    treeDfs = new TreeDFS();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("diameterOfBinaryTreeParams")
  void diameterOfBinaryTree(String description, TreeNode root, int expected) {
    assertEquals(expected, treeDfs.diameterOfBinaryTree(root));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("isSameTreeParams")
  void isSameTree(String description, TreeNode p, TreeNode q, boolean expected) {
    assertEquals(expected, treeDfs.isSameTree(p, q));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("searchBSTParams")
  void searchBST(String description, TreeNode root, int val, TreeNode expected) {
    assertTrue(treeDfs.isSameTree(expected, treeDfs.searchBST(root, val)));
  }
}