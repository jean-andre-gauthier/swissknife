import java.io.*;
import java.lang.Math;
import java.util.*;

public class A {
    public static void main(String[] args) {
      StringBuilder s = new StringBuilder(in.next());
      int n=s.length();
      int i=0;
      for (i=0; i<s.length(); i++) {
        if (s.charAt(i) != 'a') {
          break;
        }
      }

      int j=i;
      for (; j<s.length(); j++) {
        if (s.charAt(j) == 'a') {
          break;
        }
        s.setCharAt(j, shiftChar(s.charAt(j)));
      }

      if (j==i) {
        s.setCharAt(n-1, shiftChar(s.charAt(n-1)));
      }

      out.println(s);
    }

    private static char shiftChar(char c) {
      return (char)('a' + ((c-'a'+25)%26));
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

      long nextLong() {
        return Long.parseLong(next());
      }

      double nextDouble() {
        return Double.parseDouble(next());
      }
    }
}