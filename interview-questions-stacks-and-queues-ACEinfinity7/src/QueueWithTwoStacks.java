import java.util.Stack;

public class QueueWithTwoStacks<T> {
	private Stack<T> s1, s2;
	private int size;
	
	
	public QueueWithTwoStacks() {
		s1 = new Stack<T>();
		s2 = new Stack<T>();
		size = 0;
	}
	
	public void enqueue(T item) {
		while (!s2.isEmpty()) {
			s1.push(s2.pop());
		}
		size++;
		s1.push(item);
	}
	
	public T dequeue() {
		while (!s1.isEmpty()) {
			s2.push(s1.pop());
		}
		size--;
		return s2.pop();
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public static void main(String[] args) {
		QueueWithTwoStacks<Integer> test = new QueueWithTwoStacks<Integer>();
		
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		
		System.out.println(test.dequeue());
		System.out.println(test.dequeue());
		System.out.println(test.dequeue());
		
		test.enqueue(4);
		System.out.println(test.dequeue());
		
	}
}
