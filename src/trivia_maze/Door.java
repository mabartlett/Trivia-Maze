/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is the class for the doors between rooms. Each door has two rooms. 
 * @author Team 2
 * @version Autumn 2020
 */
public class Door implements Serializable {
	public static final long serialVersionUID = 3L;
	
	/** Represents whether the door has been interacted with by the player. */
	private boolean myLocked;
	
	/** 
	 * Represents whether the door cannot be opened at all by the player. The 
	 * Door would be permalocked if the player answers a question wrong. 
	 */
	private boolean myPermaLocked;
	
	/**
	 * Constructs a door.
	 */
	public Door() {
			myLocked = true;
			myPermaLocked = false;
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
			result = isLocked() == ((Door)theOther).isLocked() &&
						isPermaLocked() == ((Door)theOther).isPermaLocked();
		}
		return result;
	}
}