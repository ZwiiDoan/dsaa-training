package per.duyd.training.dsaa.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BinarySearchTreesTest {

  BinarySearchTrees binarySearchTrees = new BinarySearchTrees();

  public static Stream<Arguments> searchBSTParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7)),
            2,
            new TreeNode(2, new TreeNode(1), new TreeNode(3))
        ),
        Arguments.of("Example 2",
            new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(7)),
            5,
            null
        )
    );
  }

  public static Stream<Arguments> getAllElementsParams() {
    return Stream.of(Arguments.of("Example 1", new TreeNode(2, new TreeNode(1), new TreeNode(4)),
            new TreeNode(1, new TreeNode(0), new TreeNode(3)), List.of(0, 1, 1, 2, 3, 4)),
        Arguments.of("Example 2", new TreeNode(1, null, new TreeNode(8)),
            new TreeNode(8, new TreeNode(1), null), List.of(1, 1, 8, 8)));
  }

  public static Stream<Arguments> lowestCommonAncestorParams() {
    return Stream.of(
        Arguments.of(
            "Example 1",
            new TreeNode(6,
                new TreeNode(2,
                    new TreeNode(0),
                    new TreeNode(4,
                        new TreeNode(3),
                        new TreeNode(5))),
                new TreeNode(8,
                    new TreeNode(7),
                    new TreeNode(9))
            ),
            new TreeNode(2),
            new TreeNode(8),
            new TreeNode(6)
        ),
        Arguments.of(
            "Example 2",
            new TreeNode(6,
                new TreeNode(2,
                    new TreeNode(0),
                    new TreeNode(4,
                        new TreeNode(3),
                        new TreeNode(5))),
                new TreeNode(8,
                    new TreeNode(7),
                    new TreeNode(9))
            ),
            new TreeNode(2),
            new TreeNode(4),
            new TreeNode(2)
        ),
        Arguments.of(
            "Example 3",
            new TreeNode(2, new TreeNode(1), null),
            new TreeNode(2),
            new TreeNode(1),
            new TreeNode(2)
        )
    );
  }

  public static Stream<Arguments> deleteNodeParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)),
                new TreeNode(6, null, new TreeNode(7))),
            3,
            new TreeNode(5, new TreeNode(4, new TreeNode(2), null),
                new TreeNode(6, null, new TreeNode(7)))
        ),
        Arguments.of("Example 2",
            new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)),
                new TreeNode(6, null, new TreeNode(7))),
            0,
            new TreeNode(5, new TreeNode(3, new TreeNode(2), new TreeNode(4)),
                new TreeNode(6, null, new TreeNode(7)))
        ),
        Arguments.of("Example 3", null, 0, null)
    );
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("searchBSTParams")
  void searchBST(String description, TreeNode root, int val, TreeNode expected) {
    assertTrue(binarySearchTrees.isSameTree(expected, binarySearchTrees.searchBST(root, val)));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("getAllElementsParams")
  void getAllElements(String description, TreeNode root1, TreeNode root2, List<Integer> expected) {
    assertIterableEquals(expected, binarySearchTrees.getAllElements(root1, root2));
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("lowestCommonAncestorParams")
  void lowestCommonAncestor(String description, TreeNode root, TreeNode p, TreeNode q,
                            TreeNode expected) {
    assertEquals(expected.val, binarySearchTrees.lowestCommonAncestor(root, p, q).val);
    assertEquals(expected.val, binarySearchTrees.betterLowestCommonAncestor(root, p, q).val);
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("deleteNodeParams")
  void deleteNode(String description, TreeNode root, int key, TreeNode expected) {
    assertTrue(binarySearchTrees.isSameTree(expected, binarySearchTrees.deleteNode(root, key)));
  }

}