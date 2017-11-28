import java.lang.Math;
import java.util.*;

public class ProblemA
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int nlog = (new Double(Math.log10(n) / Math.log10(2))).intValue() + 1;
          int[] result = new int[nlog];
          for (int i = 0; i < nlog; i++) {
              result[nlog-i-1] = n & 1;
              n = n >> 1;
          }
          for (int i = 0;i<nlog;i++) {
            if (result[i] != 0) {
              if (i > 0) {
                System.out.printf(" ");
              }

              System.out.printf("%d", nlog-i);
            }
          }
          System.out.println();
          input.close();
     }
}