package per.duyd.training.dsaa.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PointSETTest {
  @Test
  public void testConstructor() {
    PointSET set = new PointSET();
    Assertions.assertTrue(set.isEmpty());
    Assertions.assertEquals(0, set.size());
  }

  @Test
  public void testInsert() {
    PointSET set = new PointSET();
    Point2D p1 = new Point2D(0.5, 0.5);
    Point2D p2 = new Point2D(1.0, 1.0);

    set.insert(p1);
    Assertions.assertFalse(set.isEmpty());
    Assertions.assertEquals(1, set.size());
    Assertions.assertTrue(set.contains(p1));

    set.insert(p2);
    Assertions.assertEquals(2, set.size());
    Assertions.assertTrue(set.contains(p2));
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.0, 0.5, 1.0})
  public void testContains(double x) {
    PointSET set = new PointSET();
    Point2D p = new Point2D(x, 0.5);
    set.insert(p);
    Assertions.assertTrue(set.contains(p));
  }

  @Test
  public void testRange() {
    PointSET set = new PointSET();

    // Add points to the set, including points within and outside the rectangle
    set.insert(new Point2D(0.1, 0.1)); // Outside rectangle
    set.insert(new Point2D(0.4, 0.4)); // Inside rectangle
    set.insert(new Point2D(0.7, 0.7)); // Inside rectangle
    set.insert(new Point2D(0.9, 0.9)); // Outside rectangle

    RectHV rect = new RectHV(0.2, 0.2, 0.8, 0.8);
    Iterable<Point2D> pointsInRange = set.range(rect);

    // Assert that the correct points are found within the rectangle
    List<Point2D> expectedPoints = Arrays.asList(new Point2D(0.4, 0.4), new Point2D(0.7, 0.7));
    Assertions.assertEquals(expectedPoints, StreamSupport.stream(pointsInRange.spliterator(),
        false).collect(Collectors.toList()));
  }

  @Test
  public void testNearest_EmptySet() {
    PointSET set = new PointSET();
    Point2D p = new Point2D(0.5, 0.5);
    Assertions.assertNull(set.nearest(p));
  }

  @Test
  public void testNearest_SinglePoint() {
    PointSET set = new PointSET();
    Point2D p1 = new Point2D(0.5, 0.5);
    set.insert(p1);
    Assertions.assertSame(p1, set.nearest(p1));
  }

  @ParameterizedTest
  @ValueSource(doubles = {0.0, 0.1, 0.5, 1.0})
  public void testNearest_DifferentDistances(double distance) {
    PointSET set = new PointSET();

    // Add points with varying distances to the test point
    Point2D testPoint = new Point2D(0.5, 0.5);
    Point2D nearestPoint = new Point2D(0.5 + distance, 0.5); // Point at the specified distance
    set.insert(nearestPoint);
    // Add other points as needed to cover different scenarios

    Point2D actualNearest = set.nearest(testPoint);
    Assertions.assertSame(nearestPoint, actualNearest);
  }

  @Test
  public void testNearest_MultipleCandidates() {
    PointSET set = new PointSET();

    set.insert(new Point2D(0.1, 0.1));
    set.insert(new Point2D(0.9, 0.9));
    // Add points with equal distances to the test point
    Point2D testPoint = new Point2D(0.5, 0.5);
    Point2D p1 = new Point2D(0.3, 0.3);
    Point2D p2 = new Point2D(0.7, 0.7);
    set.insert(p1);
    set.insert(p2);

    Point2D actualNearest = set.nearest(testPoint);

    // Assert that a valid nearest point is returned (either p1 or p2)
    Assertions.assertTrue(actualNearest == p1 || actualNearest == p2);
  }

}