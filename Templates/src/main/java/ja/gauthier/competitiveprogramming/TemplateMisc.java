package ja.gauthier.competitiveprogramming;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.function.*;

public class TemplateMisc {

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static final boolean greaterThan(double a, double b) {
    return !smallerEqualTo(a, b);
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static final boolean greaterEqualTo(double a, double b) {
    return !smallerThan(a, b);
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static final boolean equalTo(double a, double b) {
    return Math.abs(ulpDiff(a, b)) < ULP;
  }

  static boolean equalToRelative(double a, double b) {
    return Math.abs(a - b) < EPS;
  }

  static final double EPS = 1e-9;

  @FunctionalInterface
  static interface LongBiPredicate {
    boolean test(long a, long b);
  }

  static <T> int partition(ArrayList<T> list, Predicate<T> p) {
    return partition(list, p, 0, list.size());
  }

  static <T> int partition(ArrayList<T> list, Predicate<T> p, int from, int to) {
    int first = from;
    int last = to;
    while (first != last) {
      if (!p.test(list.get(first))) {
        Collections.swap(list, first, last - 1);
        --last;
      } else {
        ++first;
      }
    }
    return first;
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static final boolean smallerThan(double a, double b) {
    return ulpDiff(a, b) <= -ULP;
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static final boolean smallerEqualTo(double a, double b) {
    return ulpDiff(a, b) < ULP;
  }

  static final double ULP = 2;

  static long ulpDiff(double a, double b) {
    if (equalToRelative(a, b)) {
      return 0;
    }

    long aLong = Double.doubleToRawLongBits(a);
    long bLong = Double.doubleToRawLongBits(b);

    if (Math.signum(aLong) == 1.0 || Math.signum(aLong) == 0.0) {
      if (Math.signum(bLong) == -1) {
        return Long.MAX_VALUE;
      } else {
        return aLong - bLong;
      }
    } else {
      if (Math.signum(bLong) == 1) {
        return Long.MIN_VALUE;
      } else {
        return bLong - aLong;
      }
    }
  }

  public static void main(String[] args) {
    smallerThan(Double.NEGATIVE_INFINITY, Math.nextUp(-Double.MAX_VALUE));
  }
}
