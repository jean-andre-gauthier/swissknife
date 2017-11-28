import java.lang.Math;
import java.util.*;

public class ProblemA
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          long[] ns = new long[n];
          for (int i = 0; i < n; i++) {
            ns[i] = input.nextLong();
          }
          input.close();

          long evenSum = 0;
          int nOdd = 0;
          long oddSum = 0;
          for (int i = 0; i < n; i++) {
            if ((ns[i] & 1) == 0) {
              evenSum += ns[i];
            } else {
              oddSum += ns[i];
              nOdd++;
            }
          }

          if ((nOdd & 1) == 1) {
            long minOdd = Long.MAX_VALUE;
            for (int i = 0; i < n; i++) {
              if ((ns[i] & 1) == 1 && ns[i] < minOdd) {
                minOdd = ns[i];
              }
            }
            oddSum -= minOdd;
          }

          System.out.println(evenSum + oddSum);
     }
}