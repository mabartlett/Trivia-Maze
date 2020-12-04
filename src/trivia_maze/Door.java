/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.util.Arrays;
import java.util.Objects;

/**
 * This is the class for the doors between rooms. Each door has two rooms. 
 * @author Team 2
 * @version Autumn 2020
 */
public class Door {
	/** The allowed length of the coordinates passed to the constructor. */ 
	public static int COORDINATE_LENGTH = 2; 
	
	/** Represents whether the door has been interacted with by the player. */
	private boolean myLocked;
	
	/** 
	 * Represents whether the door cannot be opened at all by the player. The 
	 * Door would be permalocked if the player answers a question wrong. 
	 */
	private boolean myPermaLocked;
	
	/** The first pair of row, col coordinates between which the Door lies. **/
	// TODO Get rid the coordinates
	private int[] myCoords1;
	
	/** The Second pair of row, col coordinates between which the Door lies. **/
	private int[] myCoords2;
	
	/**
	 * Constructs a door.
	 * @param theCoords1 an int[] of the first pair of coordinates.
	 * @param theCoords2 an int[] of the second pair of coordinates.
	 */
	public Door(final int[] theCoords1, final int[] theCoords2) {
		if (theCoords1.length != COORDINATE_LENGTH || 
				theCoords2.length != COORDINATE_LENGTH || 
				Arrays.equals(theCoords1, theCoords2)) {
			// TODO add messages for illegal argument exceptions.
			throw new IllegalArgumentException();
		} else {
			myCoords1 = Objects.requireNonNull(theCoords1);
			myCoords2 = Objects.requireNonNull(theCoords2);
			myLocked = true;
			myPermaLocked = false;
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
	 * @return whether the Door is perma-locked.
	 */
	public boolean isPermaLocked() {
		return myPermaLocked;
	}
	
	/**
	 * @param thePermaLocked a boolean of the perma-locked state for the Door.
	 */
	public void setPermaLocked(final boolean thePermaLocked) {
		myPermaLocked = Objects.requireNonNull(thePermaLocked);
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
		if (myPermaLocked) {
			result = "x";
		} else {
			result = " ";
		}
		return result;
	}
	
	/**
	 * Compares two objects and performs deep equals
	 * @param theOther the other object to compare
	 * @return true if the two objects are Doors with identical fields.
	 */
	@Override
	public boolean equals(final Object theOther) {
		boolean result;
		if (theOther == this) {
			result = true;
		} else if (theOther == null) {
			result = false;
		} else if (!theOther.getClass().getName().equals(getClass().getName())) {
			result = false;
		} else {
			result = true;
			for (int i = 0; i < ((Door)theOther).getCoordinates().length; i++) {
				for (int j = 0; j < ((Door)theOther).getCoordinates()[i].length;
						j++) {
					if (((Door)theOther).getCoordinates()[i][j] != 
							getCoordinates()[i][j]) {
						result = false;
					}
				}
			}
			result = result && isLocked() == ((Door)theOther).isLocked() &&
						isPermaLocked() == ((Door)theOther).isPermaLocked();
		}
		return result;
	}
}
