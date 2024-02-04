package per.duyd.training.dsaa.kdtree;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {

  private final TreeSet<Point2D> treeSet;

  // construct an empty set of points
  public PointSET() {
    treeSet = new TreeSet<>();
  }

  // is the set empty?
  public boolean isEmpty() {
    return treeSet.isEmpty();
  }

  // number of points in the set
  public int size() {
    return treeSet.size();
  }

  // add the point to the set (if it is not already in the set)
  public void insert(Point2D p) {
    treeSet.add(Optional.ofNullable(p).orElseThrow(IllegalArgumentException::new));
  }

  // does the set contain point p?
  public boolean contains(Point2D p) {
    return treeSet.contains(Optional.ofNullable(p).orElseThrow(IllegalArgumentException::new));
  }

  // draw all points to standard draw
  public void draw() {
    treeSet.forEach(point2D -> StdDraw.point(point2D.x(), point2D.y()));
  }

  // all points that are inside the rectangle (or on the boundary)
  public Iterable<Point2D> range(RectHV rect) {
    Optional.ofNullable(rect).orElseThrow(IllegalArgumentException::new);
    return treeSet.stream().filter(rect::contains).collect(Collectors.toList());
  }

  // a nearest neighbor in the set to point p; null if the set is empty
  public Point2D nearest(Point2D p) {
    if (isEmpty()) {
      return null;
    }

    Optional.ofNullable(p).orElseThrow(IllegalArgumentException::new);
    return treeSet.stream().min(Comparator.comparingDouble(it -> it.distanceSquaredTo(p)))
        .orElse(null);
  }
}
