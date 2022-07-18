import java.io.*;
import java.lang.Math;
import java.util.*;

public class B {
    static int C_MAX=100_000;
    public static void main(String[] args) {
      int n=in.nextInt();
      int m=in.nextInt();
      int[]a=in.nextInts(n);
      int[][]qs=new int[m][3];
      for(int i=0;i<qs.length;++i){
        qs[i][0]=in.nextInt()-1;
        qs[i][1]=in.nextInt()-1;
        qs[i][2]=i;
      }
      Arrays.sort(qs, Comparator.comparingInt((int[] q) -> (int) (q[0] / Math.sqrt(n))).thenComparingInt(q -> q[1]));
      int[]counts=new int[C_MAX+1];
      int c=add(counts,0,a,qs[0][0],qs[0][1]);
      int[]res=new int[m];
      res[qs[0][2]]=c;
      for(int i=1;i<m;++i){
        c=add(counts,c,a,qs[i][0],qs[i-1][0]-1);
        c=remove(counts,c,a,qs[i-1][0],qs[i][0]-1);
        c=add(counts,c,a,qs[i-1][1]+1,qs[i][1]);
        c=remove(counts,c,a,qs[i][1]+1,qs[i-1][1]);
        res[qs[i][2]]=c;
      }
      out.println(res);
    }

    static int add(int[]counts,int c,int[]a,int from,int to){
      for(int i=from;i<=to&&i<a.length;++i){
        if(a[i]<=C_MAX){
          if(counts[a[i]]==a[i]){
            --c;
          }
          counts[a[i]]++;
          if(counts[a[i]]==a[i]){
            ++c;
          }
        }
      }
      return c;
    }

    static int remove(int[]counts,int c,int[]a,int from,int to){
      for(int i=from;i<=to&&i<a.length;++i){
        if(a[i]<=C_MAX){
          if(counts[a[i]]==a[i]){
            --c;
          }
          counts[a[i]]--;
          if(counts[a[i]]==a[i]){
            ++c;
          }
        }
      }
      return c;
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

      void println(boolean[] ts) {
        println(ts, true);
      }

      void println(char[] ts) {
        println(ts, true);
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

      void println(boolean[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (boolean t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(char[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (char t: ts) {
          sb.append(t);
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
        StringBuilder sb = new StringBuilder();
        for (long t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(double[] ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (double t: ts) {
          sb.append(t);
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      <T> void println(T[] ts, boolean newline) {
        println(Arrays.asList(ts), newline);
      }

      <T> void println(Collection<T> ts, boolean newline) {
        StringBuilder sb = new StringBuilder();
        for (T t: ts) {
          sb.append(t != null ? t.toString() : "null");
          sb.append((newline ? "\n" : " "));
        }
        if (sb.length() > 0) {
          sb.deleteCharAt(sb.length()-1);
        }
        pw.println(sb.toString());
      }

      void println(int[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (int[] ts: tss) {
          for (int t: ts) {
            sb.append(t).append(" ");
          }
          if (ts.length > 0) {
            sb.setCharAt(sb.length()-1, '\n');
          } else {
            sb.append('\n');
          }
        }
        pw.print(sb.toString());
        pw.flush();
      }

      void println(long[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (long[] ts: tss) {
          for (long t: ts) {
            sb.append(t).append(" ");
          }
          if (ts.length > 0) {
            sb.setCharAt(sb.length()-1, '\n');
          } else {
            sb.append('\n');
          }
        }
        pw.print(sb.toString());
        pw.flush();
      }

      void println(double[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (double[] ts: tss) {
          for (double t: ts) {
            sb.append(t).append(" ");
          }
          if (ts.length > 0) {
            sb.setCharAt(sb.length()-1, '\n');
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

      String nextLine(){
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
        return nextInts(n, oneBased ? 1 : 0);
      }

      int[] nextInts(int n, int iStart) {
        int[] ints = new int[n+iStart];
        for (int i=iStart; i<ints.length; ++i) {
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

      long[] nextLongs(int n, int iStart) {
        long[] longs = new long[n+iStart];
        for (int i=iStart; i<longs.length; ++i) {
          longs[i] = nextLong();
        }
        return longs;
      }

      long[] nextLongs(int n, boolean oneBased) {
        return nextLongs(n, oneBased ? 1 : 0);
      }

      double nextDouble() {
        return Double.parseDouble(next());
      }
    }
}