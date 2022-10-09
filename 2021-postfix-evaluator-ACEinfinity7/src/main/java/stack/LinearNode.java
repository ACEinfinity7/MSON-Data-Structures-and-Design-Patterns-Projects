package stack;

public class LinearNode<T> {

	/**
	 * the next node in the list
	 */
	private LinearNode<T> next;

	/**
	 * the element stores in this node
	 */
	private T element;

	/**
	 * constructor for LinearNode
	 */
	public LinearNode() {
		next = null;
		element = null;
	}

	/**
	 * constructor that sets element during creation
	 * 
	 * @param element - element to set as the node's value
	 */
	public LinearNode(T element) {
		next = null;
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
