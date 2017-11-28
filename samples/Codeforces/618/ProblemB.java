import java.lang.Math;
import java.util.*;

public class ProblemB
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int[][] a = new int[n][n];
          int[] colOut = new int[n];
          int[] rowOut = new int[n];
          int[] p = new int[n];
          for(int i=0;i<n;i++)
            for(int j=0;j<n;j++)
              a[i][j]=input.nextInt();
          for(int i=0;i<n;i++){
              for(int j=0;j<n;j++){
                int k=0;
                for(k=0;k<n;k++){
                  if (a[i][j] > i && a[i][j]!=i)
                    break;
                }

              }
          }
          for(int i=0;i<n;i++){
            if(p[i]==0)
              p[i]=n;
            if(i<n-1)
              System.out.printf("%d ", p[i]);
            else
              System.out.printf("%d", p[i]);
          }
          System.out.println();
          input.close();
     }
}