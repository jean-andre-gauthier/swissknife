package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.IO.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class IntervalTrees {

  static class BinaryIndexedTreeLong {
    long tree[];

    /** Assumption: size = 2^k for non-negative integer k */
    BinaryIndexedTreeLong(int size) {
      tree = new long[size + 1];
    }

    void divideAll(long factor) {
      for (int i = 1; i <= size(); ++i) {
        tree[i] /= factor;
      }
    }

    long getAt(int i) {
      long sum = tree[i];
      int z = i - (i & -i);
      i--;
      while (i > z) {
        sum -= tree[i];
        i -= i & -i;
      }
      return sum;
    }

    long getBetween(int j) {
      long sum = 0;
      while (j > 0) {
        sum += tree[j];
        j -= j & -j;
      }
      return sum;
    }

    long getBetween(int i, int j) {
      return getAt(j - 1) - getAt(i);
    }

    void incrementAt(int i, long value) {
      while (i <= size()) {
        tree[i] += value;
        i += i & -i;
      }
    }

    int indexOfCumulative(long value) {
      int index = size();
      int mask = index;
      while (index <= size() && mask != 0) {
        if (tree[index] == value) {
          return index;
        } else if (tree[index] < value) {
          value -= tree[index];
          index += mask >>= 1;
        } else {
          index -= mask >>= 1;
        }
      }
      return -1;
    }

    int largestIndexOfCumulative(long value) {
      int index = size();
      int mask = index;
      int candidate = -1;
      while (index <= size() && mask != 0) {
        if (tree[index] == value) {
          candidate = index;
          value -= tree[index];
          index += mask >>>= 1;
        } else if (tree[index] < value) {
          value -= tree[index];
          index += mask >>= 1;
        } else {
          index -= mask >>= 1;
        }
      }
      return candidate;
    }

    void multiplyAll(long factor) {
      for (int i = 1; i <= size(); ++i) {
        tree[i] *= factor;
      }
    }

    void setAt(int i, long value) {
      incrementAt(i, value - getAt(i));
    }

    int size() {
      return tree.length - 1;
    }

    int smallestIndexOfCumulative(long value) {
      int index = size();
      int mask = index;
      int candidate = -1;
      while (index <= size() && mask != 0) {
        if (tree[index] == value) {
          candidate = index;
          index -= mask >>>= 1;
        } else if (tree[index] < value) {
          value -= tree[index];
          index += mask >>= 1;
        } else {
          index -= mask >>= 1;
        }
      }
      return candidate;
    }

    public String toString() {
      StringBuilder sb = new StringBuilder(tree.length * 2);
      for (int i = 1; i <= size(); ++i) {
        sb.append(tree[i] + " ");
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      return sb.toString();
    }
  }
}
