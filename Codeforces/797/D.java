import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.function.*;

public class D {

  static int[][] vlr;
  static Map<Integer, Integer> occurrences;

  public static void main(String[] args) {
    int n = in.nextInt();
    vlr = new int[n + 1][3];
    int iRoot = -1;
    Set<Integer> notRoot = new HashSet<>(n);
    occurrences = new HashMap<>(n);

    for (int i = 1; i <= n; i++) {
      String[] vlri = in.nextLine().split(" ");
      vlr[i][0] = Integer.valueOf(vlri[0]);
      vlr[i][1] = Integer.valueOf(vlri[1]);
      vlr[i][2] = Integer.valueOf(vlri[2]);
      if (vlr[i][1] != -1) notRoot.add(vlr[i][1]);
      if (vlr[i][2] != -1) notRoot.add(vlr[i][2]);
      if (occurrences.containsKey(vlr[i][0]))
        occurrences.put(vlr[i][0], occurrences.get(vlr[i][0]) + 1);
      else occurrences.put(vlr[i][0], 1);
    }

    for (int i = 1; i <= n; i++) {
      if (!notRoot.contains(i)) {
        iRoot = i;
        break;
      }
    }

    out.println(n - nFound(iRoot, Integer.MIN_VALUE, Integer.MAX_VALUE, 0));
  }

  static int nFound(int i, int l, int r, int acc) {
    /* out.println(i + " " + l + " " + r + " " + acc); */
    if (vlr[i][0] > l && vlr[i][0] < r) acc += occurrences.get(vlr[i][0]);
    if (vlr[i][1] != -1) acc = nFound(vlr[i][1], l, Math.min(r, vlr[i][0]), acc);
    if (vlr[i][2] != -1) acc = nFound(vlr[i][2], Math.max(l, vlr[i][0]), r, acc);
    return acc;
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

    long nextLong() {
      return Long.parseLong(next());
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }
  }
}
