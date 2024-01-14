package per.duyd.training.dsaa.sqb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PermutationTest {
  @Test
  public void testMain() {
    InputStream originalIn = System.in;
    try (
        ByteArrayInputStream in = new ByteArrayInputStream(readFileFromClasspath("distinct.txt"))) {
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

  private byte[] readFileFromClasspath(String fileName) throws IOException {
    return Files.readAllBytes(Paths.get("src/test/resources/" + fileName));
  }
}