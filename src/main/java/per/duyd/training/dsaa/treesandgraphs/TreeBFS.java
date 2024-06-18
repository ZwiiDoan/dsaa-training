package per.duyd.training.dsaa.treesandgraphs;

import java.util.ArrayDeque;
import java.util.Queue;

public class TreeBFS {
  public int deepestLeavesSum(TreeNode root) {
    if (root == null) {
      return 0;
    }

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    int levelSum = 0;
    
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      levelSum = 0;

      while (levelSize > 0) {
        TreeNode node = queue.poll();
        levelSize--;
        levelSum += node.val;

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
      }
    }

    return levelSum;
  }
}
