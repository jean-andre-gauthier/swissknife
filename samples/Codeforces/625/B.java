import java.lang.Math;
import java.util.*;
import java.util.regex.*;

public class B
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          String ai = input.nextLine();
          String ss = input.nextLine();

          Matcher m = Pattern.compile(ss).matcher(ai);
          int lastStart = -1;
          int lastEnd = -1;
          int nHashes = 0;
          while (m.find()) {
              if (m.start() > lastEnd) {
                lastStart = m.start();
                lastEnd = m.end()-1;
                nHashes++;
              }
          }
          System.out.println(nHashes);
          input.close();
     }
}