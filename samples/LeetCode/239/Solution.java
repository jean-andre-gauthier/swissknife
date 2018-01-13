import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class Solution {
  public static void main(String[] args) {
    int n = in.nextInt();
    int k = in.nextInt();
    int[] nums = in.nextInts(n);
    int[] solution = new Solution().maxSlidingWindow(nums, k);
    out.println(solution);
  }

  public int[] maxSlidingWindow(int[] nums, int k) {
    int n = nums.length;
    if (n == 0) {
      return new int[0];
    }
    int[] solution = new int[n - k + 1];
    MonotonicQueueInt q = new MonotonicQueueInt(k);
    for (int i = 0; i < n; ++i) {
      q.enqueue(nums[i]);
      if (i >= k - 1) {
        solution[i - k + 1] = q.getMax();
        q.dequeue();
      }
    }
    return solution;
  }

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

  static FastScanner in = new FastScanner();
  static FastPrintWriter out = new FastPrintWriter();
  static RandScanner rand = new RandScanner();

  static class FastPrintWriter {
    BufferedOutputStream bos;
    PrintWriter pw;

    FastPrintWriter() {
      this.bos = new BufferedOutputStream(System.out);
      this.pw = new PrintWriter(bos, true);
    }

    void print(int t) {
      pw.print(t);
    }

    void print(long t) {
      pw.print(t);
    }

    void print(double t) {
      pw.print(t);
    }

    <T> void print(T t) {
      pw.print(t.toString());
    }

    void println() {
      pw.println("");
    }

    void println(int t) {
      pw.println(t);
    }

    void println(long t) {
      pw.println(t);
    }

    void println(double t) {
      pw.println(t);
    }

    <T> void println(T t) {
      pw.println(t.toString());
    }

    void println(int[] ts) {
      println(ts, false);
    }

    void println(long[] ts) {
      println(ts, false);
    }

    void println(double[] ts) {
      println(ts, false);
    }

    <T> void println(T[] ts) {
      println(ts, false);
    }

    <T> void println(Collection<T> ts) {
      println(ts, false);
    }

    <T, U> void println(Map<T, U> ts) {
      println(ts, false);
    }

    void println(int[] ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (int t : ts) {
        sb.append(t);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    void println(long[] ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (long t : ts) {
        sb.append(t);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    void println(double[] ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (double t : ts) {
        sb.append(t);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    <T> void println(T[] ts, boolean newline) {
      println(Arrays.asList(ts), newline);
    }

    <T> void println(Collection<T> ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (T t : ts) {
        sb.append(t.toString());
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    <T, U> void println(Map<T, U> ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (Map.Entry<T, U> t : ts.entrySet()) {
        sb.append(t.getKey().toString());
        sb.append(" -> ");
        sb.append(t.getValue().toString());
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    void println(int[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (int[] ts : tss) {
        for (int t : ts) {
          sb.append(t).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }

    void println(long[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (long[] ts : tss) {
        for (long t : ts) {
          sb.append(t).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }

    void println(double[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (double[] ts : tss) {
        for (double t : ts) {
          sb.append(t).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }

    <T> void println(T[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (T[] ts : tss) {
        for (T t : ts) {
          sb.append(t.toString()).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }
  }

  static class FastScanner {
    InputStreamReader is;
    BufferedReader br;
    StringTokenizer st;

    FastScanner() {
      this.is = new InputStreamReader(System.in);
      this.br = new BufferedReader(is);
    }

    void findToken() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }

    String next() {
      findToken();
      return st.nextToken();
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] nextInts(int n) {
      return nextInts(n, false);
    }

    int[] nextInts(int n, boolean oneBased) {
      if (oneBased) {
        return nextInts(n, 1);
      } else {
        return nextInts(n, 0);
      }
    }

    int[] nextInts(int n, int iMin) {
      int[] ints = new int[iMin + n];
      for (int i = iMin; i < iMin + n; ++i) {
        ints[i] = nextInt();
      }
      return ints;
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    long[] nextLongs(int n) {
      return nextLongs(n, false);
    }

    long[] nextLongs(int n, boolean oneBased) {
      if (oneBased) {
        return nextLongs(n, 1);
      } else {
        return nextLongs(n, 0);
      }
    }

    long[] nextLongs(int n, int iMin) {
      long[] longs = new long[iMin + n];
      for (int i = iMin; i < iMin + n; ++i) {
        longs[i] = nextLong();
      }
      return longs;
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    double[] nextDoubles(int n) {
      return nextDoubles(n, false);
    }

    double[] nextDoubles(int n, boolean oneBased) {
      if (oneBased) {
        return nextDoubles(n, 1);
      } else {
        return nextDoubles(n, 0);
      }
    }

    double[] nextDoubles(int n, int iMin) {
      double[] doubles = new double[iMin + n];
      for (int i = iMin; i < iMin + n; ++i) {
        doubles[i] = nextDouble();
      }
      return doubles;
    }
  }

  static class RandScanner {
    ThreadLocalRandom r;

    RandScanner() {
      this.r = ThreadLocalRandom.current();
    }

    int nextInt() {
      return r.nextInt();
    }

    int nextInt(int upperBoundExclusive) {
      return r.nextInt(upperBoundExclusive);
    }

    int nextInt(int lowerBoundInclusive, int upperBoundExclusive) {
      return r.nextInt(lowerBoundInclusive, upperBoundExclusive);
    }

    int[] nextInts(int n) {
      return nextInts(n, false);
    }

    int[] nextInts(int n, int upperBoundExclusive) {
      return nextInts(n, false, upperBoundExclusive);
    }

    int[] nextInts(int n, int lowerBoundInclusive, int upperBoundExclusive) {
      return nextInts(n, false, lowerBoundInclusive, upperBoundExclusive);
    }

    int[] nextInts(int n, boolean oneBased) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt();
      }
      return ints;
    }

    int[] nextInts(int n, boolean oneBased, int upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt(upperBoundExclusive);
      }
      return ints;
    }

    int[] nextInts(int n, boolean oneBased, int lowerBoundInclusive, int upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt(lowerBoundInclusive, upperBoundExclusive);
      }
      return ints;
    }

    long nextLong() {
      return r.nextLong();
    }

    long nextLong(long upperBoundExclusive) {
      return r.nextLong(upperBoundExclusive);
    }

    long nextLong(long lowerBoundInclusive, long upperBoundExclusive) {
      return r.nextLong(lowerBoundInclusive, upperBoundExclusive);
    }

    long[] nextLongs(int n) {
      return nextLongs(n, false);
    }

    long[] nextLongs(int n, long upperBoundExclusive) {
      return nextLongs(n, false, upperBoundExclusive);
    }

    long[] nextLongs(int n, long lowerBoundInclusive, long upperBoundExclusive) {
      return nextLongs(n, false, lowerBoundInclusive, upperBoundExclusive);
    }

    long[] nextLongs(int n, boolean oneBased) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong();
      }
      return longs;
    }

    long[] nextLongs(int n, boolean oneBased, long upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong(upperBoundExclusive);
      }
      return longs;
    }

    long[] nextLongs(int n, boolean oneBased, long lowerBoundInclusive, long upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong(lowerBoundInclusive, upperBoundExclusive);
      }
      return longs;
    }

    double nextDouble() {
      return r.nextDouble();
    }

    double nextDouble(double upperBoundExclusive) {
      return r.nextDouble(upperBoundExclusive);
    }

    double nextDouble(double lowerBoundInclusive, double upperBoundExclusive) {
      return r.nextDouble(lowerBoundInclusive, upperBoundExclusive);
    }

    double[] nextDoubles(int n) {
      return nextDoubles(n, false);
    }

    double[] nextDoubles(int n, double upperBoundExclusive) {
      return nextDoubles(n, false, upperBoundExclusive);
    }

    double[] nextDoubles(int n, double lowerBoundInclusive, double upperBoundExclusive) {
      return nextDoubles(n, false, lowerBoundInclusive, upperBoundExclusive);
    }

    double[] nextDoubles(int n, boolean oneBased) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble();
      }
      return doubles;
    }

    double[] nextDoubles(int n, boolean oneBased, double upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble(upperBoundExclusive);
      }
      return doubles;
    }

    double[] nextDoubles(
        int n, boolean oneBased, double lowerBoundInclusive, double upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble(lowerBoundInclusive, upperBoundExclusive);
      }
      return doubles;
    }
  }
}
