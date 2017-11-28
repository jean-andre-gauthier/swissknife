import java.lang.Math;
import java.util.*;

public class ProblemC
{
    private static double getProb(double li, double ri, double p) {
      return (((ri - (ri % p)) - (li + (p - li % p) % p)) / p + 1) / (ri - li + 1);
    }

    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int p = input.nextInt();
          int li[] = new int[n];
          int ri[] = new int[n];
          double sum = 0;

          for (int i = 0; i < n; i++) {
            li[i] = input.nextInt();
            ri[i] = input.nextInt();
          }

          for (int i = 0; i < n; i++) {
            double probSelfDivisible = getProb(li[i], ri[i], p);

            sum += 1000 * ((2 * probSelfDivisible)
              + (1 - probSelfDivisible)
                * (getProb(li[(i-1+n) % n], ri[(i-1+n) % n], p)
                  + getProb(li[(i+1) % n], ri[(i+1) % n], p)));
          }

          System.out.println(sum);

          input.close();
     }
}