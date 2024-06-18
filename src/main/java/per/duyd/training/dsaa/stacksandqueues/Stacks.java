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
}
