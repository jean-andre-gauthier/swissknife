import java.io.*;
import java.lang.Math;
import java.util.*;

public class B
{
    static ArrayList<ArrayList<int[]>> graph;
    static int[] mark;
    static int n;
    static int m;

    public static void main(String[] args) {
      n = in.nextInt();
      m = in.nextInt();
      graph = new ArrayList<>(n);
      mark = new int[n+1];
      for (int i=0; i<=n; i++) {
        graph.add(new ArrayList<>());
      }
      for (int i=0; i<m; i++) {
        int u = in.nextInt();
        int v = in.nextInt();
        int c = in.next().charAt(0) == 'B' ? 1 : 2;
        graph.get(u).add(new int[] {v, c});
        graph.get(v).add(new int[] {u, c});
      }

      Collection<Integer> bFlips = solve(1);
      Collection<Integer> rFlips = solve(2);
      if (bFlips == null && rFlips == null) {
        out.println("-1");
      } else if (bFlips == null || rFlips == null) {
        Collection<Integer> answer = bFlips == null ? rFlips : bFlips;
        out.println(answer.size());
        if (answer.size() > 0) {
          out.println(answer, false);
        }
      } else {
        Collection<Integer> answer = bFlips.size() < rFlips.size() ? bFlips : rFlips;
        out.println(answer.size());
        if (answer.size() > 0) {
          out.println(answer, false);
        }
      }
    }

    public static Collection<Integer> solve(int color) {
      ArrayList<Integer> flips = new ArrayList<>();
      Arrays.fill(mark, 0);
      for (int i=1; i<=n; i++) {
        if (mark[i] == 0) {
          ArrayList<Integer> p1 = new ArrayList<>();
          ArrayList<Integer> p2 = new ArrayList<>();
          if (!setNode(i, 1, p1, p2, color)) {
            return null;
          } else {
            flips.addAll(p1.size() < p2.size() ? p1 : p2);
          }
        }
      }
      return flips;
    }

    public static boolean setNode(int i, int p, Collection<Integer> p1, Collection<Integer> p2, int color) {
      if (mark[i] != 0) {
        return mark[i] == p;
      } else {
        mark[i] = p;
        if (p == 1) {
          p1.add(i);
        } else {
          p2.add(i);
        }
        for (int[] vc: graph.get(i)) {
          if (vc[1] == color) {
            if (!setNode(vc[0], p, p1, p2, color)) {
              return false;
            }
          } else {
            if (!setNode(vc[0], 3-p, p1, p2, color)) {
              return false;
            }
          }
        }
        return true;
      }
    }

    static MyScanner in = new MyScanner();
    static MyPrintWriter out = new MyPrintWriter();

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

    static class MyPrintWriter {
      BufferedOutputStream bos;
      PrintWriter pw;

      MyPrintWriter() {
        this.bos = new BufferedOutputStream(System.out);
        this.pw = new PrintWriter(bos, true);
      }

      <T> void println(T t) {
        pw.println(t.toString());
      }

      <T> void println(T[] ts) {
        println(ts, true);
      }

      <T> void println(Collection<T> ts) {
        println(ts, true);
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
}