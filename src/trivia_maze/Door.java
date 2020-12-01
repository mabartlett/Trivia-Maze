/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.util.Objects;

/**
 * This is the class for the doors between rooms. Each door has two rooms. 
 * @author Team 2
 * @version Autumn 2020
 */
public class Door {
	/** Represents whether the door has been locked. */
	private boolean myLocked;
	
	/** The first pair of coordinates between which the Door lies. **/
	private int[] myCoords1;
	
	/** The Second pair of coordinates between which the Door lies. **/
	private int[] myCoords2;
	
	/**
	 * Constructs a door.
	 * @param theCoords1 an int[] of the first pair of coordinates.
	 * @param theCoords2 an int[] of the second pair of coordinates.
	 */
	public Door(final int[] theCoords1, final int[] theCoords2) {
		if (theCoords1.length != 2 || theCoords2.length != 2 || 
				theCoords1.equals(theCoords2)) {
			myCoords1 = Objects.requireNonNull(theCoords1);
			myCoords2 = Objects.requireNonNull(theCoords2);
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
	 * Get the pairs of coordinates between which the door lies.
	 * @return a 2D array of the 2 coordinate pairs for this Door.
	 */
	public int[][] getCoordinates() {
		final int[][] result = {myCoords1, myCoords2};
		return result;
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
