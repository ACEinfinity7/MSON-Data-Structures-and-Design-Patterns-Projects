import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/******************************************************************************
 * Name: Alex Elko
 * 
 * Compilation: javac-algs4 Percolation.java Execution: java-algs4 Percolation
 * Dependencies: StdIn.java StdRandom.java WeightedQuickUnionUF.java
 *
 * Description: Modeling Percolation like a boss. woot. woot.
 ******************************************************************************/
public class PercolationStats {

//	confidence 95% multiplier
	private static final double CONFIDENCE_95 = 1.96;

//	fraction of open sites after percolation for each trial
	private final double[] x;

//	number of trials, T
	private final int trials;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException();
		}
		Percolation tester;
		this.trials = trials;
		x = new double[trials];
		int row, col;

		for (int t = 0; t < trials; t++) {
			tester = new Percolation(n);
			int[] randCoord = StdRandom.permutation(n * n);

			for (int i = 0; i < randCoord.length; i++) {
				row = (randCoord[i]) / n + 1;
				col = (randCoord[i]) % n + 1;
				tester.open(row, col);

				if (tester.percolates()) {
					break;
				}

			}

			x[t] = ((double) tester.numberOfOpenSites()) / (n * n);
			tester = null;
		}

	}

	public double mean() {
		return StdStats.mean(x);
	}

	public double stddev() {
		return StdStats.stddev(x);
	}

	public double confidenceLo() {
		return this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(trials);
	}

	public double confidenceHi() {
		return this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(trials);
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);

		PercolationStats stats = new PercolationStats(n, t);

		System.out.printf("%-23s = %f%n", "mean", stats.mean());
		System.out.printf("%-23s = %f%n", "stddev", stats.stddev());
		System.out.printf("%s = [%f, %f]%n", "95% confidence interval", stats.confidenceLo(), stats.confidenceHi());

	}
}