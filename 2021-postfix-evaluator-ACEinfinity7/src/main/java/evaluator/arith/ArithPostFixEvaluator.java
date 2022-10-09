package evaluator.arith;

import evaluator.IllegalPostFixExpressionException;
import evaluator.PostFixEvaluator;
import language.Operand;
import language.Operator;
import parser.arith.ArithPostFixParser;
import stack.LinkedStack;
import stack.StackInterface;

/**
 * An {@link ArithPostFixEvaluator} is a post fix evaluator over simple
 * arithmetic expressions.
 *
 */
public class ArithPostFixEvaluator implements PostFixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;

	/**
	 * Constructs an {@link ArithPostFixEvaluator}.
	 */
	public ArithPostFixEvaluator() {
		this.stack = new LinkedStack<Operand<Integer>>();
	}

	/**
	 * Evaluates a postfix expression.
	 * 
	 * @return the result
	 */
	@Override
	public Integer evaluate(String expr) {

		ArithPostFixParser parser = new ArithPostFixParser(expr);
		while (parser.hasNext()) {
			switch (parser.nextType()) {
			case OPERAND:
				stack.push(parser.nextOperand());
				break;
			case OPERATOR:
				Operator<Integer> op = parser.nextOperator();
				for (int i = 0; i < op.getNumberOfArguments(); i++) {
					op.setOperand(i, stack.pop());
				}
				stack.push(op.performOperation());
				break;
			default:
				throw new IllegalPostFixExpressionException("expression is not formatted correctly.");
			}
		}

		if (stack.size() != 1) {
			throw new IllegalPostFixExpressionException("expression is not formatted correctly.");
		} else {
			return stack.pop().getValue();
		}

	}

}
