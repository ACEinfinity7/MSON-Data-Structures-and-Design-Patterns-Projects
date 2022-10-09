package maze;

import structure.ListInterface;

/**
 * A {@link MazeSolution} holds a {@link Maze} and a {@link ListInterface} of
 * {@link RoomInterface}s that are an the order which can be used to reach a {@link RoomInterface}
 * that is an exit.
 *
 * @author jcollard
 *
 */
public interface MazeSolutionInterface {

  /**
   * Returns the {@link Maze} that this {@link MazeSolution} solves.
   *
   * @return the {@link Maze} that this {@link MazeSolution} solves.
   */
  Maze getMaze();

  /**
   * Returns a {@link ListInterface} containing {@link RoomInterface}s in an order
   * which can be used to reach an exit.
   *
   * @return a {@link ListInterface} containing {@link RoomInterface}s in an order
   *         which can be used to reach an exit.
   * @throws UnsolvableMazeException
   *             if there is no path that can be taken to reach the exit.
   */
  ListInterface<RoomInterface> getSolution();

}
