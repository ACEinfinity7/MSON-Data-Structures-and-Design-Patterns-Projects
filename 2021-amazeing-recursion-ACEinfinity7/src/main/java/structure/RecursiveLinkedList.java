package structure;

import org.apache.commons.math3.exception.OutOfRangeException;

public class RecursiveLinkedList<T> implements ListInterface<T> {
	/**
	 * number of nodes in the list
	 */
	private int count;

	/**
	 * the starting/ending or head/tail nodes
	 */
	private Node<T> head;

	/**
	 * Constructor for RecursiveLinkedList
	 */
	public RecursiveLinkedList() {
		count = 0;
		head = null;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListInterface<T> insertFirst(T elem) {
		if (elem == null) {
			throw new NullPointerException("New node element cannot be null.");
		}
		// similar to push
		// create a node
		Node<T> newNode = new Node<T>(elem);
		// set it's next to the current head
		newNode.setNext(head);
		// update head to point to our new node
		head = newNode;
		// return our list
		count++;
		return this;
	}

	/**
	 * returns the node at the given index
	 * 
	 * @param curr      - starting node for recursion (head normally)
	 * @param currIndex - incremented index used for comparison (call at 0)
	 * @param seekIndex - index for node wanted
	 * @return the node at the given index
	 */
	private Node<T> getAt(Node<T> curr, int currIndex, int seekIndex) {
		if (seekIndex > (count - 1) || seekIndex < 0) {
			throw new IndexOutOfBoundsException("Index is out of bounds. " + seekIndex);
		}
		if (seekIndex == 0) {
			return head;
		}
		if (currIndex == seekIndex) {
			return curr;
		}

		return getAt(curr.getNext(), currIndex + 1, seekIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListInterface<T> insertLast(T elem) {
		if (elem == null) {
			throw new NullPointerException("New node element cannot be null.");
		}
		if (count == 0) {
			head = new Node<T>(elem);
		} else {
			Node<T> end = getAt(head, 0, count - 1);
			end.setNext(new Node<T>(elem));
		}
		count++;
		return this;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListInterface<T> insertAt(int index, T elem) {
		if (elem == null) {
			throw new NullPointerException("New node element cannot be null.");
		}
		if (index == 0) {
			Node<T> newNode = new Node<T>(elem);
			newNode.setNext(head);
			head = newNode;
		} else {
			Node<T> before = getAt(head, 0, index - 1);
			Node<T> after = before.getNext();
			before.setNext(new Node<T>(elem));
			before.getNext().setNext(after);
		}
		count++;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T removeFirst() {
		if (head == null) {
			throw new IllegalStateException("List is empty.");
		}

		Node<T> newHead = head.getNext();
		T elem = head.getElement();
		head = newHead;

		count--;
		return elem;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T removeLast() {
		if (head == null) {
			throw new IllegalStateException("List is empty.");
		}
		T elem;
		if (count == 1) {
			elem = head.getElement();
			head = null;
		} else {
			Node<T> newEnd = getAt(head, 0, count - 2);
			elem = newEnd.getNext().getElement();
			newEnd.setNext(null);
		}
		count--;
		return elem;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T removeAt(int index) {
		if (head == null) {
			throw new IndexOutOfBoundsException("List is empty.");
		}
		if (index > (count - 1)) {
			throw new IndexOutOfBoundsException("Index is out of bounds.");
		}
		if (index == 0) {
			return removeFirst();
		} else {
			Node<T> prev = getAt(head, 0, index - 1);
			T elem = prev.getNext().getElement();
			prev.setNext(prev.getNext().getNext());

			count--;
			return elem;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getFirst() {
		if (head == null) {
			throw new IllegalStateException("List is empty.");
		}

		return head.getElement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getLast() {
		return get(count - 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T get(int index) {
		if (head == null) {
			throw new IllegalStateException("List is empty.");
		}

		return getAt(head, 0, index).getElement();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(T elem) {
		if (head == null) {
			throw new IllegalStateException("List is empty.");
		}

		if (contains(elem) != -1) {
			System.out.println("ERROR: " + contains(elem));
			removeAt(contains(elem));
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int contains(T elem) {
		return contains(elem, head, 0);
	}

	private int contains(T toFind, Node<T> toCheck, int currentIndex) {
		// 2 base cases
		// base 1 - I've reached end of the list (return -1)
		if (currentIndex == count) {
			return -1;
		}
		// base 2 - I've found the node (toCheck.getElement().equals(toFind)) return
		// currentIndex
		if (toCheck.getElement().equals(toFind)) {
			return currentIndex;
		}
		// 1 recursive case
		// contains(toFind, toCheck.getNext(), currentIndex+1)
		return contains(toFind, toCheck.getNext(), currentIndex + 1);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return count == 0 ? true : false;
	}

	/**
	 * returns comma separated representation of the list in string form
	 */
	@Override
	public String toString() {
		return buildRepresentation(head);
	}

	/**
	 * builds the string form of the list
	 * 
	 * @param curr - currently traversed node
	 * @return string representation of the list
	 */
	private String buildRepresentation(Node<T> curr) {
		if (curr.getNext() == null) {
			return curr.getElement().toString();
		}

		return curr.getElement().toString() + ", " + buildRepresentation(curr.getNext());
	}

}
