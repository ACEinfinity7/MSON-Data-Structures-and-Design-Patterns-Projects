package maze;

/**
 * A {@link Maze} is at least one {@link RoomInterface} which may contain paths
 * to other {@link RoomInterface}s. A {@link Maze} is said to be solvable if
 * it is possible to navigate between its {@link RoomInterface}s in such a way
 * that one arrives at a {@link RoomInterface} whose {@link RoomInterface#isExit()} returns
 * {@code true}.
 * @author jcollard, jddevaug
 *
 */
public class Maze {
  /**
   * The starting room of the {@link Maze}.
   */
  private final RoomInterface start;

  /**
   * Creates a {@link Maze} specifying the starting {@link RoomInterface}.
   * @param start the starting room of this {@link Maze}
   * @throws NullPointerException if {@code start} is {@code null}.
   */
  public Maze(final RoomInterface start) {
    if (start == null) {
      throw new NullPointerException("A Maze must have a starting room.");
    }
    this.start = start;
  }

  /**
   * Returns the starting {@link RoomInterface} of this {@link Maze}.
   * @return the starting {@link RoomInterface} of this {@link Maze}
   */
  public final RoomInterface getStart() {
    return start;
  }

}
