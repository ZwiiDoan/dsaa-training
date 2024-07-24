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
}
