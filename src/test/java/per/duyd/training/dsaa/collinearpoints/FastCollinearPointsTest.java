package per.duyd.training.dsaa.collinearpoints;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FastCollinearPointsTest {
  @Test
  public void testNoSegments() {
    Point[] points = {};
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    assertEquals(0, collinear.numberOfSegments());
  }

  @Test
  public void testOneSegment() {
    Point[] points =
        {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5)};
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    assertEquals(1, collinear.numberOfSegments());
    LineSegment[] segments = collinear.segments();
    assertEquals(new LineSegment(new Point(1, 1), new Point(5, 5)).toString(),
        segments[0].toString());
  }

  @Test
  public void testMultipleSegments() {
    Point[] points =
        {new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5),
            new Point(6, 1), new Point(7, 2), new Point(8, 3), new Point(9, 4)};
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    assertEquals(2, collinear.numberOfSegments());
    LineSegment[] segments = collinear.segments();
    assertEquals(new LineSegment(new Point(1, 1), new Point(5, 5)).toString(),
        segments[0].toString());
    assertEquals(new LineSegment(new Point(6, 1), new Point(9, 4)).toString(),
        segments[1].toString());
  }

  @Test
  public void testMultipleSegmentsWithOverlap() {
    Point[] points = {
        new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4), new Point(5, 5),
        new Point(7, 7), new Point(8, 8),
        new Point(4, 7), new Point(5, 8), new Point(6, 9),
        new Point(0, 5), new Point(2, 7), new Point(4, 9),
        new Point(6, 0), new Point(6, 1), new Point(6, 2), new Point(6, 3)
    };
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    assertEquals(2, collinear.numberOfSegments());
    LineSegment[] segments = collinear.segments();

    assertEquals(new LineSegment(new Point(6, 0), new Point(6, 9)).toString(),
        segments[0].toString()); // Shortest distinct segment with 4 points
    assertEquals(new LineSegment(new Point(1, 1), new Point(8, 8)).toString(),
        segments[1].toString()); // Longest segment
  }

  @Test
  public void testVerticalSegment() {
    Point[] points =
        {new Point(1, 1), new Point(1, 2), new Point(1, 3), new Point(1, 4), new Point(1, 5)};
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    assertEquals(1, collinear.numberOfSegments());
    LineSegment[] segments = collinear.segments();
    assertEquals(new LineSegment(new Point(1, 1), new Point(1, 5)).toString(),
        segments[0].toString());
  }

  @Test
  public void testHorizontalSegment() {
    Point[] points = {
        new Point(1, 1), new Point(2, 1), new Point(3, 1), new Point(4, 1), new Point(5, 1),
        new Point(23000, 8500), new Point(29550, 8500), new Point(30000, 8500),
        new Point(30950, 8500)
    };
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    assertEquals(2, collinear.numberOfSegments());
    LineSegment[] segments = collinear.segments();
    assertEquals(new LineSegment(new Point(1, 1), new Point(5, 1)).toString(),
        segments[0].toString());
    assertEquals(new LineSegment(new Point(23000, 8500), new Point(30950, 8500)).toString(),
        segments[1].toString());
  }


  @Test
  public void testNullInput() {
    assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(null));
  }

  @Test
  public void testNullPoint() {
    Point[] points = {new Point(1, 1), null, new Point(3, 3)};
    assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(points));
  }

  @Test
  public void testDuplicatePoint() {
    Point[] points = {new Point(1, 1), new Point(2, 2), new Point(1, 1)};
    assertThrows(IllegalArgumentException.class, () -> new FastCollinearPoints(points));
  }

}