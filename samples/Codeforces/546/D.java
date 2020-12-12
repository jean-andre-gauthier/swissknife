import java.io.*;
import java.lang.Math;
import java.util.*;

public class D {
    public static void main(String[] args) {
      int t=in.nextInt();
      int a=0,b=0;
      int qmax=5000000;
      boolean[] comp=new boolean[qmax+1];
      for(int i=2;i<=Math.sqrt(comp.length);++i){
        if(!comp[i]){
          for(int j=2*i;j<comp.length;j+=i){
            comp[j]=true;
          }
        }
      }
      //out.println(comp, false);
      int[]cum=new int[qmax+1];
      for(int i=2;i<comp.length;++i){
        if(!comp[i]){
          for(long j=i;j<comp.length;j*=i){
            for(int k=(int)j;k<comp.length;k+=j){
              cum[k]++;
            }
          }
        }
      }
      //out.println(cum, false);
      int[]tree=new int[4*cum.length];
      build(cum, 1, cum.length-1,tree,1);
//      out.println(tree,false);
      int[]res=new int[t];
      for(int i=0;i<t;++i){
        a=in.nextInt();
        b=in.nextInt();
        res[i]=query(b+1,a,tree,1,1,cum.length-1);
      }
      out.println(res);
    }

    static void build(int[]cum,int cuml,int cumr,int[]tree,int ti){
      if(cuml==cumr){
        tree[ti]=cum[cuml];
      }else{
        int mid=(cuml+cumr)/2;
        build(cum,cuml,mid,tree,2*ti);
        build(cum,mid+1,cumr,tree,2*ti+1);
        tree[ti]=tree[2*ti]+tree[2*ti+1];
      }
    }

    static int query(int a,int b,int[]tree,int ti,int cuml,int cumr){
//      out.println(a + " " + b + " " + ti + " " + cuml + " " + cumr);
      if ((cuml<a&&cumr<a)||(b<cuml&&b<cumr)){
//        out.println("if");
        return 0;
      }else if(a<=cuml&&cumr<=b){
//        out.println("elif");
        return tree[ti];
      }else{
//        out.println("else");
        int mid=(cuml+cumr)/2;
        return query(a,b,tree,2*ti,cuml,mid) + query(a,b,tree,2*ti+1,mid+1,cumr);
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
        StringBuilder sb = new StringBuilder();
        for (int t: ts) {
          sb.append(t);
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
        int[] ints = new int[n];
        for (int i=0; i<n; ++i) {
          ints[i] = nextInt();
        }
        return ints;
      }

      long nextLong() {
        return Long.parseLong(next());
      }

      long[] nextLongs(int n) {
        long[] longs = new long[n];
        for (int i=0; i<n; ++i) {
          longs[i] = nextLong();
        }
        return longs;
      }

      double nextDouble() {
        return Double.parseDouble(next());
      }
    }
}