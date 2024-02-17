package per.duyd.training.dsaa.wordnet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SAPTest {

  private Digraph graph1;
  private Digraph graph2;

  @BeforeEach
  void setup() {
    // Graph 1: Simple linear connection
    graph1 = new Digraph(3);
    graph1.addEdge(0, 1);
    graph1.addEdge(1, 2);

    // Graph 2: Two paths converging
    graph2 = new Digraph(5);
    graph2.addEdge(0, 3);
    graph2.addEdge(1, 3);
    graph2.addEdge(2, 4);
    graph2.addEdge(3, 4);
  }

  // ***** Tests for individual vertex methods *****

  @Test
  public void testLengthSimplePath() {
    Digraph g = graph1;
    SAP sap = new SAP(g);
    int result = sap.length(0, 2); // Should be 2
    assertEquals(2, result);
  }

  @Test
  public void testAncestorSimplePath() {
    Digraph g = graph1;
    SAP sap = new SAP(g);
    int result = sap.ancestor(0, 2); // Should be 0
    assertEquals(2, result);
  }

  @Test
  public void testLengthNoPath() {
    Digraph g = new Digraph(2); // Isolated vertices
    SAP sap = new SAP(g);
    int result = sap.length(0, 1);
    assertEquals(-1, result);
  }

  @Test
  public void testLengthAndAncestorConvergingPaths() {
    Digraph g = graph2;
    SAP sap = new SAP(g);
    int length = sap.length(0, 2);
    int ancestor = sap.ancestor(0, 2);
    assertEquals(3, length);
    assertEquals(4, ancestor);
  }

  // ***** Tests for iterable methods *****

  @Test
  public void testIterableLength() {
    Digraph g = graph2;
    SAP sap = new SAP(g);
    Iterable<Integer> subsetA = Arrays.asList(0, 1);
    Iterable<Integer> subsetB = Arrays.asList(2);
    int length = sap.length(subsetA, subsetB);
    assertEquals(3, length);
  }

  @Test
  public void testLengthCyclicGraph() {
    Digraph g = new Digraph(4);
    g.addEdge(0, 1);
    g.addEdge(1, 2);
    g.addEdge(2, 0); // Creates a cycle
    g.addEdge(2, 3);

    SAP sap = new SAP(g);
    int length = sap.length(0, 3);
    int ancestor = sap.ancestor(0, 3);
    assertEquals(3, length);
    assertEquals(3, ancestor);
  }

  @Test
  public void testMultipleShortestPaths() {
    Digraph g = new Digraph(6);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(3, 2);
    g.addEdge(3, 5);
    g.addEdge(1, 5);
    g.addEdge(5, 4);

    SAP sap = new SAP(g);
    int length = sap.length(1, 3);
    int ancestor = sap.ancestor(1, 3);
    assertEquals(2, length);
    // Ancestor could be either 2 or 3 - both are valid
    assertTrue(ancestor == 2 || ancestor == 5);
  }

  @Test
  public void testLengthNullDigraph() {
    assertThrows(IllegalArgumentException.class, () -> new SAP(null));
  }

  @Test
  public void testLengthInvalidVertex() {
    SAP sap = new SAP(graph1);
    assertThrows(IllegalArgumentException.class, () -> sap.length(-1, 1));
    assertThrows(IllegalArgumentException.class, () -> sap.length(0, graph1.V()));
  }

  @Test
  public void testIterableLengthNullInput() {
    SAP sap = new SAP(graph2);
    assertThrows(IllegalArgumentException.class, () -> sap.length(null, Arrays.asList(1)));
    assertThrows(IllegalArgumentException.class, () -> sap.length(Arrays.asList(2), null));
  }

  @Test
  public void testIterableLengthInvalidVertex() {
    SAP sap = new SAP(graph1);
    Iterable<Integer> vertices = Arrays.asList(0, -1, 2);
    assertThrows(IllegalArgumentException.class, () -> sap.length(vertices, Arrays.asList(1)));
  }

  @ParameterizedTest
  @CsvSource({"3,11,4,1", "9,12,3,5", "7,2,4,0", "1,6,-1,-1"})
  public void testSampleTestData(int v, int w, int length, int ancestor) {
    Digraph digraph = new Digraph(new In("wordnet/digraph1.txt"));
    SAP sap = new SAP(digraph);

    assertEquals(length, sap.length(v, w));
    assertEquals(ancestor, sap.ancestor(v, w));
  }

}