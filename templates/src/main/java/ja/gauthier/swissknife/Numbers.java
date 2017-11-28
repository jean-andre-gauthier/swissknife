package ja.gauthier.swissknife;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class Numbers {
  /** Calculates factorials up for 0 < n <= 20 */
  long factorial(int n) {
    long result = 1;
    for (int i = 2; i <= n; ++i) {
      result *= i;
    }
    return result;
  }
}
