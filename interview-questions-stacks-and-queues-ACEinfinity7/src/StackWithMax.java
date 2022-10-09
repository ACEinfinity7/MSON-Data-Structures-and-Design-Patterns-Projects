import java.util.Stack;

public class StackWithMax<T extends Comparable<? super T>> {
	private Stack<T> s, m;
	private int size;
	
	public StackWithMax() {
		s = new Stack<T>();
		m = new Stack<T>();
		size = 0;
	}
	
	public void push(T item) {
		s.push(item);
		if (m.isEmpty()) {
			m.push(item);
		}
		
		if (item.compareTo(m.peek()) > 0) {
			m.push(item);
		}
		size++;
	}
	
	public T pop() {
		if (s.peek().equals(m.peek())) {
			m.pop();
		}
		size--;
		return s.pop();
	}
	
	public T peek() {
		return s.peek();
	}
	
	public T max() {
		return m.peek();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return s.isEmpty();
	}
	
	public static void main(String[] args) {
		StackWithMax<Integer> test = new StackWithMax<Integer>();
		
		test.push(1);
		System.out.println(test.max());
		test.push(3);
		System.out.println(test.max());
		test.push(2);
		System.out.println(test.max());
		test.pop();
		System.out.println(test.max());
	}
	
}
