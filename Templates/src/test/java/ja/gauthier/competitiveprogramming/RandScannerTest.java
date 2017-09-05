package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.IO.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.io.*;
import java.lang.*;
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
public class RandScannerTest {

  private static RandScanner randScanner;

  @BeforeClass
  public static void before() {
    randScanner = new RandScanner();
  }

  public static class SingleValueGenerationTests {
    @Test
    public void testInt() {
      assertThat(randScanner.nextInt(), is(greaterThanOrEqualTo(Integer.MIN_VALUE)));
      assertThat(randScanner.nextInt(1), is(0));
      assertThat(randScanner.nextInt(1, 2), is(1));
    }

    @Test
    public void testLong() {
      assertThat(randScanner.nextLong(), is(greaterThanOrEqualTo(Long.MIN_VALUE)));
      assertThat(randScanner.nextLong(1), is(0L));
      assertThat(randScanner.nextLong(1, 2), is(1L));
      assertThat(
          randScanner.nextLong(Integer.MAX_VALUE + 1L, Integer.MAX_VALUE + 2L),
          is(Integer.MAX_VALUE + 1L));
    }

    @Test
    public void testDouble() {
      assertThat(randScanner.nextDouble(), is(allOf(greaterThanOrEqualTo(0.0), lessThan(1.0))));
      assertThat(randScanner.nextDouble(0.5), is(allOf(greaterThanOrEqualTo(0.0), lessThan(0.5))));
      assertThat(randScanner.nextDouble(1, 2), is(allOf(greaterThanOrEqualTo(1.0), lessThan(2.0))));
    }
  }

  @RunWith(Parameterized.class)
  public static class MultipleValueGenerationTests {

    @Parameters
    public static Collection<Object[]> data() {
      return Arrays.asList(new Object[][] {{1}, {2}, {4}, {8}});
    }

    @Parameter public int n;

    @Test
    public void nextInts() {
      int[] nextInts = randScanner.nextInts(n);
      assertThat(nextInts.length, is(n));
      assertThat(Arrays.stream(nextInts).allMatch(value -> value >= Integer.MIN_VALUE), is(true));

      int[] nextIntsUpperExclusive = randScanner.nextInts(n, 1);
      assertThat(nextIntsUpperExclusive.length, is(n));
      assertThat(Arrays.stream(nextIntsUpperExclusive).allMatch(value -> value == 0), is(true));

      int[] nextIntsLowerInclusiveUpperExclusive = randScanner.nextInts(n, 1, 2);
      assertThat(nextIntsLowerInclusiveUpperExclusive.length, is(n));
      assertThat(
          Arrays.stream(nextIntsLowerInclusiveUpperExclusive).allMatch(value -> value == 1),
          is(true));

      int[] nextIntsOneBased = randScanner.nextInts(n, true);
      assertThat(nextIntsOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextIntsOneBased).skip(1).allMatch(value -> value >= Integer.MIN_VALUE),
          is(true));

      int[] nextIntsUpperExclusiveOneBased = randScanner.nextInts(n, true, 1);
      assertThat(nextIntsUpperExclusiveOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextIntsUpperExclusiveOneBased).skip(1).allMatch(value -> value == 0),
          is(true));

      int[] nextIntsLowerInclusiveUpperExclusiveOneBased = randScanner.nextInts(n, true, 1, 2);
      assertThat(nextIntsLowerInclusiveUpperExclusiveOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextIntsLowerInclusiveUpperExclusiveOneBased)
              .skip(1)
              .allMatch(value -> value == 1),
          is(true));
    }

    @Test
    public void nextLongs() {
      long[] nextLongs = randScanner.nextLongs(n);
      assertThat(nextLongs.length, is(n));
      assertThat(Arrays.stream(nextLongs).allMatch(value -> value >= Long.MIN_VALUE), is(true));

      long[] nextLongsUpperExclusive = randScanner.nextLongs(n, 1);
      assertThat(nextLongsUpperExclusive.length, is(n));
      assertThat(Arrays.stream(nextLongsUpperExclusive).allMatch(value -> value == 0), is(true));

      long[] nextLongsLowerInclusiveUpperExclusive = randScanner.nextLongs(n, 1, 2);
      assertThat(nextLongsLowerInclusiveUpperExclusive.length, is(n));
      assertThat(
          Arrays.stream(nextLongsLowerInclusiveUpperExclusive).allMatch(value -> value == 1),
          is(true));

      long[] nextLongsOneBased = randScanner.nextLongs(n, true);
      assertThat(nextLongsOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextLongsOneBased).skip(1).allMatch(value -> value >= Long.MIN_VALUE),
          is(true));

      long[] nextLongsUpperExclusiveOneBased = randScanner.nextLongs(n, true, 1);
      assertThat(nextLongsUpperExclusiveOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextLongsUpperExclusiveOneBased).skip(1).allMatch(value -> value == 0),
          is(true));

      long[] nextLongsLowerInclusiveUpperExclusiveOneBased = randScanner.nextLongs(n, true, 1, 2);
      assertThat(nextLongsLowerInclusiveUpperExclusiveOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextLongsLowerInclusiveUpperExclusiveOneBased)
              .skip(1)
              .allMatch(value -> value == 1),
          is(true));
    }

    @Test
    public void nextDoubles() {
      double[] nextDoubles = randScanner.nextDoubles(n);
      assertThat(nextDoubles.length, is(n));
      assertThat(Arrays.stream(nextDoubles).allMatch(value -> value >= 0 && value < 1), is(true));

      double[] nextDoublesUpperExclusive = randScanner.nextDoubles(n, 0.5);
      assertThat(nextDoublesUpperExclusive.length, is(n));
      assertThat(
          Arrays.stream(nextDoublesUpperExclusive).allMatch(value -> value >= 0 && value < 0.5),
          is(true));

      double[] nextDoublesLowerInclusiveUpperExclusive = randScanner.nextDoubles(n, 1, 2);
      assertThat(nextDoublesLowerInclusiveUpperExclusive.length, is(n));
      assertThat(
          Arrays.stream(nextDoublesLowerInclusiveUpperExclusive)
              .allMatch(value -> value >= 1 && value < 2),
          is(true));

      double[] nextDoublesOneBased = randScanner.nextDoubles(n, true);
      assertThat(nextDoublesOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextDoublesOneBased).skip(1).allMatch(value -> value >= 0 && value < 1),
          is(true));

      double[] nextDoublesUpperExclusiveOneBased = randScanner.nextDoubles(n, true, 0.5);
      assertThat(nextDoublesUpperExclusiveOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextDoublesUpperExclusiveOneBased)
              .skip(1)
              .allMatch(value -> value >= 0 && value < 0.5),
          is(true));

      double[] nextDoublesLowerInclusiveUpperExclusiveOneBased =
          randScanner.nextDoubles(n, true, 1, 2);
      assertThat(nextDoublesLowerInclusiveUpperExclusiveOneBased.length, is(n + 1));
      assertThat(
          Arrays.stream(nextDoublesLowerInclusiveUpperExclusiveOneBased)
              .skip(1)
              .allMatch(value -> value >= 1 && value < 2),
          is(true));
    }
  }
}
