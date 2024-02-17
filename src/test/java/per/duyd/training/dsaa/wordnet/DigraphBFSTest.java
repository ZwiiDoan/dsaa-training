package per.duyd.training.dsaa.wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.princeton.cs.algs4.Digraph;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DigraphBFSTest {

  private Digraph graph1; // Simple linear graph (3 vertices)
  private Digraph graph2; // Graph with converging paths

  @BeforeEach
  void setUp() {
    // Initialize graph1
    graph1 = new Digraph(3);
    graph1.addEdge(0, 1);
    graph1.addEdge(1, 2);

    // Initialize graph2
    graph2 = new Digraph(5);
    graph2.addEdge(0, 3);
    graph2.addEdge(1, 3);
    graph2.addEdge(2, 4);
    graph2.addEdge(3, 4);
  }

  // Tests for single-source constructor
  @Test
  void testHasPathSimple() {
    DigraphBFS bfs = new DigraphBFS(graph1, 0);
    assertTrue(bfs.hasPathTo(2));
  }

  @Test
  void testDistTo() {
    DigraphBFS bfs = new DigraphBFS(graph2, 1);
    assertEquals(1, bfs.distTo(3));
    assertEquals(2, bfs.distTo(4));
  }

  @Test
  void testPathTo() {
    DigraphBFS bfs = new DigraphBFS(graph2, 0);
    Iterable<Integer> path = bfs.pathTo(4);

    assertNotNull(path);

    // Convert Iterable to a List for easier assertion
    List<Integer> pathList = new ArrayList<>();
    path.forEach(pathList::add);

    // Assert the expected path
    List<Integer> expectedPath = Arrays.asList(0, 3, 4);
    assertEquals(expectedPath, pathList);
  }

  // Tests for multi-source constructor
  @Test
  void testMultiSourceHasPath() {
    DigraphBFS bfs = new DigraphBFS(graph2, Arrays.asList(0, 2));
    assertTrue(bfs.hasPathTo(4));
  }

  // Error Handling Tests
  @Test
  void testInvalidVertex() {
    DigraphBFS bfs = new DigraphBFS(graph1, 0);
    assertThrows(IllegalArgumentException.class, () -> bfs.hasPathTo(-1)); // Out of bounds
  }

  @Test
  void testInvalidVertexMultiSource() {
    assertThrows(IllegalArgumentException.class,
        () -> new DigraphBFS(graph1, Arrays.asList(0, 3))); // Vertex 3 out-of-bounds
  }

  @Test
  void testNullVertices() {
    assertThrows(IllegalArgumentException.class, () -> new DigraphBFS(graph2, null));
  }

  @Test
  void testEmptyVertices() {
    assertThrows(IllegalArgumentException.class, () -> new DigraphBFS(graph2, Arrays.asList()));
  }

  @Test
  void testVertexWithNullValue() {
    assertThrows(IllegalArgumentException.class,
        () -> new DigraphBFS(graph1, Arrays.asList(0, null, 2)));
  }
}

