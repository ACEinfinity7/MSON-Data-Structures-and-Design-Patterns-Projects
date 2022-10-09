import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PercolationStatsTest {

	PercolationStats test200_100, test2_10000, test2_100000;

	@Before
	public void setup() {
		test200_100 = new PercolationStats(200, 100);
		test2_10000 = new PercolationStats(2, 10000);
		test2_100000 = new PercolationStats(2, 100000);
	}

	@Test
	public void test200_100() {
		assertEquals(0.59, test200_100.mean(), 0.01);
		assertEquals(0.0087, test200_100.stddev(), 0.001);
		assertEquals(0.591, test200_100.confidenceLo(), 0.01);
		assertEquals(0.594, test200_100.confidenceHi(), 0.01);
	}

	@Test
	public void test2_10000() {
		assertEquals(0.66, test2_10000.mean(), 0.01);
		assertEquals(0.11, test2_10000.stddev(), 0.01);
		assertEquals(0.664, test2_10000.confidenceLo(), 0.01);
		assertEquals(0.669, test2_10000.confidenceHi(), 0.01);

	}

	@Test
	public void test2_100000() {
		assertEquals(0.66, test2_100000.mean(), 0.01);
		assertEquals(0.11, test2_100000.stddev(), 0.01);
		assertEquals(0.666, test2_100000.confidenceLo(), 0.01);
		assertEquals(0.667, test2_100000.confidenceHi(), 0.01);

	}

}
