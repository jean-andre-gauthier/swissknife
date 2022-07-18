import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class D {
  public static void main(String[] args) {
    int n=in.nextInt();
    long x0=in.nextInt();
    long y0=in.nextInt();
    char[]type=new char[9];
    long[]dist=new long[9];
    for(int i=0;i<n;++i){
      char t=in.next().charAt(0);
      long x=in.nextLong();
      long y=in.nextLong();
      if((x0-x==y-y0&&x0-x>0)&&(type[1]==0||x0-x<dist[1])){
        type[1]=t;
        dist[1]=x0-x;
      }else if((x0==x&&y0<y)&&(type[2]==0||y-y0<dist[2])){
        type[2]=t;
        dist[2]=y-y0;
      }else if((x-x0==y-y0&&x-x0>0)&&(type[3]==0||x-x0<dist[3])){
        type[3]=t;
        dist[3]=x-x0;
      }else if((y0==y&&x0<x)&&(type[4]==0||x-x0<dist[4])){
        type[4]=t;
        dist[4]=x-x0;
      }else if((x-x0==y0-y&&x-x0>0)&&(type[5]==0||x-x0<dist[5])){
        type[5]=t;
        dist[5]=x-x0;
      }else if((x0==x&&y<y0)&&(type[6]==0||y0-y<dist[6])){
        type[6]=t;
        dist[6]=y0-y;
      }else if((x0-x==y0-y&&x0-x>0)&&(type[7]==0||x0-x<dist[7])){
        type[7]=t;
        dist[7]=x0-x;
      }else if((y0==y&&x<x0)&&(type[8]==0||x0-x<dist[8])){
        type[8]=t;
        dist[8]=x0-x;
      }
    }
//    out.println(type,false,1,8);
//    out.println(dist,false,1,8);
    boolean check=(
            (type[1]=='B'||type[1]=='Q')
            ||(type[2]=='R'||type[2]=='Q')
            ||(type[3]=='B'||type[3]=='Q')
            ||(type[4]=='R'||type[4]=='Q')
            ||(type[5]=='B'||type[5]=='Q')
            ||(type[6]=='R'||type[6]=='Q')
            ||(type[7]=='B'||type[7]=='Q')
            ||(type[8]=='R'||type[8]=='Q')
    );
    out.println(check ? "YES" : "NO");
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

    void print(boolean t) {
      pw.print(t);
    }

    void print(char t) {
      pw.print(t);
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

    void println(boolean t) {
      pw.println(t);
    }

    void println(char t) {
      pw.println(t);
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

    void println(boolean[] ts) {
      println(ts, true);
    }

    void println(char[] ts) {
      println(ts, true);
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

    void println(boolean[] ts, boolean newline) {
      println(ts, newline, 0, ts.length-1);
    }

    void println(boolean[] ts, boolean newline, int imax) {
      println(ts, newline, 0, imax);
    }

    void println(boolean[] ts, boolean newline, int imin, int imax) {
      StringBuilder sb = new StringBuilder();
      for (int i = imin; i <= imax; ++i) {
        sb.append(ts[i]);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length()-1);
      }
      pw.println(sb.toString());
    }

    void println(char[] ts, boolean newline) {
      println(ts, newline, 0, ts.length-1);
    }

    void println(char[] ts, boolean newline, int imax) {
      println(ts, newline, 0, imax);
    }

    void println(char[] ts, boolean newline, int imin, int imax) {
      StringBuilder sb = new StringBuilder();
      for (int i = imin; i <= imax; ++i) {
        sb.append(ts[i]);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length()-1);
      }
      pw.println(sb.toString());
    }

    void println(int[] ts, boolean newline) {
      println(ts, newline, 0, ts.length-1);
    }

    void println(int[] ts, boolean newline, int imax) {
      println(ts, newline, 0, imax);
    }

    void println(int[] ts, boolean newline, int imin, int imax) {
      StringBuilder sb = new StringBuilder();
      for (int i = imin; i <= imax; ++i) {
        sb.append(ts[i]);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length()-1);
      }
      pw.println(sb.toString());
    }

    void println(long[] ts, boolean newline) {
      println(ts, newline, 0, ts.length-1);
    }

    void println(long[] ts, boolean newline, int imax) {
      println(ts, newline, 0, imax);
    }

    void println(long[] ts, boolean newline, int imin, int imax) {
      StringBuilder sb = new StringBuilder();
      for (int i = imin; i <= imax; ++i) {
        sb.append(ts[i]);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length()-1);
      }
      pw.println(sb.toString());
    }

    void println(double[] ts, boolean newline) {
      println(ts, newline, 0, ts.length-1);
    }

    void println(double[] ts, boolean newline, int imax) {
      println(ts, newline, 0, imax);
    }

    void println(double[] ts, boolean newline, int imin, int imax) {
      StringBuilder sb = new StringBuilder();
      for (int i = imin; i <= imax; ++i) {
        sb.append(ts[i]);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length()-1);
      }
      pw.println(sb.toString());
    }

    <T> void println(T[] ts, boolean newline) {
      println(ts, newline, 0, ts.length-1);
    }

    <T> void println(T[] ts, boolean newline, int imax) {
      println(ts, newline, 0, imax);
    }

    <T> void println(T[] ts, boolean newline, int imin, int imax) {
      println(Arrays.asList(ts).subList(imin, imax+1), newline);
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

    void println(boolean[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (boolean[] ts : tss) {
        for (boolean t : ts) {
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

    void println(char[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (char[] ts : tss) {
        for (char t : ts) {
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
