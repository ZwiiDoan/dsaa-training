package per.duyd.training.dsaa.burrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BurrowsWheelerTest {

  @AfterEach
  void resetStreams() {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  //  @Disabled("testTransform and testInverseTransform cannot be executed together")
  @ParameterizedTest
  @CsvSource({
      "abra.txt,abra.txt.bwt",
//      "a.txt,a.txt.bwt"
  })
  void testTransform(String inputFile, String outputFile) throws IOException {
    Path outputPath = Paths.get("src/test/resources/burrows/out/" + outputFile);

    try (ByteArrayInputStream in = new ByteArrayInputStream(
        Files.readAllBytes(Paths.get("src/test/resources/burrows/" + inputFile)));
         PrintStream out = new PrintStream(Files.newOutputStream(outputPath))) {
      System.setIn(in);
      System.setOut(out);

      BurrowsWheeler.main(new String[] {"-"});

      assertEquals(new String(
              Files.readAllBytes(Paths.get("src/test/resources/burrows/" + outputFile))),
          new String(Files.readAllBytes(outputPath)));
    }
  }

  @ParameterizedTest
  @CsvSource({
      "abra.txt.bwt,abra.txt",
//      "a.txt.bwt,a.txt"
  })
  void testInverseTransform(String inputFile, String outputFile) throws IOException {
    Path outputPath = Paths.get("src/test/resources/burrows/out/" + outputFile);

    try (ByteArrayInputStream in = new ByteArrayInputStream(
        Files.readAllBytes(Paths.get("src/test/resources/burrows/" + inputFile)));
         PrintStream out = new PrintStream(Files.newOutputStream(outputPath))) {
      System.setIn(in);
      System.setOut(out);

      BurrowsWheeler.main(new String[] {"+"});

      assertEquals(
          new String(Files.readAllBytes(Paths.get("src/test/resources/burrows/" + outputFile))),
          new String(Files.readAllBytes(outputPath)));
    }
  }
}