package per.duyd.training.dsaa.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestResourceUtil {
  public static byte[] readFileFromClasspath(String fileName) throws IOException {
    return Files.readAllBytes(Paths.get("src/test/resources/" + fileName));
  }
}
