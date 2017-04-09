package ja.gauthier.competitiveprogramming;

import java.io.*;
import java.util.*;
import java.util.function.*;

public class TemplateMisc {
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
}
