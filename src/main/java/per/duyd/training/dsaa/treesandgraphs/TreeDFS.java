package per.duyd.training.dsaa.treesandgraphs;

import java.util.concurrent.atomic.AtomicInteger;

public class TreeDFS {

  public int diameterOfBinaryTree(TreeNode root) {
    AtomicInteger diameter = new AtomicInteger(0);
    findLongestPath(root, diameter);
    return diameter.get();
  }

  private int findLongestPath(TreeNode node, AtomicInteger diameter) {
    if (node == null) {
      return 0;
    }

    int leftLongestPath = findLongestPath(node.left, diameter);
    int rightLongestPath = findLongestPath(node.right, diameter);
    int longestPath = leftLongestPath + rightLongestPath;

    if (longestPath > diameter.get()) {
      diameter.set(longestPath);
    }

    return Math.max(leftLongestPath, rightLongestPath) + 1;
  }

  public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
      return true;
    } else if (p == null || q == null) {
      return false;
    } else {
      return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
  }

  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) {
      return null;
    } else if (root.val == val) {
      return root;
    } else if (root.val < val) {
      return searchBST(root.right, val);
    } else {
      return searchBST(root.left, val);
    }
  }
}
