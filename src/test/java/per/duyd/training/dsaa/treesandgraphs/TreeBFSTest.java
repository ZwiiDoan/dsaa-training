package per.duyd.training.dsaa.treesandgraphs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TreeBFSTest {

  private TreeBFS treeBFS;

  public static Stream<Arguments> deepestLeavesSumParams() {
    return Stream.of(
        Arguments.of("Example 1",
            new TreeNode(1,
                new TreeNode(2,
                    new TreeNode(4, new TreeNode(7), null),
                    new TreeNode(5)),
                new TreeNode(3,
                    null,
                    new TreeNode(6,
                        null,
                        new TreeNode(8)))),
            15
        ),
        Arguments.of("Example 2",
            new TreeNode(6,
                new TreeNode(7,
                    new TreeNode(2,
                        new TreeNode(9),
                        null),
                    new TreeNode(7,
                        new TreeNode(1),
                        new TreeNode(4))),
                new TreeNode(8,
                    new TreeNode(1),
                    new TreeNode(3,
                        null,
                        new TreeNode(5)))),
            19
        )
    );
  }

  @BeforeEach
  void setUp() {
    treeBFS = new TreeBFS();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("deepestLeavesSumParams")
  void deepestLeavesSum(String description, TreeNode root, int expected) {
    assertEquals(expected, treeBFS.deepestLeavesSum(root));
  }
}