import java.io.*;
import java.lang.Math;
import java.util.*;

public class Main
{
    private static int n;
    private static Integer[] a;
    private static Integer[] ap;
    private static int q;
    private static Integer[][] k;
    private static Integer[] kp;
    private static Integer[] b;
    private static Integer[] s;
    private static Integer[] ans;

    private static class Node {
      private boolean isQuery;
      private int a;
      private int i;
      private int j;
      private int k;

      public Node(boolean isQuery, int a, int i, int j, int k) {
        this.isQuery = isQuery;
        this.a = a;
        this.i = i;
        this.j = j;
        this.k = k;
      }
    }

    private static void build(int id, int l, int r) {
      if (r-l < 2) {
        s[id] = 1;
      } else {
        int mid = (r+l)/2;
        int il = 2*id;
        int ir = 2*id+1;
        build(il, l, mid);
        build(ir, mid, r);
        s[id] = s[il]+s[ir];
      }
    }

    private static void update(int i, int id, int l, int r) {
      if (r-l<2) {
        s[id] = 0;
      } else {
        int mid = (r+l)/2;
        int il = 2*id;
        int ir = 2*id+1;
        if (i < mid) {
          update(i, il, l, mid);
        } else {
          update(i, ir, mid, r);
        }
        s[id] = s[il]+s[ir];
      }
    }

    private static int sum(int id, int x, int y, int l, int r) {
      if (x >= r || l >= y) {
        return 0;
      }
      if (x <= l && r <= y) {
        return s[id];
      }
      int mid = (r+l)/2;
      int il = 2*id;
      int ir = 2*id+1;
      return sum(il, x, y, l, mid)+sum(ir, x, y, mid, r);
    }

    public static void main(String[] args)
    {
          MyScanner input = new MyScanner();
          PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out), true);
          n = input.nextInt();
          a = new Integer[n];
          Arrays.setAll(a, i -> input.nextInt());
          ap = new Integer[n];
          Arrays.setAll(ap, i -> i);
          q = input.nextInt();
          k = new Integer[q][3];
          kp = new Integer[q];
          ans = new Integer[q];
          Arrays.setAll(kp, i -> i);
          Arrays.setAll(k, i -> {
            k[i][0] = input.nextInt();
            k[i][1] = input.nextInt();
            k[i][2] = input.nextInt();
            return k[i];
          });

          Arrays.sort(ap, (i, j) -> a[i].compareTo(a[j]));
          Arrays.sort(kp, (i, j) -> k[i][2].compareTo(k[j][2]));

          b = new Integer[n];
          s = new Integer[4*n];

          build(1, 0, n);
          int i = 0;

          for (int j=0; j<q; j++) {
            while (i < n && a[ap[i]] <= k[kp[j]][2]) {
              update(ap[i], 1, 0, n);
              i++;
            }
            ans[kp[j]] = sum(1, k[kp[j]][0]-1, k[kp[j]][1], 0, n);
          }

          StringBuilder sb = new StringBuilder(q);
          for (int j=0; j<q; j++) {
            sb.append(ans[j] + "\n");
          }
          out.print(sb.toString());
          out.close();
     }

    //-----------MyScanner class for faster input----------
    public static class MyScanner {
        BufferedReader br;
        StringTokenizer st;

        public MyScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }

        String nextLine(){
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}