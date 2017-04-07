import java.lang.Math;
import java.util.*;

public class C
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int m = input.nextInt();
          int[] a = new int[n+1];
          for (int i = 1; i<=n; i++) {
            a[i] = input.nextInt();
          }
          Arrays.sort(a);
          StringBuilder sb = new StringBuilder();
          int i = 1;
          int j = 1;
          int k = 0;
          while (m>0 && m >= i) {
            if (j <= n && i == a[j]) {
              j++;
            } else {
              k++;
              m-=i;
              sb.append(i + " ");
            }
            i++;
          }
          System.out.println(k);
          if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb.toString());
          }
     }
}