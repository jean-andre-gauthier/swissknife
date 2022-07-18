
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class A {
    public static void main(String[] args) {
        int t=in.nextInt();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<t;++i){
//            out.println(">>>");
            int n=in.nextInt();
            boolean flip=false;
            char[]a=in.next().toCharArray();
            char[]b=in.next().toCharArray();
            int[]resi=new int[2*n+1];
            int resj=1;
            for(int j=n-1;j>=0;--j){
                if((flip&&a[n-j-1]==b[j])||(!flip&&a[j]!=b[j])){
                    if((flip&&a[n-1]!=b[j])||(!flip&&a[0]==b[j])){
//                        op(a,1);
                        resi[resj++]=1;
                    }
//                    if(a[j]!=b[j]){
//                        op(a,j+1);
                        resi[resj++]=j+1;
                    flip=!flip;
//                    }
                }
            }
            resi[0]=resj-1;
            for(int j=0;j<resj;++j){
                sb.append(resi[j]).append(" ");
            }
            sb.deleteCharAt(sb.length()-1).append("\n");
        }
        sb.deleteCharAt(sb.length()-1);
        out.println(sb.toString());
    }

    static void op(char[]a,int n){
        for(int j=0;j<n;++j){
            a[j]=a[j]=='0'?'1':'0';
        }
        for(int j=0;j<n/2;++j){
            char aj=a[j];
            a[j]=a[n-j-1];
            a[n-j-1]=aj;
        }
//        out.println(a,false);
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
