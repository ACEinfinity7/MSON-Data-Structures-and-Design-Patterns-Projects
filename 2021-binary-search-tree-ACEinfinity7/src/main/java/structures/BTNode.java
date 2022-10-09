package structures;

public class BTNode<T> implements BinaryTreeNode<T> {

	BinaryTreeNode<T> left, right;
	T data;

	public BTNode(BinaryTreeNode<T> left, BinaryTreeNode<T> right, T elem) {
		this.left = left;
		this.right = right;
		this.data = elem;
	}
	
	public BTNode() {
		left = new BTNode<T>();
		right = new BTNode<T>();
		data = null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getData() {
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setData(T data) {
		if (data == null) {
			throw new NullPointerException("New data cannot be null.");
		}
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasLeftChild() {
		return left != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasRightChild() {
		return right != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BinaryTreeNode<T> getLeftChild() {
		if (left == null) {
			throw new IllegalStateException("Left is null.");
		}
		return left;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BinaryTreeNode<T> getRightChild() {
		if (right == null) {
			throw new IllegalStateException("Right is null.");
		}
		return right;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLeftChild(BinaryTreeNode<T> left) {
		this.left = left;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRightChild(BinaryTreeNode<T> right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return getData().toString();
	}

}
