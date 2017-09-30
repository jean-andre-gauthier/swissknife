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
    int nNodes;
    int occurrence;
    int size;
    int value;

    AANodeInt() {
      children = new AANodeInt[] {this, this};
      level = 0;
      nNodes = 0;
      occurrence = 0;
      size = 0;
      value = 0;
    }

    AANodeInt(int value) {
      children = new AANodeInt[] {nil, nil};
      level = 1;
      nNodes = 1;
      occurrence = 1;
      size = 1;
      this.value = value;
    }

    AANodeInt(AANodeInt left, AANodeInt right, int level, int occurrence, int value) {
      children = new AANodeInt[] {left, right};
      this.level = level;
      nNodes = left.nNodes + right.nNodes + 1;
      this.occurrence = occurrence;
      size = left.size + right.size + occurrence;
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
              .append(nodes[i].nNodes)
              .append(" ")
              .append(nodes[i].occurrence)
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
   * Returns the unmodified root if occurrence(root, k) == 0, a tree without the node containing k
   * if occurence(root, k) == 1, a tree whose node containing k has had its occurrence decremented
   * by 1 otherwise.
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

    if (n.occurrence > 1) {
      --n.occurrence;
      --n.size;

      while (--nsTop >= 0) {
        --ns[nsTop].size;
      }

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
      --ns[nsTop].nNodes;
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
   * Returns the unmodified root if occurrence(root, k) == 0, a tree without the node containing k
   * if occurence(root, k) == 1, a tree whose node containing k has had its occurrence incremented
   * by 1 otherwise.
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

  /**
   * Calculates the set difference of t1 and t2. The occurrence of an element is the maximum between
   * 0 and the difference of its occurrences in t1 and t2. Destroys the trees contained in t1 and
   * t2.
   *
   * <p>Time complexity: O(m+n)
   *
   * <p>Space complexity: O(m+n) heap, O(log(m+n)) stack
   */
  static AANodeInt aaDifference(AANodeInt t1, AANodeInt t2) {
    int it1 = 0;
    AANodeInt[] t1Inorder = aaInorder(t1);
    int it2 = 0;
    AANodeInt[] t2Inorder = aaInorder(t2);
    int imerged = 0;
    AANodeInt[] merged = new AANodeInt[t1Inorder.length];

    while (it1 < t1Inorder.length) {
      if (it2 == t2Inorder.length || t1Inorder[it1].value < t2Inorder[it2].value) {
        merged[imerged++] = t1Inorder[it1++];
      } else if (t1Inorder[it1].value > t2Inorder[it2].value) {
        ++it2;
      } else {
        AANodeInt nodeMerged = t1Inorder[it1];

        int t1Occurrence = t1Inorder[it1++].occurrence;
        int t2Occurrence = t2Inorder[it2++].occurrence;
        int occurrence = Math.max(0, t1Occurrence - t2Occurrence);
        if (occurrence > 0) {
          nodeMerged.occurrence = occurrence;
          merged[imerged++] = nodeMerged;
        }
      }
    }

    return aaTreeFromSortedNodes(merged, 0, imerged);
  }

  /**
   * Performs a Morris inorder tree traversal on t.
   *
   * <p>Complexity: O(n)
   */
  static AANodeInt[] aaInorder(AANodeInt t) {
    int iInorder = 0;
    AANodeInt[] inorder = new AANodeInt[t.nNodes];
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
   * Returns the tree obtained by inserting k into root, or by incrementing the respective node's
   * occurrence if k is already in the tree.
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
      ++n.occurrence;
      ++n.size;

      while (--nsTop >= 0) {
        ++ns[nsTop].size;
      }

      return root;
    }

    AANodeInt nn = aaMakeLeaf(k);
    ns[nsTop - 1].children[ns[nsTop - 1].value > k ? 0 : 1] = nn;
    while (--nsTop >= 0) {
      int nside = nsTop > 0 ? ns[nsTop - 1].children[0] == ns[nsTop] ? 0 : 1 : 0;
      ++ns[nsTop].nNodes;
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

  /**
   * Merges t1 and t2. The occurrence of an element is the minimum of its occurrence in t1 and t2.
   * Destroys the trees contained in t1 and t2.
   *
   * <p>Time complexity: O(m+n)
   *
   * <p>Space complexity: O(m+n) heap, O(log(m+n)) stack
   */
  static AANodeInt aaIntersection(AANodeInt t1, AANodeInt t2) {
    int it1 = 0;
    AANodeInt[] t1Inorder = aaInorder(t1);
    int it2 = 0;
    AANodeInt[] t2Inorder = aaInorder(t2);
    int imerged = 0;
    AANodeInt[] merged = new AANodeInt[t1Inorder.length + t2Inorder.length];

    while (it1 < t1Inorder.length && it2 < t2Inorder.length) {
      if (t1Inorder[it1].value < t2Inorder[it2].value) {
        ++it1;
      } else if (t1Inorder[it1].value > t2Inorder[it2].value) {
        ++it2;
      } else {
        AANodeInt nodeMerged = t1Inorder[it1];

        int t1Occurrence = t1Inorder[it1++].occurrence;
        int t2Occurrence = t2Inorder[it2++].occurrence;
        nodeMerged.occurrence = Math.min(t1Occurrence, t2Occurrence);
        merged[imerged++] = nodeMerged;
      }
    }

    return aaTreeFromSortedNodes(merged, 0, imerged);
  }

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
  static AANodeInt aaMakeNode(
      AANodeInt left, AANodeInt right, int level, int occurrence, int value) {
    return new AANodeInt(left, right, level, occurrence, value);
  }

  /**
   * Returns the rank of value, i.e. 1 if |{e in root | e < value}| = 0, root.size+1 if |{e in root
   * | e < value}| = root.size, and the sum of the occurrences of {e in root | e < value} + 1
   * otherwise
   *
   * <p>Complexity: O(log(n))
   */
  static int aaRank(AANodeInt root, int value) {
    // if (root == AANodeInt.nil
    int rank = 1;
    AANodeInt n = root;
    while (n != AANodeInt.nil) {
      if (n.value > value) {
        if (n.children[0] == AANodeInt.nil) {
          return rank;
        }
        n = n.children[0];
      } else if (n.value < value) {
        rank += n.children[0].size + n.occurrence;
        if (n.children[1] == AANodeInt.nil) {
          return rank;
        }
        n = n.children[1];
      } else {
        return rank + n.children[0].size;
      }
    }
    return rank;
  }

  static int aaOccurrence(AANodeInt root, int k) {
    return aaFind(root, k).occurrence;
  }

  /**
   * Return the node whose rank is i
   *
   * <p>Complexity: O(log(n))
   */
  static AANodeInt aaSelect(AANodeInt root, int i) {
    AANodeInt n = root;
    while (n != AANodeInt.nil) {
      if (n.children[0].size < i && i <= n.children[0].size + n.occurrence) {
        return n;
      } else if (i <= n.children[0].size) {
        n = n.children[0];
      } else {
        i -= n.children[0].size + n.occurrence;
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
      nodeX.nNodes += nodeC.nNodes + 1;
      nodeY.nNodes -= nodeA.nNodes + 1;
      nodeX.size += nodeC.size + nodeY.occurrence;
      nodeY.size -= nodeA.size + nodeX.occurrence;
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
      nodeX.nNodes -= nodeC.nNodes + nodeD.nNodes + 2;
      nodeY.nNodes += nodeA.nNodes + 1;
      nodeX.size -= nodeC.size + nodeD.size + nodeY.occurrence + nodeZ.occurrence;
      nodeY.size += nodeA.size + nodeX.occurrence;
      nodeX.children[1] = nodeB;
      nodeY.children[0] = nodeX;
      ++nodeY.level;
      return nodeY;
    } else {
      return nodeX;
    }
  }

  /**
   * Converts a list of nodes that are sorted by values into an AA Tree (assumes that there are no
   * nodes with duplicate values).
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n) heap, O(log(n)) stack
   */
  static AANodeInt aaTreeFromSortedNodes(AANodeInt[] nodes, int from, int to) {
    if (to - from == 0) {
      return AANodeInt.nil;
    } else if (to - from == 1) {
      nodes[from].children[0] = AANodeInt.nil;
      nodes[from].children[1] = AANodeInt.nil;
      nodes[from].level = 1;
      nodes[from].nNodes = 1;
      nodes[from].size = nodes[from].occurrence;
      return nodes[from];
    }

    int mid = from + (to - from - 1) / 2;
    nodes[mid].children[0] = mid == from ? AANodeInt.nil : aaTreeFromSortedNodes(nodes, from, mid);
    nodes[mid].children[1] = aaTreeFromSortedNodes(nodes, mid + 1, to);
    nodes[mid].level =
        nodes[mid].children[1].level
            + (nodes[mid].children[0].level == nodes[mid].children[1].level ? 1 : 0);
    nodes[mid].nNodes = nodes[mid].children[0].nNodes + nodes[mid].children[1].nNodes + 1;
    nodes[mid].size =
        nodes[mid].children[0].size + nodes[mid].children[1].size + nodes[mid].occurrence;
    return nodes[mid];
  }

  /**
   * Creates an AA Tree with the provided values.
   *
   * <p>Time complexity: O(n * log(n))
   *
   * <p>Space complexity: O(n) heap
   */
  static AANodeInt aaTreeFromUnsortedValues(int... values) {
    AANodeInt root = AANodeInt.nil;
    AANodeInt[] ns = new AANodeInt[AANodeInt.MAX_NODE_LEVEL + 1];
    for (int value : values) {
      root = aaInsert(root, value, ns);
    }
    return root;
  }

  /**
   * Merges t1 and t2. The occurrence of an element is the maximum of its occurrence in t1 and t2.
   * Destroys the trees contained in t1 and t2.
   *
   * <p>Time complexity: O(m+n)
   *
   * <p>Space complexity: O(m+n) heap, O(log(m+n)) stack
   */
  static AANodeInt aaUnion(AANodeInt t1, AANodeInt t2) {
    int it1 = 0;
    AANodeInt[] t1Inorder = aaInorder(t1);
    int it2 = 0;
    AANodeInt[] t2Inorder = aaInorder(t2);
    int imerged = 0;
    AANodeInt[] merged = new AANodeInt[t1Inorder.length + t2Inorder.length];

    while (it1 + it2 < t1Inorder.length + t2Inorder.length) {
      if (it2 == t2Inorder.length
          || (it1 < t1Inorder.length && t1Inorder[it1].value < t2Inorder[it2].value)) {
        merged[imerged++] = t1Inorder[it1++];
      } else if (it1 == t1Inorder.length
          || (it2 < t2Inorder.length && t1Inorder[it1].value > t2Inorder[it2].value)) {
        merged[imerged++] = t2Inorder[it2++];
      } else {
        AANodeInt nodeMerged = t1Inorder[it1];

        int t1Occurrence = t1Inorder[it1++].occurrence;
        int t2Occurrence = t2Inorder[it2++].occurrence;

        nodeMerged.occurrence = Math.max(t1Occurrence, t2Occurrence);
        merged[imerged++] = nodeMerged;
      }
    }

    return aaTreeFromSortedNodes(merged, 0, imerged);
  }

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
