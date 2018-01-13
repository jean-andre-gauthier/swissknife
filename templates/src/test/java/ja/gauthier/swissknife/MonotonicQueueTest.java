package ja.gauthier.swissknife;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import ja.gauthier.swissknife.MonotonicQueueStack.*;
import java.util.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(org.junit.runners.Parameterized.class)
public class MonotonicQueueTest {
  @Parameters(name = "stackSize: {0}")
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][] {{1}, {2}, {3}, {4}});
  }

  @Parameter public int stackSize;

  @Test
  public void singleSwapTest() {
    MonotonicQueueInt qi = new MonotonicQueueInt(stackSize);
    assertThat(qi.size(), is(0));
    assertThat(qi.isEmpty(), is(true));

    qi.enqueue(1);
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(1));

    qi.enqueue(-1);
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(-1));
    assertThat(qi.getMax(), is(1));

    qi.enqueue(2);
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(-1));
    assertThat(qi.getMax(), is(2));

    assertThat(qi.dequeue(), is(1));
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(-1));
    assertThat(qi.getMax(), is(2));

    assertThat(qi.dequeue(), is(-1));
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(2));
    assertThat(qi.getMax(), is(2));

    assertThat(qi.dequeue(), is(2));
    assertThat(qi.size(), is(0));
    assertThat(qi.isEmpty(), is(true));
  }

  @Test
  public void multipleSwapTest() {
    MonotonicQueueInt qi = new MonotonicQueueInt(stackSize);
    assertThat(qi.size(), is(0));
    assertThat(qi.isEmpty(), is(true));

    qi.enqueue(1);
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(1));

    assertThat(qi.dequeue(), is(1));
    assertThat(qi.size(), is(0));
    assertThat(qi.isEmpty(), is(true));

    qi.enqueue(2);
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(2));
    assertThat(qi.getMax(), is(2));

    assertThat(qi.dequeue(), is(2));
    assertThat(qi.size(), is(0));
    assertThat(qi.isEmpty(), is(true));

    qi.enqueue(3);
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(3));
    assertThat(qi.getMax(), is(3));

    qi.enqueue(4);
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(3));
    assertThat(qi.getMax(), is(4));

    qi.enqueue(5);
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(3));
    assertThat(qi.getMax(), is(5));

    qi.enqueue(6);
    assertThat(qi.size(), is(4));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(3));
    assertThat(qi.getMax(), is(6));

    assertThat(qi.dequeue(), is(3));
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(4));
    assertThat(qi.getMax(), is(6));

    qi.enqueue(7);
    assertThat(qi.size(), is(4));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(4));
    assertThat(qi.getMax(), is(7));

    qi.enqueue(8);
    assertThat(qi.size(), is(5));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(4));
    assertThat(qi.getMax(), is(8));

    assertThat(qi.dequeue(), is(4));
    assertThat(qi.size(), is(4));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(5));
    assertThat(qi.getMax(), is(8));

    assertThat(qi.dequeue(), is(5));
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(6));
    assertThat(qi.getMax(), is(8));

    assertThat(qi.dequeue(), is(6));
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(7));
    assertThat(qi.getMax(), is(8));

    qi.enqueue(-1);
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(-1));
    assertThat(qi.getMax(), is(8));

    assertThat(qi.dequeue(), is(7));
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(-1));
    assertThat(qi.getMax(), is(8));

    assertThat(qi.dequeue(), is(8));
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(-1));
    assertThat(qi.getMax(), is(-1));

    assertThat(qi.dequeue(), is(-1));
    assertThat(qi.size(), is(0));
    assertThat(qi.isEmpty(), is(true));
  }

  @Test
  public void insertionTest() {
    MonotonicQueueInt qi = new MonotonicQueueInt(stackSize);

    qi.enqueue(1);
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(1));

    qi.enqueue(2);
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(2));

    qi.enqueue(3);
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(3));

    assertThat(qi.dequeue(), is(1));
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(2));
    assertThat(qi.getMax(), is(3));
  }

  @Test
  public void reverseInsertionTest() {
    MonotonicQueueInt qi = new MonotonicQueueInt(stackSize);

    qi.enqueue(3);
    assertThat(qi.size(), is(1));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(3));
    assertThat(qi.getMax(), is(3));

    qi.enqueue(2);
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(2));
    assertThat(qi.getMax(), is(3));

    qi.enqueue(1);
    assertThat(qi.size(), is(3));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(3));

    assertThat(qi.dequeue(), is(3));
    assertThat(qi.size(), is(2));
    assertThat(qi.isEmpty(), is(false));
    assertThat(qi.getMin(), is(1));
    assertThat(qi.getMax(), is(2));
  }
}
