import java.lang.Math;
import java.util.*;

public class A
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          String s = input.next();
          int nWays = 0;

          for (int i = 0; i < n; i++) {
            int x = 0;
            int y = 0;

            for (int j = i; j < n; j++) {
              if (s.charAt(j) == 'U') {
                y++;
              } else if (s.charAt(j) == 'D') {
                y--;
              } else if (s.charAt(j) == 'L') {
                x--;
              } else {
                x++;
              }
              if (x == 0 && y == 0) {
                nWays++;
              }
            }
          }
          System.out.println(nWays);
          input.close();
     }
}