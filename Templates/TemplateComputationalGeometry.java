import java.io.*;
import java.util.*;

public class TemplateComputationalGeometry {

  private static class PointLong implements Comparable<PointLong> {
    public long x;
    public long y;

    public PointLong() {
      this.x = 0;
      this.y = 0;
    }

    public PointLong(long x, long y) {
      this.x = x;
      this.y = y;
    }

    public PointLong(PointLong other) {
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

    public long crossProduct(PointLong other) {
      return this.x * other.y - this.y * other.x;
    }

    public PointLong divideBy(long factor) {
      return new PointLong(this.x / factor, this.y / factor);
    }

    public long dotProduct(PointLong other) {
      return this.x * other.x + this.y * other.y;
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

    public void minus() {
      this.x = -this.x;
      this.y = -this.y;
    }

    public void minus(PointLong other) {
      this.x -= other.x;
      this.y -= other.y;
    }

    public void plus(PointLong other) {
      this.x += other.x;
      this.y += other.y;
    }

    public void times(long factor) {
      this.x *= factor;
      this.y *= factor;
    }

    public void times(PointLong other) {
      this.x *= other.x;
      this.y *= other.y;
    }
  }

  public static long clockwise(PointLong a, PointLong b) {
    return crossProduct(b, a);
  }

  public static long clockwise(PointLong a, PointLong b, PointLong origin) {
    return clockwise(minus(a, origin), minus(b, origin));
  }

  public static long crossProduct(PointLong a, PointLong b) {
    return a.x * b.y - a.y * b.x;
  }

  public static PointLong divideBy(PointLong a, long factor) {
    return new PointLong(a.x / factor, a.y / factor);
  }

  public static long dotProduct(PointLong a, PointLong b) {
    return a.x * b.x + a.y * b.y;
  }

  public static boolean greaterEqualTo(PointLong a, PointLong b) {
    return !smallerThan(a, b);
  }

  public static boolean greaterThan(PointLong a, PointLong b) {
    return !smallerEqualTo(a, b);
  }

  public static PointLong minus(PointLong a) {
    return new PointLong(-a.x, -a.y);
  }

  public static PointLong minus(PointLong a, PointLong b) {
    return new PointLong(a.x - b.x, a.y - b.y);
  }

  public static PointLong plus(PointLong a, PointLong b) {
    return new PointLong(a.x + b.x, a.y + b.y);
  }

  public static boolean smallerEqualTo(PointLong a, PointLong b) {
    return a.x <= b.x || (a.x == b.x && a.y <= b.y);
  }

  public static boolean smallerThan(PointLong a, PointLong b) {
    return a.x < b.x || (a.x == b.x && a.y < b.y);
  }

  public static PointLong times(PointLong a, long factor) {
    return new PointLong(a.x * factor, a.y * factor);
  }

  public static PointLong times(PointLong a, PointLong b) {
    return new PointLong(a.x * b.x, a.y * b.y);
  }

  public static void main(String[] args) {}
}
