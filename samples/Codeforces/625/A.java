import java.lang.Math;
import java.util.*;

public class A
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          long n = input.nextLong();
          long a = input.nextLong();
          long b = input.nextLong();
          long c = input.nextLong();
          long nLiters = 0;
          if (a <= b-c) {
            nLiters = n / a;
          } else {
            nLiters = (n-b) / (b-c);
            long remainding = n - nLiters * (b-c);

            while (b <= remainding) {
              nLiters += 1;
              remainding -= (b-c);
            }

            nLiters += remainding / a;
          }
          System.out.println(nLiters);
          input.close();
     }
}