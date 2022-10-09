package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@link ModOperator} is an operator that performs modulus on two integers
 * 
 * @author acelko
 *
 */
public class ModOperator extends BinaryOperator<Integer> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operand<Integer> performOperation() {
		Operand<Integer> op0 = this.getOp0();
		Operand<Integer> op1 = this.getOp1();
		if (op0 == null || op1 == null)
			throw new IllegalStateException("Could not perform operation prior to operands being set.");

		Integer result = op1.getValue() % op0.getValue();
		return new Operand<Integer>(result);
	}
	
	@Override
	public void setOperand(int i, Operand<Integer> operand) {
		if (i == 1 && operand.getValue() == 0)
			throw new IllegalStateException("Denominator cannot be 0");

		super.setOperand(i, operand);
	}
}
