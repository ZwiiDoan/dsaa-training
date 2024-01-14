package per.duyd.training.dsaa.sqb;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DequeTest {
  @Test
  public void testConstructorCreatesEmptyDeque() {
    Deque<Integer> deque = new Deque<>();
    Assertions.assertTrue(deque.isEmpty());
    Assertions.assertEquals(0, deque.size());
  }

  @Test
  public void testAddFirstAddsToFront() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(10);
    deque.addFirst(5);
    Assertions.assertEquals(2, deque.size());
    Assertions.assertEquals(5, deque.removeFirst());
    Assertions.assertEquals(10, deque.removeFirst());
  }

  @Test
  public void testAddLastAddsToBack() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(5);
    deque.addLast(10);
    Assertions.assertEquals(2, deque.size());
    Assertions.assertEquals(5, deque.removeFirst());
    Assertions.assertEquals(10, deque.removeFirst());
  }

  @Test
  public void testRemoveFirstRemovesFromFront() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(5);
    deque.addFirst(10);
    Assertions.assertEquals(10, deque.removeFirst());
    Assertions.assertEquals(5, deque.removeFirst());
  }

  @Test
  public void testRemoveLastRemovesFromBack() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(5);
    deque.addLast(10);
    Assertions.assertEquals(10, deque.removeLast());
    Assertions.assertEquals(5, deque.removeLast());
  }

  @Test
  public void testIteratorIteratesInOrder() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(1);
    deque.addLast(2);
    deque.addLast(3);
    String result = "";
    for (int item : deque) {
      result += item;
    }
    Assertions.assertEquals("123", result);
  }

  @Test
  public void testValidateItemThrowsExceptionForNull() {
    Deque<Integer> deque = new Deque<>();
    Assertions.assertThrows(IllegalArgumentException.class, () -> deque.addFirst(null));
    Assertions.assertThrows(IllegalArgumentException.class, () -> deque.addLast(null));
  }

  @Test
  public void testRemoveFirstThrowsExceptionWhenEmpty() {
    Deque<Integer> deque = new Deque<>();
    Assertions.assertThrows(NoSuchElementException.class, deque::removeFirst);
  }

  @Test
  public void testRemoveLastThrowsExceptionWhenEmpty() {
    Deque<Integer> deque = new Deque<>();
    Assertions.assertThrows(NoSuchElementException.class, deque::removeLast);
  }

  // Test iterator behavior for empty deque
  @Test
  public void testIteratorHandlesEmptyDeque() {
    Deque<Integer> deque = new Deque<>();
    Iterator<Integer> iterator = deque.iterator();
    Assertions.assertFalse(iterator.hasNext());
    Assertions.assertThrows(NoSuchElementException.class, iterator::next);
  }

  // Test iterator behavior after removing elements
  @Test
  public void testIteratorAfterRemovals() {
    Deque<Integer> deque = new Deque<>();
    deque.addLast(1);
    deque.addLast(2);
    deque.removeFirst();
    Iterator<Integer> iterator = deque.iterator();
    Assertions.assertTrue(iterator.hasNext());
    Assertions.assertEquals(2, iterator.next());
    Assertions.assertFalse(iterator.hasNext());
  }

  // Test mixing addFirst and addLast operations
  @Test
  public void testMixingAddFirstAndAddLast() {
    Deque<Integer> deque = new Deque<>();
    deque.addFirst(1);
    deque.addLast(2);
    deque.addFirst(3);
    deque.addLast(4);
    Assertions.assertEquals(3, deque.removeFirst());
    Assertions.assertEquals(1, deque.removeFirst());
    Assertions.assertEquals(2, deque.removeFirst());
    Assertions.assertEquals(4, deque.removeFirst());
  }

  @Test
  public void testMain() {
    Assertions.assertDoesNotThrow(() -> Deque.main(new String[] {}));
  }
}