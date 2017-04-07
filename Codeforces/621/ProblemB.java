import java.lang.Math;
import java.util.*;

public class ProblemB
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          boolean[][] bs = new boolean[1000][1000];
          int n = input.nextInt();
          for (int i = 0; i < n; i++) {
            bs[input.nextInt()-1][input.nextInt()-1] = true;
          }
          input.close();

          int nPairs = 0;

          for (int i = 0; i < 1999; i++) {
            int nBishopsD = 0;
            for (int j = 0; j <= (i < 1000 ? i : 1998 - i); j++) {
              int x = i < 1000 ? j : i - 999 + j;
              int y = i < 1000 ? i - j : 999 - j;
              if (bs[x][y]) {
                nPairs += nBishopsD;
                nBishopsD++;
              }
            }
          }

          for (int i = 0; i < 1999; i++) {
            int nBishopsD = 0;
            for (int j = 0; j <= (i < 1000 ? i : 1998 - i); j++) {
              int x = i < 1000 ? j : i - 999 + j;
              int y = i < 1000 ? i - j : 999 - j;
              if (bs[999-x][y]) {
                nPairs += nBishopsD;
                nBishopsD++;
              }
            }
          }

          System.out.println(nPairs);
     }
}