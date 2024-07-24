package per.duyd.training.dsaa.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Heaps {
  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Long> wordCounts = Arrays.stream(words)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    PriorityQueue<Map.Entry<String, Long>> maxPQ =
        new PriorityQueue<>((e1, e2) -> {
          int countCompare = Long.compare(e2.getValue(), e1.getValue());
          return countCompare != 0 ? countCompare : e1.getKey().compareTo(e2.getKey());
        });

    wordCounts.entrySet().forEach(maxPQ::add);

    List<String> ans = new ArrayList<>();

    for (int i = 0; i < k; i++) {
      ans.add(maxPQ.remove().getKey());
    }

    return ans;
  }

  public static class SeatManager {
    private final PriorityQueue<Integer> minPQ;

    public SeatManager(int n) {
      minPQ = new PriorityQueue<>(n);
      for (int i = 1; i <= n; i++) {
        minPQ.add(i);
      }
    }

    public int reserve() {
      return minPQ.remove();
    }

    public void unreserve(int seatNumber) {
      minPQ.add(seatNumber);
    }
  }
}
