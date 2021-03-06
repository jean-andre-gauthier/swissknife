import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class C {
  public static void main(String[] args) {
    String a = in.next();
    String b = in.next();
    // out.println("a: " + a);
    // out.println("b: " + b);

    int[] p = new int[a.length() + 1];
    int[] s = new int[a.length() + 1];

    p[0] = 0;
    for (int ai = 0, bi = 0; ai < a.length(); ++ai) {
      if (bi < b.length() && a.charAt(ai) == b.charAt(bi)) {
        ++bi;
      }
      p[ai + 1] = bi;
    }

    s[0] = 0;
    for (int ai = a.length() - 1, bi = b.length() - 1; ai >= 0; --ai) {
      if (bi > 0 && a.charAt(ai) == b.charAt(bi)) {
        --bi;
      }
      s[a.length() - ai] = b.length() - bi - 1;
    }

    // out.println(p, false);
    // out.println(s, false);

    int pi = 0;
    int si = 0;
    long max = 0;
    for (int i = 0; i < p.length; ++i) {
      if (p[i] + s[p.length - i - 1] <= b.length() && p[i] + s[p.length - i - 1] > max) {
        pi = p[i];
        si = s[p.length - i - 1];
        max = pi + si;
      }
    }

    // out.println(pi + " " + si);

    if (pi + si == 0) {
      out.println("-");
    } else {
      out.println(b.substring(0, pi) + b.substring(b.length() - si, b.length()));
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
    Random r;

    RandScanner() {
      this.r = new Random();
    }

    int nextInt() {
      return r.nextInt();
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
      return r.nextLong();
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
      return r.nextDouble();
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
}
