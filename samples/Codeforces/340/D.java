import java.io.*;
import java.lang.Math;
import java.util.*;

public class D {
    public static void main(String[] args) {
      int n=in.nextInt();
      int a[]=in.nextInts(n);
      int amax=100_000;
      int d[]=new int[n];
      int t[]=new int[amax+1];
      int tree[]=new int[4*(amax+1)];
//      out.println(a,false);
      for(int i=0;i<n;++i){
        d[i]=Math.max(1,get(tree,1,0,a[i]-1,0,n-1)+1);
        update(tree,1,a[i],d[i],0,n-1);
        t[a[i]]=d[i];
//        out.println(d,false);
//        out.println(t,false, 20);
//        out.println(tree,false, 20);
      }
      int res=Integer.MIN_VALUE;
      for(int i=0;i<n;++i){
        if(d[i]>=res){
          res=d[i];
        }
      }
      out.println(res);
    }

    static int get(int[]t,int ti,int ai,int aj,int al, int ar){
      if((ai<al&&aj<al)||(ar<ai&&ar<aj)){
        return Integer.MIN_VALUE;
      }
      if(ai<=al&&ar<=aj){
        return t[ti];
      }
      int am=(al+ar)/2;
      return Math.max(get(t,2*ti,ai,aj,al,am), get(t,2*ti+1,ai,aj,am+1,ar));
    }

    static void update(int[]t,int ti,int ai,int v,int al,int ar) {
      if(al==ar){
        t[ti]=v;
        return;
      }
      int am=(al+ar)/2;
      if(ai<=am){
        update(t,2*ti,ai,v,al,am);
      }else{
        update(t,2*ti+1,ai,v,am+1,ar);
      }
      t[ti]=Math.max(t[2*ti], t[2*ti+1]);
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
          sb.append(t.toString());
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