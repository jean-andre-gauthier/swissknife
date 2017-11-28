import java.lang.Math;
import java.util.*;

public class C
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int k = input.nextInt()-1;
          int j = 0;
          int count = 1;
          int[][] t = new int[n][n];
          while (j < k) {
            for (int i = 0; i < n; i++) {
              t[i][j] = count;
              count++;
            }
            j++;
          }
          int i = 0;
          while (i < n) {
            for (int col = k; col < n; col++) {
              t[i][col] = count;
              count++;
            }
            i++;
          }
          int kSum = 0;
          for (int row = 0; row < n; row++) {
            kSum+=t[row][k];
          }
          System.out.println(kSum);
          for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
              if (col == n-1) {
                System.out.println(t[row][col]);
              } else {
                System.out.print(t[row][col] + " ");
              }
            }
          }
          input.close();
     }
}