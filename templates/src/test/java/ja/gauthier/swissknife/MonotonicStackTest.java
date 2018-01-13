package ja.gauthier.swissknife;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import ja.gauthier.swissknife.MonotonicQueueStack.*;
import java.util.*;
import org.junit.Test;

public class MonotonicStackTest {
  @Test
  public void getMaxIntTest() {
    MonotonicStackInt s = new MonotonicStackInt(1);
    s.push(1);
    assertThat(s.getMax(), is(1));
    s.push(2);
    assertThat(s.getMax(), is(2));
    s.push(-1);
    assertThat(s.getMax(), is(2));
    s.pop();
    assertThat(s.getMax(), is(2));
    s.pop();
    assertThat(s.getMax(), is(1));
  }

  @Test
  public void getMaxLongTest() {
    MonotonicStackLong s = new MonotonicStackLong(1);
    s.push(1L);
    assertThat(s.getMax(), is(1L));
    s.push(2L);
    assertThat(s.getMax(), is(2L));
    s.push(-1L);
    assertThat(s.getMax(), is(2L));
    s.pop();
    assertThat(s.getMax(), is(2L));
    s.pop();
    assertThat(s.getMax(), is(1L));
  }

  @Test
  public void getMaxDoubleTest() {
    MonotonicStackDouble s = new MonotonicStackDouble(1);
    s.push(1.0);
    assertThat(s.getMax(), is(1.0));
    s.push(2.0);
    assertThat(s.getMax(), is(2.0));
    s.push(-1.0);
    assertThat(s.getMax(), is(2.0));
    s.pop();
    assertThat(s.getMax(), is(2.0));
    s.pop();
    assertThat(s.getMax(), is(1.0));
  }

  @Test
  public void getMinIntTest() {
    MonotonicStackInt s = new MonotonicStackInt(1);
    s.push(1);
    assertThat(s.getMin(), is(1));
    s.push(2);
    assertThat(s.getMin(), is(1));
    s.push(-1);
    assertThat(s.getMin(), is(-1));
    s.pop();
    assertThat(s.getMin(), is(1));
    s.pop();
    assertThat(s.getMin(), is(1));
  }

  @Test
  public void getMinLongTest() {
    MonotonicStackLong s = new MonotonicStackLong(1);
    s.push(1L);
    assertThat(s.getMin(), is(1L));
    s.push(2L);
    assertThat(s.getMin(), is(1L));
    s.push(-1L);
    assertThat(s.getMin(), is(-1L));
    s.pop();
    assertThat(s.getMin(), is(1L));
    s.pop();
    assertThat(s.getMin(), is(1L));
  }

  @Test
  public void getMinDoubleTest() {
    MonotonicStackDouble s = new MonotonicStackDouble(1);
    s.push(1.0);
    assertThat(s.getMin(), is(1.0));
    s.push(2.0);
    assertThat(s.getMin(), is(1.0));
    s.push(-1.0);
    assertThat(s.getMin(), is(-1.0));
    s.pop();
    assertThat(s.getMin(), is(1.0));
    s.pop();
    assertThat(s.getMin(), is(1.0));
  }

  @Test
  public void emptyPopPushSizeIntTest() {
    MonotonicStackInt s = new MonotonicStackInt(1);
    assertThat(s.isEmpty(), is(true));
    assertThat(s.size(), is(0));
    s.push(1);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(1));
    s.push(2);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    s.push(-1);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(3));
    assertThat(s.pop(), is(-1));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    s.push(-2);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(3));
    assertThat(s.pop(), is(-2));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    assertThat(s.pop(), is(2));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(1));
    assertThat(s.pop(), is(1));
    assertThat(s.isEmpty(), is(true));
    assertThat(s.size(), is(0));
  }

  @Test
  public void emptyPopPushSizeLongTest() {
    MonotonicStackLong s = new MonotonicStackLong(1);
    assertThat(s.isEmpty(), is(true));
    assertThat(s.size(), is(0));
    s.push(1L);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(1));
    s.push(2L);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    s.push(-1L);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(3));
    assertThat(s.pop(), is(-1L));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    s.push(-2L);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(3));
    assertThat(s.pop(), is(-2L));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    assertThat(s.pop(), is(2L));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(1));
    assertThat(s.pop(), is(1L));
    assertThat(s.isEmpty(), is(true));
    assertThat(s.size(), is(0));
  }

  @Test
  public void emptyPopPushSizeDoubleTest() {
    MonotonicStackDouble s = new MonotonicStackDouble(1);
    assertThat(s.isEmpty(), is(true));
    assertThat(s.size(), is(0));
    s.push(1.0);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(1));
    s.push(2.0);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    s.push(-1.0);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(3));
    assertThat(s.pop(), is(-1.0));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    s.push(-2.0);
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(3));
    assertThat(s.pop(), is(-2.0));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(2));
    assertThat(s.pop(), is(2.0));
    assertThat(s.isEmpty(), is(false));
    assertThat(s.size(), is(1));
    assertThat(s.pop(), is(1.0));
    assertThat(s.isEmpty(), is(true));
    assertThat(s.size(), is(0));
  }

  @Test
  public void resizeIntTest() {
    MonotonicStackInt s = new MonotonicStackInt(1);
    int j = 0;
    while (++j <= 4) {
      for (int i = 0; i < 128; ++i) {
        s.push(i);
      }
      for (int i = 127; i >= 32; --i) {
        assertThat(s.pop(), is(i));
      }
      for (int i = 32; i < 128; ++i) {
        s.push(i);
      }
      for (int i = 127; i >= 0; --i) {
        assertThat(s.pop(), is(i));
      }
      assertThat(s.isEmpty(), is(true));
      assertThat(s.size(), is(0));
    }
  }

  @Test
  public void resizeLongTest() {
    MonotonicStackLong s = new MonotonicStackLong(1);
    long j = 0;
    while (++j <= 4) {
      for (long i = 0; i < 128L; ++i) {
        s.push(i);
      }
      for (long i = 127; i >= 32L; --i) {
        assertThat(s.pop(), is(i));
      }
      for (long i = 32; i < 128L; ++i) {
        s.push(i);
      }
      for (long i = 127; i >= 0L; --i) {
        assertThat(s.pop(), is(i));
      }
      assertThat(s.isEmpty(), is(true));
      assertThat(s.size(), is(0));
    }
  }

  @Test
  public void resizeDoubleTest() {
    MonotonicStackDouble s = new MonotonicStackDouble(1);
    int j = 0;
    while (++j <= 4) {
      for (double i = 0.0; i < 128.0; ++i) {
        s.push(i);
      }
      for (double i = 127.0; i >= 32.0; --i) {
        assertThat(s.pop(), is(i));
      }
      for (double i = 32.0; i < 128.0; ++i) {
        s.push(i);
      }
      for (double i = 127.0; i >= 0.0; --i) {
        assertThat(s.pop(), is(i));
      }
      assertThat(s.isEmpty(), is(true));
      assertThat(s.size(), is(0));
    }
  }

  @Test
  public void boundsTest() {
    MonotonicStackInt si = new MonotonicStackInt(1);
    si.push(Integer.MIN_VALUE);
    si.push(Integer.MAX_VALUE);
    assertThat(si.getMin(), is(Integer.MIN_VALUE));
    assertThat(si.getMax(), is(Integer.MAX_VALUE));
    assertThat(si.pop(), is(Integer.MAX_VALUE));
    assertThat(si.pop(), is(Integer.MIN_VALUE));
    MonotonicStackLong sl = new MonotonicStackLong(1);
    sl.push(Long.MIN_VALUE);
    sl.push(Long.MAX_VALUE);
    assertThat(sl.getMin(), is(Long.MIN_VALUE));
    assertThat(sl.getMax(), is(Long.MAX_VALUE));
    assertThat(sl.pop(), is(Long.MAX_VALUE));
    assertThat(sl.pop(), is(Long.MIN_VALUE));
    MonotonicStackDouble sd = new MonotonicStackDouble(1);
    sd.push(Double.MIN_VALUE);
    sd.push(Double.MAX_VALUE);
    sd.push(-Double.MAX_VALUE);
    assertThat(sd.getMin(), is(-Double.MAX_VALUE));
    assertThat(sd.getMax(), is(Double.MAX_VALUE));
    assertThat(sd.pop(), is(-Double.MAX_VALUE));
    assertThat(sd.pop(), is(Double.MAX_VALUE));
    assertThat(sd.pop(), is(Double.MIN_VALUE));
  }
}
