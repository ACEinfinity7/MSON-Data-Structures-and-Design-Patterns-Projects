import java.util.Iterator;
import java.util.NoSuchElementException;

// test
public class Deque<Item> implements Iterable<Item> {

	private class LinearNode<T> {

		/**
		 * the next node in the list
		 */
		private LinearNode<T> next;

		/**
		 * the previous node in the list
		 */
		private LinearNode<T> prev;

		/**
		 * the element stores in this node
		 */
		private T element;

		/**
		 * constructor for LinearNode
		 */
		public LinearNode() {
			next = null;
			prev = null;
			element = null;
		}

		/**
		 * constructor that sets element during creation
		 * 
		 * @param element - element to set as the node's value
		 */
		public LinearNode(T element) {
			this();
			this.element = element;
		}

		/**
		 * gets the next node
		 * 
		 * @return LinearNode<T> reference to next node
		 */
		public LinearNode<T> getNext() {
			return next;
		}

		/**
		 * sets the reference to the next node
		 * 
		 * @param node node to follow this one
		 */
		public void setNext(LinearNode<T> node) {
			this.next = node;
		}

		/**
		 * gets the next node
		 * 
		 * @return LinearNode<T> reference to next node
		 */
		public LinearNode<T> getPrev() {
			return prev;
		}

		/**
		 * sets the reference to the next node
		 * 
		 * @param node node to follow this one
		 */
		public void setPrev(LinearNode<T> node) {
			this.prev = node;
		}

		/**
		 * gets the element stored in this node
		 * 
		 * @return T element stored in this node
		 */
		public T getElement() {
			return element;
		}

		/**
		 * sets the element in this node
		 * 
		 * @param element element to be set in this node
		 */
		public void setElement(T element) {
			this.element = element;
		}

	}

	/**
	 * nodes to reference the head and tail of queue
	 */
	private LinearNode<Item> head, tail;
	
	private int size;

	/**
	 * Contstructor
	 */
	public Deque() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * 
	 * @return true if empty, false if not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * @return the size of the queue
	 */
	public int size() {
		return size;
	}

	/**
	 * adds the item to the front of the queue
	 * @param item - the item to add to the queue
	 */
	public void addFirst(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		LinearNode<Item> node = new LinearNode<Item>(item);

		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.setNext(head);
			head.setPrev(node);
			head = node;
		}

		size++;
	}

	/**
	 * adds the item to the back of the queue
	 * @param item - the item to add to the queue
	 */
	public void addLast(Item item) {
		if (item == null) {
			throw new IllegalArgumentException();
		}
		LinearNode<Item> node = new LinearNode<Item>(item);

		if (head == null) {
			head = node;
			tail = node;
		} else {
			node.setPrev(tail);
			tail.setNext(node);
			tail = node;
		}

		size++;

	}

	/**
	 * removes the first item in the front of the queue
	 * @return the first item in the queue
	 */
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item elem = head.getElement();
		if (size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.getNext();
			head.setPrev(null);
		}
		size--;
		return elem;
	}

	/**
	 * removes the last item in the back of the queue
	 * @return the last item in the queue
	 */
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		Item elem = tail.getElement();

		if (size == 1) {
			head = null;
			tail = null;
		} else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		size--;
		return elem;
	}

	private class DequeueIterator<T> implements Iterator<T> {
		LinearNode<T> curr;

		public DequeueIterator(LinearNode<T> head) {
			curr = head;
		}

		@Override
		public boolean hasNext() {
			return (curr != null);
		}

		@Override
		public T next() {
			if (curr == null) {
				throw new NoSuchElementException();
			}
			T val = curr.getElement();
			curr = curr.getNext();
			return val;
		}

	}

	/**
	 * @return iterator of queue that goes from front to back
	 */
	public Iterator<Item> iterator() {
		return new DequeueIterator<Item>(head);
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		deque.addFirst(1);
		deque.addFirst(2);
		deque.addFirst(3);
		Iterator<Integer> iterator = deque.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}