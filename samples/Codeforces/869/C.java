import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class C {
  public static void main(String[] args) {
    long mod = 998244353;

    int a = in.nextInt();
    int b = in.nextInt();
    int c = in.nextInt();
    // out.println(a + " " + b + " " + c);

    int max = Math.max(a, Math.max(b, c));

    long[] fact = new long[max + 1];
    fact[0] = 1;
    for (int i = 1; i <= max; ++i) {
      fact[i] = (fact[i - 1] * i) % mod;
    }
    // out.println(fact);
    // out.println(">>>");

    long[][] dp = new long[max + 1][max + 1];
    for (int n = 1; n <= max; ++n) {
      dp[n][0] = 1;
    }
    dp[1][1] = 1;

    for (int n = 2; n <= max; ++n) {
      for (int k = 1; k <= n; ++k) {
        dp[n][k] = (dp[n - 1][k - 1] + dp[n - 1][k]) % mod;
      }
    }
    // out.println(dp);

    long sumab = 0;
    for (int i = 0; i <= Math.min(a, b); ++i) {
      sumab = (sumab + (((dp[a][i] * dp[b][i]) % mod) * fact[i]) % mod) % mod;
    }
    long sumac = 0;
    for (int i = 0; i <= Math.min(a, c); ++i) {
      sumac = (sumac + (((dp[a][i] * dp[c][i]) % mod) * fact[i]) % mod) % mod;
    }
    long sumbc = 0;
    for (int i = 0; i <= Math.min(b, c); ++i) {
      sumbc = (sumbc + (((dp[b][i] * dp[c][i]) % mod) * fact[i]) % mod) % mod;
    }

    long sum = (((sumab * sumac) % mod) * sumbc) % mod;
    out.println(sum);
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
