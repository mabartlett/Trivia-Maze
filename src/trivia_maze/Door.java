/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.util.ArrayList;
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
	private ArrayList<Room> myRooms;
	
	/**
	 * Constructs a door.
	 * @param theRooms the two rooms this door is between.
	 */
	public Door(final ArrayList<Room> theRooms) {
		if (theRooms.size() != 2) {
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
	 * Locks or unlocks the door according to the passed argument.
	 * @param theLocked true locks the door and false unlocks it.
	 */
	public void setLocked(final boolean theLocked) {
		myLocked = Objects.requireNonNull(theLocked);
	}

	/**
	 * @return the rooms
	 */
	public ArrayList<Room> getRooms() {
		return myRooms;
	}
	
	/**
	 * A locked door appears as an "x" in the console and as a space unlocked.
	 * @return a String representation of the Door.
	 */
	public String toString() {
		String result;
		if (myLocked) {
			result = "x";
		} else {
			result = " ";
		}
		return result;
	}
}
