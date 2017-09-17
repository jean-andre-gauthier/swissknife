package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.AATree.*;
import static ja.gauthier.competitiveprogramming.Algorithms.*;
import static ja.gauthier.competitiveprogramming.IO.*;
import static ja.gauthier.competitiveprogramming.IntervalTrees.*;
import static ja.gauthier.competitiveprogramming.Permutations.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.text.*;
import java.util.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class AATreeTest {
  /* @Test */
  /* public void increasingInsertionTest() { */
  /*   AANodeInt tree = AANodeInt.nil; */
  /*   assertThat(contains(tree, 0), is(false)); */
  /*   assertThat(occurrence(tree, 0), is(0)); */
  /*   assertThat(contains(tree, 1), is(false)); */
  /*   assertThat(occurrence(tree, 1), is(0)); */
  /*   for (int i = 0; i < 31; ++i) { */
  /*     tree = insert(tree, i); */
  /*     for (int j = 0; j <= i; ++j) { */
  /*       assertThat(contains(tree, j), is(true)); */
  /*       assertThat(occurrence(tree, j), is(1)); */
  /*     } */
  /*     assertThat(contains(tree, i + 1), is(false)); */
  /*     assertThat(occurrence(tree, i + 1), is(0)); */
  /*   } */
  /* } */
  public static class InorderTests {
    @Test
    public void inorder1Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      assertIncreasingValues(aaInorder(node0), 0, 1);
    }

    @Test
    public void inorder2Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 2, 1);
      assertIncreasingValues(aaInorder(node1), 0, 2);
    }

    @Test
    public void inorder3Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 2, 0);
      assertIncreasingValues(aaInorder(node0), 0, 2);
    }

    @Test
    public void inorder4Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node1 = aaMakeNode(node0, node2, 0, 3, 1);
      assertIncreasingValues(aaInorder(node1), 0, 3);
    }

    @Test
    public void inorder5Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 2, 1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 0, 3, 2);
      assertIncreasingValues(aaInorder(node2), 0, 3);
    }

    @Test
    public void inorder6Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 2, 0);
      AANodeInt node2 = aaMakeNode(node0, AANodeInt.nil, 0, 3, 2);
      assertIncreasingValues(aaInorder(node2), 0, 3);
    }

    @Test
    public void inorder7Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 0, 2, 2);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node2, 0, 3, 0);
      assertIncreasingValues(aaInorder(node0), 0, 3);
    }

    @Test
    public void inorder8Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 0, 2, 1);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 3, 0);
      assertIncreasingValues(aaInorder(node0), 0, 3);
    }

    @Test
    public void inorder9Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 2, 1);
      AANodeInt node2 = aaMakeNode(node1, node3, 0, 4, 2);
      assertIncreasingValues(aaInorder(node2), 0, 4);
    }

    @Test
    public void inorder10Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 2, 0);
      AANodeInt node2 = aaMakeNode(node0, node3, 0, 4, 2);
      assertIncreasingValues(aaInorder(node2), 0, 4);
    }

    @Test
    public void inorder11Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node3 = aaMakeNode(node2, AANodeInt.nil, 0, 2, 3);
      AANodeInt node1 = aaMakeNode(node0, node3, 0, 4, 1);
      assertIncreasingValues(aaInorder(node1), 0, 4);
    }

    @Test
    public void inorder12Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node3, 0, 2, 2);
      AANodeInt node1 = aaMakeNode(node0, node2, 0, 4, 1);
      assertIncreasingValues(aaInorder(node1), 0, 4);
    }

    @Test
    public void inorder13Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node1 = aaMakeNode(node0, node2, 0, 3, 1);
      AANodeInt node3 = aaMakeNode(node1, node4, 0, 5, 3);
      assertIncreasingValues(aaInorder(node3), 0, 5);
    }

    @Test
    public void inorder14Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 2, 1);
      AANodeInt node4 = aaMakeNode(node3, AANodeInt.nil, 0, 2, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 0, 5, 2);
      assertIncreasingValues(aaInorder(node2), 0, 5);
    }

    @Test
    public void inorder15Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 2, 1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 0, 2, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 0, 5, 2);
      assertIncreasingValues(aaInorder(node2), 0, 5);
    }

    @Test
    public void inorder16Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 2, 0);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 0, 2, 3);
      AANodeInt node2 = aaMakeNode(node0, node3, 0, 5, 2);
      assertIncreasingValues(aaInorder(node2), 0, 5);
    }

    @Test
    public void inorder17Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node3 = aaMakeNode(node2, node4, 0, 3, 3);
      AANodeInt node1 = aaMakeNode(node0, node3, 0, 5, 1);
      assertIncreasingValues(aaInorder(node1), 0, 5);
    }

    @Test
    public void inorder18Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node1 = aaMakeNode(node0, node2, 0, 3, 1);
      AANodeInt node5 = aaMakeNode(node4, AANodeInt.nil, 0, 2, 5);
      AANodeInt node3 = aaMakeNode(node1, node5, 0, 6, 3);
      assertIncreasingValues(aaInorder(node3), 0, 6);
    }

    @Test
    public void inorder19Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node1 = aaMakeNode(node0, node2, 0, 3, 1);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, node5, 0, 2, 4);
      AANodeInt node3 = aaMakeNode(node1, node4, 0, 6, 3);
      assertIncreasingValues(aaInorder(node3), 0, 6);
    }

    @Test
    public void inorder20Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 2, 1);
      AANodeInt node4 = aaMakeNode(node3, node5, 0, 3, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 0, 6, 2);
      assertIncreasingValues(aaInorder(node2), 0, 6);
    }

    @Test
    public void inorder21Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 2, 0);
      AANodeInt node4 = aaMakeNode(node3, node5, 0, 3, 4);
      AANodeInt node2 = aaMakeNode(node0, node4, 0, 6, 2);
      assertIncreasingValues(aaInorder(node2), 0, 6);
    }

    @Test
    public void inorder22Test() {
      AANodeInt node0 = aaMakeLeaf(0);
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node6 = aaMakeLeaf(6);
      AANodeInt node1 = aaMakeNode(node0, node2, 0, 3, 1);
      AANodeInt node5 = aaMakeNode(node4, node6, 0, 3, 5);
      AANodeInt node3 = aaMakeNode(node1, node5, 0, 7, 3);
      assertIncreasingValues(aaInorder(node3), 0, 7);
    }
  }

  @RunWith(Parameterized.class)
  public static class InsertPermutationsTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      PermutationsInt permutationsIterable = new PermutationsInt(range(7));
      PermutationsInt.Iterator permutationsIterator = permutationsIterable.iterator();
      List<Object[]> permutations = new ArrayList<>();
      permutationsIterator.forEachRemaining(p -> permutations.add(new int[][] {p}));
      return permutations;
    }

    @Parameter public static int[] values;

    @Test
    public void test() {
      AANodeInt root = AANodeInt.nil;
      AANodeInt[] ns = new AANodeInt[AANodeInt.MAX_NODE_LEVEL];
      for (int i = 0; i < values.length; ++i) {
        assertThat(aaContains(root, values[i]), is(false));
        assertThat(root.size, is(i));
        root = aaInsert(root, values[i], ns);
        assertThat(aaContains(root, values[i]), is(true));
        assertThat(root.size, is(i + 1));
        assertAAInvariants(root);
      }
      for (int i = 0; i < values.length; ++i) {
        assertThat(aaRank(root, i), is(i + 1));
        assertThat(aaSelect(root, i + 1).value, is(i));
      }
      for (int i = 0; i < values.length; ++i) {
        assertThat(aaContains(root, values[i]), is(true));
        assertThat(root.size, is(values.length - i));
        root = aaDelete(root, values[i], ns);
        assertThat(aaContains(root, values[i]), is(false));
        assertThat(root.size, is(values.length - i - 1));
        assertAAInvariants(root);
      }
    }
  }

  @RunWith(Parameterized.class)
  public static class InsertRangeTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(new Object[][] {{1}, {2}, {4}, {8}, {16}, {32}, {64}, {128}});
    }

    @Parameter public static int size;

    @Test
    public void test() {
      AANodeInt root = AANodeInt.nil;
      AANodeInt[] ns = new AANodeInt[AANodeInt.MAX_NODE_LEVEL];
      for (int i = 0; i < size; ++i) {
        assertThat(aaContains(root, i), is(false));
        assertThat(root.size, is(i));
        root = aaInsert(root, i, ns);
        assertThat(aaContains(root, i), is(true));
        assertThat(root.size, is(i + 1));
        assertThat(aaRank(root, i), is(i + 1));
        assertThat(aaSelect(root, i + 1).value, is(i));
        assertAAInvariants(root);
      }
      for (int i = 0; i < size; ++i) {
        for (int j = 0; j < i; ++j) {
          assertThat(aaContains(root, j), is(false));
        }
        for (int j = i; j < size; ++j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaRank(root, j), is(j - i + 1));
          assertThat(aaSelect(root, j - i + 1).value, is(j));
        }
        assertThat(root.size, is(size - i));
        root = aaDelete(root, i, ns);
        for (int j = 0; j <= i; ++j) {
          assertThat(aaContains(root, j), is(false));
        }
        for (int j = i + 1; j < size; ++j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaRank(root, j), is(j - i));
          assertThat(aaSelect(root, j - i).value, is(j));
        }
        assertThat(root.size, is(size - i - 1));
        assertAAInvariants(root);
      }
    }
  }

  public static class MiscTests {
    @Test
    public void nilNodeTest() {
      AANodeInt node = AANodeInt.nil;
      assertNodeNil(node);
      assertThat(aaContains(node, 0), is(false));
      assertThat(aaFind(node, 0), is(AANodeInt.nil));
      assertThat(aaSkew(node), is(AANodeInt.nil));
      assertThat(aaSplit(node), is(AANodeInt.nil));

      AANodeInt[] inorder = aaInorder(node);
      assertThat(inorder.length, is(0));
    }

    @Test
    public void aaMakeLeafaaMakeNodeTest() {
      AANodeInt leafLeft = aaMakeLeaf(1);
      assertNodeLeaf(leafLeft, 1);
      AANodeInt leafRight = aaMakeLeaf(4);
      assertNodeLeaf(leafRight, 4);
      AANodeInt root = aaMakeNode(leafLeft, leafRight, 2, 3, 5);
      assertNode(root, leafLeft, leafRight, 2, 3, 5);
    }
  }

  public static class SkewTests {
    @Test
    public void skew01Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt root = aaSkew(node1);
      assertNodeLeaf(root, 1);
    }

    @Test
    public void skew02Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 1, 2, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, AANodeInt.nil, node2, 1, 2, 1);
      assertNodeLeaf(node2, 2);
    }

    @Test
    public void skew03Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 1, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 1, 2, 1);

      AANodeInt root = aaSkew(node1);
      assertNode(root, AANodeInt.nil, node2, 1, 2, 1);
      assertNodeLeaf(node2, 2);
    }

    @Test
    public void skew04Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 2, 2, 2);
      AANodeInt node3 = aaMakeNode(node2, AANodeInt.nil, 2, 3, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
    }

    @Test
    public void skew05Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 2, 1);
      AANodeInt node3 = aaMakeNode(node1, AANodeInt.nil, 2, 3, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, AANodeInt.nil, node3, 2, 3, 1);
      assertNode(node3, node2, AANodeInt.nil, 2, 2, 3);
      assertNodeLeaf(node2, 2);
    }

    @Test
    public void skew06Test() {
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 3, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, AANodeInt.nil, node2, 2, 3, 1);
      assertNode(node2, AANodeInt.nil, node3, 2, 2, 2);
      assertNodeLeaf(node3, 3);
    }

    @Test
    public void skew07Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 3, 2);
      AANodeInt node4 = aaMakeNode(node2, AANodeInt.nil, 2, 4, 4);

      AANodeInt root = aaSkew(node4);
      assertNode(root, node1, node4, 2, 4, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, AANodeInt.nil, 2, 2, 4);
      assertNodeLeaf(node3, 3);
    }

    @Test
    public void skew08Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 2, 2, 2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node3 = aaMakeNode(node2, node4, 2, 4, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, node1, node3, 2, 4, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, node4, 2, 2, 3);
      assertNodeLeaf(node4, 4);
    }

    @Test
    public void skew09Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 2, 1);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node3 = aaMakeNode(node1, node4, 2, 4, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, AANodeInt.nil, node3, 2, 4, 1);
      assertNode(node3, node2, node4, 2, 3, 3);
      assertNodeLeaf(node2, 2);
      assertNodeLeaf(node4, 4);
    }

    @Test
    public void skew10Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 3, 2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node2, node5, 2, 5, 4);

      AANodeInt root = aaSkew(node4);
      assertNode(root, node1, node4, 2, 5, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node5, 2, 3, 4);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void skew11Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 3, 2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node2, node5, 3, 5, 4);

      AANodeInt root = aaSkew(node4);
      assertNode(root, node2, node5, 3, 5, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void skew12Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 3, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 5, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, node1, node4, 2, 5, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node5, 2, 3, 4);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void skew13Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 3, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 3, 5, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, node1, node4, 3, 5, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node5, 2, 3, 4);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
    }
  }

  public static class SplitTests {
    @Test
    public void split01Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt root = aaSplit(node1);
      assertNodeLeaf(root, 1);
    }

    @Test
    public void split02Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 1, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node1, AANodeInt.nil, 1, 2, 2);
      assertNodeLeaf(node1, 1);
    }

    @Test
    public void split03Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 1, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 1, 2, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, AANodeInt.nil, node2, 1, 2, 1);
      assertNodeLeaf(node2, 2);
    }

    @Test
    public void split04Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 1, 2, 2);
      AANodeInt node3 = aaMakeNode(node2, AANodeInt.nil, 1, 3, 3);

      AANodeInt root = aaSplit(node3);
      assertNode(root, node2, AANodeInt.nil, 1, 3, 3);
      assertNode(node2, node1, AANodeInt.nil, 1, 2, 2);
      assertNodeLeaf(node1, 1);
    }

    @Test
    public void split05Test() {
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node3, 1, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 1, 3, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
    }

    @Test
    public void split06Test() {
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 2, 2, 3);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node3, 2, 3, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 4, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node3, 3, 4, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      assertNode(node3, AANodeInt.nil, node4, 2, 2, 3);
      assertNodeLeaf(node4, 4);
    }

    @Test
    public void split07Test() {
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node4 = aaMakeNode(node3, AANodeInt.nil, 2, 2, 4);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node4, 2, 3, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 4, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 4, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      assertNode(node4, node3, AANodeInt.nil, 2, 2, 4);
      assertNodeLeaf(node3, 3);
    }

    @Test
    public void split08Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 4);
      AANodeInt node3 = aaMakeNode(node2, node4, 2, 3, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 4, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 4, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 2, 1, 4);
      assertNodeLeaf(node2, 2);
    }

    @Test
    public void split09Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 4);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 2, 2, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 4, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node4, 3, 4, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 2, 1, 4);
    }

    @Test
    public void split10Test() {
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 3, 4);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node4, 2, 4, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 5, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 5, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      assertNode(node4, node3, node5, 2, 3, 4);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void split11Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, node5, 2, 2, 4);
      AANodeInt node3 = aaMakeNode(node2, node4, 2, 4, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 5, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 5, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1);
      assertNodeLeaf(node2, 2);
      assertNode(node4, AANodeInt.nil, node5, 2, 2, 4);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void split12Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, node5, 2, 2, 4);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 5, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node4, 3, 5, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, AANodeInt.nil, node5, 2, 2, 4);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void split13Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node5 = aaMakeNode(node4, AANodeInt.nil, 2, 2, 5);
      AANodeInt node3 = aaMakeNode(node2, node5, 2, 4, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 5, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node5, 3, 5, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1);
      assertNodeLeaf(node2, 2);
      assertNode(node5, node4, AANodeInt.nil, 2, 2, 5);
      assertNodeLeaf(node4, 4);
    }

    @Test
    public void split14Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node5 = aaMakeNode(node4, AANodeInt.nil, 2, 2, 5);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node5, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 5, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 5, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node5, node4, AANodeInt.nil, 2, 2, 5);
      assertNodeLeaf(node4, 4);
    }

    @Test
    public void split15Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 3, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 5, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 5, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 2, 1, 5);
    }

    @Test
    public void split16Test() {
      AANodeInt node2 = aaMakeLeaf(2);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node6 = aaMakeLeaf(6);
      AANodeInt node5 = aaMakeNode(node4, node6, 2, 3, 5);
      AANodeInt node3 = aaMakeNode(node2, node5, 2, 5, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 6, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node5, 3, 6, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1);
      assertNodeLeaf(node2, 2);
      assertNode(node5, node4, node6, 2, 3, 5);
      assertNodeLeaf(node4, 4);
      assertNodeLeaf(node6, 6);
    }

    @Test
    public void split17Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeLeaf(4);
      AANodeInt node6 = aaMakeLeaf(6);
      AANodeInt node5 = aaMakeNode(node4, node6, 2, 3, 5);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node5, 2, 4, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 6, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 6, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node5, node4, node6, 2, 3, 5);
      assertNodeLeaf(node4, 4);
      assertNodeLeaf(node6, 6);
    }

    @Test
    public void split18Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node6 = aaMakeLeaf(6);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, node6, 2, 2, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 6, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 6, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNode(node5, AANodeInt.nil, node6, 2, 2, 5);
      assertNodeLeaf(node6, 6);
    }

    @Test
    public void split19Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node6 = aaMakeNode(node5, AANodeInt.nil, 2, 2, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 6, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node6, 3, 6, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNode(node6, node5, AANodeInt.nil, 2, 2, 6);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void split20Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node5, node7, 2, 3, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 5, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 7, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node6, 3, 7, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNode(node6, node5, node7, 2, 3, 6);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }

    @Test
    public void split21Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node5, node7, 1, 3, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 5, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 7, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node1, node4, 2, 7, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node6, 2, 5, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
      assertNode(node6, node5, node7, 1, 3, 6);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }

    @Test
    public void split22Test() {
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node5, node7, 1, 3, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 5, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 7, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node1, node4, 2, 7, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      assertNode(node4, node3, node6, 2, 5, 4);
      assertNodeLeaf(node3, 3);
      assertNode(node6, node5, node7, 1, 3, 6);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }

    @Test
    public void split23Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 3, 2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node2, node5, 2, 5, 4);

      AANodeInt root = aaSplit(node4);
      assertNode(node4, node2, node5, 2, 5, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void split24Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 3, 2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node2, node5, 2, 5, 4);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node4, node7, 2, 7, 6);

      AANodeInt root = aaSplit(node6);
      assertNode(node6, node4, node7, 2, 7, 6);
      assertNode(node4, node2, node5, 2, 5, 4);
      assertNode(node2, node1, node3, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }
  }

  /* @Test */
  /* public void inorderIncreasingInsertionTest() { */
  /*   AANodeInt tree = AANodeInt.nil; */
  /*   insertIncreasingValues(tree, 0, 16); */
  /*   AANodeInt[] inorderSequence = aaInorder(tree); */
  /*   assertNoDuplicates(inorderSequence, 0, 16); */
  /*   assertPreorderNoDuplicates(inorderSequence, 0, 16); */
  /* } */

  private static void assertIncreasingValues(
      AANodeInt[] sequence, int fromInclusive, int toExclusive) {
    assertThat(sequence.length, is(toExclusive - fromInclusive));
    for (int i = fromInclusive; i < toExclusive; ++i) {
      assertThat(sequence[i].value, is(i));
    }
  }

  // private static void assertNoDuplicates(AANodeInt[] sequence, int fromInclusive, int toExclusive) {
  //   assertThat(sequence.length, is(toExclusive - fromInclusive));
  //   Arrays.sort(sequence, (i, j) -> Integer.compare(i.value, j.value));
  //
  //   for (int i = 0; i < sequence.length; ++i) {
  //     assertThat(sequence[i], is(fromInclusive++));
  //   }
  // }

  private static void assertNode(
      AANodeInt node, AANodeInt leftChild, AANodeInt rightChild, int level, int size, int value) {
    assertThat(node.level, is(level));
    assertThat(node.size, is(size));
    assertThat(node.value, is(value));
    assertThat(node.children.length, is(2));
    assertThat(node.children[0], is(leftChild));
    assertThat(node.children[1], is(rightChild));
  }

  private static void assertNodeLeaf(AANodeInt node, int value) {
    assertThat(node.level, is(1));
    assertThat(node.size, is(1));
    assertThat(node.value, is(value));
    assertThat(node.children.length, is(2));
    assertThat(node.children[0], is(AANodeInt.nil));
    assertThat(node.children[1], is(AANodeInt.nil));
  }

  private static void assertNodeNil(AANodeInt node) {
    assertThat(node, is(AANodeInt.nil));
    assertThat(node.level, is(0));
    assertThat(node.size, is(0));
    assertThat(node.children.length, is(2));
    assertThat(node.children[0], is(AANodeInt.nil));
    assertThat(node.children[1], is(AANodeInt.nil));
  }

  private static void assertAAInvariants(AANodeInt root) {
    AANodeInt[] inorderNodes = aaInorder(root);
    for (AANodeInt node : inorderNodes) {
      if (node.children[0] == AANodeInt.nil && node.children[1] == AANodeInt.nil) {
        assertThat(node.level, is(1));
      }
      assertThat(node.children[0].level, is(node.level - 1));
      assertThat(
          node.children[1].level,
          allOf(greaterThanOrEqualTo(node.level - 1), lessThanOrEqualTo(node.level)));
      assertThat(node.children[1].children[1].level, is(lessThan(node.level)));
      if (node.level > 1) {
        assertThat(node.children[0], is(not(AANodeInt.nil)));
        assertThat(node.children[1], is(not(AANodeInt.nil)));
      }
    }
  }

  // private static void assertPreorderNoDuplicates(
  //     AANodeInt[] sequence, int fromInclusive, int toExclusive) {
  //   int lowerBound = fromInclusive;
  //   int upperBound = toExclusive;
  //   int i = 0;
  //   for (AANodeInt item : sequence) {
  //     assertThat(item, is(not(AANodeInt.nil)));
  //     assertThat(item.value, is(greaterThanOrEqualTo(lowerBound)));
  //     assertThat(item.value, is(lessThan(upperBound)));
  //     while (i > 0 && item.value > sequence[i - 1].value) {
  //       lowerBound = sequence[--i].value;
  //     }
  //     sequence[i++] = item;
  //   }
  // }
}
