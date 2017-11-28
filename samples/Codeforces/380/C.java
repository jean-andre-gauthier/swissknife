import java.lang.Math;
import java.util.*;

public class C
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          s = input.next().toCharArray();
          int n = s.length;
          int m = input.nextInt();
          int[] l = new int[m];
          int[] r = new int[m];
          for (int i = 0; i < m; i++) {
            l[i] = input.nextInt()-1;
            r[i] = input.nextInt()-1;
          }
          input.close();

          t = new int[4*n];
          o = new int[4*n];
          c = new int[4*n];

          bst(1, 0, n);
          StringBuilder sb = new StringBuilder(m);
          for (int i = 0; i < m; i++) {
            sb.append(qst(1, 0, n, l[i], r[i]+1).t + "\n");
          }
          sb.delete(sb.length() - 1, sb.length());
          System.out.println(sb);
     }

     private static char[] s;
     private static int[] t;
     private static int[] o;
     private static int[] c;

     private static class Node {
      public final int t;
      public final int c;
      public final int o;

      public Node(int t, int c, int o) {
        this.t = t;
        this.c = c;
        this.o = o;
      }
     }

     private static void bst(int i, int l, int r) {
        if (r-l < 2) {
          t[i] = 0;
          o[i] = s[l] == '(' ? 1 : 0;
          c[i] = s[l] == ')' ? 1 : 0;
        } else {
          int il = 2*i;
          int ir = 2*i + 1;
          int m = (l + r) / 2;
          bst(il, l, m);
          bst(ir, m, r);
          int temp = Math.min(o[il], c[ir]);
          t[i] = t[il] + t[ir] + 2*temp;
          c[i] = c[il] + c[ir] - temp;
          o[i] = o[il] + o[ir] - temp;
        }
     }

     private static Node qst(int i, int x, int y, int l, int r) {
        if (r <= x || y <= l) {
          return new Node(0, 0, 0);
        } else if (l <= x && y <= r) {
          return new Node(t[i], c[i], o[i]);
        } else {
          int m = (x + y) / 2;
          int il = 2 * i;
          int ir = 2 * i + 1;
          Node a = qst(il, x, m, l, r);
          Node b = qst(ir, m, y, l, r);
          int temp = Math.min(a.o, b.c);
          return new Node(a.t + b.t + 2*temp, a.c + b.c - temp, a.o + b.o - temp);
        }
     }
}