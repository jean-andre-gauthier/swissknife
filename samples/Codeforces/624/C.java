import java.lang.Math;
import java.util.*;

public class C
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int m = input.nextInt();
          boolean[][] e = new boolean[n][n];
          int[] nNeighbours = new int[n];
          for (int i = 0; i < m; i++) {
            int a = input.nextInt()-1;
            int b = input.nextInt()-1;
            e[a][b] = e[b][a] = true;
            nNeighbours[a]++;
            nNeighbours[b]++;
          }
          input.close();

          char[] s = new char[n];
          for (int i = 0; i < n; i++) {
            if (nNeighbours[i] == n-1) {
              s[i] = 'b';
            }
          }
          int firstNotB = 0;
          while (firstNotB < n && s[firstNotB] == 'b') {
            firstNotB++;
          }
          if (firstNotB < n) {
            s[firstNotB] = 'a';
            for (int i = 0; i < n; i++) {
              if (e[firstNotB][i] && s[i] != 'b') {
                s[i] = 'a';
              }
            }
            int firstNotAB = 0;
            while (firstNotAB < n && (s[firstNotAB] == 'a' || s[firstNotAB] == 'b')) {
              firstNotAB++;
            }
            if (firstNotAB < n) {
              s[firstNotAB] = 'c';
              for (int i = 0; i < n; i++) {
                if (e[firstNotAB][i] && s[i] != 'a' && s[i] != 'b') {
                  s[i] = 'c';
                }
              }
              int firstNotABC = 0;
              while (firstNotABC < n && (s[firstNotABC] == 'a' || s[firstNotABC] == 'b' || s[firstNotABC] == 'c')) {
                firstNotABC++;
              }
              if (firstNotABC < n) {
                System.out.println("No");
                return;
              } else {
                for (int i = 0; i < n; i++) {
                  for (int j = 0; j < n; j++) {
                    if (i != j
                      && ((Math.abs(s[i] - s[j]) <= 1 && !e[i][j])
                        || (Math.abs(s[i] - s[j]) == 2 && e[i][j])))  {
                      System.out.println("No");
                      return;
                    }
                  }
                }
              }
            }
          }
          System.out.println("Yes\n" + String.valueOf(s));
     }
}