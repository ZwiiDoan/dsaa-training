import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
  private final Point[] points;
  private final LineSegment[] lineSegments;

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Invalid input points");
    }

    this.points = new Point[points.length];
    System.arraycopy(points, 0, this.points, 0, points.length);
    sortAndValidatePoints();
    this.lineSegments = findLineSegments();
  }

  // the number of line segments
  public int numberOfSegments() {
    return lineSegments.length;
  }

  // the line segments
  public LineSegment[] segments() {
    LineSegment[] lineSegmentsCopy = new LineSegment[lineSegments.length];
    System.arraycopy(lineSegments, 0, lineSegmentsCopy, 0, lineSegments.length);
    return lineSegmentsCopy;
  }

  // the line segments
  private LineSegment[] findLineSegments() {
    ArrayList<LineSegment> lineSegments = new ArrayList<>();
    Point[] pointsCopy = new Point[points.length];
    System.arraycopy(points, 0, pointsCopy, 0, points.length);

    for (Point originPoint : points) {
      Comparator<Point> slopeComparator = originPoint.slopeOrder();
      Arrays.sort(pointsCopy, slopeComparator);
      findLineSegments(pointsCopy, lineSegments, originPoint);
    }

    LineSegment[] segments = new LineSegment[lineSegments.size()];
    lineSegments.toArray(segments);
    return segments;
  }

  private void findLineSegments(Point[] pointsCopy, ArrayList<LineSegment> lineSegments,
                                Point originPoint) {
    double targetSlope = Double.NEGATIVE_INFINITY;
    int collinearCount = 1;
    int startSegment = 1;

    for (int q = 1; q < pointsCopy.length; q++) {
      double currentSlope = originPoint.slopeTo(pointsCopy[q]);

      if (currentSlope == targetSlope) {
        collinearCount++;
      } else {
        if (collinearCount >= 3) {
          addLineSegment(pointsCopy, lineSegments, originPoint, startSegment, q);
        }
        targetSlope = currentSlope;
        collinearCount = 1;
        startSegment = q;
      }
    }

    if (collinearCount >= 3) {
      addLineSegment(pointsCopy, lineSegments, originPoint, startSegment, pointsCopy.length);
    }
  }

  private void addLineSegment(Point[] pointsCopy, ArrayList<LineSegment> lineSegments,
                              Point originPoint, int startSegment, int endSegment) {
    Point maxPoint = pointsCopy[startSegment];

    for (int i = startSegment; i < endSegment; i++) {
      if (originPoint.compareTo(pointsCopy[i]) > 0) {
        return;
      }

      if (maxPoint.compareTo(pointsCopy[i]) < 0) {
        maxPoint = pointsCopy[i];
      }
    }

    lineSegments.add(new LineSegment(originPoint, maxPoint));
  }

  private void sortAndValidatePoints() {
    for (Point point : points) {
      if (point == null) {
        throw new IllegalArgumentException("Every points cannot be null");
      }
    }

    Arrays.sort(this.points);

    for (int i = 0; i < points.length - 1; i++) {
      if (points[i].compareTo(points[i + 1]) == 0) {
        throw new IllegalArgumentException("Points have to be unique");
      }
    }
  }
}
