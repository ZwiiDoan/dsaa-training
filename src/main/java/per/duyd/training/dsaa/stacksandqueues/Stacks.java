package per.duyd.training.dsaa.stacksandqueues;

import java.util.Stack;

public class Stacks {

  private static final String DOUBLE_PERIOD = "..";
  private static final String SLASH = "/";
  private static final String DOT = ".";
  private static final String EMPTY = "";

  public String simplifyPath(String path) {
    Stack<String> stack = new Stack<>();

    for (String part : path.split(SLASH)) {
      if (EMPTY.equals(part) || DOT.equals(part)) {
        continue;
      }

      if (DOUBLE_PERIOD.equals(part)) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(part);
      }
    }

    if (stack.isEmpty()) {
      return SLASH;
    } else {
      StringBuilder pathBuilder = new StringBuilder();

      for (String part : stack) {
        pathBuilder.append(SLASH).append(part);
      }

      return pathBuilder.toString();
    }
  }

  public String removeStars(String s) {
    Stack<Character> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);

      if (c == '*') {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else {
        stack.push(c);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (char c : stack) {
      sb.append(c);
    }

    return sb.toString();
  }

  public String robotWithString(String s) {
    Stack<Character> t = new Stack<>();
    StringBuilder p = new StringBuilder();

    int n = s.length();
    char[] rightMin = new char[n];
    rightMin[n - 1] = s.charAt(n - 1);
    for (int i = n - 2; i >= 0; i--) {
      rightMin[i] = s.charAt(i) < rightMin[i + 1] ? s.charAt(i) : rightMin[i + 1];
    }

    for (int i = 0; i < n; i++) {
      char curr = s.charAt(i);
      while (!t.isEmpty() && curr >= t.peek() && rightMin[i] >= t.peek()) {
        p.append(t.pop());
      }
      t.push(curr);
    }

    while (!t.isEmpty()) {
      p.append(t.pop());
    }

    return p.toString();
  }

  public boolean validateStackSequences(int[] pushed, int[] popped) {
    Stack<Integer> stack = new Stack<>();

    int i = 0;
    for (int p : pushed) {
      stack.push(p);
      while (i < popped.length && !stack.isEmpty() && popped[i] == stack.peek()) {
        stack.pop();
        i++;
      }
    }

    return i == popped.length;
  }

  public int[] asteroidCollision(int[] asteroids) {
    Stack<Integer> stack = new Stack<>();
    for (int asteroid : asteroids) {
      boolean destroyed = false;

      while (!stack.isEmpty() && stack.peek() > 0 && asteroid < 0) {
        int top = stack.pop();

        if (top + asteroid == 0) {
          destroyed = true;
          break;
        } else {
          asteroid = Math.abs(top) > Math.abs(asteroid) ? top : asteroid;
        }
      }

      if (!destroyed) {
        stack.push(asteroid);
      }
    }

    return stack.stream().mapToInt(Integer::intValue).toArray();
  }

  public static class MinStack {

    private final Stack<int[]> stack;

    public MinStack() {
      stack = new Stack<>();
    }

    public void push(int val) {
      if (stack.isEmpty()) {
        stack.push(new int[] {val, val});
      } else {
        stack.push(new int[] {val, Math.min(stack.peek()[1], val)});
      }
    }

    public void pop() {
      stack.pop();
    }

    public int top() {
      return stack.peek()[0];
    }

    public int getMin() {
      return stack.peek()[1];
    }
  }

  public static class MaxStack {
    private final Stack<int[]> stack;

    public MaxStack() {
      stack = new Stack<>();
    }

    public void push(int val) {
      if (stack.isEmpty()) {
        stack.push(new int[] {val, val});
      } else {
        stack.push(new int[] {val, Math.max(stack.peek()[1], val)});
      }
    }

    public void pop() {
      stack.pop();
    }

    public int top() {
      return stack.peek()[0];
    }

    public int getMax() {
      return stack.peek()[1];
    }
  }
}
