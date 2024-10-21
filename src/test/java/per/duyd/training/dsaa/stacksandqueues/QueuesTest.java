package per.duyd.training.dsaa.stacksandqueues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueuesTest {

  private Queues queues;

  public static Stream<Arguments> testPredictVictoryPartyParams() {
    return Stream.of(
        Arguments.of("Example 1", "RD", "Radiant"),
        Arguments.of("Example 1", "RDD", "Dire")
    );
  }

  @BeforeEach
  void setUp() {
    queues = new Queues();
  }

  @Test
  void testMyStack() {
    Queues.MyStack myStack = new Queues.MyStack();
    myStack.push(1);
    myStack.push(2);

    assertEquals(2, myStack.pop());
    assertEquals(1, myStack.pop());
    assertTrue(myStack.empty());
  }

  @ParameterizedTest(name = "{0}")
  @MethodSource({"testPredictVictoryPartyParams"})
  void testPredictVictoryParty(String description, String senate, String expected) {
    assertEquals(expected, queues.predictPartyVictory(senate));
  }
}