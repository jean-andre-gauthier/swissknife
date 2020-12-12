import java.io.*;
import java.lang.Math;
import java.util.*;

public class B {
    public static void main(String[] args) {
      String s=in.next();
      int n=s.length();
      int[] p=new int[n];
      p[0]=0;
      for(int i=1;i<n;++i){
        int j=p[i-1];
        while(s.charAt(i)!=s.charAt(j)&&j>0){
          j=p[j-1];
        }
        if(s.charAt(i)==s.charAt(j)){
          p[i]=j+1;
        }else{
          p[i]=0;
        }
      }
      //out.println(p, false);
      if(p[n-1]==0){
        out.println("Just a legend");
        return;
      }
      for(int i=0;i<n-1;++i){
        if(p[i]==p[n-1]){
          out.println(s.substring(0,p[i]));
          return;
        }
      }
      if(p[p[n-1]-1]==0){
        out.println("Just a legend");
        return;
      }else{
        out.println(s.substring(0,p[p[n-1]-1]));
        return;
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
        int[] ints = new int[n+1];
        for (int i=1; i<=n; ++i) {
          ints[i] = nextInt();
        }
        return ints;
      }

      long nextLong() {
        return Long.parseLong(next());
      }

      double nextDouble() {
        return Double.parseDouble(next());
      }
    }
}