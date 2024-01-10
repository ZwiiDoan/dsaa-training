package per.duyd.training.dsaa.uinionfind;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PercolationTest {
  @Test
  public void testInitialState() {
    Percolation percolation = new Percolation(5);
    assertFalse(percolation.isOpen(1, 1)); // All sites initially blocked
    assertFalse(percolation.isFull(1, 1));
    assertFalse(percolation.percolates());
    assertThrows(IllegalArgumentException.class, () -> new Percolation(0));
  }

  @Test
  public void testOpenAndFull() {
    Percolation percolation = new Percolation(5);
    percolation.open(1, 1);
    assertTrue(percolation.isOpen(1, 1));
    assertTrue(percolation.isFull(1, 1)); // Top row site is connected to top virtual site
  }

  @Test
  public void testPercolation() {
    Percolation percolation = new Percolation(5);
    percolation.open(1, 1);
    percolation.open(2, 1);
    percolation.open(3, 1);
    percolation.open(4, 1);
    assertFalse(percolation.percolates());
    percolation.open(5, 1); // Connect top row to bottom row
    assertTrue(percolation.percolates());
  }

  @Test
  public void testFullnessInBottomRow() {
    Percolation percolation = new Percolation(4);
    percolation.open(1, 4);
    percolation.open(2, 4);
    percolation.open(2, 3);
    percolation.open(3, 3);
    percolation.open(3, 2);
    percolation.open(4, 2); // Connect top row to bottom row
    assertTrue(percolation.percolates());
    assertTrue(percolation.isFull(4, 2)); // Bottom row site can be full
  }

  @Test
  public void testNumberOfOpenSites() {
    Percolation percolation = new Percolation(3);
    percolation.open(2, 2);
    assertEquals(1, percolation.numberOfOpenSites());
    percolation.open(1, 2);
    percolation.open(2, 1);
    percolation.open(2, 3);
    percolation.open(3, 2);
    assertEquals(5, percolation.numberOfOpenSites());
  }

  @Test
  public void testBoundaryConditions() {
    Percolation percolation = new Percolation(3);
    assertThrows(IllegalArgumentException.class, () -> percolation.open(3, 0));
    assertThrows(IllegalArgumentException.class, () -> percolation.open(1, 4));
    assertThrows(IllegalArgumentException.class, () -> percolation.open(4, 1));
    assertThrows(IllegalArgumentException.class, () -> percolation.open(0, 3));
  }

  @Test
  public void testPercolationWithInternalConnections() {
    Percolation percolation = new Percolation(5);
    percolation.open(1, 1);
    percolation.open(2, 1);
    percolation.open(3, 2);
    percolation.open(4, 2);
    percolation.open(5, 2);
    assertFalse(percolation.percolates()); // Not percolated yet
    percolation.open(3, 1); // Connect top row to bottom row through internal site
    assertTrue(percolation.percolates());
  }

  @Test
  public void testFullnessPropagation() {
    Percolation percolation = new Percolation(5);
    percolation.open(1, 1);
    percolation.open(1, 3);
    percolation.open(2, 3);
    percolation.open(3, 3);
    assertTrue(percolation.isFull(3, 3)); // Fullness should propagate
  }

  @Test
  public void testBackwashPrevention() {
    Percolation percolation = new Percolation(5);
    percolation.open(1, 5);
    percolation.open(2, 5);
    percolation.open(3, 5);
    percolation.open(4, 5);
    percolation.open(5, 1);
    percolation.open(5, 5); // Connect top row to bottom row through side column
    assertTrue(percolation.percolates());
    assertFalse(percolation.isFull(5, 1)); // Backwash should be prevented
  }
}