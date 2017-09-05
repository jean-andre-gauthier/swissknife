package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.IO.*;
import static ja.gauthier.competitiveprogramming.IntervalTrees.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.text.*;
import java.util.*;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class BinaryIndexedTreeTest {

  public static class FixedTreeSizeTests {
    @Test
    public void getBetweenIncrementAtSetAtTest() {
      BinaryIndexedTreeLong tree = new BinaryIndexedTreeLong(16);
      assertIntervals(tree, 1, 17, 0, 0);

      tree.incrementAt(2, 1);
      assertIntervals(tree, 1, 2, 0, 0, 2, 17, 1, 1);

      tree.incrementAt(1, 2);
      assertIntervals(tree, 1, 2, 2, 2, 2, 17, 1, 3);

      tree.incrementAt(7, 4);
      assertIntervals(tree, 1, 2, 2, 2, 2, 7, 1, 3, 7, 17, 4, 7);

      tree.incrementAt(16, 1);
      assertIntervals(tree, 1, 2, 2, 2, 2, 7, 1, 3, 7, 16, 4, 7, 16, 17, 1, 8);

      tree.incrementAt(14, 5);
      assertIntervals(tree, 1, 2, 2, 2, 2, 7, 1, 3, 7, 14, 4, 7, 14, 16, 5, 12, 16, 17, 1, 13);

      tree.incrementAt(1, -1);
      assertIntervals(tree, 1, 2, 1, 1, 2, 7, 1, 2, 7, 14, 4, 6, 14, 16, 5, 11, 16, 17, 1, 12);

      tree.setAt(1, 11);
      assertIntervals(tree, 1, 2, 11, 11, 2, 7, 1, 12, 7, 14, 4, 16, 14, 16, 5, 21, 16, 17, 1, 22);

      tree.setAt(7, 14);
      assertIntervals(tree, 1, 2, 11, 11, 2, 7, 1, 12, 7, 14, 14, 26, 14, 16, 5, 31, 16, 17, 1, 32);
      assertLargestSmallestIndexOfCumulative(tree, 1, -1, -1, -1, 11, 1, 1, 1, 32, 16, 16, 16);

      tree.multiplyAll(2);
      assertIntervals(
          tree, 1, 2, 22, 22, 2, 7, 2, 24, 7, 14, 28, 52, 14, 16, 10, 62, 16, 17, 2, 64);
      assertLargestSmallestIndexOfCumulative(tree, 1, -1, -1, -1, 22, 1, 1, 1, 64, 16, 16, 16);

      tree.multiplyAll(3);
      assertIntervals(
          tree, 1, 2, 66, 66, 2, 7, 6, 72, 7, 14, 84, 156, 14, 16, 30, 186, 16, 17, 6, 192);
      assertLargestSmallestIndexOfCumulative(tree, 1, -1, -1, -1, 66, 1, 1, 1, 192, 16, 16, 16);

      tree.divideAll(2);
      assertIntervals(
          tree, 1, 2, 33, 33, 2, 7, 3, 36, 7, 14, 42, 78, 14, 16, 15, 93, 16, 17, 3, 96);
      assertLargestSmallestIndexOfCumulative(tree, 1, -1, -1, -1, 33, 1, 1, 1, 96, 16, 16, 16);

      tree.divideAll(3);
      assertIntervals(tree, 1, 2, 11, 11, 2, 7, 1, 12, 7, 14, 14, 26, 14, 16, 5, 31, 16, 17, 1, 32);
      assertLargestSmallestIndexOfCumulative(tree, 1, -1, -1, -1, 11, 1, 1, 1, 32, 16, 16, 16);
    }
  }

  @RunWith(Parameterized.class)
  public static class ParameterizedTreeSizeTests {

    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(new Object[][] {{1}, {2}, {4}, {8}, {16}, {32}, {64}, {128}});
    }

    @Parameter public int treeSize;

    @Test
    public void constantValuesTest() {
      BinaryIndexedTreeLong tree = new BinaryIndexedTreeLong(treeSize);
      assertIntervals(tree, 1, treeSize + 1, 0, 0);
      for (int i = 1; i <= tree.size(); ++i) {
        tree.setAt(i, 1);
      }
      for (int i = 1; i <= tree.size(); ++i) {
        assertIntervals(tree, i, i + 1, 1, i);
      }
      for (int i = 1; i <= tree.size(); ++i) {
        assertLargestSmallestIndexOfCumulative(tree, i, i, i, i);
      }
      assertLargestSmallestIndexOfCumulative(tree, 0, -1, -1, -1);
      assertLargestSmallestIndexOfCumulative(tree, tree.size() + 1, -1, -1, -1);
    }

    @Test
    public void decreasingValuesTest() {
      BinaryIndexedTreeLong tree = new BinaryIndexedTreeLong(treeSize);
      assertIntervals(tree, 1, treeSize + 1, 0, 0);
      for (int i = 1; i <= tree.size(); ++i) {
        for (int j = 1; j <= i; ++j) {
          tree.incrementAt(j, 1);
        }
      }
      for (int i = 1; i <= tree.size(); ++i) {
        assertIntervals(
            tree,
            i,
            i + 1,
            tree.size() + 1 - i,
            (tree.size() * (tree.size() + 1) - (tree.size() - i) * (tree.size() + 1 - i)) / 2);
      }
      assertLargestSmallestIndexOfCumulative(tree, 0, -1, -1, -1);
      assertLargestSmallestIndexOfCumulative(
          tree, tree.size() * (tree.size() + 1) / 2 + 1, -1, -1, -1);
    }

    @Test
    public void increasingValuesTest() {
      BinaryIndexedTreeLong tree = new BinaryIndexedTreeLong(treeSize);
      assertIntervals(tree, 1, treeSize + 1, 0, 0);
      for (int i = 1; i <= tree.size(); ++i) {
        tree.setAt(i, i);
      }
      for (int i = 1; i <= tree.size(); ++i) {
        int cumulativeValue = i * (i + 1) / 2;
        assertIntervals(tree, i, i + 1, i, cumulativeValue);
        assertLargestSmallestIndexOfCumulative(tree, cumulativeValue, i, i, i);
      }
      assertLargestSmallestIndexOfCumulative(tree, 0, -1, -1, -1);
      assertLargestSmallestIndexOfCumulative(
          tree, tree.size() * (tree.size() + 1) / 2 + 1, -1, -1, -1);
    }

    @Test
    public void largeGapsTest() {
      if (treeSize > 4) {
        BinaryIndexedTreeLong tree = new BinaryIndexedTreeLong(treeSize);
        assertIntervals(tree, 1, treeSize + 1, 0, 0);
        int gapStart = 2;
        int gapEnd = tree.size();
        tree.setAt(gapStart, 1);
        tree.setAt(gapEnd, 1);
        assertIntervals(
            tree,
            1,
            gapStart,
            0,
            0,
            gapStart,
            gapStart + 1,
            1,
            1,
            gapStart + 1,
            gapEnd - 1,
            0,
            1,
            gapEnd,
            gapEnd + 1,
            1,
            2);
        assertLargestSmallestIndexOfCumulative(tree, 1, treeSize / 2, gapStart, gapEnd - 1);
      }
    }

    @Test
    public void smallGapsTest() {
      if (treeSize > 4) {
        BinaryIndexedTreeLong tree = new BinaryIndexedTreeLong(treeSize);
        assertIntervals(tree, 1, treeSize + 1, 0, 0);
        for (int i = 1; i <= tree.size(); i += 2) {
          tree.setAt(i, 1);
          tree.setAt(i + 1, 0);
        }
        for (int i = 1; i <= tree.size(); i += 2) {
          assertIntervals(tree, i, i + 1, 1, (i + 1) / 2);
          assertIntervals(tree, i + 1, i + 2, 0, (i + 1) / 2);
          assertLargestSmallestIndexOfCumulative(tree, (i + 1) / 2, i + 1, i, i + 1);
        }
      }
    }
  }

  private static void assertIntervals(BinaryIndexedTreeLong tree, long... intervals) {
    for (int i = 0; i < intervals.length; i += 4) {
      int from = (int) intervals[i];
      int to = (int) intervals[i + 1];
      long value = intervals[i + 2];
      long cumulativeValue = intervals[i + 3];
      assertThat(tree.getAt(from), is(value));
      for (int j = from; j < to; ++j) {
        assertThat(tree.getBetween(j), is(cumulativeValue));
      }
    }
  }

  private static void assertLargestSmallestIndexOfCumulative(
      BinaryIndexedTreeLong tree, long... values) {
    for (int i = 0; i < values.length; i += 4) {
      long cumulativeValue = values[i];
      int indexOfCumulative = (int) values[i + 1];
      int smallestIndexOfCumulative = (int) values[i + 2];
      int largestIndexOfCumulative = (int) values[i + 3];
      assertThat(tree.indexOfCumulative(cumulativeValue), is(indexOfCumulative));
      assertThat(tree.largestIndexOfCumulative(cumulativeValue), is(largestIndexOfCumulative));
      assertThat(tree.smallestIndexOfCumulative(cumulativeValue), is(smallestIndexOfCumulative));
    }
  }
}
