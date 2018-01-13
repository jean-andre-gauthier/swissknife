package ja.gauthier.swissknife;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class MonotonicQueueStack {
  static class MonotonicStackInt {
    int[][] stack;
    int top;

    MonotonicStackInt(int capacity) {
      stack = new int[capacity][3];
      top = -1;
    }

    int getMax() {
      return stack[top][2];
    }

    int getMin() {
      return stack[top][1];
    }

    boolean isEmpty() {
      return top == -1;
    }

    int pop() {
      return stack[top--][0];
    }

    void push(int i) {
      if (top == stack.length - 1) {
        int oldStackLength = stack.length;
        int newStackLength = oldStackLength * 2;
        stack = Arrays.copyOf(stack, newStackLength);
        for (int j = oldStackLength; j < newStackLength; ++j) {
          stack[j] = new int[3];
        }
      }
      top++;
      stack[top][0] = i;
      stack[top][1] = top == 0 ? i : Math.min(i, stack[top - 1][1]);
      stack[top][2] = top == 0 ? i : Math.max(i, stack[top - 1][2]);
    }

    int size() {
      return top + 1;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(stack.length * 6);
      for (int j = 0; j < 3; ++j) {
        toString(sb, j, false);
        sb.append("\n");
      }
      return sb.toString();
    }

    void toString(StringBuilder sb, int j, boolean reversed) {
      if (reversed) {
        for (int i = 0; i < size(); ++i) {
          sb.append(stack[i][j] + " ");
        }
      } else {
        for (int i = 0; i < size(); ++i) {
          sb.append(stack[size() - i - 1][j] + " ");
        }
      }
      if (size() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }

  static class MonotonicQueueInt {
    MonotonicStackInt stackIn;
    MonotonicStackInt stackOut;

    MonotonicQueueInt(int stackCapacity) {
      stackIn = new MonotonicStackInt(stackCapacity);
      stackOut = new MonotonicStackInt(stackCapacity);
    }

    int dequeue() {
      if (!stackOut.isEmpty()) {
        return stackOut.pop();
      } else {
        while (!stackIn.isEmpty()) {
          stackOut.push(stackIn.pop());
        }
        return stackOut.pop();
      }
    }

    int getMax() {
      return stackIn.isEmpty()
          ? stackOut.getMax()
          : stackOut.isEmpty() ? stackIn.getMax() : Math.max(stackIn.getMax(), stackOut.getMax());
    }

    int getMin() {
      return stackIn.isEmpty()
          ? stackOut.getMin()
          : stackOut.isEmpty() ? stackIn.getMin() : Math.min(stackIn.getMin(), stackOut.getMin());
    }

    boolean isEmpty() {
      return stackIn.isEmpty() && stackOut.isEmpty();
    }

    void enqueue(int i) {
      stackIn.push(i);
    }

    int size() {
      return stackIn.size() + stackOut.size();
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(size() * 6);
      for (int j = 0; j < 3; ++j) {
        stackIn.toString(sb, j, true);
        if (stackIn.size() > 0) {
          sb.append(" ");
        }
        stackOut.toString(sb, j, false);
        sb.append("\n");
      }
      return sb.toString();
    }
  }

  static class MonotonicStackLong {
    long[][] stack;
    int top;

    MonotonicStackLong(int capacity) {
      stack = new long[capacity][3];
      top = -1;
    }

    long getMax() {
      return stack[top][2];
    }

    long getMin() {
      return stack[top][1];
    }

    boolean isEmpty() {
      return top == -1;
    }

    long pop() {
      return stack[top--][0];
    }

    void push(long i) {
      if (top == stack.length - 1) {
        int oldStackLength = stack.length;
        int newStackLength = oldStackLength * 2;
        stack = Arrays.copyOf(stack, newStackLength);
        for (int j = oldStackLength; j < newStackLength; ++j) {
          stack[j] = new long[3];
        }
      }
      top++;
      stack[top][0] = i;
      stack[top][1] = top == 0 ? i : Math.min(i, stack[top - 1][1]);
      stack[top][2] = top == 0 ? i : Math.max(i, stack[top - 1][2]);
    }

    int size() {
      return top + 1;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(stack.length * 6);
      for (int j = 0; j < 3; ++j) {
        toString(sb, j, false);
        sb.append("\n");
      }
      return sb.toString();
    }

    void toString(StringBuilder sb, int j, boolean reversed) {
      if (reversed) {
        for (int i = 0; i < size(); ++i) {
          sb.append(stack[i][j] + " ");
        }
      } else {
        for (int i = 0; i < size(); ++i) {
          sb.append(stack[size() - i - 1][j] + " ");
        }
      }
      if (size() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }

  static class MonotonicQueueLong {
    MonotonicStackLong stackIn;
    MonotonicStackLong stackOut;

    MonotonicQueueLong(int stackCapacity) {
      stackIn = new MonotonicStackLong(stackCapacity);
      stackOut = new MonotonicStackLong(stackCapacity);
    }

    long dequeue() {
      if (!stackOut.isEmpty()) {
        return stackOut.pop();
      } else {
        while (!stackIn.isEmpty()) {
          stackOut.push(stackIn.pop());
        }
        return stackOut.pop();
      }
    }

    long getMax() {
      return stackIn.isEmpty()
          ? stackOut.getMax()
          : stackOut.isEmpty() ? stackIn.getMax() : Math.max(stackIn.getMax(), stackOut.getMax());
    }

    long getMin() {
      return stackIn.isEmpty()
          ? stackOut.getMin()
          : stackOut.isEmpty() ? stackIn.getMin() : Math.min(stackIn.getMin(), stackOut.getMin());
    }

    boolean isEmpty() {
      return stackIn.isEmpty() && stackOut.isEmpty();
    }

    void enqueue(long i) {
      stackIn.push(i);
    }

    int size() {
      return stackIn.size() + stackOut.size();
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(size() * 6);
      for (int j = 0; j < 3; ++j) {
        stackIn.toString(sb, j, true);
        if (stackIn.size() > 0) {
          sb.append(" ");
        }
        stackOut.toString(sb, j, false);
        sb.append("\n");
      }
      return sb.toString();
    }
  }

  static class MonotonicStackDouble {
    double[][] stack;
    int top;

    MonotonicStackDouble(int capacity) {
      stack = new double[capacity][3];
      top = -1;
    }

    double getMax() {
      return stack[top][2];
    }

    double getMin() {
      return stack[top][1];
    }

    boolean isEmpty() {
      return top == -1;
    }

    double pop() {
      return stack[top--][0];
    }

    void push(double i) {
      if (top == stack.length - 1) {
        int oldStackLength = stack.length;
        int newStackLength = oldStackLength * 2;
        stack = Arrays.copyOf(stack, newStackLength);
        for (int j = oldStackLength; j < newStackLength; ++j) {
          stack[j] = new double[3];
        }
      }
      top++;
      stack[top][0] = i;
      stack[top][1] = top == 0 ? i : Math.min(i, stack[top - 1][1]);
      stack[top][2] = top == 0 ? i : Math.max(i, stack[top - 1][2]);
    }

    int size() {
      return top + 1;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(stack.length * 6);
      for (int j = 0; j < 3; ++j) {
        toString(sb, j, false);
        sb.append("\n");
      }
      return sb.toString();
    }

    void toString(StringBuilder sb, int j, boolean reversed) {
      if (reversed) {
        for (int i = 0; i < size(); ++i) {
          sb.append(stack[i][j] + " ");
        }
      } else {
        for (int i = 0; i < size(); ++i) {
          sb.append(stack[size() - i - 1][j] + " ");
        }
      }
      if (size() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
    }
  }

  static class MonotonicQueueDouble {
    MonotonicStackDouble stackIn;
    MonotonicStackDouble stackOut;

    MonotonicQueueDouble(int stackCapacity) {
      stackIn = new MonotonicStackDouble(stackCapacity);
      stackOut = new MonotonicStackDouble(stackCapacity);
    }

    double dequeue() {
      if (!stackOut.isEmpty()) {
        return stackOut.pop();
      } else {
        while (!stackIn.isEmpty()) {
          stackOut.push(stackIn.pop());
        }
        return stackOut.pop();
      }
    }

    double getMax() {
      return stackIn.isEmpty()
          ? stackOut.getMax()
          : stackOut.isEmpty() ? stackIn.getMax() : Math.max(stackIn.getMax(), stackOut.getMax());
    }

    double getMin() {
      return stackIn.isEmpty()
          ? stackOut.getMin()
          : stackOut.isEmpty() ? stackIn.getMin() : Math.min(stackIn.getMin(), stackOut.getMin());
    }

    boolean isEmpty() {
      return stackIn.isEmpty() && stackOut.isEmpty();
    }

    void enqueue(double i) {
      stackIn.push(i);
    }

    int size() {
      return stackIn.size() + stackOut.size();
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(size() * 6);
      for (int j = 0; j < 3; ++j) {
        stackIn.toString(sb, j, true);
        if (stackIn.size() > 0) {
          sb.append(" ");
        }
        stackOut.toString(sb, j, false);
        sb.append("\n");
      }
      return sb.toString();
    }
  }
}
