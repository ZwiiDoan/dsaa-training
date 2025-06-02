package per.duyd.training.dsaa.heap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Heaps {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Long> wordCounts = Arrays.stream(words)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        PriorityQueue<Map.Entry<String, Long>> maxPQ = new PriorityQueue<>((e1, e2) -> {
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

    public static class SmallestInfiniteSet {
        private final PriorityQueue<Integer> minPQ;

        public SmallestInfiniteSet() {
            minPQ = new PriorityQueue<>();
            for (int i = 1; i <= 1000; i++) {
                minPQ.add(i);
            }
        }

        public int popSmallest() {
            return minPQ.remove();
        }

        public void addBack(int num) {
            if (!minPQ.contains(num)) {
                minPQ.add(num);
            }
        }
    }

    public String repeatLimitedString(String s, int repeatLimit) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder result = new StringBuilder();

        int i = 25; // Start from 'z'

        while (i >= 0) {
            if (freq[i] == 0) {
                i--;
                continue;
            }

            int count = Math.min(freq[i], repeatLimit);

            // Append up to repeatLimit of the current char
            for (int j = 0; j < count; j++) {
                result.append((char) (i + 'a'));
            }

            freq[i] -= count;

            if (freq[i] == 0) {
                i--; // Move to next smaller char
                continue;
            }

            // Need to break the repeat — find next available smaller char
            int j = i - 1;
            while (j >= 0 && freq[j] == 0) {
                j--;
            }

            if (j < 0) {
                break; // No smaller char to break the repeat
            }

            result.append((char) (j + 'a'));
            freq[j]--;

            // Stay on `i` to keep appending after the break
        }

        return result.toString();
    }

    public long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> candidatesPQ = new PriorityQueue<>(
                (c1, c2) -> c1[0] == c2[0] ? Integer.compare(c1[1], c2[1]) : Integer.compare(c1[0], c2[0]));

        int left = 0;
        int right = costs.length - 1;
        for (int i = 0; i < candidates && left <= right; i++) {
            candidatesPQ.add(new int[]{costs[left], left});
            left++;
        }

        for (int i = 0; i < candidates && left <= right; i++) {
            candidatesPQ.add(new int[]{costs[right], right});
            right--;
        }

        long totalCost = 0;

        for (int i = 0; i < k; i++) {
            int[] hire = candidatesPQ.poll();
            totalCost += hire[0];

            if (left <= right) {
                if (hire[1] < left) {
                    candidatesPQ.add(new int[]{costs[left], left});
                    left++;
                } else {
                    candidatesPQ.add(new int[]{costs[right], right});
                    right--;
                }
            }
        }

        return totalCost;
    }

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minPq = new PriorityQueue<>((a1, a2) -> Integer.compare(a1[0], a2[0]));
        List<List<Integer>> result = new ArrayList<>();
        Set<List<Integer>> visited = new HashSet<>();

        minPq.add(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(List.of(0, 0));

        while (result.size() < k && !minPq.isEmpty()) {
            int[] minPair = minPq.poll();
            int i = minPair[1];
            int j = minPair[2];
            result.add(List.of(nums1[i], nums2[j]));

            int nextI = i + 1;
            int nextJ = j + 1;

            if (nextI < nums1.length && !visited.contains(List.of(nextI, j))) {
                minPq.add(new int[]{nums1[nextI] + nums2[j], nextI, j});
                visited.add(List.of(nextI, j));
            }

            if (nextJ < nums2.length && !visited.contains(List.of(i, nextJ))) {
                minPq.add(new int[]{nums1[i] + nums2[nextJ], i, nextJ});
                visited.add(List.of(i, nextJ));
            }
        }

        return result;
    }

    public int mostBooked(int n, int[][] meetings) {
        List<int[]> sortedMeetings = Arrays.stream(meetings)
                .sorted((a, b) -> Integer.compare(a[0], b[0]))
                .collect(Collectors.toList());

        int[] bookedCount = new int[n];

        PriorityQueue<long[]> bookedRooms = new PriorityQueue<>((a, b) ->
                a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])
        );
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>(); // just room index
        for (int i = 0; i < n; i++) availableRooms.add(i);

        for (int[] meeting : sortedMeetings) {
            int start = meeting[0];
            int end = meeting[1];

            // Free up rooms that are done before the current meeting starts
            while (!bookedRooms.isEmpty() && bookedRooms.peek()[0] <= start) {
                long[] room = bookedRooms.poll();
                availableRooms.add((int) room[1]);
            }

            // If there are available rooms, book one
            if (!availableRooms.isEmpty()) {
                int room = availableRooms.poll();
                bookedCount[room]++;
                bookedRooms.add(new long[]{end, room}); // Store end time and room index
            } else {
                // If no rooms are available, book the room that will be free the soonest
                long[] room = bookedRooms.poll();
                bookedCount[(int) room[1]]++;
                bookedRooms.add(new long[]{room[0] + (end - start), room[1]});
            }
        }

        return IntStream.range(1, bookedCount.length)
                .reduce(0, (a, b) -> bookedCount[a] >= bookedCount[b] ? a : b);
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        // Min-heap that sorts by value
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.val));

        int currentMax = Integer.MIN_VALUE;

        // Initialize heap with first element of each list
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).getFirst();
            minHeap.offer(new Element(value, i, 0));
            currentMax = Math.max(currentMax, value);
        }

        // Best range found so far
        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;

        while (true) {
            Element curr = minHeap.poll(); // Get the current minimum

            // Update best range if smaller
            if (currentMax - curr.val < rangeEnd - rangeStart) {
                rangeStart = curr.val;
                rangeEnd = currentMax;
            }

            // Move to the next element in the same list
            if (curr.idx + 1 < nums.get(curr.list).size()) {
                int nextVal = nums.get(curr.list).get(curr.idx + 1);
                minHeap.offer(new Element(nextVal, curr.list, curr.idx + 1));
                currentMax = Math.max(currentMax, nextVal);
            } else {
                // One list is exhausted; we can't include all k lists anymore
                break;
            }
        }

        return new int[]{rangeStart, rangeEnd};
    }

    // Helper class to store value, list index, and element index
    private static class Element {
        int val;
        int list;
        int idx;

        Element(int val, int list, int idx) {
            this.val = val;
            this.list = list;
            this.idx = idx;
        }
    }
}
