package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.Algorithms.*;
import static ja.gauthier.competitiveprogramming.IO.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class Permutations {

  /**
   * Heap's algorithm for generating permutations.
   *
   * <p>Complexity: O(n!)
   */
  static class PermutationsInt implements Iterable<int[]> {
    static class Iterator implements java.util.Iterator<int[]> {
      int[] array;
      boolean hasNext;
      int[] last;
      int[] is;
      int nElements;
      int[] ns;
      int stackTop;
      int[] ws;

      Iterator(int[] array) {
        nElements = array.length;

        this.array = new int[nElements];
        System.arraycopy(array, 0, this.array, 0, nElements);

        last = new int[nElements];
        System.arraycopy(array, 0, last, 0, nElements);
        if ((nElements & 1) == 0) {
          rotate(last, 1);
          if (nElements > 1) {
            swap(last, 1, nElements - 1);
          }
          if (nElements > 2) {
            swap(last, 0, nElements - 2);
          }
        } else {
          swap(last, 0, nElements - 1);
        }

        hasNext = nElements != 0;
        is = new int[nElements];
        ns = new int[nElements];
        if (nElements > 0) {
          ns[0] = nElements;
        }
        stackTop = 0;
        ws = new int[nElements];
      }

      public boolean hasNext() {
        return hasNext;
      }

      public int[] next() {
        while (nElements > 0 && stackTop >= 0) {
          switch (ws[stackTop]) {
            case 0:
              if (ns[stackTop] == 1) {
                --stackTop;
                hasNext = !equal(array, last);
                return Arrays.copyOf(array, array.length);
              }
              is[stackTop] = 0;
              ws[stackTop] = 1;
              break;
            case 1:
              if (is[stackTop] < ns[stackTop] - 1) {
                ns[stackTop + 1] = ns[stackTop] - 1;
                ws[stackTop] = 2;
                ws[++stackTop] = 0;
              } else {
                ws[stackTop] = 3;
              }
              break;
            case 2:
              if ((ns[stackTop] & 1) == 0) {
                swap(array, is[stackTop], ns[stackTop] - 1);
              } else {
                swap(array, 0, ns[stackTop] - 1);
              }
              ++is[stackTop];
              ws[stackTop] = 1;
              break;
            case 3:
              ns[stackTop + 1] = ns[stackTop] - 1;
              ws[stackTop] = 4;
              ws[++stackTop] = 0;
              break;
            case 4:
              --stackTop;
              break;
          }
        }
        throw new NoSuchElementException();
      }
    }

    int[] array;

    public PermutationsInt(int[] array) {
      this.array = array;
    }

    public Iterator iterator() {
      return new Iterator(array);
    }
  }
}
