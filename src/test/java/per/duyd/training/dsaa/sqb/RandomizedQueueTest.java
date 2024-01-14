package per.duyd.training.dsaa.sqb;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RandomizedQueueTest {
  @Test
  public void testConstructor() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    Assertions.assertEquals(0, queue.size());
    Assertions.assertTrue(queue.isEmpty());
  }

  @Test
  public void testEnqueue() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    Assertions.assertEquals(3, queue.size());
    Assertions.assertFalse(queue.isEmpty());
  }

  @Test
  public void testEnqueueNullItem() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    Assertions.assertThrows(IllegalArgumentException.class, () -> queue.enqueue(null));
  }

  @Test
  public void testDequeue() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    int firstItem = queue.dequeue();
    Assertions.assertTrue(firstItem == 1 || firstItem == 2 || firstItem == 3);
    Assertions.assertEquals(2, queue.size());
  }

  @Test
  public void testDequeueEmptyQueue() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    Assertions.assertThrows(NoSuchElementException.class, queue::dequeue);
  }

  @Test
  public void testSample() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    int randomItem = queue.sample();
    Assertions.assertTrue(randomItem == 1 || randomItem == 2 || randomItem == 3);
    Assertions.assertEquals(3, queue.size()); // Size remains unchanged
  }

  @Test
  public void testSampleEmptyQueue() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    Assertions.assertThrows(NoSuchElementException.class, queue::sample);
  }

  @Test
  public void testIterator() {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    Iterator<Integer> iterator1 = queue.iterator();
    int count = 0;
    while (iterator1.hasNext()) {
      Integer item = iterator1.next();
      Assertions.assertTrue(item == 1 || item == 2 || item == 3);
      count++;
    }
    Assertions.assertEquals(3, count);
    Assertions.assertFalse(iterator1.hasNext());
    Assertions.assertThrows(NoSuchElementException.class, iterator1::next);
  }

  @Test
  public void testMain() {
    Assertions.assertDoesNotThrow(() -> RandomizedQueue.main(new String[] {}));
  }
}