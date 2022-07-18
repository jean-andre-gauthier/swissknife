package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.Algorithms.*;
import static ja.gauthier.swissknife.IO.*;
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
public class AlgorithmsTest {
  @RunWith(Parameterized.class)
  public static class EqualTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {new int[] {}, new int[] {}, true},
            {new int[] {1}, new int[] {}, false},
            {new int[] {}, new int[] {1}, false},
            {new int[] {1}, new int[] {1}, true},
            {new int[] {1}, new int[] {2}, false},
            {new int[] {1, 2}, new int[] {2}, false},
            {new int[] {1}, new int[] {1, 2}, false},
            {new int[] {1, 2, 3}, new int[] {1, 2, 3}, true},
            {new int[] {1, 2, 3}, new int[] {1, 2, 4}, false}
          });
    }

    @Parameter(0)
    public static int[] a;

    @Parameter(1)
    public static int[] b;

    @Parameter(2)
    public static boolean expectedEqual;

    @Test
    public void test() {
      assertThat(isEqual(a, b), is(expectedEqual));
    }
  }

  @RunWith(Parameterized.class)
  public static class IsPermutationTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {new int[] {}, new int[] {}, true},
            {new int[] {1}, new int[] {}, false},
            {new int[] {}, new int[] {1}, false},
            {new int[] {1}, new int[] {1}, true},
            {new int[] {1}, new int[] {2}, false},
            {new int[] {1, 2}, new int[] {2}, false},
            {new int[] {1}, new int[] {1, 2}, false},
            {new int[] {1, 2}, new int[] {1, 2}, true},
            {new int[] {1, 2}, new int[] {2, 1}, true},
            {new int[] {1, 2}, new int[] {1, 1}, false},
            {new int[] {1, 2}, new int[] {2, 2}, false},
            {new int[] {1, 1}, new int[] {1, 1}, true},
            {new int[] {1, 1}, new int[] {1, 2}, false},
            {new int[] {1, 1}, new int[] {2, 1}, false},
            {new int[] {1, 1}, new int[] {2, 2}, false},
            {new int[] {1, 1}, new int[] {1, 1, 1}, false},
            {new int[] {1, 1, 1}, new int[] {1, 1}, false},
            {new int[] {1, 2, 2}, new int[] {1, 2, 2}, true},
            {new int[] {1, 2, 2}, new int[] {2, 1, 2}, true},
            {new int[] {1, 2, 2}, new int[] {2, 2, 1}, true},
            {new int[] {1, 1, 1}, new int[] {1, 1, 1, 1}, false},
            {new int[] {1, 1, 1, 1}, new int[] {1, 1, 1}, false},
            {new int[] {1, 2, 2, 2}, new int[] {1, 2, 2}, false},
            {new int[] {1, 2, 2}, new int[] {1, 2, 2, 2}, false},
            {new int[] {1, 2, 3}, new int[] {1, 2, 3}, true},
            {new int[] {1, 2, 3}, new int[] {2, 1, 3}, true},
            {new int[] {1, 2, 3}, new int[] {3, 1, 2}, true},
            {new int[] {1, 2, 3}, new int[] {1, 3, 2}, true},
            {new int[] {1, 2, 3}, new int[] {2, 3, 1}, true},
            {new int[] {1, 2, 3}, new int[] {3, 2, 1}, true},
            {new int[] {1, 2, 3}, new int[] {3, 2, 1}, true},
            {new int[] {1, 2, 3}, new int[] {1, 2, 4}, false},
            {new int[] {1, 1, 2, 2}, new int[] {1, 1, 2, 2}, true},
            {new int[] {1, 1, 2, 2}, new int[] {2, 2, 1, 1}, true},
          });
    }

    @Parameter(0)
    public static int[] a;

    @Parameter(1)
    public static int[] b;

    @Parameter(2)
    public static boolean expectedIsPermutation;

    @Test
    public void test() {
      assertThat(isPermutation(a, b), is(expectedIsPermutation));
    }
  }

  @RunWith(Parameterized.class)
  public static class MergeTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {new int[] {}, new int[] {}, new int[] {}},
            {new int[] {1}, new int[] {}, new int[] {1}},
            {new int[] {}, new int[] {1}, new int[] {1}},
            {new int[] {1, 2}, new int[] {}, new int[] {1, 2}},
            {new int[] {}, new int[] {1, 2}, new int[] {1, 2}},
            {new int[] {1}, new int[] {1}, new int[] {1, 1}},
            {new int[] {1, 1}, new int[] {1}, new int[] {1, 1, 1}},
            {new int[] {1}, new int[] {1, 1}, new int[] {1, 1, 1}},
            {new int[] {1}, new int[] {2}, new int[] {1, 2}},
            {new int[] {2}, new int[] {1}, new int[] {1, 2}},
            {new int[] {1, 2}, new int[] {3}, new int[] {1, 2, 3}},
            {new int[] {3}, new int[] {1, 2}, new int[] {1, 2, 3}},
            {new int[] {1, 3}, new int[] {2}, new int[] {1, 2, 3}},
            {new int[] {2}, new int[] {1, 3}, new int[] {1, 2, 3}},
            {new int[] {1, 2, 2}, new int[] {2}, new int[] {1, 2, 2, 2}},
            {new int[] {1, 2, 2}, new int[] {3}, new int[] {1, 2, 2, 3}},
            {new int[] {2}, new int[] {1, 2, 2}, new int[] {1, 2, 2, 2}},
            {new int[] {3}, new int[] {1, 2, 2}, new int[] {1, 2, 2, 3}},
            {new int[] {1, 3}, new int[] {2, 4}, new int[] {1, 2, 3, 4}},
            {new int[] {2, 4}, new int[] {1, 3}, new int[] {1, 2, 3, 4}},
            {new int[] {1, 4}, new int[] {2, 3}, new int[] {1, 2, 3, 4}},
            {new int[] {2, 3}, new int[] {1, 4}, new int[] {1, 2, 3, 4}},
            {new int[] {1, 2}, new int[] {3, 4}, new int[] {1, 2, 3, 4}},
            {new int[] {3, 4}, new int[] {1, 2}, new int[] {1, 2, 3, 4}},
            {new int[] {1, 2, 4}, new int[] {3, 5}, new int[] {1, 2, 3, 4, 5}},
            {new int[] {3, 5}, new int[] {1, 2, 4}, new int[] {1, 2, 3, 4, 5}},
            {new int[] {1, 2, 4}, new int[] {2, 4}, new int[] {1, 2, 2, 4, 4}},
            {new int[] {2, 4}, new int[] {1, 2, 4}, new int[] {1, 2, 2, 4, 4}}
          });
    }

    @Parameter(0)
    public static int[] t1;

    @Parameter(1)
    public static int[] t2;

    @Parameter(2)
    public static int[] expected;

    @Test
    public void mergeTest() {
      assertThat(isEqual(merge(t1, t2), expected), is(true));
    }
  }

  @RunWith(Parameterized.class)
  public static class RangeTest {
    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {{0, 0}, {0, 1}, {1, 1}, {1, 2}, {0, 5}, {1, 5}, {-1, 0}, {-1, 1}});
    }

    @Parameter(0)
    public static int from;

    @Parameter(1)
    public static int to;

    @Test
    public void rangeTestWithFrom() {
      int[] range = rangeInt(from, to);
      assertThat(range.length, is(to - from));
      for (int i = 0; i < range.length; ++i) {
        assertThat(range[i], is(i + from));
      }
    }

    @Test
    public void rangeTestWithoutFrom() {
      int[] range = rangeInt(to);
      assertThat(range.length, is(to));
      for (int i = 0; i < range.length; ++i) {
        assertThat(range[i], is(i));
      }
    }
  }

  @RunWith(Parameterized.class)
  public static class ReverseTest {

    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(new Object[][] {{0}, {1}, {2}, {3}, {4}, {5}, {10}, {11}});
    }

    @Parameter public static int size;

    @Test
    public void reverseTest() {
      int[] input = rangeInt(size);
      reverse(input);
      for (int i = 0; i < input.length; ++i) {
        assertThat(input[input.length - i - 1], is(i));
      }
    }
  }

  @RunWith(Parameterized.class)
  public static class RotateTest {

    @Parameters
    public static Collection<Object[]> parameters() {
      return Arrays.asList(
          new Object[][] {
            {0, 0}, {1, 0}, {1, 1}, {1, -1}, {2, 0}, {2, 1}, {2, 2}, {2, -1}, {2, -2}, {3, 0},
            {3, 1}, {3, 2}, {3, 3}, {3, -1}, {3, -2}, {3, -3}, {4, 0}, {4, 1}, {4, 3}, {4, 4},
            {4, -1}, {4, -3}, {4, -4}
          });
    }

    @Parameter(0)
    public static int size;

    @Parameter(1)
    public static int offset;

    @Test
    public void rotateTest() {
      int[] input = rangeInt(size);
      rotate(input, offset);
      for (int i = 0; i < input.length; ++i) {
        assertThat(input[((i + offset) % input.length + input.length) % input.length], is(i));
      }
    }
  }
}
