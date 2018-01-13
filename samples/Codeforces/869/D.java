import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class D {
  public static void main(String[] args) {
    long mod = (long) Math.pow(10, 9) + 1;
    int n = in.nextInt();
    int m = in.nextInt();
    int[][] ms = new int[m][];
    for (int i = 0; i < m; ++i) {
      ms[i] = in.nextInts(2);
    }
    out.println(n + " " + m);
    out.println(ms);

    Map<Integer, Integer> nodeToSize = new HashMap<>();
    Map<Integer, List<Integer>> nodeToNeighbours = new HashMap<>();

    for (int i = 0; i < m; ++i) {
      int left = ms[i][0];
      fill(left, n, nodeToSize, nodeToNeighbours);
      out.println(">>>");
      out.println(nodeToNeighbours);
      out.println(">>>");
      out.println(nodeToSize);
      int right = ms[i][1];
      fill(right, n, nodeToSize, nodeToNeighbours);
      out.println(">>>");
      out.println(nodeToNeighbours);
      out.println(">>>");
      out.println(nodeToSize);
      nodeToNeighbours.get(left).add(right);
      nodeToNeighbours.get(right).add(left);
      out.println(">>>");
      out.println(nodeToNeighbours);
      out.println(">>>");
      out.println(nodeToSize);
      out.println("---");
    }

    if (nodeToNeighbours.get(1) == null) {
      nodeToSize.put(1, n);
      nodeToNeighbours.put(1, new ArrayList<>());
    }

    long result = 0;
    Set<Integer> visited = new HashSet<>();
    for (Integer i : nodeToNeighbours.keySet()) {
      visited.add(i);
      result = (result + dfs(i, mod, nodeToSize, nodeToNeighbours, visited)) % mod;
      visited.clear();
    }

    out.println(result);
  }

  static long dfs(
      int i,
      long mod,
      Map<Integer, Integer> nodeToSize,
      Map<Integer, List<Integer>> nodeToNeighbours,
      Set<Integer> visited) {
    long result = 0;
    for (Integer j : nodeToNeighbours.get(i)) {
      if (!visited.contains(j)) {
        visited.add(j);
        result = (result + dfs(j, mod, nodeToSize, nodeToNeighbours, visited)) % mod;
        visited.remove(j);
      }
    }
    return result + (long) Math.pow(nodeToSize.get(i), 2);
  }

  static int size(int i, int n) {
    int height =
        (int) (Math.floor(Math.log(n) / Math.log(2)) - Math.floor(Math.log(i) / Math.log(2)));
    int treeSize = (int) Math.pow(2, height);
    int left = i * treeSize;
    int right = (i + 1) * treeSize - 1;
    return treeSize - (right - left);
  }

  static void fill(
      int i,
      int n,
      Map<Integer, Integer> nodeToSize,
      Map<Integer, List<Integer>> nodeToNeighbours) {
    nodeToSize.put(i, 1);
    List<Integer> neighboursI = nodeToNeighbours.get(i);
    boolean isNewI = neighboursI == null;
    if (isNewI) {
      neighboursI = new ArrayList<>();
      nodeToNeighbours.put(i, neighboursI);
    }

    if (2 * i <= n) {
      List<Integer> neighbours2I = nodeToNeighbours.get(2 * i);
      boolean isNew2I = neighbours2I == null;
      if (isNew2I) {
        neighbours2I = new ArrayList<>();
        nodeToNeighbours.put(2 * i, neighbours2I);
        nodeToSize.put(2 * i, size(2 * i, n));
      }
      if (isNewI || isNew2I) {
        neighbours2I.add(i);
        neighboursI.add(2 * i);
      }
    }

    if (2 * i + 1 <= n) {
      List<Integer> neighbours2I1 = nodeToNeighbours.get(2 * i + 1);
      boolean isNew2I1 = neighbours2I1 == null;
      if (isNew2I1) {
        neighbours2I1 = new ArrayList<>();
        nodeToNeighbours.put(2 * i + 1, neighbours2I1);
        nodeToSize.put(2 * i + 1, size(2 * i + 1, n));
      }
      if (isNewI || isNew2I1) {
        neighbours2I1.add(i);
        neighboursI.add(2 * i + 1);
      }
    }

    while (i > 1) {
      neighboursI = nodeToNeighbours.get(i);
      List<Integer> neighboursI2 = nodeToNeighbours.get(i / 2);
      boolean isNewI2 = neighboursI2 == null;
      if (isNewI2) {
        neighboursI2 = new ArrayList<>();
        nodeToNeighbours.put(i / 2, neighboursI2);
        neighboursI2.add(i);
        neighboursI.add(i / 2);
      }
      nodeToSize.put(i / 2, 1);

      List<Integer> neighboursI20 = nodeToNeighbours.get(2 * (i / 2));
      boolean isNewI2 = neighboursI20 == null;
      if (isNewI2) {
        neighboursI2 = new ArrayList<>();
        nodeToNeighbours.put(i / 2, neighboursI2);
      }
      i >>= 1;
    }
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
