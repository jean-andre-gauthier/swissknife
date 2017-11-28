import java.lang.Math;
import java.util.*;

public class C
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          int bn = 2*n;
          int m = input.nextInt();
          int bm = 3*m;
          input.close();

          int min = 1;

          if (bn <= bm) {
            for (int i = 1; i <= bm; i++) {
              if (i % 6 != 0) {
                min+=1;
              } else {
                min+=2;
              }
            }


            System.out.println(min);
            System.out.println(Math.max(min,bm));
          } else {
            for (int i = 1; i <= bn; i++) {
              if (i % 6 != 0) {
                min+=1;
              } else {
                min+=3;
              }
            }
            System.out.println(Math.max(min,bn));
          }
     }
}