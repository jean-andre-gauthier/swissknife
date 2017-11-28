package ja.gauthier.swissknife;

import static ja.gauthier.swissknife.ComputationalGeometry.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.runners.Parameterized.class)
public class DoubleOrientedAreaTest {
  @Parameters(name = "doubleOrientedArea(Polygon {index}) = {1}")
  public static Collection<Object[]> polygons() {
    return Arrays.asList(
        new Object[][] {
          {new PolygonLong(new long[][] {{0, 0}, {1, 0}, {1, 1}, {0, 1}}), 2},
          {new PolygonLong(new long[][] {{0, 0}, {2, 0}, {1, 1}}), 2},
          {new PolygonLong(new long[][] {{1, 1}, {3, 1}, {2, 2}, {3, 3}, {1, 3}}), 6},
          {
            new PolygonLong(
                new long[][] {
                  {-1, 1}, {-2, 1}, {-3, 2}, {-3, -2}, {-2, -1}, {-1, -1}, {-1, -2}, {-2, -3},
                  {2, -3}, {1, -2}, {1, -1}, {2, -1}, {3, -2}, {3, 2}, {2, 1}, {1, 1}, {1, 2},
                  {2, 3}, {-2, 3}, {-1, 2}
                }),
            48
          }
        });
  }

  @Parameter(0)
  public PolygonLong polygon;

  @Parameter(1)
  public long area;

  @Test
  public void doubleOrientedAreaTest() {
    assertThat(doubleOrientedArea(polygon), is(area));
  }
}
