import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

	/**
	 * starting size of array
	 */
	private static final int INIT_SIZE = 8;

	/**
	 * storage method for queue items
	 */
	private Item[] array;
	
	/**
	 * current index in array for most recently inserted item
	 */
	private int size;
	
	/**
	 * current index in array for next to remove item
	 */
	private int front;

	/**
	 * Constructor
	 */
	public RandomizedQueue() {
		array = (Item[]) new Object[INIT_SIZE];
		size = 0;
		front = 0;
	}

	/**
	 * 
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * 
	 * @return the number of items in the queue
	 */
	public int size() {
		return size - front;
	}

	/**
	 * takes an array and resizes it, and copies existing data across
	 * @param capacity - new size of array
	 * @param array - the old array
	 * @param size - the old end index
	 * @param front - the old start index
	 * @return a new array with size of capacity and with data from front to size
	 */
	private static <Item> Item[] resize(int capacity, Item[] array, int size, int front) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size - front; i++) {
			copy[i] = array[i + front];
		}

		return copy;
	}

	/**
	 * add an item to the queue
	 * @param item - the item to be added to the queue
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		if (array.length == size) {
			array = resize(2 * array.length, array, size, front);
			size -= front;
			front = 0;
		}

		array[size++] = item;
	}

	/**
	 * remove and return a random item in the queue
	 * @return the removed item
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		int index = StdRandom.uniform(front, size);
		Item temp = array[index];
		array[index] = array[front];
		array[front] = null;

		front++;
		if (array.length/4 == size()) {
			array = resize(array.length / 2, array, size, front);
			size -= front;
			front = 0;
		}
		if (front == size) {
			front = 0;
			size = 0;
			array = resize(INIT_SIZE, array, size, front);
		}

		return temp;
	}

	/**
	 * return but does not remove a random item
	 * @return the random item from the queue
	 */
	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return array[StdRandom.uniform(front, size)];
	}

	private class RandomizedQueueIterator<Item> implements Iterator<Item> {

		private int front;
		private Item[] array;

		public RandomizedQueueIterator(Item[] array) {
			this.array = array.clone();
			front = 0;
		}

		@Override
		public boolean hasNext() {
			return front < array.length;
		}

		@Override
		public Item next() {
			if (front >= array.length || array.length == 0) {
				throw new NoSuchElementException();
			}
			int index = StdRandom.uniform(front, array.length);
			Item temp = array[index];
			array[index] = array[front];
			array[front] = temp;

			return array[front++];
		}

	}

	/**
	 * @return an iterator that gives all items in the queue in random order
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator<Item>(resize(size(), array, size, front));
	}

//	@Override
//	public String toString() {
//		String val = "";
//		for (int i = 0; i < array.length; i++) {
//			if (i == front) {
//				val = val + "| ";
//			}
//			val = val + array[i] + " ";
//		}
//		val = val + " size: " + size + " len/4: " + (array.length/4);
//		return val;
//	}

	public static void main(String[] args) {
//		not used
//		RandomizedQueue<Integer> test = new RandomizedQueue<Integer>();
//		test.enqueue(1);
//		test.enqueue(2);
//		test.enqueue(3);
//		System.out.println(test);
//		test.dequeue();
//		System.out.println(test);
//		test.enqueue(4);
//		System.out.println(test);
//		test.enqueue(5);
//		System.out.println(test);
//		test.enqueue(6);
//		System.out.println(test);

	}
}