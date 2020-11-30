/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */

import java.util.Objects;

/**
 * This is the class for the doors between rooms. Each door has two rooms. 
 * @author Team 2
 * @version Autumn 2020
 */
public class Door {
	/** Represents whether the door has been locked. */
	private boolean myLocked;
	
	/** The two rooms this door passes through. */
	private Room[] myRooms;
	
	/**
	 * Constructs a door.
	 * @param theRooms the two rooms this door is between.
	 */
	public Door(final Room[] theRooms) {
		if (theRooms.length != 2) {
			throw new IllegalArgumentException();
		} else {
			myRooms = Objects.requireNonNull(theRooms);
		}
	}
	
	/**
	 * @return whether or not the door has been locked.
	 */
	public boolean isLocked() {
		return myLocked;
	}

	/**
	 * @return the rooms
	 */
	public Room[] getRooms() {
		return myRooms;
	}
}