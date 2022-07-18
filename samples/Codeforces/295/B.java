
import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

public class B {
    public static void main(String[] args) {
        int n=in.nextInt();
        int[][]w=new int[n][];
        for(int i=0;i<n;++i){
            w[i]=in.nextInts(n);
        }
        int[]a=in.nextInts(n);
        reverse(a);
        long[][]d=new long[n][n];
        for(int i=0;i<n;++i){
            for(int j=0;j<n;++j){
                d[i][j]=w[i][j];
            }
        }
        long[] res=new long[n];
        for(int k=0;k<n;++k){
            for(int i=0;i<n;++i){
                for(int j=0;j<n;++j){
                    d[i][j]=Math.min(d[i][j], d[i][a[k]-1]+d[a[k]-1][j]);
                }
            }
//            out.println(d);
            res[k]=0;
            for(int i=0;i<=k;++i){
                for(int j=0;j<=k;++j){
                    res[k]+=d[a[i]-1][a[j]-1];
                }
            }
        }
        reverse(res);
        out.println(res,false);
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


    @FunctionalInterface
    static interface LongBiPredicate {
        boolean test(long a, long b);
    }

    static <T> int partition(ArrayList<T> list, Predicate<T> p) {
        return partition(list, p, 0, list.size());
    }

    static <T> int partition(ArrayList<T> list, Predicate<T> p, int from, int to) {
        int first = from;
        int last = to;
        while (first != last) {
            if (!p.test(list.get(first))) {
                Collections.swap(list, first, last - 1);
                --last;
            } else {
                ++first;
            }
        }
        return first;
    }

    /**
     * Checks whether two arrays have identical contents
     *
     * <p>Complexity: O(n)
     */
    static boolean isEqual(int[] a, int[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    static boolean isEqual(long[] a, long[] b) {
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether an array is a permutation of another
     *
     * <p>Complexity: O(nlog(n)) - O(n)
     *
     * <p>TODO Replace implementation by one that uses a HashSet with a primitive int
     */
    static boolean isPermutation(int[] a, int[] b) {
        int[] aCopy = Arrays.copyOf(a, a.length);
        int[] bCopy = Arrays.copyOf(b, b.length);
        Arrays.sort(aCopy);
        Arrays.sort(bCopy);
        return isEqual(aCopy, bCopy);
    }

    static boolean isPermutation(long[] a, long[] b) {
        long[] aCopy = Arrays.copyOf(a, a.length);
        long[] bCopy = Arrays.copyOf(b, b.length);
        Arrays.sort(aCopy);
        Arrays.sort(bCopy);
        return isEqual(aCopy, bCopy);
    }

    /**
     * Merges two sorted arrays
     *
     * <p>Complexity: O(t1.length + t2.length)
     */
    static int[] merge(int[] t1, int[] t2) {
        int[] merged = new int[t1.length + t2.length];
        int it1 = 0, it2 = 0, imerged = 0;

        while (imerged < merged.length) {
            if (it2 == t2.length || (it1 < t1.length && t1[it1] <= t2[it2])) {
                merged[imerged++] = t1[it1++];
            } else {
                merged[imerged++] = t2[it2++];
            }
        }

        return merged;
    }

    static long[] merge(long[] t1, long[] t2) {
        long[] merged = new long[t1.length + t2.length];
        int it1 = 0, it2 = 0, imerged = 0;

        while (imerged < merged.length) {
            if (it2 == t2.length || (it1 < t1.length && t1[it1] <= t2[it2])) {
                merged[imerged++] = t1[it1++];
            } else {
                merged[imerged++] = t2[it2++];
            }
        }

        return merged;
    }

    /**
     * Returns an array containing numbers from fromInclusive to toExclusive - 1
     *
     * <p>Complexity: O(toExclusive - fromInclusive)
     */
    static int[] rangeInt(int toExclusive) {
        return rangeInt(0, toExclusive);
    }

    static int[] rangeInt(int fromInclusive, int toExclusive) {
        int nItems = toExclusive - fromInclusive;
        int[] range = new int[nItems];
        for (int i = 0; i < nItems; ++i) {
            range[i] = i + fromInclusive;
        }
        return range;
    }

    static long[] rangeLong(int toExclusive) {
        return rangeLong(0, toExclusive);
    }

    static long[] rangeLong(int fromInclusive, int toExclusive) {
        int nItems = toExclusive - fromInclusive;
        long[] range = new long[nItems];
        for (int i = 0; i < nItems; ++i) {
            range[i] = i + fromInclusive;
        }
        return range;
    }

    /**
     * Reverses the contents of an array in-place
     *
     * <p>Complexity: O(elements.length)
     */
    static void reverse(int[] elements) {
        reverse(elements, 0, elements.length);
    }

    static void reverse(int[] elements, int fromInclusive, int toExclusive) {
        int nElements = toExclusive - fromInclusive;
        for (int i = fromInclusive; i < fromInclusive + nElements / 2; ++i) {
            swap(elements, i, toExclusive - i + fromInclusive - 1);
        }
    }

    static void reverse(long[] elements) {
        reverse(elements, 0, elements.length);
    }

    static void reverse(long[] elements, int fromInclusive, int toExclusive) {
        int nElements = toExclusive - fromInclusive;
        for (int i = fromInclusive; i < fromInclusive + nElements / 2; ++i) {
            swap(elements, i, toExclusive - i + fromInclusive - 1);
        }
    }

    /**
     * Rotates elements in-place, by offset positions.
     *
     * <p>Assumption: -elements.length <= offset <= elements.length
     *
     * <p>Complexity: O(elements.length)
     */
    static void rotate(int[] elements, int offset) {
        int positiveOffset = offset >= 0 ? offset : elements.length + offset;
        reverse(elements);
        reverse(elements, 0, positiveOffset);
        reverse(elements, positiveOffset, elements.length);
    }

    static void rotate(long[] elements, int offset) {
        int positiveOffset = offset >= 0 ? offset : elements.length + offset;
        reverse(elements);
        reverse(elements, 0, positiveOffset);
        reverse(elements, positiveOffset, elements.length);
    }

    /**
     * Swaps elements[i] and elements[j]
     *
     * <p>Complexity: O(1)
     */
    static void swap(int[] elements, int i, int j) {
        int temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }

    static void swap(long[] elements, int i, int j) {
        long temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
