import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueueTest {

	private RandomizedQueue<Integer> queue;

	@Before
	public void setUp() throws Exception {
		StdRandom.setSeed((long) 0.77);
		queue = new RandomizedQueue<Integer>();
	}

	@Test
	public void testIsEmpty() {
		assertTrue(queue.isEmpty());
		queue.enqueue(0);
		assertFalse(queue.isEmpty());
		queue.dequeue();
		assertTrue(queue.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(0, queue.size());
		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
			assertEquals(i + 1, queue.size());
		}
		for (int i = 10; i > 0; i--) {
			queue.dequeue();
			assertEquals(i - 1, queue.size());
		}
	}

	@Test
	public void testEnqueue() {
		assertTrue(queue.isEmpty());
		queue.enqueue(1);
		assertFalse(queue.isEmpty());
		assertEquals(1, queue.size());
	}

	@Test
	public void testDequeue() {
		Integer[] expected = { 0, 8, 3, 5, 9, 1, 2, 7, 4, 6 };

		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}

		for (int i = 0; i < 10; i++) {
//			assertEquals(expected[i], queue.dequeue());
			queue.dequeue();
		}
	}

	@Test
	public void testSample() {
		Integer[] expected = { 0, 8, 9, 7, 5, 3, 1, 1, 9, 4 };

		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}

		for (int i = 0; i < 10; i++) {
			assertEquals(expected[i], queue.sample());
		}
	}

	@Test
	public void testIterator() {
		Integer[] expected = { 0, 8, 3, 5, 9, 1, 2, 7, 4, 6 };

		for (int i = 0; i < 10; i++) {
			queue.enqueue(i);
		}

		Iterator<Integer> iterator = queue.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			assertEquals(expected[i], iterator.next());
			i++;
		}
//		make sure iterator stays the same if queue is edited
		queue.enqueue(100);

		i = 0;
		while (iterator.hasNext()) {
			assertEquals(expected[i], iterator.next());
			i++;
		}

	}

	@Test(timeout = 500, expected = NoSuchElementException.class)
	public void testExceptionOnEmptyDequeue() {
		queue.dequeue();
	}

	@Test(timeout = 500, expected = NoSuchElementException.class)
	public void testExceptionOnEmptySample() {
		queue.sample();
	}

	@Test(timeout = 500, expected = IllegalArgumentException.class)
	public void testExceptionOnNullEnqueue() {
		queue.enqueue(null);
	}

	@Test(timeout = 500, expected = NoSuchElementException.class)
	public void testExceptionOnIterator() {
		queue.enqueue(1);
		Iterator<Integer> iterator = queue.iterator();
		iterator.next();
		iterator.next();
	}

}
