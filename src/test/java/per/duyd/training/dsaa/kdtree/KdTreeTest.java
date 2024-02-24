package per.duyd.training.dsaa.kdtree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import org.junit.jupiter.api.Test;

class KdTreeTest {
  @Test
  public void testEmptyTree() {
    KdTree tree = new KdTree();
    assertTrue(tree.isEmpty());
    assertEquals(0, tree.size());
    assertNull(tree.nearest(new Point2D(0, 0)));
    assertFalse(tree.contains(new Point2D(1, 2)));
    assertFalse(tree.range(new RectHV(0, 0, 1, 1)).iterator().hasNext());
  }

  @Test
  public void testSinglePointInsertion() {
    KdTree tree = new KdTree();
    Point2D p = new Point2D(3, 4);
    tree.insert(p);
    assertFalse(tree.isEmpty());
    assertEquals(1, tree.size());
    assertTrue(tree.contains(p));
    assertEquals(p, tree.nearest(p));
  }

  @Test
  public void testMultiplePointInsertions() {
    KdTree tree = new KdTree();
    Point2D p1 = new Point2D(1, 2);
    Point2D p2 = new Point2D(5, 6);
    Point2D p3 = new Point2D(2, 3);
    tree.insert(p1);
    tree.insert(p2);
    tree.insert(p3);
    assertEquals(3, tree.size());
    assertTrue(tree.contains(p1));
    assertTrue(tree.contains(p2));
    assertTrue(tree.contains(p3));
  }

  @Test
  public void testRangeSearch() {
    KdTree tree = new KdTree();

    // Insert several points, including points within and outside the range
    Point2D p1 = new Point2D(1, 1); // Outside
    Point2D p2 = new Point2D(2.5, 3); // Inside
    Point2D p3 = new Point2D(4, 4); // Inside
    Point2D p4 = new Point2D(5, 2); // Outside
    tree.insert(p1);
    tree.insert(p2);
    tree.insert(p3);
    tree.insert(p4);

    RectHV rect = new RectHV(2, 2, 4, 4);  // Search range
    Iterable<Point2D> pointsInRange = tree.range(rect);

    // Verify that only points within the rectangle are returned
    int count = 0;
    for (Point2D point : pointsInRange) {
      assertTrue(rect.contains(point));  // Ensure each point is within the rectangle
      count++;
    }

    assertEquals(2, count);  // Assert that only the expected number of points were found
  }

  @Test
  public void testNearestNeighborSearch() {
    KdTree tree = new KdTree();

    // Insert several points with varying distances to the query point
    Point2D p1 = new Point2D(1, 5);
    Point2D p2 = new Point2D(3, 4);  // Closest to the query point
    Point2D p3 = new Point2D(5, 2);
    Point2D p4 = new Point2D(5, 8);
    tree.insert(p1);
    tree.insert(p2);
    tree.insert(p3);
    tree.insert(p4);

    Point2D queryPoint = new Point2D(3.5, 4.2);
    Point2D nearestPoint = tree.nearest(queryPoint);

    // Verify that the correct nearest neighbor is returned
    assertEquals(p2, nearestPoint);
    tree.draw();
  }

}