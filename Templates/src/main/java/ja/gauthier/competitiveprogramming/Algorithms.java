package ja.gauthier.competitiveprogramming;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class Algorithms {

  static boolean equal(int[] a, int[] b) {
    if (a.length != b.length) {
      return false;
    }
    for (int i = 0; i < a.length; ++i) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    return true;
  }

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
   * Checks whether an array is a permutation of another
   *
   * <p>Complexity: O(nlog(n)) - O(n)
   *
   * <p>TODO Replace implementation by one that uses a HashSet with a primitive int
   */
  static boolean isPermutation(int[] a, int[] b) {
    int[] aCopy = Arrays.copyOf(a, a.length);
    int[] bCopy = Arrays.copyOf(b, b.length);
    Arrays.sort(aCopy);
    Arrays.sort(bCopy);
    return equal(aCopy, bCopy);
  }

  static int[] range(int toExclusive) {
    return range(0, toExclusive);
  }

  static int[] range(int fromInclusive, int toExclusive) {
    int nItems = toExclusive - fromInclusive;
    int[] range = new int[nItems];
    for (int i = 0; i < nItems; ++i) {
      range[i] = i + fromInclusive;
    }
    return range;
  }

  static void reverse(int[] elements) {
    reverse(elements, 0, elements.length);
  }

  static void reverse(int[] elements, int fromInclusive, int toExclusive) {
    int nElements = toExclusive - fromInclusive;
    for (int i = fromInclusive; i < fromInclusive + nElements / 2; ++i) {
      swap(elements, i, toExclusive - i + fromInclusive - 1);
    }
  }

  /**
   * Rotates elements in-place, by offset positions.
   *
   * <p>Assumption: -elements.length <= offset <= elements.length
   *
   * <p>Complexity: O(n)
   */
  static void rotate(int[] elements, int offset) {
    int positiveOffset = offset >= 0 ? offset : elements.length + offset;
    reverse(elements);
    reverse(elements, 0, positiveOffset);
    reverse(elements, positiveOffset, elements.length);
  }

  static void swap(int[] elements, int i, int j) {
    int temp = elements[i];
    elements[i] = elements[j];
    elements[j] = temp;
  }
}
