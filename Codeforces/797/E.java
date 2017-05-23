import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.function.*;

public class E {
  public static void main(String[] args) {
    int n = in.nextInt();
    int[] a = in.nextInts(n, true);
    int q = in.nextInt();
    int kmax = Math.min(n, (int) Math.sqrt(n));
    int[][] M = new int[n + 1][kmax + 1];
    for (int k = 1; k <= kmax; k++) {
      M[n][k] = 1;
    }
    for (int p = n - 1; p >= 1; --p) {
      for (int k = kmax; k >= 1; --k) {
        int nextP = p + a[p] + k;
        if (nextP > n) {
          M[p][k] = 1;
        } else {
          M[p][k] = M[nextP][k] + 1;
        }
      }
    }
    /* out.println(M); */
    for (int i = 1; i <= q; ++i) {
      int p = in.nextInt();
      int k = in.nextInt();
      if (k <= kmax) {
        out.println(M[p][k]);
      } else {
        int res = 0;
        while (p <= n) {
          p += a[p] + k;
          ++res;
        }
        out.println(res);
      }
    }
  }

  static MyScanner in = new MyScanner();
  static MyPrintWriter out = new MyPrintWriter();

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
      long[] longs = new long[n];
      for (int i = 0; i < n; ++i) {
        longs[i] = nextLong();
      }
      return longs;
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    double[] nextDoubles(int n) {
      double[] doubles = new double[n];
      for (int i = 0; i < n; ++i) {
        doubles[i] = nextDouble();
      }
      return doubles;
    }
  }
}
