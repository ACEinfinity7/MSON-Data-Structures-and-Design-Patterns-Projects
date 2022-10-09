package language.arith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import language.Operand;
import language.Operator;

public class ExponentOperatorTest {
	Operator<Integer> operator;
	Operand<Integer> op0;
	Operand<Integer> op1;

	/**
	 * Runs before each test.
	 */
	@Before
	public void setup() {
		operator = new ExponentOperator();
		op0 = new Operand<Integer>(2);
		op1 = new Operand<Integer>(3);
	}

	@Test(timeout = 5000)
	public void testPerformOperation() {
		operator.setOperand(0, op0);
		operator.setOperand(1, op1);

		Operand<Integer> result = operator.performOperation();
		int value = result.getValue();
		assertEquals("Operator applied to 2 and 3 should produce 8.", 8, value);
	}

	@Test(timeout = 5000)
	public void testPerformOperationPositiveBase() {
		operator.setOperand(0, new Operand<Integer>(3));
		operator.setOperand(1, new Operand<Integer>(2));

		Operand<Integer> result = operator.performOperation();
		int value = result.getValue();
		assertEquals("Operator applied to 3 and 2 should produce 9.", 9, value);
	}

	@Test(timeout = 5000)
	public void testPerformOperationNegativeBaseEvenExponent() {
		operator.setOperand(0, new Operand<Integer>(-2));
		operator.setOperand(1, new Operand<Integer>(2));

		Operand<Integer> result = operator.performOperation();
		int value = result.getValue();
		assertEquals("Operator applied to -2 and 2 should produce 4.", 4, value);
	}

	@Test(timeout = 5000)
	public void testPerformOperationNegativeBaseOddExponent() {
		operator.setOperand(0, new Operand<Integer>(-3));
		operator.setOperand(1, new Operand<Integer>(3));

		Operand<Integer> result = operator.performOperation();
		int value = result.getValue();
		assertEquals("Operator applied to -3 and 3 should produce -27.", -27, value);
	}

	@Test(timeout = 5000, expected = IllegalArgumentException.class)
	public void testIllegalArgumentException() {
		operator.setOperand(2, op0);
		fail("Binary operator should not except input to position 2");
	}

	@Test(timeout = 5000)
	public void testGetNumberOfArguments() {
		assertEquals("Binary operator should have 2 arguments.", operator.getNumberOfArguments(), 2);
	}

	@Test(timeout = 5000, expected = NullPointerException.class)
	public void testNullArgumentException() {
		operator.setOperand(0, null);
		fail("Operator should not allow null arguments");
	}

	@Test(timeout = 5000, expected = IllegalStateException.class)
	public void testIllegalStateException() {
		operator.setOperand(0, new Operand<Integer>(5));
		operator.setOperand(0, new Operand<Integer>(12));
		fail("Operator should not allow the same operand position to be set more than once");
	}

	@Test(timeout = 5000, expected = IllegalStateException.class)
	public void testIllegalStateExceptionPerform() {
		operator.performOperation();
		fail("Operator should not compute when all arguments have not been set.");
	}

	@Test(timeout = 5000, expected = IllegalStateException.class)
	public void testIllegalStateExceptionZeroDenominator() {
		operator.setOperand(1, new Operand<Integer>(-3));
		fail("Operator should not allow the denominator to be set to negative value");
	}

}
