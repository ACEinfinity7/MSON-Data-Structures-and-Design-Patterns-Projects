package maze;

import structure.ListInterface;
import structure.RecursiveLinkedList;

public class MazeSolution implements MazeSolutionInterface {

	/**
	 * maze this solver is solving
	 */
	private Maze maze;

	/**
	 * lists to hold visited rooms, and the solution path
	 */
	private ListInterface<RoomInterface> visited, solution;

	/**
	 * Constructor
	 * 
	 * @param maze - maze to solve
	 */
	public MazeSolution(Maze maze) {
		this.maze = maze;
		visited = new RecursiveLinkedList<RoomInterface>();
		solution = new RecursiveLinkedList<RoomInterface>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Maze getMaze() {
		return maze;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListInterface<RoomInterface> getSolution() {
		boolean canBeSolved = CanBeSolved(maze.getStart());
		if (canBeSolved) {
			return solution;
		} else {
			throw new UnsolvableMazeException("Maze cannot be solved.");
		}
	}

	/**
	 * Figures out if a maze can be solved
	 * @param r - starting room of the maze
	 * @return true if the maze can be solved, false if not
	 */
	private boolean CanBeSolved(RoomInterface r) {
		visited.insertFirst(r);
		if (r.isExit()) {
			solution.insertFirst(r);
			return true;
		}

		ListInterface<RoomInterface> rooms = r.getRooms();
		for (int i = 0; i < rooms.size(); i++) {
			RoomInterface room = rooms.get(i);
			if (visited.contains(room) == -1) {
				if (CanBeSolved(room)) {
					solution.insertFirst(r);
					return true;
				}
			}
			
		}


		return false;
	}
}
