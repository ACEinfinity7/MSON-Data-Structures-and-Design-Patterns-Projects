/*************************************************************************
 * Compilation: javac-algs4 BruteCollinearPoints.java Execution: none
 * Dependencies: Point.java LineSegment.java
 *
 * A program that examines 4 points at a time and checks whether they all lie on
 * the same line segment, returning all such line segments. To check whether the
 * 4 points p, q, r, and s are collinear, check whether the three slopes between
 * p and q, between p and r, and between p and s are all equal.
 *
 *************************************************************************/
public class BruteCollinearPoints {

	LineSegment[] segments;

	public BruteCollinearPoints(Point[] points) {
		if (points == null) {
			throw new IllegalArgumentException();
		}

		int numSegments = 0;

		for (int i = 0; i < points.length; i++) {
			for (int j = i+1; j < points.length; j++) {
				for (int k = j+1; k < points.length; k++) {
					for (int m = k+1; m < points.length; m++) {
						
						
					}
				}
			}
		}

	}

	public int numberOfSegments() {
		// the number of line segments
		return 0;
	}

	public LineSegment[] segments() {
		// the line segments
		return null;
	}
}