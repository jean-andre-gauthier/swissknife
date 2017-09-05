package ja.gauthier.competitiveprogramming;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class MinMaxQueueStack {
  static class MinMaxStackInt {
    int[][] stack;
    int top;

    MinMaxStackInt(int capacity) {
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
      if (stack.length >= 4 && top == stack.length / 4) {
        stack = Arrays.copyOf(stack, Math.max(1, stack.length / 2));
      }
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

  static class MinMaxQueueInt {
    MinMaxStackInt stackIn;
    MinMaxStackInt stackOut;

    MinMaxQueueInt(int stackCapacity) {
      stackIn = new MinMaxStackInt(stackCapacity);
      stackOut = new MinMaxStackInt(stackCapacity);
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

  static class MinMaxStackLong {
    long[][] stack;
    int top;

    MinMaxStackLong(int capacity) {
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
      if (stack.length >= 4 && top == stack.length / 4) {
        stack = Arrays.copyOf(stack, Math.max(1, stack.length / 2));
      }
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

  static class MinMaxQueueLong {
    MinMaxStackLong stackIn;
    MinMaxStackLong stackOut;

    MinMaxQueueLong(int stackCapacity) {
      stackIn = new MinMaxStackLong(stackCapacity);
      stackOut = new MinMaxStackLong(stackCapacity);
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

  static class MinMaxStackDouble {
    double[][] stack;
    int top;

    MinMaxStackDouble(int capacity) {
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
      if (stack.length >= 4 && top == stack.length / 4) {
        stack = Arrays.copyOf(stack, Math.max(1, stack.length / 2));
      }
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

  static class MinMaxQueueDouble {
    MinMaxStackDouble stackIn;
    MinMaxStackDouble stackOut;

    MinMaxQueueDouble(int stackCapacity) {
      stackIn = new MinMaxStackDouble(stackCapacity);
      stackOut = new MinMaxStackDouble(stackCapacity);
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
