package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.Algorithms.*;
import static ja.gauthier.swissknife.Doubles.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class ComputationalGeometry {

  abstract static class Point {}

  /** Assumption: neither x nor y are +/-Infinity, NaN or -0 */
  static class PointDouble extends Point implements Comparable<PointDouble> {
    double x;
    double y;

    PointDouble() {
      this.x = 0;
      this.y = 0;
    }

    PointDouble(double x, double y) {
      this.x = x;
      this.y = y;
    }

    PointDouble(long x, long y) {
      this.x = x;
      this.y = y;
    }

    PointDouble(PointDouble other) {
      this.x = other.x;
      this.y = other.y;
    }

    PointDouble(PointLong other) {
      this.x = other.x;
      this.y = other.y;
    }

    PointLong asPointLong() {
      return new PointLong(this);
    }

    public int compareTo(PointDouble other) {
      if (other == null) {
        throw new NullPointerException();
      } else if (this.equals(other)) {
        return 0;
      } else if (smallerThanP(this, other)) {
        return -1;
      } else {
        return 1;
      }
    }

    /* void divideBy(double factor) { */
    /*   this.x /= factor; */
    /*   this.y /= factor; */
    /* } */

    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } else if (other == null || getClass() != other.getClass()) {
        return false;
      } else {
        PointDouble otherPointDouble = (PointDouble) other;
        return equalTo(this.x, otherPointDouble.x) && equalTo(this.y, otherPointDouble.y);
      }
    }

    public int hashCode() {
      long xLongBits = Double.doubleToLongBits(this.x);
      long yLongBits = Double.doubleToLongBits(this.y);
      return (int) (31 * (xLongBits ^ (xLongBits >>> 32)) + (int) (yLongBits ^ (yLongBits >>> 32)));
    }
    /*  */
    /* void minus() { */
    /*   this.x = -this.x; */
    /*   this.y = -this.y; */
    /* } */
    /*  */
    /* void minus(PointDouble other) { */
    /*   this.x -= other.x; */
    /*   this.y -= other.y; */
    /* } */
    /*  */
    /* void plus(PointDouble other) { */
    /*   this.x += other.x; */
    /*   this.y += other.y; */
    /* } */
    /*  */
    /* void times(double factor) { */
    /*   this.x *= factor; */
    /*   this.y *= factor; */
    /* } */
    /*  */
    /* void times(PointDouble other) { */
    /*   this.x *= other.x; */
    /*   this.y *= other.y; */
    /* } */

    public String toString() {
      return "(" + this.x + "," + this.y + ")";
    }
  }

  static class PointLong extends Point implements Comparable<PointLong> {
    long x;
    long y;

    PointLong() {
      this.x = 0;
      this.y = 0;
    }

    PointLong(double x, double y) {
      this.x = Math.round(x);
      this.y = Math.round(y);
    }

    PointLong(long x, long y) {
      this.x = x;
      this.y = y;
    }

    PointLong(PointDouble other) {
      this.x = Math.round(other.x);
      this.y = Math.round(other.y);
    }

    PointLong(PointLong other) {
      this.x = other.x;
      this.y = other.y;
    }

    PointDouble asPointDouble() {
      return new PointDouble(this);
    }

    public int compareTo(PointLong other) {
      if (other == null) {
        throw new NullPointerException();
      } else if (this.equals(other)) {
        return 0;
      } else if (smallerThanP(this, other)) {
        return -1;
      } else {
        return 1;
      }
    }

    /* void divideBy(long factor) { */
    /*   this.x = (long) Math.round(this.x / (double) factor); */
    /*   this.y = (long) Math.round(this.y / (double) factor); */
    /* } */

    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } else if (other == null || getClass() != other.getClass()) {
        return false;
      } else {
        PointLong otherPointLong = (PointLong) other;
        return this.x == otherPointLong.x && this.y == otherPointLong.y;
      }
    }

    public int hashCode() {
      return (int) (31 * (this.x ^ (this.x >>> 32)) + (int) (this.y ^ (this.y >>> 32)));
    }

    /* void minus() { */
    /*   this.x = -this.x; */
    /*   this.y = -this.y; */
    /* } */
    /*  */
    /* void minus(PointLong other) { */
    /*   this.x -= other.x; */
    /*   this.y -= other.y; */
    /* } */
    /*  */
    /* void plus(PointLong other) { */
    /*   this.x += other.x; */
    /*   this.y += other.y; */
    /* } */
    /*  */
    /* void times(long factor) { */
    /*   this.x *= factor; */
    /*   this.y *= factor; */
    /* } */
    /*  */
    /* void times(PointLong other) { */
    /*   this.x *= other.x; */
    /*   this.y *= other.y; */
    /* } */

    public String toString() {
      return "(" + this.x + "," + this.y + ")";
    }
  }

  static double angle(PointDouble a) {
    return Math.atan2(a.y, a.x);
  }

  static double angle(PointLong a) {
    return Math.atan2(a.y, a.x);
  }

  static double angle(PointDouble a, PointDouble b) {
    return Math.atan2(crossProduct(a, b), dotProduct(a, b));
  }

  static double angle(PointLong a, PointLong b) {
    return Math.atan2(crossProduct(a, b), dotProduct(a, b));
  }

  static double angle(PointDouble a, PointDouble b, PointDouble origin) {
    return angle(minus(a, origin), minus(b, origin));
  }

  static double angle(PointLong a, PointLong b, PointLong origin) {
    return angle(minus(a, origin), minus(b, origin));
  }

  static PointDouble bisector(PointDouble a, PointDouble b) {
    return plus(times(a, norm(b)), times(b, norm(a)));
  }

  static PointDouble bisector(PointLong a, PointLong b) {
    return plus(times(a.asPointDouble(), norm(b)), times(b.asPointDouble(), norm(a)));
  }

  static double clockwise(PointDouble a, PointDouble b) {
    return crossProduct(a, b);
  }

  static long clockwise(PointLong a, PointLong b) {
    return crossProduct(a, b);
  }

  static double clockwise(PointDouble a, PointDouble b, PointDouble origin) {
    return clockwise(minus(a, origin), minus(b, origin));
  }

  static long clockwise(PointLong a, PointLong b, PointLong origin) {
    return clockwise(minus(a, origin), minus(b, origin));
  }

  static double crossProduct(PointDouble a, PointDouble b) {
    return a.x * b.y - a.y * b.x;
  }

  static long crossProduct(PointLong a, PointLong b) {
    return a.x * b.y - a.y * b.x;
  }

  static double distance(PointDouble a, PointDouble b) {
    return norm(minus(a, b));
  }

  static double distance(PointLong a, PointLong b) {
    return norm(minus(a, b));
  }

  static PointDouble divideBy(PointDouble a, double factor) {
    return new PointDouble(a.x / factor, a.y / factor);
  }

  static PointDouble divideBy(PointLong a, long factor) {
    return new PointDouble(a.x / (double) factor, a.y / (double) factor);
  }

  static double dotProduct(PointDouble a, PointDouble b) {
    return a.x * b.x + a.y * b.y;
  }

  static long dotProduct(PointLong a, PointLong b) {
    return a.x * b.x + a.y * b.y;
  }

  static boolean greaterEqualToP(PointDouble a, PointDouble b) {
    return !smallerThanP(a, b);
  }

  static boolean greaterEqualToP(PointLong a, PointLong b) {
    return !smallerThanP(a, b);
  }

  static boolean greaterThanP(PointDouble a, PointDouble b) {
    return !smallerEqualToP(a, b);
  }

  static boolean greaterThanP(PointLong a, PointLong b) {
    return !smallerEqualToP(a, b);
  }

  static PointDouble minus(PointDouble a) {
    return new PointDouble(-a.x, -a.y);
  }

  static PointLong minus(PointLong a) {
    return new PointLong(-a.x, -a.y);
  }

  static PointDouble minus(PointDouble a, PointDouble b) {
    return new PointDouble(a.x - b.x, a.y - b.y);
  }

  static PointLong minus(PointLong a, PointLong b) {
    return new PointLong(a.x - b.x, a.y - b.y);
  }

  static double norm(PointDouble a) {
    return Math.sqrt(dotProduct(a, a));
  }

  static double norm(PointLong a) {
    return Math.sqrt(dotProduct(a, a));
  }

  static PointDouble perpendicular(PointDouble a) {
    return new PointDouble(-a.y, a.x);
  }

  static PointLong perpendicular(PointLong a) {
    return new PointLong(-a.y, a.x);
  }

  static PointDouble plus(PointDouble a, PointDouble b) {
    return new PointDouble(a.x + b.x, a.y + b.y);
  }

  static PointLong plus(PointLong a, PointLong b) {
    return new PointLong(a.x + b.x, a.y + b.y);
  }

  static PointDouble rotate(PointDouble a, double angle) {
    return new PointDouble(
        Math.cos(angle) * a.x - Math.sin(angle) * a.y,
        Math.sin(angle) * a.x + Math.cos(angle) * a.y);
  }

  static PointDouble rotate(PointLong a, double angle) {
    return new PointDouble(
        Math.cos(angle) * a.x - Math.sin(angle) * a.y,
        Math.sin(angle) * a.x + Math.cos(angle) * a.y);
  }

  static PointDouble rotate(PointDouble a, double angle, PointDouble origin) {
    return plus(origin, rotate(minus(a, origin), angle));
  }

  static PointDouble rotate(PointLong a, double angle, PointLong origin) {
    return plus(origin.asPointDouble(), rotate(minus(a, origin), angle));
  }

  static boolean smallerEqualToP(PointDouble a, PointDouble b) {
    return smallerThan(a.y, b.y) || (equalTo(a.y, b.y) && smallerEqualTo(a.x, b.x));
  }

  static boolean smallerEqualToP(PointLong a, PointLong b) {
    return a.y < b.y || (a.y == b.y && a.x <= b.x);
  }

  static boolean smallerThanP(PointDouble a, PointDouble b) {
    return smallerThan(a.y, b.y) || (equalTo(a.y, b.y) && smallerThan(a.x, b.x));
  }

  static boolean smallerThanP(PointLong a, PointLong b) {
    return a.y < b.y || (a.y == b.y && a.x < b.x);
  }

  static void sortByAngle(ArrayList<PointLong> points) {
    sortByAngle(points, new PointLong(0, 0));
  }

  static void sortByAngle(ArrayList<PointLong> points, PointLong origin) {
    int iPivot = partition(points, p -> greaterEqualToP(p, origin));
    Collections.sort(
        points.subList(0, iPivot), (PointLong p1, PointLong p2) -> (int) clockwise(p2, p1, origin));
    Collections.sort(
        points.subList(iPivot, points.size()),
        (PointLong p1, PointLong p2) -> (int) clockwise(p2, p1, origin));
  }

  static PointDouble times(PointDouble a, double factor) {
    return new PointDouble(a.x * factor, a.y * factor);
  }

  static PointLong times(PointLong a, long factor) {
    return new PointLong(a.x * factor, a.y * factor);
  }

  static PointDouble times(PointDouble a, PointDouble b) {
    return new PointDouble(a.x * b.x, a.y * b.y);
  }

  static PointLong times(PointLong a, PointLong b) {
    return new PointLong(a.x * b.x, a.y * b.y);
  }

  abstract static class Line {}

  static class LineLong extends Line {
    PointLong a;
    PointLong ab;

    LineLong(PointLong a, PointLong b, boolean twoPoints) {
      this.a = new PointLong(a);
      this.ab = new PointLong(twoPoints ? minus(b, a) : b);
    }

    public PointLong b() {
      return plus(a, ab);
    }

    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } else if (other == null || getClass() != other.getClass()) {
        return false;
      } else {
        LineLong otherLineLong = (LineLong) other;
        return ((this.a == null && otherLineLong.a == null)
                || (this.a != null && this.a.equals(otherLineLong.a)))
            && ((this.ab == null && otherLineLong.ab == null)
                || (this.ab != null && this.ab.equals(otherLineLong.ab)));
      }
    }

    public int hashCode() {
      return Objects.hash(this.a, this.ab);
    }

    public String toString() {
      return new StringBuffer()
          .append("((")
          .append(this.a.x)
          .append(",")
          .append(this.a.y)
          .append("),(")
          .append(this.ab.x)
          .append(",")
          .append(this.ab.y)
          .append("))")
          .toString();
    }
  }

  static enum EndpointType {
    RAY((a, b) -> true),
    OPEN((a, b) -> a < b),
    CLOSED((a, b) -> a <= b);

    LongBiPredicate predicate;

    EndpointType(LongBiPredicate predicate) {
      this.predicate = predicate;
    }
  }

  /** Assumption: 1. Parallel lines are not on same line 2. Input lines are not points */
  static PointDouble intersect(
      LineLong ab,
      EndpointType paba,
      EndpointType pabb,
      LineLong cd,
      EndpointType pcda,
      EndpointType pcdb) {
    long abCrossCd = crossProduct(ab.ab, cd.ab);

    if (abCrossCd == 0 || isPoint(ab) || isPoint(cd)) {
      return null;
    } else {
      PointLong ac = minus(cd.a, ab.a);
      long acCrossCd = crossProduct(ac, cd.ab);
      long acCrossAb = crossProduct(ac, ab.ab);

      if (abCrossCd < 0) {
        abCrossCd = -abCrossCd;
        acCrossCd = -acCrossCd;
        acCrossAb = -acCrossAb;
      }

      boolean intersect =
          paba.predicate.test(0, acCrossCd)
              && pabb.predicate.test(acCrossCd, 1)
              && pcda.predicate.test(0, acCrossAb)
              && pcdb.predicate.test(acCrossAb, 1);

      if (intersect) {
        return plus(
            ab.a.asPointDouble(), times(ab.ab.asPointDouble(), acCrossCd / (double) abCrossCd));
      } else {
        return null;
      }
    }
  }

  static boolean isPoint(LineLong l) {
    return l.ab.x == 0 && l.ab.y == 0;
  }

  static boolean onLine(PointLong p, LineLong l) {
    return isPoint(l) ? l.a.equals(p) : crossProduct(minus(p, l.a), l.ab) == 0;
  }

  static boolean onSegment(PointLong p, LineLong s) {
    if (isPoint(s)) {
      return s.a.equals(p);
    } else {
      PointLong pa = minus(s.a, p);
      PointLong pb = minus(s.b(), p);
      return crossProduct(pa, pb) == 0 && dotProduct(pa, pb) <= 0;
    }
  }

  static double distanceToLine(PointLong p, LineLong l) {
    if (isPoint(l)) {
      return distance(p, l.a);
    } else {
      return crossProduct(minus(p, l.a), l.ab) / norm(l.ab);
    }
  }

  static double distanceToSegment(PointLong p, LineLong s) {
    if (dotProduct(minus(p, s.a), s.ab) <= 0) {
      return distance(p, s.a);
    } else if (dotProduct(minus(p, s.b()), s.ab) <= 0) {
      return distanceToLine(p, s);
    } else {
      return distance(p, s.b());
    }
  }

  abstract static class Polygon {}

  static class PolygonLong extends Polygon {
    ArrayList<PointLong> points;

    PolygonLong(int nExpectedPoints) {
      this.points = new ArrayList<>(nExpectedPoints);
    }

    PolygonLong(long[][] points) {
      this.points = new ArrayList<>(points.length);
      for (long[] point : points) {
        this.points.add(new PointLong(point[0], point[1]));
      }
    }

    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } else if (other == null || getClass() != other.getClass()) {
        return false;
      } else {
        PolygonLong otherPolygonLong = (PolygonLong) other;
        if (this.points == null && otherPolygonLong.points == null) {
          return true;
        } else if (this.points != null
            && otherPolygonLong.points != null
            && this.points.size() == otherPolygonLong.points.size()) {
          for (int i = 0; i < this.points.size(); ++i) {
            if (!this.points.get(i).equals(otherPolygonLong.points.get(i))) {
              return false;
            }
          }
          return true;
        } else {
          return false;
        }
      }
    }

    public int hashCode() {
      return Objects.hashCode(this.points);
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(this.points.size() * 5);
      sb.append("(");
      for (PointLong p : this.points) {
        sb.append("(").append(p.x).append(",").append(p.y).append("),");
      }
      if (sb.length() > 1) {
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.append(")").toString();
    }
  }

  static int previous(int i, PolygonLong pp) {
    if (i == 0) {
      return pp.points.size() - 1;
    } else {
      return i - 1;
    }
  }

  static int next(int i, PolygonLong pp) {
    if (i == pp.points.size() - 1) {
      return 0;
    } else {
      return i + 1;
    }
  }

  static long doubleOrientedArea(PolygonLong pp) {
    long area = 0;
    for (int i = 0; i < pp.points.size(); i++) {
      PointLong pnext = pp.points.get(next(i, pp));
      PointLong pprev = pp.points.get(previous(i, pp));
      PointLong pcur = pp.points.get(i);
      area += pcur.x * (pnext.y - pprev.y);
    }
    return area;
  }

  static boolean anticlockwise(PolygonLong pp) {
    int ipmin = 0;
    for (int i = 1; i < pp.points.size(); i++) {
      if (pp.points.get(ipmin).compareTo(pp.points.get(i)) > 0) {
        ipmin = i;
      }
    }
    return clockwise(
            pp.points.get(previous(ipmin, pp)),
            pp.points.get(next(ipmin, pp)),
            pp.points.get(ipmin))
        > 0;
  }

  int sgn(long l) {
    return l < 0 ? -1 : l > 0 ? 1 : 0;
  }

  public static void main(String[] args) {
    PointLong pointp0p1 = new PointLong(0, 1);
    PointLong pointp0p4 = new PointLong(0, 4);
    LineLong lineXp0 = new LineLong(pointp0p1, pointp0p4, false);
    PointLong pointm1p0 = new PointLong(-1, 0);
    PointLong pointp1p0 = new PointLong(1, 0);
    LineLong lineY0 = new LineLong(pointm1p0, pointp1p0, false);
    PointDouble origin = new PointDouble(0, 0);
    PointLong pointp1p5 = new PointLong(1, 5);
    LineLong lineY5Xp1 = new LineLong(pointp0p1, pointp1p5, false);
    PointDouble pointm05p0 = new PointDouble(-0.2, 0);
    PointLong pointm05m1 = new PointLong(-0.5, -1);
    PointLong pointm1m2 = new PointLong(1, -2);
    LineLong lineYm2Xm2 = new LineLong(pointm05m1, pointm1m2, false);
    PointDouble pointm37thm87th = new PointDouble(-3 / 7.0, -8 / 7.0);
    intersect(
        lineY5Xp1,
        EndpointType.RAY,
        EndpointType.RAY,
        lineYm2Xm2,
        EndpointType.RAY,
        EndpointType.RAY);
  }
}
