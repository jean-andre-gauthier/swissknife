package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.Algorithms.*;
import static ja.gauthier.swissknife.IO.*;
import static ja.gauthier.swissknife.Permutations.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.lang.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import org.junit.*;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class PermutationsTest {
  @RunWith(Parameterized.class)
  public static class ImplicitPermutationsTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {{0, 0}, {1, 1}, {2, 2}, {3, 6}, {4, 24}, {5, 120}, {6, 720}, {7, 5040}});
    }

    @Parameter(0)
    public static int size;

    @Parameter(1)
    public static int expectedSize;

    @Test
    public void test() {
      int[] array = range(size);
      PermutationsInt permutations = new PermutationsInt(array);
      PermutationsInt.Iterator permutationsIterator = permutations.iterator();
      for (int i = 0; i < expectedSize; ++i) {
        assertThat(permutationsIterator.hasNext(), is(true));
        assertThat(isPermutation(permutationsIterator.next(), array), is(true));
      }
      assertThat(permutationsIterator.hasNext(), is(false));
    }
  }

  @RunWith(Parameterized.class)
  public static class ExplicitPermutationsTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {new int[] {1}, new int[][] {{1}}},
            {new int[] {1, 2}, new int[][] {{1, 2}, {2, 1}}},
            {
              new int[] {1, 2, 3},
              new int[][] {{1, 2, 3}, {2, 1, 3}, {3, 1, 2}, {1, 3, 2}, {2, 3, 1}, {3, 2, 1}}
            },
            {
              new int[] {1, 2, 3, 4},
              new int[][] {
                {1, 2, 3, 4},
                {2, 1, 3, 4},
                {3, 1, 2, 4},
                {1, 3, 2, 4},
                {2, 3, 1, 4},
                {3, 2, 1, 4},
                {4, 2, 1, 3},
                {2, 4, 1, 3},
                {1, 4, 2, 3},
                {4, 1, 2, 3},
                {2, 1, 4, 3},
                {1, 2, 4, 3},
                {1, 3, 4, 2},
                {3, 1, 4, 2},
                {4, 1, 3, 2},
                {1, 4, 3, 2},
                {3, 4, 1, 2},
                {4, 3, 1, 2},
                {4, 3, 2, 1},
                {3, 4, 2, 1},
                {2, 4, 3, 1},
                {4, 2, 3, 1},
                {3, 2, 4, 1},
                {2, 3, 4, 1}
              }
            }
          });
    }

    @Parameter(0)
    public static int[] initialPermutation;

    @Parameter(1)
    public static int[][] expectedPermutations;

    @Test
    public void test() {
      PermutationsInt permutations = new PermutationsInt(initialPermutation);
      PermutationsInt.Iterator permutationsIterator = permutations.iterator();
      assertThat(permutationsIterator, is(notNullValue()));

      for (int[] expectedPermutation : expectedPermutations) {
        assertThat(permutationsIterator.hasNext(), is(true));
        int[] actualPermutation = permutationsIterator.next();
        assertThat(isEqual(actualPermutation, expectedPermutation), is(true));
      }
      assertThat(permutationsIterator.hasNext(), is(false));
    }
  }
}
