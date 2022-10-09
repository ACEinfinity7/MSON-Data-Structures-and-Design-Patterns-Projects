package maze;


/**
 * A {@link MazeBuilderInterface} provides a set of methods for connecting {@link RoomInterface}s
 * in a way that is manageable for constructing a {@link Maze}.
 *
 * @author jcollard, jddevaug
 *
 */
public interface MazeBuilderInterface {

  /**
   * <p>
   * Creates a {@link RoomInterface} with the specified descriptions.
   * </p>
   * <p>
   * The returned {@link RoomInterface} is guaranteed to have no {@link RoomInterface}s in the
   * {@link ListInterface} returned by its {@link RoomInterface#getRooms()} method.
   * </p>
   * <p>
   * The returned {@link RoomInterface}'s {@link RoomInterface#isExit()} method must return
   * {@code false}.
   * </p>
   *
   * @param longDescription
   *            the long description for the {@link RoomInterface}
   * @param shortDescription
   *            the short description for the {@link RoomInterface}
   * @return a newly created {@link RoomInterface} with the specified descriptions.
   * @throws NullPointerException
   *             if {@code longDescription} or {@code shortDescription} are
   *             {@code null}.
   */
  RoomInterface createRoom(String longDescription, String shortDescription);

  /**
   * <p>
   * Creates a {@link RoomInterface} with the specified descriptions.
   * </p>
   * <p>
   * The returned {@link RoomInterface} is guaranteed to have no {@link RoomInterface}s in the
   * {@link ListInterface} returned by its {@link RoomInterface#getRooms()} method.
   * </p>
   * <p>
   * The returned {@link RoomInterface}'s {@link RoomInterface#isExit()} method must return
   * {@code true}.
   * </p>
   *
   * @param longDescription
   *            the long description for the {@link RoomInterface}
   * @param shortDescription
   *            the short description for the {@link RoomInterface}
   * @return a newly created {@link RoomInterface} with the specified descriptions.
   * @throws NullPointerException
   *             if {@code longDescription} or {@code shortDescription} are
   *             {@code null}.
   */
  RoomInterface createExit(String longDescription, String shortDescription);

  /**
   * <p>
   * Creates a passage between {@code room0} and {@code room1} such that
   * {@code room1} is added to the {@link ListInterface} returned by
   * {@code room0}'s {@link RoomInterface#getRooms()} method and {@code room0} is added
   * to the {@link ListInterface} returned by {@code room1}'s
   * {@link RoomInterface#getRooms()} method.
   * </p>
   * <p>
   * If {@code room0} is already in {@code room1}'s {@link RoomInterface#getRooms()}'s
   * list then {@code room1} should not be modified.
   * </p>
   * <p>
   * If the {@code room1} is already in {@code room0}'s
   * {@link RoomInterface#getRooms()}'s {@link ListInterface} then {@code room0} should
   * not be modified.
   * </p>
   * <p>
   * For convenience, this {@link MazeBuilderInterface} is returned
   * </p>
   *
   * @param room0
   *            the first of two {@link RoomInterface}s to be connected
   * @param room1
   *            the second of two {@link RoomInterface}s to be connected
   * @return this {@link MazeBuilderInterface}
   * @throws NullPointerException
   *             if {@code room0} or {@code room1} are {@code null}
   */
  MazeBuilderInterface addPassage(RoomInterface room0, RoomInterface room1);

  /**
   * <p>
   * Creates a passage from {@code fromRoom} to {@code toRoom} such that
   * {@code toRoom} is added to the {@link ListInterface} returned by
   * {@code fromRoom}'s {@link RoomInterface#getRooms()} method.
   * </p>
   * <p>
   * If {@code toRoom} is already in {@code fromRoom}'s
   * {@link RoomInterface#getRooms()}'s {@link ListInterface} then {@link fromRoom}
   * should not be modified.
   * </p>
   * <p>
   * For convenience, this {@link MazeBuilderInterface} is returned
   *
   * @param fromRoom
   *            the room from which the passage is accessible
   * @param toRoom
   *            the room for which the passage goes to
   * @return this {@link MazeBuilderInterface}
   * @throws NullPointerException
   *             if {@code fromRoom} or {@code toRoom} are {@code null}
   */
  MazeBuilderInterface addOneWayPassage(RoomInterface fromRoom, RoomInterface toRoom);

}
