package per.duyd.training.dsaa.hashing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CheckingForExistenceTest {
  private CheckingForExistence checkingForExistence;

  public static Stream<Arguments> testContainsDuplicateParams() {
    return Stream.of(Arguments.of("Array contains duplicate", new int[] {1, 2, 3, 1}, true),
        Arguments.of("Array does NOT contain duplicate", new int[] {1, 2, 3, 4}, false),
        Arguments.of("Array contains duplicate 2", new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true));
  }

  @BeforeEach
  void setUp() {
    checkingForExistence = new CheckingForExistence();
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource("testContainsDuplicateParams")
  void testContainsDuplicate(String description, int[] nums, boolean expected) {
    assertEquals(expected, checkingForExistence.containsDuplicate(nums));
  }
}