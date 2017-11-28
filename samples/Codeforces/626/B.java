import java.lang.Math;
import java.util.*;

public class B
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          int n = input.nextInt();
          String s = input.next();
          input.close();
          int nr = 0;
          int ng = 0;
          int nb = 0;
          for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
              nr++;
            } else if (s.charAt(i) == 'G') {
              ng++;
            } else if (s.charAt(i) == 'B') {
              nb++;
            }
          }
          if (nr > 0 && ng > 0 && nb > 0) {
            System.out.println("BGR");
          } else if (nb == 0) {
            if (nr > 1 && ng > 1) {
              System.out.println("BGR");
            } else if (nr == 1 && ng > 1) {
              System.out.println("BR");
            } else if (nr > 1 && ng == 1) {
              System.out.println("BG");
            } else if (ng == 0) {
              System.out.println("R");
            } else if (nr == 0) {
              System.out.println("G");
            } else {
              System.out.println("B");
            }
          } else if (ng == 0) {
            if (nr > 1 && nb > 1) {
              System.out.println("BGR");
            } else if (nr == 1 && nb > 1) {
              System.out.println("GR");
            } else if (nr > 1 && nb == 1) {
              System.out.println("BG");
            } else if (nb == 0) {
              System.out.println("R");
            } else if (nr == 0) {
              System.out.println("B");
            } else {
              System.out.println("G");
            }
          } else if (nr == 0) {
            if (nb > 1 && ng > 1) {
              System.out.println("BGR");
            } else if (nb == 1 && ng > 1) {
              System.out.println("BR");
            } else if (nb > 1 && ng == 1) {
              System.out.println("GR");
            } else if (nb == 0) {
              System.out.println("G");
            } else if (ng == 0) {
              System.out.println("B");
            } else {
              System.out.println("R");
            }
          }
     }
}