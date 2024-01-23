package per.duyd.training.dsaa.collinearpoints;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

  private final Point[] points;
  private final LineSegment[] lineSegments;

  // finds all line segments containing 4 points
  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException("Invalid input points");
    }

    this.points = new Point[points.length];
    System.arraycopy(points, 0, this.points, 0, points.length);
    sortAndValidatePoints(this.points);
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

  private LineSegment[] findLineSegments() {
    ArrayList<LineSegment> lineSegments = new ArrayList<>();
    int pointsCount = points.length;

    for (int p = 0; p < pointsCount - 3; p++) {
      Point pointP = points[p];
      for (int q = p + 1; q < pointsCount - 2; q++) {
        Point pointQ = points[q];
        double slopePq = pointP.slopeTo(pointQ);
        for (int r = q + 1; r < pointsCount - 1; r++) {
          Point pointR = points[r];
          double slopePr = pointP.slopeTo(pointR);
          for (int s = r + 1; s < pointsCount; s++) {
            Point pointS = points[s];
            double slopePs = pointP.slopeTo(pointS);
            if (slopePq == slopePr && slopePq == slopePs) {
              lineSegments.add(new LineSegment(pointP, pointS));
            }
          }
        }
      }
    }

    LineSegment[] segments = new LineSegment[lineSegments.size()];
    lineSegments.toArray(segments);
    return segments;
  }

  private void sortAndValidatePoints(Point[] points) {
    for (Point point : points) {
      if (point == null) {
        throw new IllegalArgumentException("Every point cannot be null");
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
