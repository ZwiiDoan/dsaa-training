import edu.princeton.cs.algs4.Digraph;
import java.util.Optional;

public class SAP {

  private final Digraph digraph;
  private final int V;
  private final SAPCache sapCache;


  // constructor takes a digraph (not necessarily a DAG)
  public SAP(Digraph G) {
    Optional.ofNullable(G).orElseThrow(IllegalArgumentException::new);
    this.digraph = new Digraph(G);
    this.V = this.digraph.V();
    this.sapCache = new SAPCache();
  }

  // length of shortest ancestral path between v and w; -1 if no such path
  public int length(int v, int w) {
    validateVertex(v);
    validateVertex(w);

    if (v == w) {
      return 0;
    }

    Integer cacheValue = sapCache.getLength(v, w);
    if (cacheValue != null) {
      return cacheValue;
    }

    DigraphBFS vPaths = new DigraphBFS(this.digraph, v);
    DigraphBFS wPaths = new DigraphBFS(this.digraph, w);

    int length = length(vPaths, wPaths);
    sapCache.putLength(v, w, length);
    return length;
  }

  private int length(DigraphBFS vPaths, DigraphBFS wPaths) {
    int minDist = Integer.MAX_VALUE;
    for (int i = 0; i < V; i++) {
      if (vPaths.hasPathTo(i) && wPaths.hasPathTo(i)) {
        int dist = vPaths.distTo(i) + wPaths.distTo(i);
        if (dist < minDist) {
          minDist = dist;
        }
      }
    }

    return minDist == Integer.MAX_VALUE ? -1 : minDist;
  }

  // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
  public int ancestor(int v, int w) {
    validateVertex(v);
    validateVertex(w);

    if (v == w) {
      return v;
    }

    Integer cacheValue = sapCache.getAncestor(v, w);
    if (cacheValue != null) {
      return cacheValue;
    }

    DigraphBFS vPaths = new DigraphBFS(this.digraph, v);
    DigraphBFS wPaths = new DigraphBFS(this.digraph, w);

    int ancestor = ancestor(vPaths, wPaths);
    sapCache.putAncestor(v, w, ancestor);
    return ancestor;
  }

  private int ancestor(DigraphBFS vPaths, DigraphBFS wPaths) {
    int a = -1;
    int minDist = Integer.MAX_VALUE;
    for (int i = 0; i < V; i++) {
      if (vPaths.hasPathTo(i) && wPaths.hasPathTo(i)) {
        int dist = vPaths.distTo(i) + wPaths.distTo(i);
        if (dist < minDist) {
          minDist = dist;
          a = i;
        }
      }
    }

    return a;
  }

  // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
  public int length(Iterable<Integer> v, Iterable<Integer> w) {
    validateVertices(v);
    validateVertices(w);

    Integer cacheValue = sapCache.getLength(v, w);
    if (cacheValue != null) {
      return cacheValue;
    }

    DigraphBFS vPaths = new DigraphBFS(this.digraph, v);
    DigraphBFS wPaths = new DigraphBFS(this.digraph, w);

    int length = length(vPaths, wPaths);
    sapCache.putLength(v, w, length);
    return length;
  }

  // a common ancestor that participates in shortest ancestral path; -1 if no such path
  public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
    validateVertices(v);
    validateVertices(w);

    Integer cacheValue = sapCache.getAncestor(v, w);
    if (cacheValue != null) {
      return cacheValue;
    }

    DigraphBFS vPaths = new DigraphBFS(this.digraph, v);
    DigraphBFS wPaths = new DigraphBFS(this.digraph, w);

    int ancestor = ancestor(vPaths, wPaths);
    sapCache.putAncestor(v, w, ancestor);
    return ancestor;
  }

  private void validateVertex(int v) {
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

  private void validateVertices(Iterable<Integer> vertices) {
    if (vertices == null) {
      throw new IllegalArgumentException("argument is null");
    }
    for (Integer v : vertices) {
      if (v == null) {
        throw new IllegalArgumentException("vertex is null");
      }
      validateVertex(v);
    }
  }

  // do unit testing of this class
  public static void main(String[] args) {
  }
}