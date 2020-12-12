import java.io.*;
import java.lang.Math;
import java.util.*;

public class D {
    public static void main(String[] args) {
      int n=in.nextInt();
      int w=in.nextInt();
      int h=in.nextInt();
      int[][]wh=new int[n+1][2];
      wh[0][0]=w;
      wh[0][1]=h;
      for(int i=1;i<=n;++i){
        wh[i][0]=in.nextInt();
        wh[i][1]=in.nextInt();
      }
      int[]dp=new int[n+1];
      int[]path=new int[n+1];
      dfs(dp,path,0,wh);
//      out.println(dp,false);
//      out.println(path,false);
      int res=dp[0];
      out.println(res);
      if(res>0){
        ArrayList<Integer>is=new ArrayList<>();
        int i=path[0];
        is.add(i);
        while(path[i]!=0){
          i=path[i];
          is.add(i);
        }
        out.println(is,false);
      }
    }

    static void dfs(int[]dp,int[]path,int i,int[][]wh){
      if(dp[i]==0){
        for(int j=1;j<dp.length;++j){
          if(wh[i][0]<wh[j][0]&&wh[i][1]<wh[j][1]){
            dfs(dp,path,j,wh);
            if(dp[j]+1>dp[i]){
              dp[i]=dp[j]+1;
              path[i]=j;
            }
          }
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

      void println(char[][] tss) {
        StringBuilder sb = new StringBuilder();
        for (char[] ts: tss) {
          for (char t: ts) {
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