package per.duyd.training.dsaa.heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HeapsTest {

    private Heaps heaps;

    public static Stream<Arguments> topKFrequentParams() {
        return Stream.of(
                Arguments.of("Example 1", new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2,
                        List.of("i", "love")),
                Arguments.of("Example 2",
                        new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4,
                        List.of("the", "is", "sunny", "day")));
    }

    @BeforeEach
    void setUp() {
        heaps = new Heaps();
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("topKFrequentParams")
    void topKFrequent(String description, String[] words, int k, List<String> expected) {
        assertEquals(expected, heaps.topKFrequent(words, k));
    }

    @Test
    void seatManagerTest() {
        Heaps.SeatManager seatManager = new Heaps.SeatManager(5);
        assertEquals(1, seatManager.reserve());
        assertEquals(2, seatManager.reserve());
        seatManager.unreserve(2);
        assertEquals(2, seatManager.reserve());
        assertEquals(3, seatManager.reserve());
        assertEquals(4, seatManager.reserve());
        assertEquals(5, seatManager.reserve());
        seatManager.unreserve(5);
    }

    @Test
    void smallestInfiniteSetTest() {
        Heaps.SmallestInfiniteSet smallestInfiniteSet = new Heaps.SmallestInfiniteSet();
        smallestInfiniteSet.addBack(2);
        assertEquals(1, smallestInfiniteSet.popSmallest());
        assertEquals(2, smallestInfiniteSet.popSmallest());
        assertEquals(3, smallestInfiniteSet.popSmallest());
        smallestInfiniteSet.addBack(1);
        assertEquals(1, smallestInfiniteSet.popSmallest());
        assertEquals(4, smallestInfiniteSet.popSmallest());
        assertEquals(5, smallestInfiniteSet.popSmallest());
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("repeatLimitedStringParams")
    void repeatLimitedString(String description, String s, int repeatLimit, String expected) {
        assertEquals(expected, heaps.repeatLimitedString(s, repeatLimit));
    }

    public static Stream<Arguments> repeatLimitedStringParams() {
        return Stream.of(
                Arguments.of("Example 1", "cczazcc", 3, "zzcccac"),
                Arguments.of("Example 2", "aababab", 2, "bbabaa"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("totalCostParams")
    void totalCost(String description, int[] costs, int k, int candidates, long expected) {
        assertEquals(expected, heaps.totalCost(costs, k, candidates));
    }

    public static Stream<Arguments> totalCostParams() {
        return Stream.of(
                Arguments.of("Example 1", new int[]{17, 12, 10, 2, 7, 2, 11, 20, 8}, 3, 4, 11),
                Arguments.of("Example 2", new int[]{1, 2, 4, 1}, 3, 3, 4));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("kSmallestPairsParams")
    void kSmallestPairs(String description, int[] nums1, int[] nums2, int k, List<List<Integer>> expected) {
        assertEquals(expected, heaps.kSmallestPairs(nums1, nums2, k));
    }

    public static Stream<Arguments> kSmallestPairsParams() {
        return Stream.of(
                Arguments.of("Example 1", new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3,
                        List.of(List.of(1, 2), List.of(1, 4), List.of(1, 6))),
                Arguments.of("Example 2", new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2,
                        List.of(List.of(1, 1), List.of(1, 1))));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("mostBookedParams")
    void mostBooked(String description, int n, int[][] meetings, int expected) {
        assertEquals(expected, heaps.mostBooked(n, meetings));
    }

    public static Stream<Arguments> mostBookedParams() {
        return Stream.of(
                Arguments.of("Example 1", 2, new int[][]{{0, 10}, {1, 5}, {2, 7}, {3, 4}}, 0),
                Arguments.of("Example 2", 3, new int[][]{{1, 20}, {2, 10}, {3, 5}, {4, 9}, {6, 8}}, 1));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("smallestRangeParams")
    void smallestRange(String description, List<List<Integer>> nums, int[] expected) {
        assertArrayEquals(expected, heaps.smallestRange(nums));
    }

    public static Stream<Arguments> smallestRangeParams() {
        return Stream.of(
                Arguments.of("Example 1",
                        List.of(
                                List.of(4, 10, 15, 24, 26),
                                List.of(0, 9, 12, 20),
                                List.of(5, 18, 22, 30)
                        ),
                        new int[]{20, 24}
                ),
                Arguments.of("Identical lists",
                        List.of(
                                List.of(1, 2, 3),
                                List.of(1, 2, 3),
                                List.of(1, 2, 3)
                        ),
                        new int[]{1, 1}
                )
        );
    }
}