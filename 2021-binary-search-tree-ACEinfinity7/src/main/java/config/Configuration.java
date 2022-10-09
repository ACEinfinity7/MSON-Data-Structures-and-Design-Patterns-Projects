package config;

import structures.BST;
import structures.BTNode;
import structures.BTUtility;
import structures.BinarySearchTree;
import structures.BinaryTreeNode;
import structures.BinaryTreeUtility;


/**
 * This class acts as a configuration file which tells the testing framework
 * which implementation you want us to use when we grade your assignment.
 * 
 * @author jcollard jddevaug
 * 
 */
public class Configuration {

	
	public static final String STUDENT_ID_NUMBER = "12345678";
	

	public static <T> BinaryTreeNode<T> createBinaryTreeNode(BinaryTreeNode<T> left, T elem, BinaryTreeNode<T> right){
		return new BTNode<T>(left, right, elem);
	}
	
	public static BinaryTreeUtility createBinaryTreeUtility(){
		return new BTUtility();
	}
	
	public static <T extends Comparable<? super T>> BinarySearchTree<T> createBinarySearchTree(){
		return new BST();
	}
	

}
