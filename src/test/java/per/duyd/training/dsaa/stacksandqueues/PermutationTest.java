package per.duyd.training.dsaa.stacksandqueues;

import static per.duyd.training.dsaa.util.TestResourceUtil.readTestFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PermutationTest {
  @Test
  public void testMain() {
    InputStream originalIn = System.in;
    try (
        ByteArrayInputStream in = new ByteArrayInputStream(
            readTestFile("sqb/distinct.txt"))) {
      System.setIn(in);

      // Call the main method with arguments
      Assertions.assertDoesNotThrow(() -> Permutation.main(new String[] {"3"})); // Pass k as a
      // command-line argument
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      System.setIn(originalIn);
    }
  }
}