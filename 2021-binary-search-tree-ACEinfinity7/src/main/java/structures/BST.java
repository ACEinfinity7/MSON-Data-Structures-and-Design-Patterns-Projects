package structures;

import java.util.Iterator;

public class BST<T extends Comparable<? super T>> implements BinarySearchTree<T> {

	int size;
	BinaryTreeUtility utility;
	BinaryTreeNode<T> root;

	public BST() {
		size = 0;
		utility = new BTUtility();
		root = new BTNode<T>(null, null, null);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BinarySearchTree<T> add(T toAdd) {
		if (toAdd == null) {
			throw new NullPointerException("Value cannot be null.");
		}

		if (size == 0) {
			root.setData(toAdd);
		} else {
			root = add(root, toAdd);
		}
		size++;
		return this;
	}

	private BinaryTreeNode<T> add(BinaryTreeNode<T> root, T elem) {

		if (!root.hasLeftChild() && elem.compareTo(root.getData()) < 0) {
			root.setLeftChild(new BTNode<T>(null, null, elem));
			return root;
		}

		if (!root.hasRightChild() && elem.compareTo(root.getData()) >= 0) {
			root.setRightChild(new BTNode<T>(null, null, elem));
			return root;
		}

		if (elem.compareTo(root.getData()) < 0) {
			root.setLeftChild(add(root.getLeftChild(), elem));
		}

		if (elem.compareTo(root.getData()) >= 0) {
			root.setRightChild(add(root.getRightChild(), elem));
		}

		return root;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(T toFind) {
		if (toFind == null) {
			throw new NullPointerException("value cannot be null.");
		}
		if (isEmpty()) {
			return false;
		} else {
			return contains(root, toFind);
		}
	}

	private boolean contains(BinaryTreeNode<T> root, T val) {
		if (!root.hasLeftChild() && !root.hasRightChild() && root.getData().compareTo(val) != 0) {
			return false;
		}

		if (root.getData().compareTo(val) == 0) {
			return true;
		}

		if (val.compareTo(root.getData()) < 0 && root.hasLeftChild() && contains(root.getLeftChild(), val)) {
			return true;
		}

		if (val.compareTo(root.getData()) > 0 && root.hasRightChild() && contains(root.getRightChild(), val)) {
			return true;
		}

		return false;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(T toRemove) {
		System.out.println("-------------");
		if (toRemove == null) {
			throw new NullPointerException("Value cannot be null.");
		}
		int oldSize = size;
		root = remove(root, toRemove);

		return oldSize != size;
	}

	private BinaryTreeNode<T> remove(BinaryTreeNode<T> root, T elem) {
		if (root.hasLeftChild() && elem.compareTo(root.getData()) < 0) {
			root.setLeftChild(remove(root.getLeftChild(), elem));
		}

		if (root.hasRightChild() && elem.compareTo(root.getData()) > 0) {
			root.setRightChild(remove(root.getRightChild(), elem));
		}

		if (elem.compareTo(root.getData()) == 0) {
			if (!root.hasLeftChild() && root.hasRightChild()) {
				System.out.println("no left, has right");
				size--;
				return root.getRightChild();
			}

			if (!root.hasRightChild() && root.hasLeftChild()) {
				System.out.println("no right, has left");
				size--;
				return root.getLeftChild();
			}

			if (!root.hasLeftChild() && !root.hasRightChild()) {
				System.out.println("no children");
				if (size > 1) {
					System.out.println("greater than one");
					size--;
					return null;
				}

				if (size == 1) {
					System.out.println("last one");
					size--;
					return new BTNode<T>(null, null, null);
				}
			}
			System.out.println("val: " + root.getData() + " has Left: " + root.hasLeftChild() + " has right: "
					+ root.hasRightChild());
			root = new BTNode<T>(root.hasLeftChild() ? root.getLeftChild() : null,
					root.hasRightChild() ? root.getRightChild() : null, min(root.getRightChild()));
			root.setRightChild(remove(root.getRightChild(), root.getData()));
			size--;
		}

		return root;

	}

	private T min(BinaryTreeNode<T> root) {
		BinaryTreeNode<T> min = root;
		while (min.hasLeftChild()) {
			min = min.getLeftChild();
		}

		return min.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getMinimum() {
		if (isEmpty()) {
			throw new IllegalStateException("Tree cannot be empty.");
		}
		BinaryTreeNode<T> min = root;
		while (min.hasLeftChild()) {
			min = min.getLeftChild();
		}

		return min.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getMaximum() {
		if (isEmpty()) {
			throw new IllegalStateException("Tree cannot be empty.");
		}
		BinaryTreeNode<T> max = root;
		while (max.hasRightChild()) {
			max = max.getRightChild();
		}

		return max.getData();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BinaryTreeNode<T> toBinaryTreeNode() {
		if (isEmpty()) {
			throw new IllegalStateException("Binary Search Tree is empty.");
		}
		return root;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<T> iterator() {
		return utility.getInOrderIterator(root);
	}

	@Override
	public String toString() {
		String res = new String();
		for (T elem : this) {
			if (elem != null) {
				res = res + elem.toString() + " ";
			}
		}

		return res;
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> tree = new BST<Integer>();

		tree.add(1);
		tree.add(5);
		System.out.println(tree.size());
		System.out.println(tree);
		tree.remove(5);
		System.out.println(tree.size());
		System.out.println(tree);
	}

}
