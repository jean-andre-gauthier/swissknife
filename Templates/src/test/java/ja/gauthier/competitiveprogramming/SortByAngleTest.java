package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.ComputationalGeometry.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.runners.Parameterized.class)
public class SortByAngleTest {

  @Parameters(name = "sortByAngle(points = {0}, origin={1})={2}")
  public static Collection<Object[]> points() {
    PointLong p40 = new PointLong(4, 0);
    PointLong p21 = new PointLong(2, 1);
    PointLong p11 = new PointLong(1, 1);
    PointLong p23 = new PointLong(2, 3);
    PointLong p14 = new PointLong(1, 4);
    PointLong pm13 = new PointLong(-1, 3);
    PointLong pm21 = new PointLong(-2, 1);
    PointLong pm31 = new PointLong(-3, 1);
    PointLong pm1m1 = new PointLong(-1, -1);
    PointLong pm1m2 = new PointLong(-1, -2);
    PointLong pm1m3 = new PointLong(-1, -3);
    PointLong p1m2 = new PointLong(1, -2);
    PointLong p2m2 = new PointLong(2, -2);
    return Arrays.asList(
        new Object[][] {
          {
            new ArrayList<PointLong>(
                Arrays.asList(
                    p14, p21, p23, p11, pm13, pm1m1, p1m2, p40, p2m2, pm1m3, pm1m2, pm21, pm31)),
            new PointLong(0, 0),
            new ArrayList<PointLong>(
                Arrays.asList(
                    p40, p21, p11, p23, p14, pm13, pm21, pm31, pm1m1, pm1m2, pm1m3, p1m2, p2m2))
          },
          {
            new ArrayList<PointLong>(
                Arrays.asList(
                    p14, p21, p23, p11, pm13, pm1m1, p1m2, p2m2, pm1m3, pm1m2, pm21, pm31)),
            new PointLong(4, 0),
            new ArrayList<PointLong>(
                Arrays.asList(
                    p23, p14, pm13, p21, p11, pm21, pm31, pm1m1, pm1m2, pm1m3, p1m2, p2m2))
          }
        });
  }

  @Parameter public ArrayList<PointLong> input;

  @Parameter(1)
  public PointLong origin;

  @Parameter(2)
  public ArrayList<PointLong> expectedOutput;

  @Test
  public void sortByAngleTest() {
    sortByAngle(input, origin);
    assertThat(input, is(expectedOutput));
  }
}
