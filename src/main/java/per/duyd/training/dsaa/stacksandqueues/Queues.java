package per.duyd.training.dsaa.stacksandqueues;

import java.util.ArrayDeque;

public class Queues {
  public static class MyStack {

    private ArrayDeque<Integer> queue1;
    private ArrayDeque<Integer> queue2;
    private Integer top;

    public MyStack() {
      queue1 = new ArrayDeque<>();
      queue2 = new ArrayDeque<>();
    }

    public void push(int x) {
      queue1.offer(x);
      top = x;
    }

    public int pop() {
      while (queue1.size() > 1) {
        top = queue1.remove();
        queue2.add(top);
      }

      int ans = queue1.remove();
      ArrayDeque<Integer> tmp = queue1;
      queue1 = queue2;
      queue2 = tmp;

      return ans;
    }

    public int top() {
      return top;
    }

    public boolean empty() {
      return queue1.isEmpty() && queue2.isEmpty();
    }
  }

}
