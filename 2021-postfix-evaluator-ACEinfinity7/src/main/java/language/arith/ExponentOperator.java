package language.arith;

import language.BinaryOperator;
import language.Operand;

/**
 * The {@link DivOperator} is an operator that performs exponentiaion on two integers
 * @author acelko
 *
 */
public class ExponentOperator extends BinaryOperator<Integer> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Operand<Integer> performOperation() {
		Operand<Integer> op0 = this.getOp0();
		Operand<Integer> op1 = this.getOp1();
		if (op0 == null || op1 == null) 
			throw new IllegalStateException("Could not perform operation prior to operands being set.");

		Integer result = 1;
		for (int i = 0; i < op0.getValue(); i++) {
			result *= op1.getValue();
		}
		
		return new Operand<Integer>(result);
	}
	
	@Override
	public void setOperand(int i, Operand<Integer> operand) {
		if (i == 1 && operand.getValue() < 0) {
			throw new IllegalStateException("Exponent cannot be negative");
		}
		
		super.setOperand(i, operand);
	}

}
