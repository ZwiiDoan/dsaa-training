package per.duyd.training.dsaa.collinearpoints;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class SampleClientTest {
  public static void main(String[] args) {
    // read the n points from a file
    Point[] points = readPoints(args[0]);

    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();

    // print and draw the line segments
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }

  private static Point[] readPoints(String fileName) {
    In in = new In(fileName);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    return points;
  }

  void fastCollinearPointsTest(String fileName) {
    // read the n points from a file
    Point[] points = readPoints(fileName);
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
    }
  }

  void bruteCollinearPointsTest(String fileName) {
    // read the n points from a file
    Point[] points = readPoints(fileName);
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
    }
  }

  @ParameterizedTest
  @ValueSource(strings = {"collinearpoints/input6.txt", "collinearpoints/input8.txt"})
  void bruteCollinearPointsShouldSucceed(String fileName) {
    bruteCollinearPointsTest(fileName);
  }

  @ParameterizedTest
  @ValueSource(strings = {"collinearpoints/input6.txt", "collinearpoints/input8.txt"})
  void fastCollinearPointsShouldSucceed(String fileName) {
    fastCollinearPointsTest(fileName);
  }
}
