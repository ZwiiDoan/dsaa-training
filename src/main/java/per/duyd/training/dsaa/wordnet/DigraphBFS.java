package per.duyd.training.dsaa.wordnet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import java.util.HashMap;

public class DigraphBFS {
  private final HashMap<Integer, Boolean> marked;
  private final HashMap<Integer, Integer> edgeTo;
  private final HashMap<Integer, Integer> distTo;
  private final int V;

  public DigraphBFS(Digraph digraph, int s) {
    marked = new HashMap<>();
    edgeTo = new HashMap<>();
    distTo = new HashMap<>();
    V = digraph.V();

    validateVertex(s);
    bfs(digraph, s);
  }

  public DigraphBFS(Digraph digraph, Iterable<Integer> sources) {
    marked = new HashMap<>();
    edgeTo = new HashMap<>();
    distTo = new HashMap<>();
    V = digraph.V();

    validateVertices(sources);
    bfs(digraph, sources);
  }

  private void bfs(Digraph digraph, Iterable<Integer> sources) {
    Queue<Integer> queue = new Queue<>();

    sources.forEach(source -> {
      queue.enqueue(source);
      marked.put(source, true);
      distTo.put(source, 0);
    });

    bfs(digraph, queue);
  }

  private void bfs(Digraph digraph, Queue<Integer> visited) {
    while (!visited.isEmpty()) {
      Integer v = visited.dequeue();

      for (Integer w : digraph.adj(v)) {
        if (marked.get(w) == null) {
          marked.put(w, true);
          edgeTo.put(w, v);
          distTo.put(w, distTo.get(v) + 1);
          visited.enqueue(w);
        }
      }
    }
  }

  private void bfs(Digraph digraph, int s) {
    Queue<Integer> visited = new Queue<>();
    marked.put(s, true);
    distTo.put(s, 0);
    visited.enqueue(s);

    bfs(digraph, visited);
  }

  public boolean hasPathTo(int v) {
    validateVertex(v);
    return marked.get(v) != null;
  }

  public Integer distTo(int v) {
    validateVertex(v);
    Integer distance = distTo.get(v);
    return distance == null ? Integer.MAX_VALUE : distance;
  }

  public Iterable<Integer> pathTo(int v) {
    Stack<Integer> path = new Stack<>();
    Integer w = v;
    
    while (w != null) {
      path.push(w);
      w = edgeTo.get(w);
    }

    return path;
  }

  private void validateVertex(int v) {
    if (v < 0 || v >= V) {
      throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
    }
  }

  // throw an IllegalArgumentException if vertices is null, has zero vertices,
  // or has a vertex not between 0 and V-1
  private void validateVertices(Iterable<Integer> vertices) {
    if (vertices == null) {
      throw new IllegalArgumentException("argument is null");
    }
    int vertexCount = 0;
    for (Integer v : vertices) {
      vertexCount++;
      if (v == null) {
        throw new IllegalArgumentException("vertex is null");
      }
      validateVertex(v);
    }
    if (vertexCount == 0) {
      throw new IllegalArgumentException("zero vertices");
    }
  }

}
