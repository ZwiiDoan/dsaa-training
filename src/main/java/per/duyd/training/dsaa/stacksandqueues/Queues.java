package per.duyd.training.dsaa.stacksandqueues;

import java.util.ArrayDeque;
import java.util.Queue;

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

  public String predictPartyVictory(String senate) {
    Queue<Integer> rQueue = new ArrayDeque<>();
    Queue<Integer> dQueue = new ArrayDeque<>();
    int N = senate.length();

    for (int i = 0; i < N; i++) {
      if (senate.charAt(i) == 'R') {
        rQueue.offer(i);
      } else {
        dQueue.offer(i);
      }
    }

    while (!rQueue.isEmpty() && !dQueue.isEmpty()) {
      int rTurn = rQueue.poll();
      int dTurn = dQueue.poll();

      if (rTurn < dTurn) {
        rQueue.offer(rTurn + N);
      } else {
        dQueue.offer(dTurn + N);
      }
    }

    return rQueue.isEmpty() ? "Dire" : "Radiant";
  }
}
