package per.duyd.training.dsaa.burrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MoveToFrontTest {
  @AfterEach
  void resetStreams() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  //  @Disabled("testEncode and testDecode cannot be executed together")
  @Test
  void testEncode() throws IOException {
    Path outputPath = Paths.get("src/test/resources/burrows/out/abra.txt.mtf");

    try (ByteArrayInputStream in = new ByteArrayInputStream(
        Files.readAllBytes(Paths.get("src/test/resources/burrows/abra.txt")));
         PrintStream out = new PrintStream(Files.newOutputStream(outputPath))) {
      System.setIn(in);
      System.setOut(out);

      MoveToFront.main(new String[] {"-"});

      assertEquals(
          new String(Files.readAllBytes(Paths.get("src/test/resources/burrows/abra.txt.mtf"))),
          new String(Files.readAllBytes(outputPath)));
    }
  }

  @Test
  void testDecode() throws IOException {
    Path outputPath = Paths.get("src/test/resources/burrows/out/abra.txt");

    try (ByteArrayInputStream in = new ByteArrayInputStream(
        Files.readAllBytes(Paths.get("src/test/resources/burrows/abra.txt.mtf")));
         PrintStream out = new PrintStream(Files.newOutputStream(outputPath))) {
      System.setIn(in);
      System.setOut(out);

      MoveToFront.main(new String[] {"+"});

      assertEquals(new String(Files.readAllBytes(Paths.get("src/test/resources/burrows/abra.txt"))),
          new String(Files.readAllBytes(outputPath)));
    }
  }

}