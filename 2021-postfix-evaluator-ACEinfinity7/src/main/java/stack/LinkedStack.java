package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List
 * structure to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {

	/**
	 * number of nodes in the stack
	 */
	private int size;

	/**
	 * top node in the stack
	 */
	private LinearNode<T> head;

	/**
	 * constructor for the stack
	 */
	public LinkedStack() {
		size = 0;
		head = null;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();

		T val = head.getElement();
		head = head.getNext();
		size--;

		return val;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();

		return head.getElement();
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public boolean isEmpty() {
		if (head == null)
			return true;
		else
			return false;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}.
	 */
	@Override
	public void push(T elem) {
		LinearNode<T> newNode = new LinearNode<T>(elem);

		newNode.setNext(head);
		head = newNode;
		size++;

	}

}
