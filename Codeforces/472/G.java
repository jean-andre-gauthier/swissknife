import java.io.*;
import java.lang.Math;
import java.util.*;

public class G {
    public static void main(String[] args) {
      int l=2;

      String a = in.next();
      int m = a.length();
      int[][] as = new int[l][(int)Math.ceil(m/l)];
      String b = in.next();
      int n = b.length();
      int[][] bs = new int[l][(int)Math.ceil(n/l)];

      for (int i=0; i<l; i++) {
        for (int j=0; j<(m-i)/l; j++) {
          int acc = 0;
          for (int k=0; k<l; k++) {
            acc |= (a.charAt(i+j*l+k) == '1' ? 1 : 0) << (l-k-1);
          }
          as[i][j] = acc;
        }
        out.println(as[i], false);

        for (int j=0; j<(n-i)/l; j++) {
          int acc = 0;
          for (int k=0; k<l; k++) {
            acc |= (b.charAt(i+j*l+k) == '1' ? 1 : 0) << (l-k-1);
          }
          bs[i][j] = acc;
        }
        out.println(bs[i], false);
      }

      int q = in.nextInt();
      int[] res = new int[q];
      for (int i=0; i<q; i++) {
        int p1 = in.nextInt();
        int p2 = in.nextInt();
        int len = in.nextInt();

        int resi = 0;
        int ai = p1%l;
        int aj = p1/l;
        int bi = p2%l;
        int bj = p2/l;
        int lenAsBs = len/l;
        out.println(p1 + " " + p2 + " " + len);
        out.println(ai + " " + aj + " " + bi + " " + bj + " " + lenAsBs + " " + (len-lenAsBs*l));
        for (int j=0; j<lenAsBs; j++) {
          resi += Integer.bitCount(as[ai][aj+j] ^ bs[bi][bj+j]);
        }

        for (int j=0; j<len-lenAsBs*l; j++) {
          resi += a.charAt(ai+aj+lenAsBs*l+j) == b.charAt(bi+bj+lenAsBs*l+j) ? 0 : 1;
        }

        res[i] = resi;
      }

      out.println(res, false);
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

      void println(int t) {
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