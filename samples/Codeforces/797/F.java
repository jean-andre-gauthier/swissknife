import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class F {
  public static void main(String[] args) {
    int n = in.nextInt();
    int m = in.nextInt();
    long[] x = in.nextLongs(n, true);
    long[][] pc = new long[m + 1][2];
    /* for (int n = 2; n <= 50; ++n) { */
    /*   int m = n; */
    /*   long[] x = rand.nextLongs(n, true, 1, n * m + 1); */
    /*   long[][] pc = new long[m + 1][2]; */
    long totalCapacity = 0;
    for (int i = 1; i <= m; ++i) {
      pc[i][0] = in.nextLong();
      /* pc[i][0] = rand.nextLong(1, m * n + 1); */
      pc[i][1] = in.nextLong();
      /* pc[i][1] = rand.nextLong(1, m * n + 1); */
      totalCapacity += pc[i][1];
      /* } */
      /* long dpOnly = dpOnly(n, m, x, pc, totalCapacity); */
      /* long dpMmq = dpMmq(n, m, x, pc, totalCapacity); */
      /* if (dpOnly != dpMmq) { */
      /*   out.println(n + " " + m); */
      /*   out.println(x, false); */
      /*   out.println(pc); */
      /*   out.println("dpOnly: " + dpOnly + " dpMmq: " + dpMmq); */
      /*   throw new RuntimeException(); */
      /* } */
    }
    // out.println(dpOnly(n, m, x, pc, totalCapacity));
    out.println(dpMmq(n, m, x, pc, totalCapacity));
  }

  static long dpOnly(int n, int m, long[] x, long[][] pc, long totalCapacity) {

    Arrays.sort(x, 1, n + 1);
    Arrays.sort(pc, 1, m + 1, (long[] pc1, long[] pc2) -> Long.compare(pc1[0], pc2[0]));

    if (totalCapacity < n) {
      return -1;
    }

    long[][] M = new long[m + 1][n + 1];
    M[1][1] = Math.abs(x[1] - pc[1][0]);
    for (int j = 2; j <= n; ++j) {
      M[1][j] = j <= pc[1][1] ? M[1][j - 1] + Math.abs(x[j] - pc[1][0]) : -1;
    }

    for (int i = 2; i <= m; ++i) {
      for (int j = 1; j <= n; ++j) {
        long mij = M[i - 1][j];
        long dist = 0;
        for (int k = 0; k < j && k < pc[i][1]; ++k) {
          dist += Math.abs(x[j - k] - pc[i][0]);
          if (M[i - 1][j - k - 1] != -1) {
            mij =
                mij != -1 ? Math.min(mij, M[i - 1][j - k - 1] + dist) : M[i - 1][j - k - 1] + dist;
          }
        }
        M[i][j] = mij;
      }
    }
    // out.println(M);
    return M[m][n];
  }

  static long dpMmq(int n, int m, long[] x, long[][] pc, long totalCapacity) {
    Arrays.sort(x, 1, n + 1);
    Arrays.sort(pc, 1, m + 1, (long[] pc1, long[] pc2) -> Long.compare(pc1[0], pc2[0]));

    if (totalCapacity < n) {
      return -1;
    }

    long[][] M = new long[m + 1][n + 1];
    M[1][1] = Math.abs(x[1] - pc[1][0]);
    for (int j = 2; j <= n; ++j) {
      M[1][j] = j <= pc[1][1] ? M[1][j - 1] + Math.abs(x[j] - pc[1][0]) : -1;
    }

    MinMaxQueueLong mmql = new MinMaxQueueLong(n);
    for (int i = 2; i <= m; ++i) {
      long dist = 0;
      /* long dist = Math.abs(x[1] - pc[i][0]); */
      /* M[i][1] = Math.min(M[i - 1][1], dist); */
      /* mmql.enqueue(0); */
      /* mmql.enqueue(M[i - 1][1] - dist); */
      int j1 = 0;
      for (int j = 0; j <= n && M[i - 1][j] != -1; ++j, ++j1) {
        /* out.println(mmql); */
        dist += Math.abs(x[j] - pc[i][0]);
        mmql.enqueue(M[i - 1][j] - dist);
        // out.println(mmql);
        M[i][j] = mmql.getMin() + dist;
        if (mmql.size() > pc[i][1]) {
          mmql.dequeue();
        }
      }
      int j2 = j1;
      for (int j = 0; j1 + j <= n && j < pc[i][1]; ++j, ++j2) {
        if (mmql.size() > pc[i][1] - j) {
          mmql.dequeue();
        }
        /* out.println(mmql); */
        dist += Math.abs(x[j1 + j] - pc[i][0]);
        // out.println(mmql);
        M[i][j1 + j] = mmql.getMin() + dist;
      }

      for (int j = 0; j2 + j <= n; ++j) {
        M[i][j2 + j] = -1;
      }
      mmql.reset();
    }
    /* out.println(M); */
    return M[m][n];
  }

  static MyScanner in = new MyScanner();
  static MyPrintWriter out = new MyPrintWriter();
  static RandScanner rand = new RandScanner();

  static class MyPrintWriter {
    BufferedOutputStream bos;
    PrintWriter pw;

    MyPrintWriter() {
      this.bos = new BufferedOutputStream(System.out);
      this.pw = new PrintWriter(bos, true);
    }

    void flush() {
      pw.flush();
    }

    void print(String t) {
      pw.print(t);
    }

    void println() {
      pw.println("");
    }

    void println(String s) {
      pw.println(s);
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
      println(ts, true);
    }

    void println(long[] ts) {
      println(ts, true);
    }

    void println(double[] ts) {
      println(ts, true);
    }

    <T> void println(T[] ts) {
      println(ts, true);
    }

    <T> void println(Collection<T> ts) {
      println(ts, true);
    }

    <T, U> void println(Map<T, U> ts) {
      println(ts, true);
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
  }

  static class MyScanner {
    InputStreamReader is;
    BufferedReader br;
    StringTokenizer st;

    MyScanner() {
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
        return nextInts(n, 1, n + 1);
      } else {
        return nextInts(n, 0, n);
      }
    }

    int[] nextInts(int n, int iMin, int iMax) {
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
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
        return nextLongs(n, 1, n + 1);
      } else {
        return nextLongs(n, 0, n);
      }
    }

    long[] nextLongs(int n, int iMin, int iMax) {
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
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
        return nextDoubles(n, 1, n + 1);
      } else {
        return nextDoubles(n, 0, n);
      }
    }

    double[] nextDoubles(int n, int iMin, int iMax) {
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
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
      return nextInt(0, Integer.MAX_VALUE);
    }

    int nextInt(int lowerBound, int upperBound) {
      return r.nextInt(lowerBound, upperBound);
    }

    int[] nextInts(int n) {
      return nextInts(n, 0, Integer.MAX_VALUE);
    }

    int[] nextInts(int n, int lowerBound, int upperBound) {
      return nextInts(n, false, lowerBound, upperBound);
    }

    int[] nextInts(int n, boolean oneBased) {
      return nextInts(n, oneBased, 0, Integer.MAX_VALUE);
    }

    int[] nextInts(int n, boolean oneBased, int lowerBound, int upperBound) {
      if (oneBased) {
        return nextInts(n, 1, n + 1, lowerBound, upperBound);
      } else {
        return nextInts(n, 0, n, lowerBound, upperBound);
      }
    }

    // int[] nextInts(int n, int iMin, int iMax) {
    //   return nextInts(n, iMin, iMax, 0, Integer.MAX_VALUE);
    // }

    int[] nextInts(int n, int iMin, int iMax, int lowerBound, int upperBound) {
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt(lowerBound, upperBound);
      }
      return ints;
    }

    long nextLong() {
      return nextLong(0L, Long.MAX_VALUE);
    }

    long nextLong(long lowerBound, long upperBound) {
      return r.nextLong(lowerBound, upperBound);
    }

    long[] nextLongs(int n) {
      return nextLongs(n, 0L, Long.MAX_VALUE);
    }

    long[] nextLongs(int n, long lowerBound, long upperBound) {
      return nextLongs(n, false, lowerBound, upperBound);
    }

    long[] nextLongs(int n, boolean oneBased) {
      return nextLongs(n, oneBased, 0L, Long.MAX_VALUE);
    }

    long[] nextLongs(int n, boolean oneBased, long lowerBound, long upperBound) {
      if (oneBased) {
        return nextLongs(n, 1, n + 1, lowerBound, upperBound);
      } else {
        return nextLongs(n, 0, n, lowerBound, upperBound);
      }
    }

    // long[] nextLongs(int n, int iMin, int iMax) {
    //   return nextLongs(n, iMin, iMax, 0L, Long.MAX_VALUE);
    // }

    long[] nextLongs(int n, int iMin, int iMax, long lowerBound, long upperBound) {
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong(lowerBound, upperBound);
      }
      return longs;
    }

    double nextDouble() {
      return nextDouble(0, 1);
    }

    double nextDouble(double lowerBound, double upperBound) {
      return r.nextDouble(lowerBound, upperBound);
    }

    double[] nextDoubles(int n) {
      return nextDoubles(n, 0, 1);
    }

    double[] nextDoubles(int n, double lowerBound, double upperBound) {
      return nextDoubles(n, false, lowerBound, upperBound);
    }

    double[] nextDoubles(int n, boolean oneBased) {
      return nextDoubles(n, oneBased, 0, 1);
    }

    double[] nextDoubles(int n, boolean oneBased, double lowerBound, double upperBound) {
      if (oneBased) {
        return nextDoubles(n, 1, n + 1, lowerBound, upperBound);
      } else {
        return nextDoubles(n, 0, n, lowerBound, upperBound);
      }
    }

    // double[] nextDoubles(int n, int iMin, int iMax) {
    //   return nextDoubles(n, iMin, iMax);
    // }

    double[] nextDoubles(int n, int iMin, int iMax, double lowerBound, double upperBound) {
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble(lowerBound, upperBound);
      }
      return doubles;
    }
  }

  static class MinMaxStackLong {
    long[][] stack;
    int top;

    MinMaxStackLong(int capacity) {
      stack = new long[capacity][2];
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
      /* if (stack.length >= 4 && top == stack.length / 4) { */
      /*   stack = Arrays.copyOf(stack, Math.max(1, stack.length / 2)); */
      /* } */
      return stack[top--][0];
    }

    void push(long i) {
      if (top == stack.length - 1) {
        int oldStackLength = stack.length;
        int newStackLength = oldStackLength * 2;
        stack = Arrays.copyOf(stack, newStackLength);
        for (int j = oldStackLength; j < newStackLength; ++j) {
          stack[j] = new long[2];
        }
      }
      top++;
      stack[top][0] = i;
      stack[top][1] = top == 0 ? i : Math.min(i, stack[top - 1][1]);
      /* stack[top][2] = top == 0 ? i : Math.max(i, stack[top - 1][2]); */
    }

    void reset() {
      top = -1;
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

    MinMaxQueueLong(int queueCapacity) {
      stackIn = new MinMaxStackLong(queueCapacity);
      stackOut = new MinMaxStackLong(queueCapacity);
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

    void reset() {
      stackIn.reset();
      stackOut.reset();
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
