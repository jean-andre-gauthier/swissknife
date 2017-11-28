import java.lang.Math;
import java.util.*;

public class D
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int[] x = new int[n];
          int[] y = new int[n];
          for (int i=0; i<n; i++) {
            x[i] = input.nextInt();
            y[i] = input.nextInt();
          }
          input.close();
          int previousDir = 3;
          int danger = 0;
          for (int i=0; i<n; i++) {
            int j = (i-1)%n;
            if (j < 0) {
              j += n;
            }
            int k = (i+1)%n;
            int v1 = x[i] - x[j];
            int v2 = y[i] - y[j];
            int w1 = x[k] - x[i];
            int w2 = y[k] - y[i];

            if (Math.signum(v1) * Math.signum(w2) - Math.signum(v2) * Math.signum(w1) > 0) {
              danger++;
            }
          }
          System.out.println(danger);
     }
}