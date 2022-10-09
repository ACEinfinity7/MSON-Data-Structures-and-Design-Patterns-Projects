package maze;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTest {

	private Room room;

	private String full = "You open the door, and the reek " + "of garbage assaults your nose. Looking inside, "
			+ "you see a pile of refuse and offal that nearly " + "reaches the ceiling. In the ceiling above it is "
			+ "a small hole that is roughly as wide as two human "
			+ "hands. No doubt some city dweller high above disposes "
			+ "of his rubbish without ever thinking about where it goes.";
	
	private String basic = "A smelly room.";
	
	@Test
	public void testCreateRoom() {
		room = new Room(basic, full, false);
		
		assertEquals(basic, room.getShortDescription());
		assertEquals(full, room.getFullDescription());
		assertFalse(room.isExit());
		assertTrue(room.getRooms().isEmpty());
		assertEquals(0,room.getRooms().size());
		
	}
	
	@Test
	public void testCreateExit() {
		room = new Room(basic, full, true);
		
		assertTrue(room.isExit());
	}
	
	
}