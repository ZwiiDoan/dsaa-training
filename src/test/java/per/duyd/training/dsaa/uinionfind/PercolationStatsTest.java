package per.duyd.training.dsaa.uinionfind;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PercolationStatsTest {
  // Test constructor with valid input
  @Test
  public void testConstructorValidInput() {
    assertDoesNotThrow(() -> PercolationStats.main(new String[] {"200", "100"}));
    assertThrows(IllegalArgumentException.class, () -> PercolationStats.main(null));
    assertThrows(IllegalArgumentException.class, () -> PercolationStats.main(new String[] {"200"}));
  }

  // Test constructor with invalid input
  @Test
  public void testConstructorInvalidGridSize() {
    assertThrows(IllegalArgumentException.class, () -> new PercolationStats(0, 100));
  }

  // Test constructor with invalid input
  @Test
  public void testConstructorInvalidTrials() {
    assertThrows(IllegalArgumentException.class, () -> new PercolationStats(200, 0));
  }

  // Test mean calculation
  @Test
  public void testMean() {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.58921325, stats.mean(), 0.01);
  }

  // Test standard deviation calculation
  @Test
  public void testStddev() {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.00829, stats.stddev(), 0.01);
  }

  // Test confidenceLo calculation
  @Test
  public void testConfidenceLo() {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.58627, stats.confidenceLo(), 0.01);
  }

  // Test confidenceHi calculation
  @Test
  public void testConfidenceHi() {
    PercolationStats stats = new PercolationStats(200, 100);
    assertEquals(0.59137, stats.confidenceHi(), 0.01);
  }
}