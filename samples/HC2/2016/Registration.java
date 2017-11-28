import java.lang.Math;
import java.util.*;

public class Registration
{
    public static void main(String[] args)
    {
          Scanner input = new Scanner(System.in);
          StringBuffer sb = new StringBuffer();
          char cc = 0;

          while (input.hasNext()) {
            String action = input.nextLine();

            if (action.contains("kill zombie -> ")) {
              if (cc != 0) {
                sb.append(cc);
              }
              cc = action.charAt(action.length() - 1) ;
            } else if (action.equals("clean chainsaw")) {
              cc -= 3;
            } else if (action.equals("grab brain")) {
              cc += 2;
            }
          }
          sb.append(cc);
          input.close();

          System.out.println(sb.toString());
     }
}