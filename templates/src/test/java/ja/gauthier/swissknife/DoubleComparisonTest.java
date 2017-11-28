package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.Doubles.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.text.*;
import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.runners.Parameterized.class)
public class DoubleComparisonTest {

  @Parameters(name = "{0} CMP {1}")
  public static Collection<Object[]> doubles() {
    return Arrays.asList(
        new Object[][] {
          {-Double.MIN_VALUE, -Double.MIN_VALUE, false, true, true, true, false},
          {
            Double.NEGATIVE_INFINITY,
            Math.nextUp(-Double.MAX_VALUE),
            true,
            true,
            false,
            false,
            false
          },
          {
            Math.nextUp(-Double.MAX_VALUE),
            Double.NEGATIVE_INFINITY,
            false,
            false,
            false,
            true,
            true
          },
          {Double.NEGATIVE_INFINITY, -Double.MAX_VALUE, false, true, true, true, false},
          {-Double.MAX_VALUE, Double.NEGATIVE_INFINITY, false, true, true, true, false},
          {-2 * EPS, -EPS, true, true, false, false, false},
          {-EPS, -2 * EPS, false, false, false, true, true},
          {Math.nextUp(2 * -EPS), -EPS, false, true, true, true, false},
          {-EPS, Math.nextUp(2 * -EPS), false, true, true, true, false},
          {-EPS, Math.nextUp(-EPS), false, true, true, true, false},
          {Math.nextUp(-EPS), -EPS, false, true, true, true, false},
          {-Double.MIN_VALUE, 0.0, false, true, true, true, false},
          {0.0, -Double.MIN_VALUE, false, true, true, true, false},
          {-0.0, 0.0, false, true, true, true, false},
          {0.0, -0.0, false, true, true, true, false},
          {0.0, 0.0, false, true, true, true, false},
          {-Double.MIN_VALUE, Double.MIN_VALUE, false, true, true, true, false},
          {Double.MIN_VALUE, -Double.MIN_VALUE, false, true, true, true, false},
          {0.0, Double.MIN_VALUE, false, true, true, true, false},
          {Double.MIN_VALUE, 0.0, false, true, true, true, false},
          {0.0, EPS, true, true, false, false, false},
          {EPS, 0.0, false, false, false, true, true},
          {0.0, Math.nextUp(EPS), true, true, false, false, false},
          {Math.nextUp(EPS), 0.0, false, false, false, true, true},
          {Math.nextDown(EPS), EPS, false, true, true, true, false},
          {EPS, Math.nextDown(EPS), false, true, true, true, false},
          {EPS, Math.nextDown(2 * EPS), false, true, true, true, false},
          {Math.nextDown(2 * EPS), EPS, false, true, true, true, false},
          {EPS, 2 * EPS, true, true, false, false, false},
          {2 * EPS, EPS, false, false, false, true, true},
          {0.0, 0.1, true, true, false, false, false},
          {0.1, 0.0, false, false, false, true, true},
          {0.0, 1, true, true, false, false, false},
          {1, 0.0, false, false, false, true, true},
          {0.0, 10e9, true, true, false, false, false},
          {10e9, 0.0, false, false, false, true, true},
          {0.0, Double.MAX_VALUE, true, true, false, false, false},
          {Double.MAX_VALUE, 0.0, false, false, false, true, true},
          {0.0, Double.POSITIVE_INFINITY, true, true, false, false, false},
          {Double.POSITIVE_INFINITY, 0.0, false, false, false, true, true},
          {4503599627370495.0, 4503599627370496.0, true, true, false, false, false},
          {4503599627370496.0, 4503599627370495.0, false, false, false, true, true},
          {4503599627370496.0, 4503599627370496.0, false, true, true, true, false},
          {4503599627370496.0, 4503599627370497.0, false, true, true, true, false},
          {4503599627370497.0, 4503599627370496.0, false, true, true, true, false},
          {Double.MAX_VALUE, Double.MAX_VALUE, false, true, true, true, false},
          {
            Math.nextDown(Double.MAX_VALUE),
            Double.POSITIVE_INFINITY,
            true,
            true,
            false,
            false,
            false
          },
          {
            Double.POSITIVE_INFINITY,
            Math.nextDown(Double.MAX_VALUE),
            false,
            false,
            false,
            true,
            true
          },
          {Double.MAX_VALUE, Double.POSITIVE_INFINITY, false, true, true, true, false},
          {Double.POSITIVE_INFINITY, Double.MAX_VALUE, false, true, true, true, false}
        });
  }

  @Parameter(0)
  public double a;

  @Parameter(1)
  public double b;

  @Parameter(2)
  public boolean expectedSmallerThan;

  @Parameter(3)
  public boolean expectedSmallerEqualTo;

  @Parameter(4)
  public boolean expectedEqualTo;

  @Parameter(5)
  public boolean expectedGreaterEqualTo;

  @Parameter(6)
  public boolean expectedGreaterThan;

  @Test
  public void smallerThanTest() {
    assertThat(smallerThan(a, b), is(expectedSmallerThan));
  }

  @Test
  public void smallerEqualToTest() {
    assertThat(smallerEqualTo(a, b), is(expectedSmallerEqualTo));
  }

  @Test
  public void equalToTest() {
    assertThat(equalTo(a, b), is(expectedEqualTo));
  }

  @Test
  public void greaterEqualToTest() {
    assertThat(greaterEqualTo(a, b), is(expectedGreaterEqualTo));
  }

  @Test
  public void greaterThanTest() {
    assertThat(greaterThan(a, b), is(expectedGreaterThan));
  }
}
