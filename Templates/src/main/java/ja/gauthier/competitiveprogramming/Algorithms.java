package ja.gauthier.competitiveprogramming;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

/** Helper methods loosely inspired from C++'s algorithms library. */
public class Algorithms {

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
   * Checks whether two arrays have identical contents
   *
   * <p>Complexity: O(n)
   */
  static boolean isEqual(int[] a, int[] b) {
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
    return isEqual(aCopy, bCopy);
  }

  /**
   * Merges two sorted arrays
   *
   * <p>Complexity: O(t1.length + t2.length)
   */
  static int[] merge(int[] t1, int[] t2) {
    int[] merged = new int[t1.length + t2.length];
    int it1 = 0, it2 = 0, imerged = 0;

    while (imerged < merged.length) {
      if (it2 == t2.length || (it1 < t1.length && t1[it1] <= t2[it2])) {
        merged[imerged++] = t1[it1++];
      } else {
        merged[imerged++] = t2[it2++];
      }
    }

    return merged;
  }

  static int[] range(int toExclusive) {
    return range(0, toExclusive);
  }

  /**
   * Returns an array containing numbers from fromInclusive to toExclusive - 1
   *
   * <p>Complexity: O(toExclusive - fromInclusive)
   */
  static int[] range(int fromInclusive, int toExclusive) {
    int nItems = toExclusive - fromInclusive;
    int[] range = new int[nItems];
    for (int i = 0; i < nItems; ++i) {
      range[i] = i + fromInclusive;
    }
    return range;
  }

  /**
   * Reverses the contents of an array in-place
   *
   * <p>Complexity: O(elements.length)
   */
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
   * <p>Complexity: O(elements.length)
   */
  static void rotate(int[] elements, int offset) {
    int positiveOffset = offset >= 0 ? offset : elements.length + offset;
    reverse(elements);
    reverse(elements, 0, positiveOffset);
    reverse(elements, positiveOffset, elements.length);
  }

  /**
   * Swaps elements[i] and elements[j]
   *
   * <p>Complexity: O(1)
   */
  static void swap(int[] elements, int i, int j) {
    int temp = elements[i];
    elements[i] = elements[j];
    elements[j] = temp;
  }
}
