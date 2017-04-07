import java.lang.Math;
import java.util.*;

public class E
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int m = input.nextInt();
          List<List<Integer>> xy = new ArrayList<>();
          for (int i=0; i<=n; i++) {
            xy.add(new ArrayList<Integer>());
          }
          for (int i=0; i<m; i++) {
            int v = input.nextInt();
            int w = input.nextInt();
            xy.get(v).add(w);
            xy.get(w).add(v);
          }
          input.close();

          boolean[] marked = new boolean[n+1];
          List<Integer> witness = new ArrayList<>();
          for (int a=1; a<=n; a++) {
            if (!marked[a]) {
              Stack<Integer> s = new Stack<>();
              s.push(a);
              while (!s.empty()) {
                int i = s.pop();
                marked[i] = true;
                for (Integer j: xy.get(i)) {
                  if (!marked[j]) {
                    s.push(j);
                  }
                }
              }
              witness.add(a);
            }
          }

          int k = 0;
          boolean[] visited = new boolean[n+1];
          for (Integer a: witness) {
            boolean c = false;
            Stack<int[]> s = new Stack<>();
            s.push(new int[]{a, 0});
            while (!s.empty()) {
              int[] ip = s.pop();
              int i = ip[0];
              int p = ip[1];
              if (visited[i]) {
                c = true;
                break;
              } else {
                visited[i] = true;
                for (Integer j: xy.get(i)) {
                  if (j != p) {
                    s.push(new int[]{j, i});
                  }
                }
              }
            }

            if (!c) {
              k++;
            }
          }

          System.out.println(k);
     }
}