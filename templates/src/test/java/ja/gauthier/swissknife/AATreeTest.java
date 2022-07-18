package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.AATree.*;
import static ja.gauthier.swissknife.Algorithms.*;
import static ja.gauthier.swissknife.IO.*;
import static ja.gauthier.swissknife.IntervalTrees.*;
import static ja.gauthier.swissknife.Permutations.*;
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
  @RunWith(Parameterized.class)
  public static class DifferenceIntersectionUnionTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
            },
            {
              new int[] {0},
              new int[] {},
              new int[] {0},
              new int[] {1},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {1}
            },
            {
              new int[] {},
              new int[] {0},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {1}
            },
            {
              new int[] {0},
              new int[] {0},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {1},
              new int[] {0},
              new int[] {1}
            },
            {
              new int[] {0},
              new int[] {1},
              new int[] {0},
              new int[] {1},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {1, 1}
            },
            {
              new int[] {1},
              new int[] {0},
              new int[] {1},
              new int[] {1},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {1, 1}
            },
            {
              new int[] {0, 0},
              new int[] {},
              new int[] {0},
              new int[] {2},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {2}
            },
            {
              new int[] {},
              new int[] {0, 0},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {2}
            },
            {
              new int[] {0, 0},
              new int[] {0},
              new int[] {0},
              new int[] {1},
              new int[] {0},
              new int[] {1},
              new int[] {0},
              new int[] {2}
            },
            {
              new int[] {0},
              new int[] {0, 0},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {1},
              new int[] {0},
              new int[] {2}
            },
            {
              new int[] {0, 0},
              new int[] {0, 0},
              new int[] {},
              new int[] {},
              new int[] {0},
              new int[] {2},
              new int[] {0},
              new int[] {2}
            },
            {
              new int[] {0, 0},
              new int[] {1},
              new int[] {0},
              new int[] {2},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {2, 1}
            },
            {
              new int[] {1},
              new int[] {0, 0},
              new int[] {1},
              new int[] {1},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {2, 1}
            },
            {
              new int[] {0, 2},
              new int[] {1},
              new int[] {0, 2},
              new int[] {1, 1},
              new int[] {},
              new int[] {},
              new int[] {0, 1, 2},
              new int[] {1, 1, 1}
            },
            {
              new int[] {1},
              new int[] {0, 2},
              new int[] {1},
              new int[] {1},
              new int[] {},
              new int[] {},
              new int[] {0, 1, 2},
              new int[] {1, 1, 1}
            },
            {
              new int[] {0, 1},
              new int[] {1},
              new int[] {0},
              new int[] {1},
              new int[] {1},
              new int[] {1},
              new int[] {0, 1},
              new int[] {1, 1}
            },
            {
              new int[] {1, 0},
              new int[] {1},
              new int[] {0},
              new int[] {1},
              new int[] {1},
              new int[] {1},
              new int[] {0, 1},
              new int[] {1, 1}
            },
            {
              new int[] {1, 1},
              new int[] {0},
              new int[] {1},
              new int[] {2},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {1, 2}
            },
            {
              new int[] {0, 1},
              new int[] {0, 1},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {1, 1},
              new int[] {0, 1},
              new int[] {1, 1}
            },
            {
              new int[] {0, 0},
              new int[] {1, 1},
              new int[] {0},
              new int[] {2},
              new int[] {},
              new int[] {},
              new int[] {0, 1},
              new int[] {2, 2}
            },
            {
              new int[] {0, 1, 1, 1, 2, 2},
              new int[] {0, 1, 1, 2, 2, 2},
              new int[] {1},
              new int[] {1},
              new int[] {0, 1, 2},
              new int[] {1, 2, 2},
              new int[] {0, 1, 2},
              new int[] {1, 3, 3}
            },
            {
              new int[] {0, 1, 1, 2, 2, 2},
              new int[] {0, 1, 1, 1, 2, 2},
              new int[] {2},
              new int[] {1},
              new int[] {0, 1, 2},
              new int[] {1, 2, 2},
              new int[] {0, 1, 2},
              new int[] {1, 3, 3}
            },
            {
              new int[] {0, 0, 1, 1, 1, 2, 3, 3, 4},
              new int[] {1, 1, 1, 1, 2, 3},
              new int[] {0, 3, 4},
              new int[] {2, 1, 1},
              new int[] {1, 2, 3},
              new int[] {3, 1, 1},
              new int[] {0, 1, 2, 3, 4},
              new int[] {2, 4, 1, 2, 1}
            },
            {
              new int[] {1, 1, 1, 1, 2, 3},
              new int[] {0, 0, 1, 1, 1, 2, 3, 3, 4},
              new int[] {1},
              new int[] {1},
              new int[] {1, 2, 3},
              new int[] {3, 1, 1},
              new int[] {0, 1, 2, 3, 4},
              new int[] {2, 4, 1, 2, 1}
            },
          });
    }

    @Parameter(0)
    public static int[] t1Values;

    @Parameter(1)
    public static int[] t2Values;

    @Parameter(2)
    public static int[] expectedDifference;

    @Parameter(3)
    public static int[] expectedDifferenceOccurrences;

    @Parameter(4)
    public static int[] expectedIntersection;

    @Parameter(5)
    public static int[] expectedIntersectionOccurrences;

    @Parameter(6)
    public static int[] expectedUnion;

    @Parameter(7)
    public static int[] expectedUnionOccurrences;

    @Test
    public void testDifferenceIntersectionUnion() {
      AANodeInt differenceT1 = aaTreeFromUnsortedValues(t1Values);
      assertAAInvariants(differenceT1);
      AANodeInt differenceT2 = aaTreeFromUnsortedValues(t2Values);
      assertAAInvariants(differenceT2);
      AANodeInt difference = aaDifference(differenceT1, differenceT2);
      assertAAInvariants(difference);
      AANodeInt[] actualDifference = aaInorder(difference);
      assertThat(actualDifference.length, is(expectedDifference.length));
      for (int i = 0; i < expectedDifference.length; ++i) {
        assertThat(actualDifference[i].value, is(expectedDifference[i]));
        assertThat(actualDifference[i].occurrence, is(expectedDifferenceOccurrences[i]));
      }

      AANodeInt intersectionT1 = aaTreeFromUnsortedValues(t1Values);
      assertAAInvariants(intersectionT1);
      AANodeInt intersectionT2 = aaTreeFromUnsortedValues(t2Values);
      assertAAInvariants(intersectionT2);
      AANodeInt intersection = aaIntersection(intersectionT1, intersectionT2);
      assertAAInvariants(intersection);
      AANodeInt[] actualIntersection = aaInorder(intersection);
      assertThat(actualIntersection.length, is(expectedIntersection.length));
      for (int i = 0; i < expectedIntersection.length; ++i) {
        assertThat(actualIntersection[i].value, is(expectedIntersection[i]));
        assertThat(actualIntersection[i].occurrence, is(expectedIntersectionOccurrences[i]));
      }

      AANodeInt unionT1 = aaTreeFromUnsortedValues(t1Values);
      assertAAInvariants(unionT1);
      AANodeInt unionT2 = aaTreeFromUnsortedValues(t2Values);
      assertAAInvariants(unionT2);
      AANodeInt union = aaUnion(unionT1, unionT2);
      assertAAInvariants(union);
      AANodeInt[] actualUnion = aaInorder(union);
      assertThat(actualUnion.length, is(expectedUnion.length));
      for (int i = 0; i < expectedUnion.length; ++i) {
        assertThat(actualUnion[i].value, is(expectedUnion[i]));
        assertThat(actualUnion[i].occurrence, is(expectedUnionOccurrences[i]));
      }
    }
  }

  @RunWith(Parameterized.class)
  public static class InorderPostorderPreorderTests {
    @Parameters
    public static Collection<Object[]> parameters() {
      Object[][] parameters = new Object[22][4];
      {
        AANodeInt node0 = aaMakeLeaf(0);
        parameters[0][0] = node0;
        parameters[0][1] = rangeInt(1);
        parameters[0][2] = new int[] {0};
        parameters[0][3] = new int[] {0};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 1);
        parameters[1][0] = node1;
        parameters[1][1] = rangeInt(2);
        parameters[1][2] = new int[] {0, 1};
        parameters[1][3] = new int[] {1, 0};
      }
      {
        AANodeInt node1 = aaMakeLeaf(1);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 1, 0);
        parameters[2][0] = node0;
        parameters[2][1] = rangeInt(2);
        parameters[2][2] = new int[] {1, 0};
        parameters[2][3] = new int[] {0, 1};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node1 = aaMakeNode(node0, node2, 0, 1, 1);
        parameters[3][0] = node1;
        parameters[3][1] = rangeInt(3);
        parameters[3][2] = new int[] {0, 2, 1};
        parameters[3][3] = new int[] {1, 0, 2};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 1);
        AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 0, 1, 2);
        parameters[4][0] = node2;
        parameters[4][1] = rangeInt(3);
        parameters[4][2] = new int[] {0, 1, 2};
        parameters[4][3] = new int[] {2, 1, 0};
      }
      {
        AANodeInt node1 = aaMakeLeaf(1);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 1, 0);
        AANodeInt node2 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 2);
        parameters[5][0] = node2;
        parameters[5][1] = rangeInt(3);
        parameters[5][2] = new int[] {1, 0, 2};
        parameters[5][3] = new int[] {2, 0, 1};
      }
      {
        AANodeInt node1 = aaMakeLeaf(1);
        AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 0, 1, 2);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node2, 0, 1, 0);
        parameters[6][0] = node0;
        parameters[6][1] = rangeInt(3);
        parameters[6][2] = new int[] {1, 2, 0};
        parameters[6][3] = new int[] {0, 2, 1};
      }
      {
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 0, 1, 1);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 1, 0);
        parameters[7][0] = node0;
        parameters[7][1] = rangeInt(3);
        parameters[7][2] = new int[] {2, 1, 0};
        parameters[7][3] = new int[] {0, 1, 2};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node3 = aaMakeLeaf(3);
        AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 1);
        AANodeInt node2 = aaMakeNode(node1, node3, 0, 1, 2);
        parameters[8][0] = node2;
        parameters[8][1] = rangeInt(4);
        parameters[8][2] = new int[] {0, 1, 3, 2};
        parameters[8][3] = new int[] {2, 1, 0, 3};
      }
      {
        AANodeInt node1 = aaMakeLeaf(1);
        AANodeInt node3 = aaMakeLeaf(3);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 1, 0);
        AANodeInt node2 = aaMakeNode(node0, node3, 0, 1, 2);
        parameters[9][0] = node2;
        parameters[9][1] = rangeInt(4);
        parameters[9][2] = new int[] {1, 0, 3, 2};
        parameters[9][3] = new int[] {2, 0, 1, 3};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node3 = aaMakeNode(node2, AANodeInt.nil, 0, 1, 3);
        AANodeInt node1 = aaMakeNode(node0, node3, 0, 1, 1);
        parameters[10][0] = node1;
        parameters[10][1] = rangeInt(4);
        parameters[10][2] = new int[] {0, 2, 3, 1};
        parameters[10][3] = new int[] {1, 0, 3, 2};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node3 = aaMakeLeaf(3);
        AANodeInt node2 = aaMakeNode(AANodeInt.nil, node3, 0, 1, 2);
        AANodeInt node1 = aaMakeNode(node0, node2, 0, 1, 1);
        parameters[11][0] = node1;
        parameters[11][1] = rangeInt(4);
        parameters[11][2] = new int[] {0, 3, 2, 1};
        parameters[11][3] = new int[] {1, 0, 2, 3};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node4 = aaMakeLeaf(4);
        AANodeInt node1 = aaMakeNode(node0, node2, 0, 1, 1);
        AANodeInt node3 = aaMakeNode(node1, node4, 0, 1, 3);
        parameters[12][0] = node3;
        parameters[12][1] = rangeInt(5);
        parameters[12][2] = new int[] {0, 2, 1, 4, 3};
        parameters[12][3] = new int[] {3, 1, 0, 2, 4};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node3 = aaMakeLeaf(3);
        AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 1);
        AANodeInt node4 = aaMakeNode(node3, AANodeInt.nil, 0, 1, 4);
        AANodeInt node2 = aaMakeNode(node1, node4, 0, 1, 2);
        parameters[13][0] = node2;
        parameters[13][1] = rangeInt(5);
        parameters[13][2] = new int[] {0, 1, 3, 4, 2};
        parameters[13][3] = new int[] {2, 1, 0, 4, 3};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node4 = aaMakeLeaf(4);
        AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 1);
        AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 0, 1, 3);
        AANodeInt node2 = aaMakeNode(node1, node3, 0, 1, 2);
        parameters[14][0] = node2;
        parameters[14][1] = rangeInt(5);
        parameters[14][2] = new int[] {0, 1, 4, 3, 2};
        parameters[14][3] = new int[] {2, 1, 0, 3, 4};
      }
      {
        AANodeInt node1 = aaMakeLeaf(1);
        AANodeInt node4 = aaMakeLeaf(4);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 1, 0);
        AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 0, 1, 3);
        AANodeInt node2 = aaMakeNode(node0, node3, 0, 1, 2);
        parameters[15][0] = node2;
        parameters[15][1] = rangeInt(5);
        parameters[15][2] = new int[] {1, 0, 4, 3, 2};
        parameters[15][3] = new int[] {2, 0, 1, 3, 4};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node4 = aaMakeLeaf(4);
        AANodeInt node3 = aaMakeNode(node2, node4, 0, 1, 3);
        AANodeInt node1 = aaMakeNode(node0, node3, 0, 1, 1);
        parameters[16][0] = node1;
        parameters[16][1] = rangeInt(5);
        parameters[16][2] = new int[] {0, 2, 4, 3, 1};
        parameters[16][3] = new int[] {1, 0, 3, 2, 4};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node4 = aaMakeLeaf(4);
        AANodeInt node1 = aaMakeNode(node0, node2, 0, 1, 1);
        AANodeInt node5 = aaMakeNode(node4, AANodeInt.nil, 0, 1, 5);
        AANodeInt node3 = aaMakeNode(node1, node5, 0, 1, 3);
        parameters[17][0] = node3;
        parameters[17][1] = rangeInt(6);
        parameters[17][2] = new int[] {0, 2, 1, 4, 5, 3};
        parameters[17][3] = new int[] {3, 1, 0, 2, 5, 4};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node5 = aaMakeLeaf(5);
        AANodeInt node1 = aaMakeNode(node0, node2, 0, 1, 1);
        AANodeInt node4 = aaMakeNode(AANodeInt.nil, node5, 0, 1, 4);
        AANodeInt node3 = aaMakeNode(node1, node4, 0, 1, 3);
        parameters[18][0] = node3;
        parameters[18][1] = rangeInt(6);
        parameters[18][2] = new int[] {0, 2, 1, 5, 4, 3};
        parameters[18][3] = new int[] {3, 1, 0, 2, 4, 5};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node3 = aaMakeLeaf(3);
        AANodeInt node5 = aaMakeLeaf(5);
        AANodeInt node1 = aaMakeNode(node0, AANodeInt.nil, 0, 1, 1);
        AANodeInt node4 = aaMakeNode(node3, node5, 0, 1, 4);
        AANodeInt node2 = aaMakeNode(node1, node4, 0, 1, 2);
        parameters[19][0] = node2;
        parameters[19][1] = rangeInt(6);
        parameters[19][2] = new int[] {0, 1, 3, 5, 4, 2};
        parameters[19][3] = new int[] {2, 1, 0, 4, 3, 5};
      }
      {
        AANodeInt node1 = aaMakeLeaf(1);
        AANodeInt node3 = aaMakeLeaf(3);
        AANodeInt node5 = aaMakeLeaf(5);
        AANodeInt node0 = aaMakeNode(AANodeInt.nil, node1, 0, 1, 0);
        AANodeInt node4 = aaMakeNode(node3, node5, 0, 1, 4);
        AANodeInt node2 = aaMakeNode(node0, node4, 0, 1, 2);
        parameters[20][0] = node2;
        parameters[20][1] = rangeInt(6);
        parameters[20][2] = new int[] {1, 0, 3, 5, 4, 2};
        parameters[20][3] = new int[] {2, 0, 1, 4, 3, 5};
      }
      {
        AANodeInt node0 = aaMakeLeaf(0);
        AANodeInt node2 = aaMakeLeaf(2);
        AANodeInt node4 = aaMakeLeaf(4);
        AANodeInt node6 = aaMakeLeaf(6);
        AANodeInt node1 = aaMakeNode(node0, node2, 0, 1, 1);
        AANodeInt node5 = aaMakeNode(node4, node6, 0, 1, 5);
        AANodeInt node3 = aaMakeNode(node1, node5, 0, 1, 3);
        parameters[21][0] = node3;
        parameters[21][1] = rangeInt(7);
        parameters[21][2] = new int[] {0, 2, 1, 4, 6, 5, 3};
        parameters[21][3] = new int[] {3, 1, 0, 2, 5, 4, 6};
      }
      return Arrays.asList(parameters);
    }

    @Parameter(0)
    public static AANodeInt root;

    @Parameter(1)
    public static int[] expectedInorder;

    @Parameter(2)
    public static int[] expectedPostorder;

    @Parameter(3)
    public static int[] expectedPreorder;

    @Test
    public void inorderPreorderPostorderTest() {
      assertThat(
          isEqual(Arrays.stream(aaInorder(root)).mapToInt(n -> n.value).toArray(), expectedInorder),
          is(true));
      assertThat(
          isEqual(
              Arrays.stream(aaPostorder(root)).mapToInt(n -> n.value).toArray(), expectedPostorder),
          is(true));
      assertThat(
          isEqual(
              Arrays.stream(aaPreorder(root)).mapToInt(n -> n.value).toArray(), expectedPreorder),
          is(true));
    }
  }

  @RunWith(Parameterized.class)
  public static class InsertPermutationsTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      PermutationsInt permutationsIterable = new PermutationsInt(rangeInt(7));
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
    public void duplicatesTest() {
      AANodeInt root = AANodeInt.nil;
      AANodeInt[] ns = new AANodeInt[AANodeInt.MAX_NODE_LEVEL];
      for (int i = 1; i <= size; ++i) {
        for (int j = 1; j < i; ++j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaOccurrence(root, j), is(j));
          int rank = j * (j - 1) / 2 + 1;
          assertThat(aaRank(root, j), is(rank));
          AANodeInt selectedNode = aaSelect(root, rank);
          assertThat(selectedNode.level, is(greaterThan(0)));
          assertThat(selectedNode.nNodes, is(greaterThan(0)));
          assertThat(selectedNode.occurrence, is(j));
          assertThat(selectedNode.size, is(greaterThan(0)));
          assertThat(selectedNode.value, is(j));
        }
        for (int j = i; j <= size; ++j) {
          assertThat(aaContains(root, j), is(i > 1));
          assertThat(aaOccurrence(root, j), is(i - 1));
          root = aaInsert(root, j, ns);
          assertThat(aaContains(root, j), is(true));
          assertThat(aaOccurrence(root, j), is(i));
          int rank = i * (i + 1) / 2 + (j - i - 1) * i + 1;
          assertThat(aaRank(root, j), is(rank));
          AANodeInt selectedNode = aaSelect(root, rank);
          assertThat(selectedNode.level, is(greaterThan(0)));
          assertThat(selectedNode.nNodes, is(greaterThan(0)));
          assertThat(selectedNode.occurrence, is(i));
          assertThat(selectedNode.size, is(greaterThan(0)));
          assertThat(selectedNode.value, is(j));
        }
      }
      for (int i = size; i > 0; --i) {
        for (int j = size; j >= i; --j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaOccurrence(root, j), is(i));
          root = aaDelete(root, j, ns);
          assertThat(aaContains(root, j), is(i > 1));
          assertThat(aaOccurrence(root, j), is(i - 1));
          int rank = i * (i - 1) / 2 + (j - i) * i + 1;
          assertThat(aaRank(root, j), is(rank));
          if (i > 1) {
            AANodeInt selectedNode = aaSelect(root, rank);
            assertThat(selectedNode.level, is(greaterThan(0)));
            assertThat(selectedNode.nNodes, is(greaterThan(0)));
            assertThat(selectedNode.occurrence, is(i - 1));
            assertThat(selectedNode.size, is(greaterThan(0)));
            assertThat(selectedNode.value, is(j));
          }
        }
        for (int j = i - 1; j >= 1; --j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaOccurrence(root, j), is(j));
          int rank = j * (j - 1) / 2 + 1;
          assertThat(aaRank(root, j), is(rank));
          AANodeInt selectedNode = aaSelect(root, rank);
          assertThat(selectedNode.level, is(greaterThan(0)));
          assertThat(selectedNode.nNodes, is(greaterThan(0)));
          assertThat(selectedNode.occurrence, is(j));
          assertThat(selectedNode.size, is(greaterThan(0)));
          assertThat(selectedNode.value, is(j));
        }
      }
    }

    @Test
    public void noDuplicatesTest() {
      AANodeInt root = AANodeInt.nil;
      AANodeInt[] ns = new AANodeInt[AANodeInt.MAX_NODE_LEVEL];
      for (int i = 1; i <= size; ++i) {
        assertThat(aaContains(root, i), is(false));
        assertThat(aaOccurrence(root, i), is(0));
        assertThat(root.size, is(i - 1));
        root = aaInsert(root, i, ns);
        assertThat(aaContains(root, i), is(true));
        assertThat(aaOccurrence(root, i), is(1));
        assertThat(root.size, is(i));
        assertThat(aaRank(root, i), is(i));
        assertThat(aaSelect(root, i).value, is(i));
        assertAAInvariants(root);
      }
      for (int i = 1; i <= size; ++i) {
        for (int j = 1; j < i; ++j) {
          assertThat(aaContains(root, j), is(false));
          assertThat(aaOccurrence(root, j), is(0));
        }
        for (int j = i; j <= size; ++j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaOccurrence(root, j), is(1));
          assertThat(aaRank(root, j), is(j - i + 1));
          assertThat(aaSelect(root, j - i + 1).value, is(j));
        }
        assertThat(root.size, is(size - i + 1));
        root = aaDelete(root, i, ns);
        for (int j = 1; j <= i; ++j) {
          assertThat(aaContains(root, j), is(false));
          assertThat(aaOccurrence(root, j), is(0));
        }
        for (int j = i + 1; j <= size; ++j) {
          assertThat(aaContains(root, j), is(true));
          assertThat(aaOccurrence(root, j), is(1));
          assertThat(aaRank(root, j), is(j - i));
          assertThat(aaSelect(root, j - i).value, is(j));
        }
        assertThat(root.size, is(size - i));
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
      AANodeInt root = aaMakeNode(leafLeft, leafRight, 2, 6, 5);
      assertNode(root, leafLeft, leafRight, 2, 3, 6, 8, 5);
    }
  }

  @RunWith(Parameterized.class)
  public static class RankSelectTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {new int[] {}, new int[] {1}, new int[] {1}, new boolean[] {false}},
            {
              new int[] {0},
              new int[] {-1, 0, 1},
              new int[] {1, 1, 2},
              new boolean[] {false, true, false}
            },
            {
              new int[] {0, 1},
              new int[] {-1, 0, 1, 2},
              new int[] {1, 1, 2, 3},
              new boolean[] {false, true, true, false}
            },
            {
              new int[] {1, 0},
              new int[] {-1, 0, 1, 2},
              new int[] {1, 1, 2, 3},
              new boolean[] {false, true, true, false}
            },
            {
              new int[] {0, 2},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 2, 2, 3},
              new boolean[] {false, true, false, true, false}
            },
            {
              new int[] {2, 0},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 2, 2, 3},
              new boolean[] {false, true, false, true, false}
            },
            {
              new int[] {0, 1, 2},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 2, 3, 4},
              new boolean[] {false, true, true, true, false}
            },
            {
              new int[] {2, 1, 0},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 2, 3, 4},
              new boolean[] {false, true, true, true, false}
            },
            {
              new int[] {0, 0},
              new int[] {-1, 0, 1},
              new int[] {1, 1, 3},
              new boolean[] {false, true, false}
            },
            {
              new int[] {0, 0, 1, 1, 1},
              new int[] {-1, 0, 1, 2},
              new int[] {1, 1, 3, 6},
              new boolean[] {false, true, true, false}
            },
            {
              new int[] {1, 0, 1, 0, 1},
              new int[] {-1, 0, 1, 2},
              new int[] {1, 1, 3, 6},
              new boolean[] {false, true, true, false}
            },
            {
              new int[] {0, 0, 2, 2, 2},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 3, 3, 6},
              new boolean[] {false, true, false, true, false}
            },
            {
              new int[] {2, 0, 2, 0, 2},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 3, 3, 6},
              new boolean[] {false, true, false, true, false}
            },
            {
              new int[] {0, 0, 1, 2, 2, 2},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 3, 4, 7},
              new boolean[] {false, true, true, true, false}
            },
            {
              new int[] {2, 0, 2, 1, 2, 0},
              new int[] {-1, 0, 1, 2, 3},
              new int[] {1, 1, 3, 4, 7},
              new boolean[] {false, true, true, true, false}
            },
          });
    }

    @Parameter(0)
    public static int[] treeValues;

    @Parameter(1)
    public static int[] testValues;

    @Parameter(2)
    public static int[] expectedRanks;

    @Parameter(3)
    public static boolean[] expectedContains;

    @Test
    public void rankSelectTest() {
      AANodeInt root = aaTreeFromUnsortedValues(treeValues);
      for (int i = 0; i < testValues.length && i < expectedRanks.length; ++i) {
        int rank = aaRank(root, testValues[i]);
        assertThat(rank, is(expectedRanks[i]));
        boolean contains = aaContains(root, testValues[i]);
        assertThat(contains, is(expectedContains[i]));
        if (contains && treeValues.length > 0) {
          AANodeInt node = aaSelect(root, expectedRanks[i]);
          assertThat(node.value, is(testValues[i]));
        }
      }
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
      assertNode(root, AANodeInt.nil, node2, 1, 2, 1, 3, 1);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
    }

    @Test
    public void skew03Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 1, 1, 1);

      AANodeInt root = aaSkew(node1);
      assertNode(root, AANodeInt.nil, node2, 1, 2, 1, 3, 1);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
    }

    @Test
    public void skew04Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 2, 2, 2);
      AANodeInt node3 = aaMakeNode(node2, AANodeInt.nil, 2, 3, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, node1, node3, 2, 3, 2, 6, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 1, 1, 1, 1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 2, 1, 3, 3, 3);
    }

    @Test
    public void skew05Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 1, 1);
      AANodeInt node3 = aaMakeNode(node1, AANodeInt.nil, 2, 3, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, AANodeInt.nil, node3, 2, 3, 1, 6, 1);
      assertNode(node3, node2, AANodeInt.nil, 2, 2, 3, 5, 3);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
    }

    @Test
    public void skew06Test() {
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, AANodeInt.nil, node2, 2, 3, 1, 6, 1);
      assertNode(node2, AANodeInt.nil, node3, 2, 2, 2, 5, 2);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
    }

    @Test
    public void skew07Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);
      AANodeInt node4 = aaMakeNode(node2, AANodeInt.nil, 2, 4, 4);

      AANodeInt root = aaSkew(node4);
      assertNode(root, node1, node4, 2, 4, 2, 10, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, AANodeInt.nil, 2, 2, 4, 7, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
    }

    @Test
    public void skew08Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 2, 2, 2);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node3 = aaMakeNode(node2, node4, 2, 3, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, node1, node3, 2, 4, 2, 10, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, node4, 2, 2, 3, 7, 3);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
    }

    @Test
    public void skew09Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 1, 1);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node3 = aaMakeNode(node1, node4, 2, 3, 3);

      AANodeInt root = aaSkew(node3);
      assertNode(root, AANodeInt.nil, node3, 2, 4, 1, 10, 1);
      assertNode(node3, node2, node4, 2, 3, 3, 9, 3);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
    }

    @Test
    public void skew10Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(node2, node5, 2, 4, 4);

      AANodeInt root = aaSkew(node4);
      assertNode(root, node1, node4, 2, 5, 2, 15, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node5, 2, 3, 4, 12, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void skew11Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(node2, node5, 3, 4, 4);

      AANodeInt root = aaSkew(node4);
      assertNode(root, node2, node5, 3, 5, 4, 15, 4);
      assertNode(node2, node1, node3, 2, 3, 2, 6, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void skew12Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 2, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, node1, node4, 2, 5, 2, 15, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node5, 2, 3, 4, 12, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void skew13Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 3, 2, 2);

      AANodeInt root = aaSkew(node2);
      assertNode(root, node1, node4, 3, 5, 2, 15, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node5, 2, 3, 4, 12, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
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
      assertNode(root, node1, AANodeInt.nil, 1, 2, 2, 3, 2);
      assertNodeLeaf(node1, 1);
    }

    @Test
    public void split03Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 1, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, AANodeInt.nil, node2, 1, 2, 1, 3, 1);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
    }

    @Test
    public void split04Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node2 = aaMakeNode(node1, AANodeInt.nil, 1, 2, 2);
      AANodeInt node3 = aaMakeNode(node2, AANodeInt.nil, 1, 3, 3);

      AANodeInt root = aaSplit(node3);
      assertNode(root, node2, AANodeInt.nil, 1, 3, 3, 6, 3);
      assertNode(node2, node1, AANodeInt.nil, 1, 2, 2, 3, 2);
      assertNodeLeaf(node1, 1);
    }

    @Test
    public void split05Test() {
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node3, 1, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 1, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node3, 2, 3, 2, 6, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
    }

    @Test
    public void split06Test() {
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node3, 2, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node3, 3, 4, 2, 10, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1, 1, 1);
      assertNode(node3, AANodeInt.nil, node4, 2, 2, 3, 7, 3);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
    }

    @Test
    public void split07Test() {
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node4 = aaMakeNode(node3, AANodeInt.nil, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node4, 2, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 4, 2, 10, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1, 1, 1);
      assertNode(node4, node3, AANodeInt.nil, 2, 2, 4, 7, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
    }

    @Test
    public void split08Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 4, 4);
      AANodeInt node3 = aaMakeNode(node2, node4, 2, 3, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 4, 3, 10, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1, 3, 1);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 2, 1, 4, 4, 4);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
    }

    @Test
    public void split09Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 4, 4);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node4, 3, 4, 3, 10, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 2, 1, 4, 4, 4);
    }

    @Test
    public void split10Test() {
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, node4, 2, 2, 2);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node2, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 5, 2, 15, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1, 1, 1);
      assertNode(node4, node3, node5, 2, 3, 4, 12, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void split11Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, node5, 2, 4, 4);
      AANodeInt node3 = aaMakeNode(node2, node4, 2, 3, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node4, 3, 5, 3, 15, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1, 3, 1);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
      assertNode(node4, AANodeInt.nil, node5, 2, 2, 4, 9, 4);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void split12Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, node5, 2, 4, 4);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node4, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node4, 3, 5, 3, 15, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, AANodeInt.nil, node5, 2, 2, 4, 9, 4);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void split13Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node5 = aaMakeNode(node4, AANodeInt.nil, 2, 5, 5);
      AANodeInt node3 = aaMakeNode(node2, node5, 2, 3, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node5, 3, 5, 3, 15, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1, 3, 1);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
      assertNode(node5, node4, AANodeInt.nil, 2, 2, 5, 9, 5);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
    }

    @Test
    public void split14Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node5 = aaMakeNode(node4, AANodeInt.nil, 2, 5, 5);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node5, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 5, 3, 15, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node5, node4, AANodeInt.nil, 2, 2, 5, 9, 5);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
    }

    @Test
    public void split15Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 5, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 5, 4, 15, 4);
      assertNode(node2, node1, node3, 2, 3, 2, 6, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 2, 1, 5, 5, 5);
    }

    @Test
    public void split16Test() {
      AANodeInt node2 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 2, 2);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node6 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 6, 6);
      AANodeInt node5 = aaMakeNode(node4, node6, 2, 5, 5);
      AANodeInt node3 = aaMakeNode(node2, node5, 2, 3, 3);
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, node3, 2, 1, 1);

      AANodeInt root = aaSplit(node1);
      assertNode(root, node1, node5, 3, 6, 3, 21, 3);
      assertNode(node1, AANodeInt.nil, node2, 2, 2, 1, 3, 1);
      assertNode(node2, AANodeInt.nil, AANodeInt.nil, 1, 1, 2, 2, 2);
      assertNode(node5, node4, node6, 2, 3, 5, 15, 5);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
      assertNode(node6, AANodeInt.nil, AANodeInt.nil, 1, 1, 6, 6, 6);
    }

    @Test
    public void split17Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node4 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 4, 4);
      AANodeInt node6 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 6, 6);
      AANodeInt node5 = aaMakeNode(node4, node6, 2, 5, 5);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, node5, 2, 3, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 6, 3, 21, 3);
      assertNode(node2, node1, AANodeInt.nil, 2, 2, 2, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node5, node4, node6, 2, 3, 5, 15, 5);
      assertNode(node4, AANodeInt.nil, AANodeInt.nil, 1, 1, 4, 4, 4);
      assertNode(node6, AANodeInt.nil, AANodeInt.nil, 1, 1, 6, 6, 6);
    }

    @Test
    public void split18Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node6 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 6, 6);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, node6, 2, 5, 5);
      AANodeInt node4 = aaMakeNode(node3, node5, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node5, 3, 6, 4, 21, 4);
      assertNode(node2, node1, node3, 2, 3, 2, 6, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node5, AANodeInt.nil, node6, 2, 2, 5, 11, 5);
      assertNode(node6, AANodeInt.nil, AANodeInt.nil, 1, 1, 6, 6, 6);
    }

    @Test
    public void split19Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 3, 3);
      AANodeInt node5 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 1, 5, 5);
      AANodeInt node6 = aaMakeNode(node5, AANodeInt.nil, 2, 6, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node6, 3, 6, 4, 21, 4);
      assertNode(node2, node1, node3, 2, 3, 2, 6, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 1, 1, 3, 3, 3);
      assertNode(node6, node5, AANodeInt.nil, 2, 2, 6, 11, 6);
      assertNode(node5, AANodeInt.nil, AANodeInt.nil, 1, 1, 5, 5, 5);
    }

    @Test
    public void split20Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node5, node7, 2, 6, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 4, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 2, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node2, node6, 3, 7, 4, 16, 4);
      assertNode(node2, node1, node3, 2, 3, 2, 4, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNode(node6, node5, node7, 2, 3, 6, 8, 6);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }

    @Test
    public void split21Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node5, node7, 1, 1, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 1, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 1, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node1, node4, 2, 7, 1, 7, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node4, node3, node6, 2, 5, 1, 5, 4);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 2, 1, 1, 1, 3);
      assertNode(node6, node5, node7, 1, 3, 1, 3, 6);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }

    @Test
    public void split22Test() {
      AANodeInt node1 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node5, node7, 1, 1, 6);
      AANodeInt node4 = aaMakeNode(node3, node6, 2, 1, 4);
      AANodeInt node2 = aaMakeNode(node1, node4, 2, 1, 2);

      AANodeInt root = aaSplit(node2);
      assertNode(root, node1, node4, 2, 7, 1, 7, 2);
      assertNode(node1, AANodeInt.nil, AANodeInt.nil, 2, 1, 1, 1, 1);
      assertNode(node4, node3, node6, 2, 5, 1, 5, 4);
      assertNodeLeaf(node3, 3);
      assertNode(node6, node5, node7, 1, 3, 1, 3, 6);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }

    @Test
    public void split23Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeNode(AANodeInt.nil, AANodeInt.nil, 2, 1, 3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 1, 2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node2, node5, 2, 1, 4);

      AANodeInt root = aaSplit(node4);
      assertNode(node4, node2, node5, 2, 5, 1, 5, 4);
      assertNode(node2, node1, node3, 2, 3, 1, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNode(node3, AANodeInt.nil, AANodeInt.nil, 2, 1, 1, 1, 3);
      assertNodeLeaf(node5, 5);
    }

    @Test
    public void split24Test() {
      AANodeInt node1 = aaMakeLeaf(1);
      AANodeInt node3 = aaMakeLeaf(3);
      AANodeInt node2 = aaMakeNode(node1, node3, 2, 1, 2);
      AANodeInt node5 = aaMakeLeaf(5);
      AANodeInt node4 = aaMakeNode(node2, node5, 2, 1, 4);
      AANodeInt node7 = aaMakeLeaf(7);
      AANodeInt node6 = aaMakeNode(node4, node7, 2, 1, 6);

      AANodeInt root = aaSplit(node6);
      assertNode(node6, node4, node7, 2, 7, 1, 7, 6);
      assertNode(node4, node2, node5, 2, 5, 1, 5, 4);
      assertNode(node2, node1, node3, 2, 3, 1, 3, 2);
      assertNodeLeaf(node1, 1);
      assertNodeLeaf(node3, 3);
      assertNodeLeaf(node5, 5);
      assertNodeLeaf(node7, 7);
    }
  }

  private static void assertIncreasingValues(
      AANodeInt[] sequence, int fromInclusive, int toExclusive) {
    assertThat(sequence.length, is(toExclusive - fromInclusive));
    for (int i = fromInclusive; i < toExclusive; ++i) {
      assertThat(sequence[i].value, is(i));
      assertThat(sequence[i].occurrence, is(1));
    }
  }

  private static void assertNode(
      AANodeInt node,
      AANodeInt leftChild,
      AANodeInt rightChild,
      int level,
      int nNodes,
      int occurrence,
      int size,
      int value) {
    assertThat(node.level, is(level));
    assertThat(node.nNodes, is(nNodes));
    assertThat(node.occurrence, is(occurrence));
    assertThat(node.size, is(size));
    assertThat(node.value, is(value));
    assertThat(node.children.length, is(2));
    assertThat(node.children[0], is(leftChild));
    assertThat(node.children[1], is(rightChild));
  }

  private static void assertNodeLeaf(AANodeInt node, int value) {
    assertThat(node.level, is(1));
    assertThat(node.nNodes, is(1));
    assertThat(node.occurrence, is(1));
    assertThat(node.size, is(1));
    assertThat(node.value, is(value));
    assertThat(node.children.length, is(2));
    assertThat(node.children[0], is(AANodeInt.nil));
    assertThat(node.children[1], is(AANodeInt.nil));
  }

  private static void assertNodeNil(AANodeInt node) {
    assertThat(node, is(AANodeInt.nil));
    assertThat(node.level, is(0));
    assertThat(node.nNodes, is(0));
    assertThat(node.occurrence, is(0));
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
}
