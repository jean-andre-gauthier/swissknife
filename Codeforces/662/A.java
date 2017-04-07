import java.io.*;
import java.lang.Math;
import java.util.*;

public class A
{
    public static void main(String[] args)
    {
          long n = input.nextLong();
          long[] c = new long[(int)n];
          long s = 0;
          for (int i=0; i<n; i++) {
              long a = input.nextLong();
              long b = input.nextLong();
              s ^= a;
              c[i] = a ^ b;
              // System.out.println("ci: " + Long.toBinaryString(c[i]));
          }
          // System.out.println("s: " + Long.toBinaryString(s));

          long mask = ~Long.MAX_VALUE;
          int nk = 0;
          long ts = s;
          while (mask != 0) {
            // System.out.println("mask: " + Long.toBinaryString(mask));
            int i = 0;
            while (i<n && (c[i] & mask) == 0) {
              // System.out.println("ci2: " + Long.toBinaryString(c[i]));
              i++;
            }
            // System.out.println("i: " + i);
            if (i < n) {
              long cf = c[i];
              c[i] = 0;
              nk++;

              if ((ts & mask) != 0) {
                ts ^= cf;
                // System.out.println("ts: " + Long.toBinaryString(ts));
              }
              // System.out.println("cf: " + Long.toBinaryString(cf));

              for (i++; i<n; i++) {
                if ((c[i] & mask) != 0) {
                  c[i] ^= cf;
                }
                // System.out.println("ci1: " + Long.toBinaryString(c[i]));
              }
            }
            mask >>>= 1;
          }

          if (ts != 0) {
            System.out.println("1/1");
          }
          else {
            long num = (1L << nk) - 1;
            long den = 1L << nk;
            System.out.println(num + "/" + den);
          }
    }

    static MyScanner input = new MyScanner(new BufferedReader(new InputStreamReader(System.in)));

    static class MyScanner {
      BufferedReader br;
      StringTokenizer st;

      MyScanner(BufferedReader br) {
        this.br = br;
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