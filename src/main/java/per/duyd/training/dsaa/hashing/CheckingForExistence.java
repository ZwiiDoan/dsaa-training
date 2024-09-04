package per.duyd.training.dsaa.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckingForExistence {
  public boolean containsDuplicate(int[] nums) {
    Set<Integer> seen = new HashSet<>();

    for (int num : nums) {
      if (!seen.add(num)) {
        return true;
      }
    }

    return false;
  }

  public String destinationCity(String[][] paths) {
    Map<String, Integer> outDegree = new HashMap<>();

    for (String[] path : paths) {
      outDegree.put(path[0], outDegree.getOrDefault(path[0], 0) + 1);
      outDegree.put(path[1], outDegree.getOrDefault(path[1], 0));
    }

    return outDegree.entrySet().stream().filter(entry -> entry.getValue() == 0).findFirst()
        .map(Map.Entry::getKey).orElse(null);
  }

  public boolean isPathCrossing(String path) {
    Set<String> visited = new HashSet<>();
    visited.add(getKey(0, 0));

    int x = 0, y = 0;
    int length = path.length();
    for (int i = 0; i < length; i++) {
      int[] movement = getMovement(path.charAt(i));
      x += movement[0];
      y += movement[1];
      String key = getKey(x, y);
      if (visited.contains(key)) {
        return true;
      } else {
        visited.add(key);
      }
    }

    return false;
  }

  private String getKey(int x, int y) {
    return x + "," + y;
  }

  private int[] getMovement(char direction) {
    return switch (direction) {
      case 'N' -> new int[] {0, 1};
      case 'S' -> new int[] {0, -1};
      case 'E' -> new int[] {1, 0};
      case 'W' -> new int[] {-1, 0};
      default -> throw new IllegalArgumentException("Unexpected value: " + direction);
    };
  }
}
