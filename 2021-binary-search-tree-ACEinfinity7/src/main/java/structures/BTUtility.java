package structures;

import java.util.Iterator;
import java.util.Stack;

public class BTUtility implements BinaryTreeUtility {

	/**
	 * abstract class to act as a base for all 3 different traversal types uses a
	 * stack so that values can be referenced in O(1), rather than all at once
	 * recursively
	 * 
	 * @author acelko
	 */
	abstract class TreeIterator<T> implements Iterator<T> {
		Stack<BinaryTreeNode<T>> stack = new Stack<BinaryTreeNode<T>>();

		public TreeIterator(BinaryTreeNode<T> root) {
			if (root == null)
				throw new NullPointerException("Root cannot be null.");
			setup(root);

		}

		abstract void setup(BinaryTreeNode<T> root);

		@Override
		public boolean hasNext() {
			return !stack.isEmpty();
		}

	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Iterator<T> getPreOrderIterator(BinaryTreeNode<T> root) {
		return new TreeIterator<T>(root) {

			@Override
			void setup(BinaryTreeNode<T> root) {
				stack.push(root);
			}

			@Override
			public T next() {
				// get value then add right child then left child
				BinaryTreeNode<T> val = stack.pop();
				if (val.hasRightChild())
					stack.push(val.getRightChild());
				if (val.hasLeftChild())
					stack.push(val.getLeftChild());

				return val.getData();
			}

		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Iterator<T> getInOrderIterator(BinaryTreeNode<T> root) {
		return new TreeIterator<T>(root) {

			/**
			 * add all left hand nodes to stack
			 */
			@Override
			void setup(BinaryTreeNode<T> root) {
				BinaryTreeNode<T> curr = root;

				while (curr.hasLeftChild()) {
					stack.push(curr);
					curr = curr.getLeftChild();
				}

				stack.push(curr);
			}

			@Override
			public T next() {
				// take value and then add all left hand nodes of right hand node
				BinaryTreeNode<T> val = stack.pop();
				if (val.hasRightChild()) {
					setup(val.getRightChild());
				}

				return val.getData();

			}
		};
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> Iterator<T> getPostOrderIterator(BinaryTreeNode<T> root) {
		return new TreeIterator<T>(root) {

			@Override
			public T next() {
				BinaryTreeNode<T> val = stack.pop();
				System.out.println("val: " + val);
				while (!stack.isEmpty() && val.hasRightChild() && stack.peek() == val.getRightChild()) {
					BinaryTreeNode<T> right = stack.pop();
					stack.push(val);
					val = right;
					setup(val);
					val = stack.pop();
				}
			
				return val.getData();
			}

			@Override
			void setup(BinaryTreeNode<T> root) {
				BinaryTreeNode<T> curr = root;
				if (!root.hasLeftChild() && !root.hasRightChild()) {
					stack.push(root);
				} else {

					while (curr.hasLeftChild()) {
						if (curr.hasRightChild()) {
							stack.push(curr.getRightChild());
						}

						stack.push(curr);
						curr = curr.getLeftChild();
					}

					if (curr.hasRightChild()) {
						stack.push(curr.getRightChild());
						stack.push(curr);
					}
				}
			}

		};

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> int getDepth(BinaryTreeNode<T> root) {
		if (!root.hasLeftChild() && !root.hasRightChild()) {
			return 0;
		}

		int left = root.hasLeftChild() ? getDepth(root.getLeftChild()) : 0;
		int right = root.hasRightChild() ? getDepth(root.getRightChild()) : 0;

		return (left < right ? right : left) + 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> boolean isBalanced(BinaryTreeNode<T> root, int tolerance) {
		if (root == null) {
			throw new NullPointerException("Root cannot be null.");
		}
		if (tolerance < 0) {
			throw new IllegalArgumentException("Tolerance cannot be negative.");
		}

		if (!root.hasLeftChild() && !root.hasRightChild()) {
			return true;
		}

		if (!root.hasLeftChild()) {
			return getDepth(root.getRightChild()) + 1 <= tolerance;
		}

		if (!root.hasRightChild()) {
			return getDepth(root.getLeftChild()) + 1 <= tolerance;
		}

		if (root.hasLeftChild() && root.hasRightChild()) {
			return Math.abs(getDepth(root.getLeftChild()) - getDepth(root.getRightChild())) <= tolerance
					&& isBalanced(root.getRightChild(), tolerance) && isBalanced(root.getLeftChild(), tolerance);
		}

		return false;

//		int left = root.hasLeftChild() ? getDepth(root.getLeftChild()) : 0;
//		int right = root.hasRightChild() ? getDepth(root.getRightChild()) : 0;
//
//
//		boolean leftBal = root.hasLeftChild() ? isBalanced(root.getLeftChild(), tolerance) : true;
//		boolean rightBal = root.hasRightChild() ? isBalanced(root.getRightChild(), tolerance) : true;
//
//		return leftBal && rightBal && Math.abs(left - right) <= tolerance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Comparable<? super T>> boolean isBST(BinaryTreeNode<T> root) {
		if (root == null) {
			throw new NullPointerException("root cannot be null.");
		}

		if (!root.hasLeftChild() && !root.hasRightChild()) {
			return true;
		}

		if (root.hasLeftChild() && root.getLeftChild().getData().compareTo(root.getData()) > 0) {
			return false;
		}

		if (root.hasRightChild() && root.getRightChild().getData().compareTo(root.getData()) < 0) {
			return false;
		}

		if (root.hasLeftChild()) {
			return isBST(root.getLeftChild());
		}

		if (root.hasRightChild()) {
			return isBST(root.getRightChild());
		}

		return true;
	}

}
