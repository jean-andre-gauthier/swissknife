package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.IO.*;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

public class AATree {
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
}
