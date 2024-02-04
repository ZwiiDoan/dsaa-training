import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KdTree {
  private KdNode root;
  private int size;

  private static final int K = 2;

  private static class KdNode {
    private Point2D point2D;
    private KdNode left;
    private KdNode right;
    private final int level;

    private KdNode(Point2D point2D, int level) {
      this.point2D = point2D;
      this.level = level;
    }
  }

  private static class BtNode {
    private final double splitDistance;
    private final KdNode node;

    public BtNode(double splitDistance, KdNode node) {
      this.splitDistance = splitDistance;
      this.node = node;
    }
  }

  private static class NearestNeighbor {
    private final KdNode node;
    private final double minDistance;

    public NearestNeighbor(KdNode node, double minDistance) {
      this.node = node;
      this.minDistance = minDistance;
    }
  }

  // construct an empty set of points
  public KdTree() {
  }

  // is the set empty?
  public boolean isEmpty() {
    return root == null;
  }

  // number of points in the set
  public int size() {
    return size;
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    Optional.ofNullable(p).orElseThrow(IllegalArgumentException::new);
    root = addNode(root, 0, p);
  }

  private KdNode addNode(KdNode currentNode, int level, Point2D p) {
    if (currentNode == null) {
      size++;
      return new KdNode(p, level);
    }

    if (currentNode.point2D.compareTo(p) == 0) {
      currentNode.point2D = p;
      return currentNode;
    }

    if (splitCompare(currentNode, p) <= 0) {
      currentNode.left = addNode(currentNode.left, level + 1, p);
    } else {
      currentNode.right = addNode(currentNode.right, level + 1, p);
    }

    return currentNode;
  }

  private int splitCompare(KdNode parent, Point2D p) {
    int xCompare = Double.compare(p.x(), parent.point2D.x());
    int yCompare = Double.compare(p.y(), parent.point2D.y());

    if (parent.level % K == 0) {
      return xCompare != 0 ? xCompare : yCompare;
    } else {
      return yCompare != 0 ? yCompare : xCompare;
    }
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    Optional.ofNullable(p).orElseThrow(IllegalArgumentException::new);
    return searchNode(root, p) != null;
  }

  private KdNode searchNode(KdNode node, Point2D p) {
    if (node == null) {
      return null;
    }

    if (node.point2D.equals(p)) {
      return node;
    }

    int comparison = splitCompare(node, p);

    if (comparison < 0) {
      return searchNode(node.left, p);
    } else if (comparison > 0) {
      return searchNode(node.right, p);
    } else {
      KdNode nodeLeft = searchNode(node.left, p);
      return nodeLeft != null ? nodeLeft : searchNode(node.right, p);
    }
  }

  // draw all points to standard draw
  public void draw() {
    draw(null, root);
  }

  private void draw(KdNode parent, KdNode node) {
    if (node == null) {
      return;
    }

    //Draw split lines
    StdDraw.setPenRadius(0.01);
    if (parent != null) {
      int currentLevel = parent.level + 1;
      int comparison = splitCompare(parent, node.point2D);
      if (currentLevel % K == 0) {
        StdDraw.setPenColor(Color.RED);
        if (comparison <= 0) {
          StdDraw.line(node.point2D.x(), parent.point2D.y(), node.point2D.x(), 0.0);
        } else {
          StdDraw.line(node.point2D.x(), parent.point2D.y(), node.point2D.x(), 1.0);
        }
      } else {
        StdDraw.setPenColor(Color.BLUE);
        if (comparison <= 0) {
          StdDraw.line(parent.point2D.x(), node.point2D.y(), 0.0, node.point2D.y());
        } else {
          StdDraw.line(parent.point2D.x(), node.point2D.y(), 1.0, node.point2D.y());
        }
      }
    } else {
      StdDraw.setPenColor(Color.RED);
      StdDraw.line(node.point2D.x(), 0.0, node.point2D.x(), 1.0);
    }

    //Draw points
    StdDraw.setPenRadius(0.02);
    StdDraw.setPenColor(Color.BLACK);
    node.point2D.draw();
    draw(node, node.left);
    draw(node, node.right);
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    Optional.ofNullable(rect).orElseThrow(IllegalArgumentException::new);
    List<Point2D> points = new ArrayList<>();
    range(root, points, rect);
    return points;
  }

  private void range(KdNode currentNode, List<Point2D> points, RectHV rect) {
    if (currentNode == null) {
      return;
    }

    Point2D currentPoint = currentNode.point2D;
    if (rect.contains(currentPoint)) {
      points.add(currentPoint);
    }

    if (currentNode.level % K == 0) {
      if (rect.xmin() <= currentPoint.x()) {
        range(currentNode.left, points, rect);
      }

      if (rect.xmax() > currentPoint.x()) {
        range(currentNode.right, points, rect);
      }
    } else {
      if (rect.ymin() <= currentPoint.y()) {
        range(currentNode.left, points, rect);
      }

      if (rect.ymax() > currentPoint.y()) {
        range(currentNode.right, points, rect);
      }
    }
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    Optional.ofNullable(p).orElseThrow(IllegalArgumentException::new);
    if (isEmpty()) {
      return null;
    }

    Stack<KdNode> nodeStack = new Stack<>();
    Stack<BtNode> btStack = new Stack<>();
    NearestNeighbor nearestNeighbor = new NearestNeighbor(root, Double.POSITIVE_INFINITY);
    nodeStack.push(root);

    while (!nodeStack.isEmpty() || !btStack.isEmpty()) {
      if (!nodeStack.isEmpty()) {
        nearestNeighbor = nearest(nodeStack.pop(), nearestNeighbor, nodeStack, btStack, p);
      } else if (!btStack.isEmpty()) {
        BtNode btNode = btStack.pop();
        if (btNode.splitDistance <= nearestNeighbor.minDistance) {
          nearestNeighbor = nearest(btNode.node, nearestNeighbor, nodeStack, btStack, p);
        }
      }
    }

    return nearestNeighbor.node.point2D;
  }

  private NearestNeighbor nearest(KdNode currentNode, NearestNeighbor nearestNeighbor,
                                  Stack<KdNode> nodeStack,
                                  Stack<BtNode> btStack, Point2D p) {
    NearestNeighbor newNearestNeighbor = nearestNeighbor;
    Point2D currentPoint = currentNode.point2D;
    double currentDistance = p.distanceSquaredTo(currentPoint);

    if (currentDistance < nearestNeighbor.minDistance) {
      newNearestNeighbor = new NearestNeighbor(currentNode, currentDistance);
    }

    Point2D splitPoint = new Point2D(p.x(), currentPoint.y());
    if (currentNode.level % K == 0) {
      splitPoint = new Point2D(currentPoint.x(), p.y());
    }

    double splitDistance = p.distanceSquaredTo(splitPoint);
    int splitCompare = splitCompare(currentNode, p);

    if (splitCompare <= 0) {
      if (splitDistance <= newNearestNeighbor.minDistance && currentNode.right != null) {
        btStack.push(new BtNode(splitDistance, currentNode.right));
      }

      if (currentNode.left != null) {
        nodeStack.push(currentNode.left);
      }
    } else {
      if (splitDistance <= newNearestNeighbor.minDistance && currentNode.left != null) {
        btStack.push(new BtNode(splitDistance, currentNode.left));
      }

      if (currentNode.right != null) {
        nodeStack.push(currentNode.right);
      }
    }

    return newNearestNeighbor;
  }
}
