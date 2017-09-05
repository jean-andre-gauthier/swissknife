package ja.gauthier.competitiveprogramming;

public class Doubles {
  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static boolean equalTo(double a, double b) {
    return Math.abs(ulpDiff(a, b)) < ULP;
  }

  static boolean equalToRelative(double a, double b) {
    return Math.abs(a - b) < EPS;
  }

  static final double EPS = 1e-9;

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static boolean greaterThan(double a, double b) {
    return !smallerEqualTo(a, b);
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static boolean greaterEqualTo(double a, double b) {
    return !smallerThan(a, b);
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static boolean smallerThan(double a, double b) {
    return ulpDiff(a, b) <= -ULP;
  }

  /**
   * Assumptions: 1.no special thoughts have been given to NaNs 2. +/-Infinity and -0.0 are handled
   * appropriately
   */
  static boolean smallerEqualTo(double a, double b) {
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
}
