package ja.gauthier.competitiveprogramming;

import static ja.gauthier.competitiveprogramming.TemplateComputationalGeometry.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.runners.Parameterized.class)
public class IntersectTest {

  @Parameters
  public static Collection<Object[]> lines() {
    PointLong pointp0p1 = new PointLong(0, 1);
    PointLong pointp0p4 = new PointLong(0, 4);
    LineLong lineXp0 = new LineLong(pointp0p1, pointp0p4, false);
    PointLong pointm1p0 = new PointLong(-1, 0);
    PointLong pointp1p0 = new PointLong(1, 0);
    LineLong lineY0 = new LineLong(pointm1p0, pointp1p0, false);
    PointDouble origin = new PointDouble(0, 0);
    PointLong pointp1p5 = new PointLong(1, 5);
    LineLong lineY5Xp1 = new LineLong(pointp0p1, pointp1p5, false);
    PointDouble pointm05p0 = new PointDouble(-0.2, 0);

    return Arrays.asList(
        new Object[][] {
          {
            lineXp0,
            EndpointType.RAY,
            EndpointType.RAY,
            lineY0,
            EndpointType.RAY,
            EndpointType.RAY,
            origin
          }
          /* }, */
          /* { */
          /*   lineY0, */
          /*   EndpointType.RAY, */
          /*   EndpointType.RAY, */
          /*   lineY5Xp1, */
          /*   EndpointType.RAY, */
          /*   EndpointType.RAY, */
          /*   pointm05p0 */
          /* } */
        });
  }

  @Parameter(0)
  public LineLong lineAB;

  @Parameter(1)
  public EndpointType endPointABa;

  @Parameter(2)
  public EndpointType endPointABb;

  @Parameter(3)
  public LineLong lineCD;

  @Parameter(4)
  public EndpointType endPointCDa;

  @Parameter(5)
  public EndpointType endPointCDb;

  @Parameter(6)
  public PointDouble expectedIntersection;

  @Test
  public void intersectTest() {
    assertThat(
        intersect(lineAB, endPointABa, endPointABb, lineCD, endPointCDa, endPointCDb),
        is(expectedIntersection));
  }
}
