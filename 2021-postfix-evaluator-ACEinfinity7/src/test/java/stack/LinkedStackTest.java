package stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class LinkedStackTest {

	private StackInterface<Integer> integerStack;
	private StackInterface<String> stringStack;

	@Before
	public void setup() {
		integerStack = new LinkedStack<Integer>();
		stringStack = new LinkedStack<String>();
	}

	@Test(timeout = 5000)
	public void testIntegerStack() {
		assertTrue("Stack should be empty after being constructed.", integerStack.isEmpty());
		assertEquals("Stack should contain zero elements after being constructed.", 0, integerStack.size());

		integerStack.push(5);
		assertFalse("Stack should not be empty.", integerStack.isEmpty());
		assertEquals("The top element should be 5", new Integer(5), integerStack.top());
		assertEquals("The integerStack should contain one element.", 1, integerStack.size());

		integerStack.push(4);
		assertEquals("The top element should be 4", new Integer(4), integerStack.top());
		assertEquals("The integerStack should contain two elements.", 2, integerStack.size());

		Integer t = integerStack.pop();
		assertEquals("The popped element should be 4", new Integer(4), t);
		assertEquals("The top element should be 5", new Integer(5), integerStack.top());
		assertEquals("The integerStack should contain one element.", 1, integerStack.size());
		assertFalse("The integerStack should not be empty.", integerStack.isEmpty());

		t = integerStack.pop();
		assertEquals("The popped element should be 5", new Integer(5), t);
		assertTrue("The integerStack should be empty.", integerStack.isEmpty());
	}

	@Test(timeout = 5000, expected = StackUnderflowException.class)
	public void testIntegerStackUnderflowPop() {
		integerStack.pop();
	}

	@Test(timeout = 5000, expected = StackUnderflowException.class)
	public void testIntegerStackUnderflowTop() {
		integerStack.top();
	}

	@Test(timeout = 5000)
	public void testStringStack() {
		assertTrue("Stack should be empty after being constructed.", stringStack.isEmpty());
		assertEquals("Stack should contain zero elements after being constructed.", 0, stringStack.size());

		stringStack.push("first");
		assertFalse("Stack should not be empty.", stringStack.isEmpty());
		assertEquals("The top element should be 'first'", "first", stringStack.top());
		assertEquals("The integerStack should contain one element.", 1, stringStack.size());

		stringStack.push("second");
		assertEquals("The top element should be 'second'", "second", stringStack.top());
		assertEquals("The integerStack should contain two elements.", 2, stringStack.size());

		String t = stringStack.pop();
		assertEquals("The popped element should be 'second'", "second", t);
		assertEquals("The top element should be 'first'", "first", stringStack.top());
		assertEquals("The integerStack should contain one element.", 1, stringStack.size());
		assertFalse("The integerStack should not be empty.", stringStack.isEmpty());

		t = stringStack.pop();
		assertEquals("The popped element should be 5", "first", t);
		assertTrue("The integerStack should be empty.", stringStack.isEmpty());
	}
	
	@Test(timeout = 5000, expected = StackUnderflowException.class)
	public void testStringStackUnderflowPop() {
		stringStack.pop();
	}

	@Test(timeout = 5000, expected = StackUnderflowException.class)
	public void testStringStackUnderflowTop() {
		stringStack.top();
	}

}
