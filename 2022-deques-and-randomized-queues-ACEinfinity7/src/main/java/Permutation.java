import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {

  public static void main(String[] args) {
	  RandomizedQueue<String> queue = new RandomizedQueue<String>();
	  
	  while (!StdIn.isEmpty()) {
		  queue.enqueue(StdIn.readString());
	  }
	  
	  int k = Integer.parseInt(args[0]);
	  for (int i = 0; i < k; i++) {
		  System.out.println(queue.dequeue());
	  }

  }

}
