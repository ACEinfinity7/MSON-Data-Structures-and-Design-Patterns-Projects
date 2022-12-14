package maze;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import structure.ListInterface;
import config.Configuration;

public class MazeSolutionTest {

	private MazeBuilderInterface builder;

	// NOTICE: This test assumes your builder works. If you find errors, it is
	// possible that your MazeBuilder implementation is broken.

	@Before
	public void setup() {
		builder = Configuration.getMazeBuilder(); 
		if (builder == null)   
			fail("You seem to have forgotten to set which builder to use in the Configuraiton.getMazeBuilder method.");
		if (builder == Configuration.getMazeBuilder())
			fail("You should return a new instance of MazeBuilder when Configuration.getMazeBuilder() is called.");
	}

	@Test(timeout = 500)
	public void testSimpleMaze() {
		RoomInterface r0 = builder.createExit("This is an exit", "very simple.");
		Maze maze = new Maze(r0);
		MazeSolutionInterface solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);
		
		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<RoomInterface> rooms = solution.getSolution();
		assertFalse(rooms.isEmpty());
		assertEquals("The solution should contain 1 room", 1, rooms.size());
		assertEquals("The one room should be the exit.", r0, rooms.getFirst());
	}
	
	private void checkSolution(RoomInterface start, ListInterface<RoomInterface> rooms){
		assertEquals(start, rooms.getFirst());
 
		while(!rooms.isEmpty()){
			RoomInterface current = rooms.removeFirst();
			if(rooms.isEmpty()){
				assertTrue("Last room in solution was not an exit.", current.isExit());
				return;
			}
			RoomInterface movingTo = rooms.getFirst();
			assertNotEquals("The room " + current.getFullDescription() + " did not contain a path to " + movingTo.getFullDescription(), -1, current.getRooms().contains(movingTo));
		}
	}
	
	@Test(timeout = 500)
	public void testFourRoomMaze() {
		RoomInterface r0 = builder.createRoom("An orange room!", "Orange Hallway");
		RoomInterface r1 = builder.createRoom("A red room!", "Red Hallway");
		RoomInterface r2 = builder.createRoom("A blue room!", "Blue Hallway.");
		RoomInterface r3 = builder.createExit("A purple room!", "Purple Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r1, r2).addOneWayPassage(r2, r0).addOneWayPassage(r2, r3).addOneWayPassage(r3, r1);
		Maze maze = new Maze(r0);
		MazeSolutionInterface solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);
		
		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<RoomInterface> rooms = solution.getSolution();
		checkSolution(r0, rooms);
	}
	
	@Test(timeout = 500, expected = UnsolvableMazeException.class)
	public void testFourRoomMazeNoExit() {
		RoomInterface r0 = builder.createRoom("An orange room!", "Orange Hallway");
		RoomInterface r1 = builder.createRoom("A red room!", "Red Hallway");
		RoomInterface r2 = builder.createRoom("A blue room!", "Blue Hallway.");
		RoomInterface r3 = builder.createRoom("A purple room!", "Purple Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r1, r2).addOneWayPassage(r2, r0).addOneWayPassage(r2, r3).addOneWayPassage(r3, r1);
		Maze maze = new Maze(r0);
		MazeSolutionInterface solution = Configuration.getMazeSolution(maze);
		solution.getSolution();
	}
	
	@Test(timeout = 500)
	public void testDeadEndMaze() {
		RoomInterface r0 = builder.createRoom("An orange room!", "Orange Hallway");
		RoomInterface r1 = builder.createRoom("A red room!", "Red Hallway");
		RoomInterface r2 = builder.createRoom("A blue room!", "Blue Hallway.");
		RoomInterface r3 = builder.createExit("A purple room!", "Purple Hallway.");
		builder.addOneWayPassage(r0, r1).addPassage(r0, r2).addPassage(r2, r3);
		Maze maze = new Maze(r0);
		MazeSolutionInterface solution = Configuration.getMazeSolution(maze);
		assertNotNull(
				"You seem to have forgotten to set which MazeSolution to use in the Configuration.getMazeSolution method.",
				solution);
		
		assertEquals("The mazes should match.", maze, solution.getMaze());
		ListInterface<RoomInterface> rooms = solution.getSolution();
		checkSolution(r0, rooms);
	}

}
