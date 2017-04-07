import java.io.*;
import java.lang.Math;
import java.util.*;

public class G {
    public static void main(String[] args) {
      String as = in.next();
      int m = as.length();
      double[] a = new double[m];
      for (int i=0; i<m; i++) {
        a[i] = as.charAt(i)=='1' ? 1 : -1;
      }

      String bs = in.next();
      int n = bs.length();
      double[] b = new double[n];
      for (int i=0; i<n; i++) {
        b[i] = bs.charAt(n-i-1)=='1' ? 1 : -1;
      }

      int q = in.nextInt();
      int[] p1 = new int[q];
      int[] p2 = new int[q];
      int[] len = new int[q];
      for (int i=0; i<q; i++) {
        p1[i] = in.nextInt();
        p2[i] = in.nextInt();
        len[i] = in.nextInt();
      }

      int L = (int)Math.ceil(Math.sqrt(m));
      int nL = (int)Math.ceil(m/(double)L);
      double[][] l = new double[nL][];
      for (int i=0; i<nL; i++) {
        int aj = L*i;
        int ak = Math.min(L*(i+1), m);
        l[i] = multiplyPolynomials(Arrays.copyOfRange(a, aj, ak), b, true);
        for (int j=0; j<l[i].length/2; j++) {
          double temp = l[i][j];
          l[i][j] = l[i][l[i].length-j-1];
          l[i][l[i].length-j-1] = temp;
        }

        int cj = Math.min(ak-aj, n)-1;
        int ck = l[i].length-cj;
        l[i] = Arrays.copyOfRange(l[i], cj, ck);

        for (int ll=0; ll<l[i].length; ll++) {
          l[i][ll] = (L - l[i][ll]) / 2.0;
        }
        // out.println(l[i], false);
      }
      // out.println("---");


      int[] res = new int[q];
      for (int i=0; i<q; i++) {
        int jlStart = 0;
        int jlEnd = 0;

        if (p1[i]==0 && len[i]>=L) {
          jlStart = 0;
          jlEnd = (int)(len[i]/(double)L);
        } else if (p1[i]+len[i]==m && len[i]>=L) {
          jlStart = (int)Math.ceil(p1[i]/(double)L);
          jlEnd = (int)Math.ceil(m/(double)L);
        } else if (len[i]>=L) {
          jlStart = (int)Math.ceil(p1[i]/(double)L);
          jlEnd = (int)((p1[i]+len[i])/(double)L);
        }

        boolean readL = jlStart != jlEnd;
        int hd = 0;
        if (readL) {
          int klStart = p2[i] + jlStart*L - p1[i];
          int klEnd = klStart + (jlEnd-jlStart)*L;
          // out.println(p1[i] + " " + p2[i] + " " + len[i] + " " + jlStart + " " + jlEnd + " " + klStart + " " + klEnd);
          for (int j=jlStart, k=klStart; j<jlEnd; j++, k+=L) {
            hd += l[j][k];
          }

          int nBefore = jlStart*L-p1[i];
          int nAfter = len[i] - (jlEnd-jlStart)*L - (jlStart*L-p1[i]);
          // out.println(nBefore + " " + nAfter);
          // out.println(hd + " " + hammingDistance(a, b, p1[i], p2[i], nBefore) + " " + hammingDistance(a, b, jlEnd*L, klEnd, nAfter));

          hd += hammingDistance(a, b, p1[i], p2[i], nBefore);
          hd += hammingDistance(a, b, jlEnd*L, klEnd, nAfter);
        } else {
            // out.println(hd + " " + hammingDistance(a, b, p1[i], p2[i], len[i]));
            hd += hammingDistance(a, b, p1[i], p2[i], len[i]);
        }
        res[i] = hd;
      }

      // out.println(res, false);
      out.println(res, true);
    }

    static int hammingDistance(double[] a, double[] b, int p1, int p2, int len) {
      int hd = 0;
      for (int i=0; i<len; i++) {
        if (a[p1+i] != b[b.length-p2-i-1]) {
          hd++;
        }
      }
      return hd;
    }

    private static class Complex {
      private final double re;
      private final double im;

      public Complex(double re, double im) {
        this.re = re;
        this.im = im;
      }

      public static final Complex ONE = new Complex(1, 0);
      public static final Complex I = new Complex(0, 1);

      public Complex add(Complex c) {
        return new Complex(this.re+c.re, this.im+c.im);
      }

      public Complex subtract(Complex c) {
        return new Complex(this.re-c.re, this.im-c.im);
      }

      public Complex times(Complex c) {
        return new Complex(this.re*c.re - this.im*c.im, this.re*c.im + this.im*c.re);
      }

      public Complex divideBy(Complex c) {
        return new Complex((this.re*c.re + this.im*c.im)/(c.re*c.re + c.im*c.im), (this.im*c.re - this.re*c.im)/(c.re*c.re + c.im*c.im));
      }

      public Complex conjugate() {
        return new Complex(this.re, -this.im);
      }

      public double re() {
        return this.re;
      }

      public double im() {
        return this.im;
      }
    }

    private static Complex[] fastFourierTransform(double[] a, boolean invert) {
      int n = a.length;
      Complex[] ac = new Complex[n];
      for (int i=0; i<n; i++) {
        ac[i] = new Complex(a[i], 0);
      }
      return fastFourierTransform(ac, invert);
    }

    private static Complex[] fastFourierTransform(Complex[] a, boolean invert) {
      int logn = (int)(Math.log(a.length) / Math.log(2));
      int n = a.length;

      if (logn == 0) {
        return a;
      }

      int[] p = new int[a.length];
      for (int i=0; i<n; i++) {
        int reverse = 0;
        for (int k=0, j=i; k<logn; j>>>=1) {
          reverse <<= 1;
          reverse |= j&1;
          k++;
        }
        p[i] = reverse;
      }

      Complex[] wn = new Complex[n];
      double alpha = (invert ? -1 : 1) * 2*Math.PI / n;
      Complex wni = new Complex(Math.cos(alpha), Math.sin(alpha));
      Complex acc = Complex.ONE;
      for (int i=0; i<n; i++) {
        wn[i] = acc;
        acc = acc.times(wni);
      }

      for (int i=0; i<n; i++) {
          if (i < p[i]) {
            Complex u = a[i];
            Complex v = a[p[i]];
            a[i] = v;
            a[p[i]] = u;
          }
      }

      for (int length=2; length<=n; length<<=1) {
        for (int k=0; k<n; k+=length) {
          int wlen = n/length;
          for (int i=0, wi=0; i<length/2; i++, wi+=wlen) {
            int ui = k+i;
            Complex u = a[ui];
            int vi = k+i+length/2;
            Complex v = a[vi];
            a[ui] = u.add(wn[wi].times(v));
            a[vi] = u.subtract(wn[wi].times(v));
          }
        }
      }

      if (invert) {
        Complex nc = new Complex(n, 0);
        for (int i=0; i<n; i++) {
          a[i] = a[i].divideBy(nc);
        }
      }

      return a;
    }

    private static double[] multiplyPolynomials(double[] a, double[] b, boolean round) {
      int n = (int)Math.pow(2, Math.ceil(Math.log(Math.max(a.length, b.length))/Math.log(2))+1);
      a = Arrays.copyOf(a, n);
      b = Arrays.copyOf(b, n);

      Complex[] ffta = fastFourierTransform(a, false);
      Complex[] fftb = fastFourierTransform(b, false);

      for (int i=0; i<n; i++) {
        ffta[i] = ffta[i].times(fftb[i]);
      }
      Complex[] fftc = fastFourierTransform(ffta, true);

      double[] c = new double[n];
      for (int i=0; i<n; i++) {
        c[i] = round ? Math.round(fftc[i].re) : fftc[i].re;
      }

      int rn = n;
      for (int i=n-1; i>=0; i--) {
        if (c[i] != 0) {
          break;
        }
        rn--;
      }
      return Arrays.copyOf(c, Math.max(rn,1));
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