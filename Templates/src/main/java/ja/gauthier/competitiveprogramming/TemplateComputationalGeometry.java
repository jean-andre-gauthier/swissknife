package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.TemplateIO.*;
import static ja.gauthier.competitiveprogramming.TemplateMisc.*;

import java.io.*;
import java.util.*;

public class TemplateComputationalGeometry {

  abstract static class Point {}

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
      } else if (smallerThan(this, other)) {
        return -1;
      } else {
        return 1;
      }
    }

    void divideBy(double factor) {
      this.x /= factor;
      this.y /= factor;
    }

    public boolean equals(Object other) {
      if (this == other) {
        return true;
      } else if (other == null || getClass() != other.getClass()) {
        return false;
      } else {
        PointDouble otherPointDouble = (PointDouble) other;
        return this.x == otherPointDouble.x && this.y == otherPointDouble.y;
      }
    }

    public int hashCode() {
      long xLongBits = Double.doubleToLongBits(this.x);
      long yLongBits = Double.doubleToLongBits(this.y);
      return (int) (31 * (xLongBits ^ (xLongBits >>> 32)) + (int) (yLongBits ^ (yLongBits >>> 32)));
    }

    void minus() {
      this.x = -this.x;
      this.y = -this.y;
    }

    void minus(PointDouble other) {
      this.x -= other.x;
      this.y -= other.y;
    }

    void plus(PointDouble other) {
      this.x += other.x;
      this.y += other.y;
    }

    void times(double factor) {
      this.x *= factor;
      this.y *= factor;
    }

    void times(PointDouble other) {
      this.x *= other.x;
      this.y *= other.y;
    }

    @Override
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
      } else if (smallerThan(this, other)) {
        return -1;
      } else {
        return 1;
      }
    }

    void divideBy(long factor) {
      this.x = (long) Math.round(this.x / (double) factor);
      this.y = (long) Math.round(this.y / (double) factor);
    }

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

    void minus() {
      this.x = -this.x;
      this.y = -this.y;
    }

    void minus(PointLong other) {
      this.x -= other.x;
      this.y -= other.y;
    }

    void plus(PointLong other) {
      this.x += other.x;
      this.y += other.y;
    }

    void times(long factor) {
      this.x *= factor;
      this.y *= factor;
    }

    void times(PointLong other) {
      this.x *= other.x;
      this.y *= other.y;
    }

    @Override
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

  static boolean greaterEqualTo(PointDouble a, PointDouble b) {
    return !smallerThan(a, b);
  }

  static boolean greaterEqualTo(PointLong a, PointLong b) {
    return !smallerThan(a, b);
  }

  static boolean greaterThan(PointDouble a, PointDouble b) {
    return !smallerEqualTo(a, b);
  }

  static boolean greaterThan(PointLong a, PointLong b) {
    return !smallerEqualTo(a, b);
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

  static boolean smallerEqualTo(PointDouble a, PointDouble b) {
    return a.y < b.y || (a.y == b.y && a.x <= b.x);
  }

  static boolean smallerEqualTo(PointLong a, PointLong b) {
    return a.y < b.y || (a.y == b.y && a.x <= b.x);
  }

  static boolean smallerThan(PointDouble a, PointDouble b) {
    return a.y < b.y || (a.y == b.y && a.x < b.x);
  }

  static boolean smallerThan(PointLong a, PointLong b) {
    return a.y < b.y || (a.y == b.y && a.x < b.x);
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

    public PointLong b() {
      return plus(a, ab);
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

  static void sortByAngle(ArrayList<PointLong> points) {
    sortByAngle(points, new PointLong(0, 0));
  }

  static void sortByAngle(ArrayList<PointLong> points, PointLong origin) {
    int iPivot = partition(points, p -> greaterEqualTo(p, origin));
    Collections.sort(
        points.subList(0, iPivot), (PointLong p1, PointLong p2) -> (int) clockwise(p2, p1, origin));
    Collections.sort(
        points.subList(iPivot, points.size()),
        (PointLong p1, PointLong p2) -> (int) clockwise(p2, p1, origin));
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
    PointLong p11 = new PointLong(1, 1);
    PointLong p14 = new PointLong(1, 4);
    PointLong p21 = new PointLong(2, 1);
    PointLong p23 = new PointLong(2, 3);
    ArrayList<PointLong> input = new ArrayList<PointLong>(Arrays.asList(p14, p21, p23, p11));
    sortByAngle(input);
  }
}
