package ja.gauthier.competitiveprogramming;

import java.io.*;
import java.util.*;

public class TemplateComputationalGeometry {

  abstract static class Point {}

  static class PointLong extends Point implements Comparable<PointLong> {
    long x;
    long y;

    PointLong() {
      this.x = 0;
      this.y = 0;
    }

    PointLong(long x, long y) {
      this.x = x;
      this.y = y;
    }

    PointLong(PointLong other) {
      this.x = other.x;
      this.y = other.y;
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

    PointLong divideBy(long factor) {
      return new PointLong(this.x / factor, this.y / factor);
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
  }

  static double angle(PointLong a) {
    return Math.atan2(a.y, a.x);
  }

  static double angle(PointLong a, PointLong b) {
    return Math.atan2(crossProduct(a, b), dotProduct(a, b));
  }

  static double angle(PointLong a, PointLong b, PointLong origin) {
    return angle(minus(a, origin), minus(b, origin));
  }

  /* static PointLong bisector(PointLong a, PointLong b) { */
  /*   return plus(times(a, norm(b)), times(b, norm(a))); */
  /* } */

  static long clockwise(PointLong a, PointLong b) {
    return crossProduct(b, a);
  }

  static long clockwise(PointLong a, PointLong b, PointLong origin) {
    return clockwise(minus(a, origin), minus(b, origin));
  }

  static long crossProduct(PointLong a, PointLong b) {
    return a.x * b.y - a.y * b.x;
  }

  static double distance(PointLong a, PointLong b) {
    return norm(minus(a, b));
  }

  static PointLong divideBy(PointLong a, long factor) {
    return new PointLong(a.x / factor, a.y / factor);
  }

  static long dotProduct(PointLong a, PointLong b) {
    return a.x * b.x + a.y * b.y;
  }

  static boolean greaterEqualTo(PointLong a, PointLong b) {
    return !smallerThan(a, b);
  }

  static boolean greaterThan(PointLong a, PointLong b) {
    return !smallerEqualTo(a, b);
  }

  static PointLong minus(PointLong a) {
    return new PointLong(-a.x, -a.y);
  }

  static PointLong minus(PointLong a, PointLong b) {
    return new PointLong(a.x - b.x, a.y - b.y);
  }

  static double norm(PointLong a) {
    return Math.sqrt(dotProduct(a, a));
  }

  static PointLong perpendicular(PointLong a) {
    return new PointLong(-a.y, a.x);
  }

  static PointLong plus(PointLong a, PointLong b) {
    return new PointLong(a.x + b.x, a.y + b.y);
  }

  static PointLong rotate(PointLong a, double angle) {
    return new PointLong(
        Math.round(Math.cos(angle) * a.x - Math.sin(angle) * a.y),
        Math.round(Math.sin(angle) * a.x + Math.cos(angle) * a.y));
  }

  static PointLong rotate(PointLong a, double angle, PointLong origin) {
    return plus(origin, rotate(minus(a, origin), angle));
  }

  static boolean smallerEqualTo(PointLong a, PointLong b) {
    return a.x <= b.x || (a.x == b.x && a.y <= b.y);
  }

  static boolean smallerThan(PointLong a, PointLong b) {
    return a.x < b.x || (a.x == b.x && a.y < b.y);
  }

  static PointLong times(PointLong a, long factor) {
    return new PointLong(a.x * factor, a.y * factor);
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
    return l.ab.x != 0 && l.ab.y != 0;
  }

  static boolean onLine(PointLong p, LineLong l) {
    return false;
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

  static void main(String[] args) {}
}
