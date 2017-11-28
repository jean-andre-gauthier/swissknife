import java.lang.Math;
import java.util.*;

public class G
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          long mod = 1000000007;
          long n = input.nextLong();
          long[] h = new long[(int)n+1];
          for (int i=1; i<=n; i++) {
              h[i] = input.nextLong();
          }
          input.close();

          long s = 0, sprev = n > 1 ? Math.min(h[1]-1, h[2]-1) % mod : 0, sum = sprev * sprev;
          for (int r=3; r<=n; r++) {
            s = (sprev*(Math.min(Math.min(h[r-2]-1, h[r-1]-1), h[r]-1)) + Math.min(h[r-1]-1, h[r]-1)) % mod;
            sum = (sum + Math.min(h[r-1]-1, h[r]-1) * s) % mod;
            sprev = s;
          }

          for (int i=1; i<=n; i++) {
            sum = (sum + h[i] - 1) % mod;
          }

          System.out.println(sum);
     }
}