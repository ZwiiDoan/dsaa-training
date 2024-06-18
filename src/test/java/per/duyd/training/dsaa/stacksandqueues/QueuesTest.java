package per.duyd.training.dsaa.stacksandqueues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QueuesTest {

  private Queues queues;

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
}