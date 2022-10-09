package maze;

import structure.ListInterface;
import structure.RecursiveLinkedList;

public class Room implements RoomInterface {
	
	/**
	 * Strings to hold short and full descriptions
	 */
	private String shortDesc, fullDesc;
	
	/**
	 * List of all adjoining rooms for this room
	 */
	private ListInterface<RoomInterface> rooms;
	
	/**
	 * true if an exit, false if not
	 */
	private boolean isExit;
	
	/**
	 * Constructor
	 * @param shortDesc
	 * @param fullDesc
	 */
	public Room(String shortDesc, String fullDesc, boolean isExit) {
		this.shortDesc = shortDesc;
		this.fullDesc = fullDesc;
		this.isExit = isExit;
		rooms = new RecursiveLinkedList<RoomInterface>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getFullDescription() {
		return fullDesc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getShortDescription() {
		return shortDesc;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExit() {
		return isExit;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ListInterface<RoomInterface> getRooms() {
		return rooms;
	}

}
