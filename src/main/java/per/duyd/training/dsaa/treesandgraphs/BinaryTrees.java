package per.duyd.training.dsaa.treesandgraphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTrees {
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

  public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
    List<Integer> elements1 = new ArrayList<>();
    getAllElements(root1, elements1);
    List<Integer> elements2 = new ArrayList<>();
    getAllElements(root2, elements2);

    List<Integer> allElements = new ArrayList<>();
    int i = 0, j = 0;
    while (i < elements1.size() && j < elements2.size()) {
      int element1 = elements1.get(i);
      int element2 = elements2.get(j);
      if (element1 <= element2) {
        allElements.add(element1);
        i++;
      } else {
        allElements.add(element2);
        j++;
      }
    }

    while (i < elements1.size()) {
      allElements.add(elements1.get(i));
      i++;
    }

    while (j < elements2.size()) {
      allElements.add(elements2.get(j));
      j++;
    }

    return allElements;
  }

  private void getAllElements(TreeNode node, List<Integer> elements) {
    if (node != null) {
      getAllElements(node.left, elements);
      elements.add(node.val);
      getAllElements(node.right, elements);
    }
  }
}
