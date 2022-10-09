import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/******************************************************************************
 * Name: Alex Elko
 * 
 * Compilation: javac-algs4 Percolation.java Execution: java-algs4 Percolation
 * Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *
 * Description: Modeling Percolation like a boss. woot. woot.
 ******************************************************************************/
public class Percolation {

	private boolean[] grid;
	private int openSites;
	private final int n;
	private final WeightedQuickUnionUF percolation;
	private final WeightedQuickUnionUF fullness;

	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		openSites = 0;
		this.n = n;
		grid = new boolean[n * n];
		percolation = new WeightedQuickUnionUF(n * n + 2);
		fullness = new WeightedQuickUnionUF(n * n + 1);

	}

	public void open(int row, int col) {
		int curr = checkIndices(row, col);
		if (!grid[curr - 1]) {
			grid[curr - 1] = true;

//		top slot
			if (curr <= n) {
				percolation.union(curr, 0);
				fullness.union(curr, 0);
			}

//		top	
			if (((curr - n) > 0) && grid[curr - n - 1]) {
				percolation.union(curr, curr - n);
				fullness.union(curr, curr - n);
			}

//		bottom
			if ((curr <= n * (n - 1)) && grid[curr + n - 1]) {
				percolation.union(curr, curr + n);
				fullness.union(curr, curr + n);
			}

//		left
			if ((curr % n != 1) && (n != 1) && grid[curr - 2]) {
				percolation.union(curr, curr - 1);
				fullness.union(curr, curr - 1);
			}

//		right
			if ((curr % n != 0) && grid[curr]) {
				percolation.union(curr, curr + 1);
				fullness.union(curr, curr + 1);
			}

//		attach bottom 
			if (curr > n * (n - 1)) {
				percolation.union(curr, n * n + 1);
			}

			openSites++;

		}
	}

	public boolean isOpen(int row, int col) {
		return grid[checkIndices(row, col) - 1];
	}

	public boolean isFull(int row, int col) {
		return fullness.find(0) == fullness.find(checkIndices(row, col));
	}

	public int numberOfOpenSites() {
		return openSites;
	}

	public boolean percolates() {
		return percolation.find(0) == percolation.find(n * n + 1);
	}

	private int checkIndices(int row, int col) {
		if ((row < 1) || (row > n) || (col < 1) || (col > n)) {
			throw new IllegalArgumentException(row + " " + col);
		}

		return row * n + col - n;
	}

}
