import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class E {
  public static void main(String[] args) {
    int n = in.nextInt();
    int k = in.nextInt();
    int[][] xrf = new int[n][];
    for (int i = 0; i < n; ++i) {
      xrf[i] = in.nextInts(3);
    }
    AANodeInt[] temp = new AANodeInt[AANodeInt.MAX_NODE_LEVEL + 1];
    // out.println(n + " " + k);
    Arrays.sort(xrf, (xrfi1, xrfi2) -> Integer.compare(xrfi1[1], xrfi2[1]));
    // out.println(xrf);
    Map<Integer, AANodeInt> m = new HashMap<>();
    for (int i = 0; i < n; ++i) {
      AANodeInt ftree = m.get(xrf[i][2]);
      if (ftree == null) {
        m.put(xrf[i][2], aaMakeLeaf(xrf[i][0]));
      } else {
        m.put(xrf[i][2], aaInsert(ftree, xrf[i][0], temp));
      }
    }
    long bad = 0;
    for (int i = 0; i < n; ++i) {
      for (int fi = xrf[i][2] - k; fi <= xrf[i][2] + k; ++fi) {
        AANodeInt ftree = m.get(fi);
        if (ftree != null) {
          bad += aaRank(ftree, xrf[i][0] + xrf[i][1]) - aaRank(ftree, xrf[i][0] - xrf[i][1] - 1);
        }
        if (fi == xrf[i][2]) {
          --bad;
        }
      }
      /* out.println("bad: " + bad); */
      m.put(xrf[i][2], aaDelete(m.get(xrf[i][2]), xrf[i][0], temp));
    }
    out.println(bad);
  }

  static class AANodeInt {
    static int MAX_NODE_LEVEL = 31;
    static AANodeInt nil = new AANodeInt();

    AANodeInt[] children;
    int level;
    int size;
    int value;

    AANodeInt() {
      this.children = new AANodeInt[] {this, this};
      level = 0;
      size = 0;
      value = 0;
    }

    AANodeInt(int value) {
      this.children = new AANodeInt[] {nil, nil};
      this.level = 1;
      this.size = 1;
      this.value = value;
    }

    AANodeInt(AANodeInt left, AANodeInt right, int level, int size, int value) {
      this.children = new AANodeInt[] {left, right};
      this.level = level;
      this.size = size;
      this.value = value;
    }

    public String toString() {
      if (this == nil) {
        return "nil";
      } else {
        StringBuilder sb = new StringBuilder();
        AANodeInt[] nodes = aaInorder(this);
        for (int i = 0; i < nodes.length; ++i) {
          sb.append(nodes[i].level)
              .append(" ")
              .append(nodes[i].size)
              .append(" ")
              .append(nodes[i].value)
              .append(", ");
        }
        sb.delete(sb.length() - 2, sb.length() - 1);
        return sb.toString();
      }
    }
  }

  /**
   * Returns true if the tree contains k.
   *
   * <p>Complexity: O(log(n))
   */
  static boolean aaContains(AANodeInt t, int k) {
    return aaFind(t, k) != AANodeInt.nil;
  }

  /**
   * Deletes k in the tree, if such a value exists.
   *
   * <p>Complexity: O(log(n))
   */
  static AANodeInt aaDelete(AANodeInt root, int k, AANodeInt[] ns) {
    if (root == AANodeInt.nil) {
      return root;
    }

    AANodeInt n = root;
    int nsTop = 0;

    while (n.value != k) {
      ns[nsTop++] = n;
      if (n.value > k && n.children[0] != AANodeInt.nil) {
        n = n.children[0];
      } else if (n.value < k && n.children[1] != AANodeInt.nil) {
        n = n.children[1];
      } else {
        break;
      }
    }

    if (n.value != k) {
      return root;
    }

    if (n.children[0] == AANodeInt.nil || n.children[1] == AANodeInt.nil) {
      int nside = n.children[1] == AANodeInt.nil ? 0 : 1;
      if (nsTop > 0) {
        ns[nsTop - 1].children[ns[nsTop - 1].children[0] == n ? 0 : 1] = n.children[nside];
      } else {
        root = n.children[nside];
      }
    } else {
      ns[nsTop++] = n;
      AANodeInt nn = n.children[1];
      while (nn.children[0] != AANodeInt.nil) {
        ns[nsTop++] = nn;
        nn = nn.children[0];
      }
      n.value = nn.value;
      ns[nsTop - 1].children[ns[nsTop - 1].children[0] == nn ? 0 : 1] = nn.children[1];
    }

    while (--nsTop >= 0) {
      AANodeInt nTop = ns[nsTop];
      --ns[nsTop].size;

      if (ns[nsTop].children[0].level < ns[nsTop].level - 1
          || ns[nsTop].children[1].level < ns[nsTop].level - 1) {
        if (ns[nsTop].children[1].level > --ns[nsTop].level) {
          --ns[nsTop].children[1].level;
        }

        ns[nsTop] = aaSkew(ns[nsTop]);
        ns[nsTop].children[1] = aaSkew(ns[nsTop].children[1]);
        ns[nsTop].children[1].children[1] = aaSkew(ns[nsTop].children[1].children[1]);
        ns[nsTop] = aaSplit(ns[nsTop]);
        ns[nsTop].children[1] = aaSplit(ns[nsTop].children[1]);
      }

      if (nsTop > 0) {
        ns[nsTop - 1].children[ns[nsTop - 1].children[0] == nTop ? 0 : 1] = ns[nsTop];
      } else {
        root = ns[nsTop];
      }
    }

    return root;
  }

  /**
   * Deletes k in the tree, if such a value exists.
   *
   * <p>Complexity: O(log(n))
   */
  static AANodeInt aaDelete(AANodeInt root, int k) {
    return aaDelete(root, k, new AANodeInt[AANodeInt.MAX_NODE_LEVEL + 1]);
  }

  static AANodeInt aaFind(AANodeInt t, int k) {
    while (t != AANodeInt.nil) {
      if (t.value == k) {
        return t;
      } else {
        t = t.children[t.value > k ? 0 : 1];
      }
    }
    return t;
  }

  // static AANodeInt[] aaDeleteMin(AANodeInt t) {
  //   return jsSplitMin(t);
  // }
  //
  // static AANodeInt[] aaDeleteMax(AANodeInt node) {
  //   return jsSplitMax(t);
  // }
  //
  // static AANodeInt aaFindMin(AANodeInt t) {
  //   while (t.children[0] != AANodeInt.nil) {
  //     t = t.children[0];
  //   }
  //   return t;
  // }
  //
  // static AANodeInt aaFindMax(AANodeInt node) {
  //   while (t.children[1] != AANodeInt.nil) {
  //     t = t.children[1];
  //   }
  //   return t;
  // }
  //
  // static AANodeInt aaDifference(AANodeInt t1, AANodeInt t2) {
  //   return null;
  // }

  /**
   * Performs a Morris inorder tree traversal on t.
   *
   * <p>Complexity: O(n)
   */
  static AANodeInt[] aaInorder(AANodeInt t) {
    int iInorder = 0;
    AANodeInt[] inorder = new AANodeInt[t.size];
    while (t != AANodeInt.nil) {
      AANodeInt previous = t.children[0];
      if (previous != AANodeInt.nil) {
        while (previous.children[1] != AANodeInt.nil && previous.children[1] != t) {
          previous = previous.children[1];
        }
        if (previous.children[1] == t) {
          inorder[iInorder++] = t;
          previous.children[1] = AANodeInt.nil;
          t = t.children[1];
        } else {
          previous.children[1] = t;
          t = t.children[0];
        }
      } else {
        inorder[iInorder++] = t;
        t = t.children[1];
      }
    }
    return inorder;
  }

  /**
   * Returns the tree obtained by inserting k into root (does not insert a duplicate).
   *
   * <p>Complexity: O(log(n))
   */
  static AANodeInt aaInsert(AANodeInt root, int k, AANodeInt[] ns) {
    if (root == AANodeInt.nil) {
      return aaMakeLeaf(k);
    }

    AANodeInt n = root;
    int nsTop = 0;
    while (n.value != k) {
      ns[nsTop++] = n;
      if (n.value > k && n.children[0] != AANodeInt.nil) {
        n = n.children[0];
      } else if (n.value < k && n.children[1] != AANodeInt.nil) {
        n = n.children[1];
      } else {
        break;
      }
    }

    if (n.value == k) {
      return root;
    }

    AANodeInt nn = aaMakeLeaf(k);
    ns[nsTop - 1].children[ns[nsTop - 1].value > k ? 0 : 1] = nn;
    while (--nsTop >= 0) {
      int nside = nsTop > 0 ? ns[nsTop - 1].children[0] == ns[nsTop] ? 0 : 1 : 0;
      ++ns[nsTop].size;
      ns[nsTop] = aaSkew(ns[nsTop]);
      ns[nsTop] = aaSplit(ns[nsTop]);
      if (nsTop > 0) {
        ns[nsTop - 1].children[nside] = ns[nsTop];
      }
    }

    return ns[0];
  }

  /**
   * Returns the tree obtained by inserting k into root (does not insert a duplicate).
   *
   * <p>Complexity: O(log(n))
   */
  static AANodeInt aaInsert(AANodeInt root, int k) {
    return aaInsert(root, k, new AANodeInt[AANodeInt.MAX_NODE_LEVEL + 1]);
  }

  // static AANodeInt aaIntersect(AANodeInt t1, AANodeInt t2) {
  //   return null;
  // }

  /**
   * Creates a leaf (without performing skews/splits).
   *
   * <p>Complexity: O(1)
   */
  static AANodeInt aaMakeLeaf(int value) {
    return new AANodeInt(value);
  }

  /**
   * Creates a node (without performing skews/splits).
   *
   * <p>Complexity: O(1)
   */
  static AANodeInt aaMakeNode(AANodeInt left, AANodeInt right, int level, int size, int value) {
    return new AANodeInt(left, right, level, size, value);
  }

  static int aaRank(AANodeInt root, int value) {
    int rank = 0;
    AANodeInt n = root;
    while (n != AANodeInt.nil) {
      if (n.value > value) {
        n = n.children[0];
      } else if (n.value < value) {
        rank += n.children[0].size + 1;
        n = n.children[1];
      } else {
        return rank + n.children[0].size + 1;
      }
    }
    return rank;
  }

  // static AANodeInt[] aaRemove(AANodeInt root, int fromInclusive, int toExclusive) {
  //   return AANodeInt.nil;
  // }
  //
  // static AANodeInt[] aaRetain(AANodeInt root, int fromInclusive, int toExclusive) {
  //   return AANodeInt.nil;
  // }

  static AANodeInt aaSelect(AANodeInt root, int i) {
    AANodeInt n = root;
    while (n != AANodeInt.nil) {
      if (n.children[0].size == i - 1) {
        return n;
      } else if (n.children[0].size > i - 1) {
        n = n.children[0];
      } else {
        i -= n.children[0].size + 1;
        n = n.children[1];
      }
    }
    throw new NoSuchElementException();
  }

  /**
   * Skew operation for AA trees (removes a left link)
   *
   * <p>Complexity: O(1)
   */
  static AANodeInt aaSkew(AANodeInt nodeY) {
    if (nodeY != AANodeInt.nil && nodeY.children[0].level == nodeY.level) {
      AANodeInt nodeX = nodeY.children[0];
      AANodeInt nodeA = nodeY.children[0].children[0];
      AANodeInt nodeB = nodeY.children[0].children[1];
      AANodeInt nodeC = nodeY.children[1];
      nodeX.size += nodeC.size + 1;
      nodeY.size -= nodeA.size + 1;
      nodeY.children[0].children[1] = nodeY;
      nodeY.children[0] = nodeB;
      return nodeX;
    } else {
      return nodeY;
    }
  }

  /**
   * Split operation for AA trees (removes two consecutive right links)
   *
   * <p>Complexity: O(1)
   */
  static AANodeInt aaSplit(AANodeInt nodeX) {
    AANodeInt nodeY = nodeX.children[1];
    AANodeInt nodeZ = nodeY.children[1];
    if (nodeX != AANodeInt.nil
        && nodeY != AANodeInt.nil
        && nodeZ != AANodeInt.nil
        && nodeX.level == nodeY.level
        && nodeY.level == nodeZ.level) {
      AANodeInt nodeA = nodeX.children[0];
      AANodeInt nodeB = nodeY.children[0];
      AANodeInt nodeC = nodeZ.children[0];
      AANodeInt nodeD = nodeZ.children[1];
      nodeX.size -= nodeC.size + nodeD.size + 2;
      nodeY.size += nodeA.size + 1;
      nodeX.children[1] = nodeB;
      nodeY.children[0] = nodeX;
      ++nodeY.level;
      return nodeY;
    } else {
      return nodeX;
    }
  }

  // static AANodeInt aaUnion(AANodeInt t1, AANodeInt t2) {
  //   int tsTop = 0;
  //   AANodeInt[][] ts = new AANodeInt[2 * (AANodeInt.MAX_NODE_LEVEL + 1)][2];
  //   do {
  //     if (
  //     AANodeInt[] t21t22 = jsSplit(t2, t1.value);
  //     ts[tsTop + 1][0] = t1.children[0];
  //     ts[tsTop + 1][1] = t11t12[0];
  //     ts[tsTop][0] = t1.children[1];
  //     ts[tsTop][1] = t11t12[1];
  //     tsTop += 2;
  //     t1 = ts[tsTop-1][0];
  //     t2 = ts[tsTop-1][1];
  //     --tsTop;
  //   } while (tsTop > 0);
  //   return null;
  // }

  // static int[] toPreorder(AANodeInt root) {
  //   int[] values = new int[root.size];
  //   int index = 0;
  //   AANodeInt currentNode = root;
  //   do {
  //     values[index++] = currentNode.value;
  //     if (currentNode.children[0] == AANodeInt.nil) {
  //       currentNode = currentNode.children[1];
  //     } else {
  //       AANodeInt pred = currentNode.children[0];
  //       while (pred.children[1] != AANodeInt.nil && pred.children[1] != currentNode) {
  //         pred = pred.children[1];
  //       }
  //
  //       if (pred.children[1] == AANodeInt.nil) {
  //         pred.children[1] = currentNode;
  //         currentNode = currentNode.children[0];
  //       } else {
  //         pred.children[1] = AANodeInt.nil;
  //         currentNode = currentNode.children[1];
  //       }
  //     }
  //   } while (currentNode != root);
  //   return values;
  // }
  static MyScanner in = new MyScanner();
  static MyPrintWriter out = new MyPrintWriter();
  static RandScanner rand = new RandScanner();

  static class MyPrintWriter {
    BufferedOutputStream bos;
    PrintWriter pw;

    MyPrintWriter() {
      this.bos = new BufferedOutputStream(System.out);
      this.pw = new PrintWriter(bos, true);
    }

    void print(String t) {
      pw.print(t);
    }

    void println() {
      pw.println("");
    }

    void println(String s) {
      pw.println(s);
    }

    void println(int t) {
      pw.println(t);
    }

    void println(long t) {
      pw.println(t);
    }

    void println(double t) {
      pw.println(t);
    }

    <T> void println(T t) {
      pw.println(t.toString());
    }

    void println(int[] ts) {
      println(ts, true);
    }

    void println(long[] ts) {
      println(ts, true);
    }

    void println(double[] ts) {
      println(ts, true);
    }

    <T> void println(T[] ts) {
      println(ts, true);
    }

    <T> void println(Collection<T> ts) {
      println(ts, true);
    }

    <T, U> void println(Map<T, U> ts) {
      println(ts, true);
    }

    void println(int[] ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (int t : ts) {
        sb.append(t);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    void println(long[] ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (long t : ts) {
        sb.append(t);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    void println(double[] ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (double t : ts) {
        sb.append(t);
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    <T> void println(T[] ts, boolean newline) {
      println(Arrays.asList(ts), newline);
    }

    <T> void println(Collection<T> ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (T t : ts) {
        sb.append(t.toString());
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    <T, U> void println(Map<T, U> ts, boolean newline) {
      StringBuilder sb = new StringBuilder();
      for (Map.Entry<T, U> t : ts.entrySet()) {
        sb.append(t.getKey().toString());
        sb.append(" -> ");
        sb.append(t.getValue().toString());
        sb.append((newline ? "\n" : " "));
      }
      if (sb.length() > 0) {
        sb.deleteCharAt(sb.length() - 1);
      }
      pw.println(sb.toString());
    }

    void println(int[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (int[] ts : tss) {
        for (int t : ts) {
          sb.append(t).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }

    void println(long[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (long[] ts : tss) {
        for (long t : ts) {
          sb.append(t).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }

    void println(double[][] tss) {
      StringBuilder sb = new StringBuilder();
      for (double[] ts : tss) {
        for (double t : ts) {
          sb.append(t).append(" ");
        }
        if (ts.length > 0) {
          sb.setCharAt(sb.length() - 1, '\n');
        } else {
          sb.append('\n');
        }
      }
      pw.print(sb.toString());
      pw.flush();
    }
  }

  static class MyScanner {
    InputStreamReader is;
    BufferedReader br;
    StringTokenizer st;

    MyScanner() {
      this.is = new InputStreamReader(System.in);
      this.br = new BufferedReader(is);
    }

    void findToken() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    }

    String next() {
      findToken();
      return st.nextToken();
    }

    String nextLine() {
      String str = "";
      try {
        str = br.readLine();
      } catch (IOException e) {
        e.printStackTrace();
      }
      return str;
    }

    int nextInt() {
      return Integer.parseInt(next());
    }

    int[] nextInts(int n) {
      return nextInts(n, false);
    }

    int[] nextInts(int n, boolean oneBased) {
      if (oneBased) {
        return nextInts(n, 1, n + 1);
      } else {
        return nextInts(n, 0, n);
      }
    }

    int[] nextInts(int n, int iMin, int iMax) {
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt();
      }
      return ints;
    }

    long nextLong() {
      return Long.parseLong(next());
    }

    long[] nextLongs(int n) {
      return nextLongs(n, false);
    }

    long[] nextLongs(int n, boolean oneBased) {
      if (oneBased) {
        return nextLongs(n, 1, n + 1);
      } else {
        return nextLongs(n, 0, n);
      }
    }

    long[] nextLongs(int n, int iMin, int iMax) {
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong();
      }
      return longs;
    }

    double nextDouble() {
      return Double.parseDouble(next());
    }

    double[] nextDoubles(int n) {
      return nextDoubles(n, false);
    }

    double[] nextDoubles(int n, boolean oneBased) {
      if (oneBased) {
        return nextDoubles(n, 1, n + 1);
      } else {
        return nextDoubles(n, 0, n);
      }
    }

    double[] nextDoubles(int n, int iMin, int iMax) {
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble();
      }
      return doubles;
    }
  }

  static class RandScanner {
    ThreadLocalRandom r;

    RandScanner() {
      this.r = ThreadLocalRandom.current();
    }

    int nextInt() {
      return r.nextInt();
    }

    int nextInt(int upperBoundExclusive) {
      return r.nextInt(upperBoundExclusive);
    }

    int nextInt(int lowerBoundInclusive, int upperBoundExclusive) {
      return r.nextInt(lowerBoundInclusive, upperBoundExclusive);
    }

    int[] nextInts(int n) {
      return nextInts(n, false);
    }

    int[] nextInts(int n, int upperBoundExclusive) {
      return nextInts(n, false, upperBoundExclusive);
    }

    int[] nextInts(int n, int lowerBoundInclusive, int upperBoundExclusive) {
      return nextInts(n, false, lowerBoundInclusive, upperBoundExclusive);
    }

    int[] nextInts(int n, boolean oneBased) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt();
      }
      return ints;
    }

    int[] nextInts(int n, boolean oneBased, int upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt(upperBoundExclusive);
      }
      return ints;
    }

    int[] nextInts(int n, boolean oneBased, int lowerBoundInclusive, int upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      int[] ints = new int[iMax];
      for (int i = iMin; i < iMax; ++i) {
        ints[i] = nextInt(lowerBoundInclusive, upperBoundExclusive);
      }
      return ints;
    }

    long nextLong() {
      return r.nextLong();
    }

    long nextLong(long upperBoundExclusive) {
      return r.nextLong(upperBoundExclusive);
    }

    long nextLong(long lowerBoundInclusive, long upperBoundExclusive) {
      return r.nextLong(lowerBoundInclusive, upperBoundExclusive);
    }

    long[] nextLongs(int n) {
      return nextLongs(n, false);
    }

    long[] nextLongs(int n, long upperBoundExclusive) {
      return nextLongs(n, false, upperBoundExclusive);
    }

    long[] nextLongs(int n, long lowerBoundInclusive, long upperBoundExclusive) {
      return nextLongs(n, false, lowerBoundInclusive, upperBoundExclusive);
    }

    long[] nextLongs(int n, boolean oneBased) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong();
      }
      return longs;
    }

    long[] nextLongs(int n, boolean oneBased, long upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong(upperBoundExclusive);
      }
      return longs;
    }

    long[] nextLongs(int n, boolean oneBased, long lowerBoundInclusive, long upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      long[] longs = new long[iMax];
      for (int i = iMin; i < iMax; ++i) {
        longs[i] = nextLong(lowerBoundInclusive, upperBoundExclusive);
      }
      return longs;
    }

    double nextDouble() {
      return r.nextDouble();
    }

    double nextDouble(double upperBoundExclusive) {
      return r.nextDouble(upperBoundExclusive);
    }

    double nextDouble(double lowerBoundInclusive, double upperBoundExclusive) {
      return r.nextDouble(lowerBoundInclusive, upperBoundExclusive);
    }

    double[] nextDoubles(int n) {
      return nextDoubles(n, false);
    }

    double[] nextDoubles(int n, double upperBoundExclusive) {
      return nextDoubles(n, false, upperBoundExclusive);
    }

    double[] nextDoubles(int n, double lowerBoundInclusive, double upperBoundExclusive) {
      return nextDoubles(n, false, lowerBoundInclusive, upperBoundExclusive);
    }

    double[] nextDoubles(int n, boolean oneBased) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble();
      }
      return doubles;
    }

    double[] nextDoubles(int n, boolean oneBased, double upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble(upperBoundExclusive);
      }
      return doubles;
    }

    double[] nextDoubles(
        int n, boolean oneBased, double lowerBoundInclusive, double upperBoundExclusive) {
      int iMin = oneBased ? 1 : 0;
      int iMax = oneBased ? n + 1 : n;
      double[] doubles = new double[iMax];
      for (int i = iMin; i < iMax; ++i) {
        doubles[i] = nextDouble(lowerBoundInclusive, upperBoundExclusive);
      }
      return doubles;
    }
  }
}
