import java.lang.Math;
import java.util.*;

public class C
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int m = input.nextInt();
          String s = input.next();
          input.close();

          int so = 0;
          int sc = 0;
          for (int i = 0; i<m; i++) {
            if (s.charAt(i) == '(') {
              so++;
            } else {
              sc++;
            }
          }
     }
}