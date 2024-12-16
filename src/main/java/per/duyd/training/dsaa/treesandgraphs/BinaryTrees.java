package per.duyd.training.dsaa.treesandgraphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
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

  public boolean isSymmetric(TreeNode root) {
    if (root == null) {
      return false;
    }

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      TreeNode[] levelNodes = new TreeNode[levelSize];

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();

        if (node != null) {
          queue.offer(node.left);
          queue.offer(node.right);
        }

        levelNodes[i] = node;
      }

      for (int i = 0, j = levelSize - 1; i < j; i++, j--) {
        if (levelNodes[i] == levelNodes[j]) {
          continue;
        } else if (levelNodes[i] == null || levelNodes[j] == null) {
          return false;
        } else if (levelNodes[i].val != levelNodes[j].val) {
          return false;
        }
      }
    }

    return true;
  }

  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> resultPaths = new ArrayList<>();
    if (root == null) {
      return resultPaths;
    }

    Stack<NodeAndPath> treeStack = new Stack<>();
    treeStack.push(new NodeAndPath(root, List.of(root.val), root.val));

    while (!treeStack.isEmpty()) {
      NodeAndPath nodeAndPath = treeStack.pop();

      if (nodeAndPath.pathSum == targetSum) {
        if (nodeAndPath.treeNode.left == null && nodeAndPath.treeNode.right == null) {
          resultPaths.add(new ArrayList<>(nodeAndPath.path));
        }
      }

      if (nodeAndPath.treeNode.right != null) {
        List<Integer> newPath = new ArrayList<>(nodeAndPath.path);
        newPath.add(nodeAndPath.treeNode.right.val);
        treeStack.push(new NodeAndPath(nodeAndPath.treeNode.right, newPath,
            nodeAndPath.pathSum + nodeAndPath.treeNode.right.val));
      }

      if (nodeAndPath.treeNode.left != null) {
        List<Integer> newPath = new ArrayList<>(nodeAndPath.path);
        newPath.add(nodeAndPath.treeNode.left.val);
        treeStack.push(new NodeAndPath(nodeAndPath.treeNode.left, newPath,
            nodeAndPath.pathSum + nodeAndPath.treeNode.left.val));
      }
    }

    return resultPaths;
  }

  public List<List<Integer>> pathSumBackTracking(TreeNode root, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    backtrack(root, targetSum, path, result);
    return result;
  }

  private void backtrack(TreeNode node, int remainingSum, List<Integer> path,
                         List<List<Integer>> result) {
    if (node == null) {
      return;
    }

    path.add(node.val);

    if (node.left == null && node.right == null && remainingSum == node.val) {
      result.add(new ArrayList<>(path));
    } else {
      backtrack(node.left, remainingSum - node.val, path, result);
      backtrack(node.right, remainingSum - node.val, path, result);
    }

    path.remove(path.size() - 1); // backtrack
  }

  private static class NodeAndPath {
    TreeNode treeNode;
    List<Integer> path;
    int pathSum;

    public NodeAndPath(TreeNode treeNode, List<Integer> path, int pathSum) {
      this.treeNode = treeNode;
      this.path = path;
      this.pathSum = pathSum;
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

  public boolean leafSimilar(TreeNode root1, TreeNode root2) {
    if (root1 == null && root2 == null) {
      return true;
    }

    List<Integer> leafSequence1 = new ArrayList<>();
    List<Integer> leafSequence2 = new ArrayList<>();

    getLeaf(root1, leafSequence1);
    getLeaf(root2, leafSequence2);

    return leafSequence1.equals(leafSequence2);
  }

  private void getLeaf(TreeNode node, List<Integer> leafSequence) {
//    if (node != null) {
//      if (node.left != null) {
//        getLeaf(node.left, leafSequence);
//      }
//
//      if (node.right != null) {
//        getLeaf(node.right, leafSequence);
//      }
//
//      if (node.left == null && node.right == null) {
//        leafSequence.add(node.val);
//      }
//    }
    Stack<TreeNode> stack = new Stack<>();
    stack.push(node);

    while (!stack.isEmpty()) {
      TreeNode currentNode = stack.pop();

      if (currentNode == null) {
        continue;
      }

      if (currentNode.right != null) {
        stack.push(currentNode.right);
      }

      if (currentNode.left != null) {
        stack.push(currentNode.left);
      }

      if (currentNode.left == null && currentNode.right == null) {
        leafSequence.add(currentNode.val);
      }
    }
  }

  public TreeNode invertTree(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode tmp = root.right;
    root.right = invertTree(root.left);
    root.left = invertTree(tmp);

    return root;
  }

  public TreeNode removeLeafNodes(TreeNode root, int target) {
    if (root == null) {
      return null;
    }

    root.left = removeLeafNodes(root.left, target);
    root.right = removeLeafNodes(root.right, target);

    if (root.left == null && root.right == null && root.val == target) {
      return null;
    } else {
      return root;
    }
  }

  public int pathSumCount(TreeNode root, int targetSum) {
    Map<Long, Integer> preSumMap = new HashMap<>();
    return countPathsWithTargetSum(root, 0, targetSum, preSumMap);
  }

  private int countPathsWithTargetSum(TreeNode root, long previousSum, int targetSum,
                                      Map<Long, Integer> preSumMap) {
    if (root == null) {
      return 0;
    }

    int count = 0;
    long currentSum = root.val + previousSum;

    if (currentSum == targetSum) {
      count++;
    }

    count += preSumMap.getOrDefault(currentSum - targetSum, 0);

    preSumMap.put(currentSum, preSumMap.getOrDefault(currentSum, 0) + 1);

    count += countPathsWithTargetSum(root.left, currentSum, targetSum, preSumMap);
    count += countPathsWithTargetSum(root.right, currentSum, targetSum, preSumMap);

    preSumMap.put(currentSum, preSumMap.getOrDefault(currentSum, 0) - 1);

    return count;
  }

  public int pathSumCountIterative(TreeNode root, int targetSum) {
    if (root == null) {
      return 0;
    }

    int count = 0;
    Stack<SumNode> stack = new Stack<>();
    stack.push(new SumNode(root, 0, new HashMap<>()));

    while (!stack.isEmpty()) {
      SumNode sumNode = stack.pop();
      TreeNode currentNode = sumNode.node;
      long previousSum = sumNode.previousSum;
      Map<Long, Integer> previousMap = sumNode.preSumMap;

      if (currentNode == null) {
        continue;
      }

      long currentSum = previousSum + currentNode.val;

      if (currentSum == targetSum) {
        count++;
      }

      count += previousMap.getOrDefault(currentSum - targetSum, 0);

      Map<Long, Integer> currentMap = new HashMap<>(previousMap);
      currentMap.put(currentSum, previousMap.getOrDefault(currentSum, 0) + 1);

      stack.push(new SumNode(currentNode.right, currentSum, currentMap));
      stack.push(new SumNode(currentNode.left, currentSum, currentMap));
    }

    return count;
  }

  record SumNode(TreeNode node, long previousSum, Map<Long, Integer> preSumMap) {
  }

  public static final boolean DIRECTION_LEFT = true;
  public static final boolean DIRECTION_RIGHT = false;

  public int longestZigZag(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return Math.max(longestZigZag(root.left, 0, DIRECTION_LEFT),
        longestZigZag(root.right, 0, DIRECTION_RIGHT));
  }

  private int longestZigZag(TreeNode currentNode, int currentLength, boolean currentDirection) {
    if (currentNode == null) {
      return currentLength;
    }

    currentLength++;

    return Math.max(
        longestZigZag(currentNode.left, currentDirection == DIRECTION_RIGHT ? currentLength : 0,
            DIRECTION_LEFT),
        longestZigZag(currentNode.right, currentDirection == DIRECTION_LEFT ? currentLength : 0,
            DIRECTION_RIGHT));
  }

  public int longestZigZagIterative(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int maxZigZagLength = 0;

    Stack<ZigZagNode> stack = new Stack<>();
    stack.push(new ZigZagNode(root.right, 0, DIRECTION_RIGHT));
    stack.push(new ZigZagNode(root.left, 0, DIRECTION_LEFT));

    while (!stack.isEmpty()) {
      ZigZagNode currentNode = stack.pop();
      if (currentNode.node == null) {
        maxZigZagLength = Math.max(maxZigZagLength, currentNode.currentLength);
      } else {
        stack.push(new ZigZagNode(currentNode.node.right,
            currentNode.currentDirection == DIRECTION_LEFT ? currentNode.currentLength + 1 : 0,
            DIRECTION_RIGHT));
        stack.push(new ZigZagNode(currentNode.node.left,
            currentNode.currentDirection == DIRECTION_RIGHT ? currentNode.currentLength + 1 : 0,
            DIRECTION_LEFT));
      }
    }

    return maxZigZagLength;
  }

  record ZigZagNode(TreeNode node, int currentLength, boolean currentDirection) {
  }

  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    Queue<TreeNode> queue = new ArrayDeque<>();
    if (root == null) {
      return ans;
    }

    queue.offer(root);
    while (!queue.isEmpty()) {
      List<Integer> levelNodes = new ArrayList<>();
      int levelSize = queue.size();

      while (levelSize > 0) {
        TreeNode node = queue.poll();
        levelSize--;
        levelNodes.add(node.val);

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      ans.add(levelNodes);
    }

    return ans;
  }

  public int maxLevelSum(TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);

    int maxSum = Integer.MIN_VALUE;
    int maxLevel = 1;
    int currLevel = 0;

    while (!queue.isEmpty()) {
      currLevel++;
      int levelSize = queue.size();
      int levelSum = 0;

      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        levelSum += node.val;

        if (node.left != null) {
          queue.offer(node.left);
        }

        if (node.right != null) {
          queue.offer(node.right);
        }
      }

      if (levelSum > maxSum) {
        maxSum = levelSum;
        maxLevel = currLevel;
      }
    }

    return maxLevel;
  }

  public List<Double> averageOfLevels(TreeNode root) {
    Queue<TreeNode> queue = new ArrayDeque<>();
    List<Double> levelAverages = new ArrayList<>();

    if (root == null) {
      return List.of();
    }

    queue.offer(root);

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      double levelSum = 0;

      for (int i = 0; i < levelSize; i++) {
        TreeNode treeNode = queue.poll();
        levelSum += treeNode.val;

        if (treeNode.left != null) {
          queue.offer(treeNode.left);
        }
        if (treeNode.right != null) {
          queue.offer(treeNode.right);
        }
      }

      levelAverages.add(levelSum / levelSize);
    }

    return levelAverages;
  }

  public boolean isEvenOddTree(TreeNode root) {
    if (root == null) {
      return false;
    }

    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    boolean isEvenLevel = true;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();

      int previousValue = isEvenLevel ? Integer.MIN_VALUE : Integer.MAX_VALUE;
      for (int i = 0; i < levelSize; i++) {
        TreeNode treeNode = queue.poll();
        if (isEvenLevel && (previousValue >= treeNode.val || treeNode.val % 2 == 0)) {
          return false;
        } else if (!isEvenLevel && (previousValue <= treeNode.val || treeNode.val % 2 != 0)) {
          return false;
        } else {
          previousValue = treeNode.val;
        }

        if (treeNode.left != null) {
          queue.offer(treeNode.left);
        }
        if (treeNode.right != null) {
          queue.offer(treeNode.right);
        }
      }

      isEvenLevel = !isEvenLevel;
    }

    return true;
  }
}
