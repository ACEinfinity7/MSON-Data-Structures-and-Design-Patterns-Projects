package maze;

public class MazeBuilder implements MazeBuilderInterface {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RoomInterface createRoom(String longDescription, String shortDescription) {
		if (shortDescription == null || longDescription == null) {
			throw new NullPointerException("Description cannot be null.");
		}
		return new Room(shortDescription, longDescription, false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RoomInterface createExit(String longDescription, String shortDescription) {
		if (shortDescription == null || longDescription == null) {
			throw new NullPointerException("Description cannot be null.");
		}
		return new Room(shortDescription, longDescription, true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MazeBuilderInterface addPassage(RoomInterface room0, RoomInterface room1) {
		if (room0 == null || room1 == null) {
			throw new NullPointerException("Room cannot be null.");
		}

		if (room0.getRooms().contains(room1) == -1)
			room0.getRooms().insertLast(room1);

		if (room1.getRooms().contains(room0) == -1) {
			room1.getRooms().insertLast(room0);
		}

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public MazeBuilderInterface addOneWayPassage(RoomInterface fromRoom, RoomInterface toRoom) {
		if (fromRoom == null || toRoom == null) {
			throw new NullPointerException("Room cannot be null.");
		}

		if (fromRoom.getRooms().contains(toRoom) == -1) {
			fromRoom.getRooms().insertLast(toRoom);
		}

		return this;
	}

}
