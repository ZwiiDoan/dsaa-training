package per.duyd.training.dsaa.treesandgraphs;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>An important aspect of BST is the nodes are unique</p>
 *
 * <p>Successor = "after node", i.e. the next node, or the smallest node after the current one.
 * It's also the next node in the inorder traversal. To find a successor, go to the right once and then as many times to the left as you could.
 * </p>
 *
 * <p>Predecessor = "before node", i.e. the previous node, or the largest node before the current one.
 * It's also the previous node in the inorder traversal. To find a predecessor, go to the left once and then as many times to the right as you could.
 * </p>
 */
public class BinarySearchTrees extends BinaryTrees {

  public TreeNode searchBST(TreeNode root, int val) {
    if (root == null) {
      return null;
    } else if (root.val == val) {
      return root;
    } else if (root.val > val) {
      return searchBST(root.left, val);
    } else {
      return searchBST(root.right, val);
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

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    List<TreeNode> pathToP = new ArrayList<>();
    List<TreeNode> pathToQ = new ArrayList<>();
    buildPathToTargetNode(root, p, pathToP);
    buildPathToTargetNode(root, q, pathToQ);

    TreeNode lowestCommonAncestor = root;
    int maxCommonPathLength = Math.min(pathToQ.size(), pathToP.size());
    for (int i = 1; i < maxCommonPathLength; i++) {
      if (pathToP.get(i).val == pathToQ.get(i).val) {
        lowestCommonAncestor = pathToP.get(i);
      } else {
        break;
      }
    }

    return lowestCommonAncestor;
  }

  private void buildPathToTargetNode(TreeNode node, TreeNode target, List<TreeNode> path) {
    if (node == null) {
      return;
    }

    path.add(node);

    if (node.val > target.val) {
      buildPathToTargetNode(node.left, target, path);
    } else if (node.val < target.val) {
      buildPathToTargetNode(node.right, target, path);
    }
  }

  public TreeNode betterLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    TreeNode current = root;

    while (current != null) {
      if (current.val > p.val && current.val > q.val) {
        current = current.left;
      } else if (current.val < p.val && current.val < q.val) {
        current = current.right;
      } else {
        return current;
      }
    }

    return null;
  }

  public TreeNode deleteNode(TreeNode root, int key) {
    if (root == null) {
      return null;
    }

    if (root.val < key) {
      root.right = deleteNode(root.right, key);
    } else if (root.val > key) {
      root.left = deleteNode(root.left, key);
    } else {
      if (root.left == null && root.right == null) {
        return null;
      } else if (root.right != null) {
        root.val = findSuccessor(root);
        root.right = deleteNode(root.right, root.val);
      } else {
        root.val = findPredecessor(root);
        root.left = deleteNode(root.left, root.val);
      }
    }

    return root;
  }

  private int findSuccessor(TreeNode root) {
    TreeNode successor = root.right;
    while (successor.left != null) {
      successor = successor.left;
    }
    return successor.val;
  }

  private int findPredecessor(TreeNode root) {
    TreeNode predecessor = root.left;
    while (predecessor.right != null) {
      predecessor = predecessor.right;
    }
    return predecessor.val;
  }

}
