/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * This is the class for the individual rooms in the maze.
 * @author Team 2
 * @version Autumn 2020
 */
public class Room implements Serializable{
	/** The serial version UID. */
	private static final long serialVersionUID = 2L;
	
	/** The valid directions that the player can go. **/
	public static final String[] VALID_DIRECTIONS = {"n", "s", "e", "w"};
	
	/** The allowed length for the myText field. */
	public static final int MAX_TEXT_LENGTH = 3;
	
	/** The x coordinate of the room, i.e., its column in the array. **/
	private int myX;
	
	/** The y coordinate of the room, i.e., its row in the array. **/
	private int myY;

	/** A String representing the directions from the room that have doors. */
	private String myDirections;
	
	/** A map of one of the valid directions to a door. */
	private HashMap<String, Door> myDoors;

	/** The text that appears to represent each room. */
	private String myText;
	
	/**
	 * Constructs a Room.
	 * @param theX an int representing the room's x coordinate or column.
	 * @param theY an int representing the room's y coordinate or row.
	 * @param theDirections a String containing only "n", "s", "e", or "w".
	 * @param theText a String of what the text is for the Room.
	 */
	public Room(final int theX, final int theY, final String theDirections,
			final String theText) {
		if (theX < 0) {
			throw new IllegalArgumentException("Room's x coordinate cannot be "
					+ "negative.");
		} else if (theY < 0) {
			throw new IllegalArgumentException("Room's y coordinate cannot be "
					+ "negative.");
		} else if ("".equals(theDirections)) {
			throw new IllegalArgumentException("Room must have at least 1 "
					+ "door.");
		} else {
			// Verify theDirections has no invalid directions.
			boolean valid;
			for (int i = 0; i < theDirections.length(); i++) {
				valid = false;
				for (String j: VALID_DIRECTIONS) {
					if (j.equals(theDirections.substring(i, i + 1))) {
						valid = true;
					}
				}
				if (!valid) {
					throw new IllegalArgumentException("Room was passed an "
							+ "illegal direction");
				}
			}
			myX = Objects.requireNonNull(theX);
			myY = Objects.requireNonNull(theY);
			myDirections = Objects.requireNonNull(theDirections);
			myText = Objects.requireNonNull(theText);
			myDoors = new HashMap<String, Door>();
			// Add the doors.
			for (int i = 0; i < myDirections.length(); i++) {
				myDoors.put(myDirections.substring(i, i + 1), new Door());
			}
		}
	}

	/**
	 * This method is called whenever the player tries to pass through a door. 
	 * @param theDirection the direction the player is trying to go to.
	 * @return true if the player passed through the door and false otherwise.
	 */
	public boolean passThroughDoor(final String theDirection) {
		boolean result = true;
		if (!verifyDirection(theDirection)) {
			throw new IllegalArgumentException();
		} else if (myDoors.get(theDirection) == null) {
			result = false;
		} else if (myDoors.get(theDirection).isPermaLocked()) {
			result = false;
		}
		return result;
	}
	
	/**
	 * Locks a door to the appropriate direction.
	 * @param theDirection a String of the direction, either "n", "s", "e", 
	 * or "w".
	 */
	public void lockDoor(final String theDirection) {
		if (verifyDirection(theDirection)) {
			final Door door = myDoors.get(theDirection);
			door.setPermaLocked(true);
		} else {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return myX;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return myY;
	}

	/**
	 * @return the directions
	 */
	public String getDirections() {
		return myDirections;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return myText;
	}
	
	/** @return the doors */
	public HashMap<String, Door> getDoors() {
		return myDoors;
	}
	
	/**
	 * Change the text representation of the room.
	 * @param theText the text with which the Room will be represented. It must
	 * be an empty string or a String longer than 1 character.
	 */
	public void setText(final String theText) {
		if (theText.length() > MAX_TEXT_LENGTH) {
			throw new IllegalArgumentException("String is too long.");
		} else if ("".equals(theText)) {
			throw new IllegalArgumentException("String must not be empty.");
		} else {
			myText = Objects.requireNonNull(theText);
		}
	}
	
	/**
	 * @return a String representation of the Room object.
	 */
	@Override
	public String toString() {
		return myText;
	}
	
	/**
	 * @return Whether the fields are the same for another Room and this one.
	 * @param theOther the other object to compare it to.
	 */
	@Override
	public boolean equals(final Object theOther) {
		boolean result;
		if (theOther == this) {
			result = true;
		} else if (theOther == null) {
			result = false;
		} else if (!theOther.getClass().getName().equals(getClass().
				getName())) {
			result = false;
		} else {
			result = myX == ((Room)theOther).getX();
			result = result && myY == ((Room)theOther).getY();
			result = result && myDirections.equals(((Room)theOther).
					getDirections());
			result = result && myText.equals(((Room)theOther).getText());
			result = result && myDoors.equals(((Room)theOther).getDoors());
		}
		return result;
	}
	
	/**
	 * Verify that the direction is valid, i.e., either "n", "s", "e", or "w".
	 * @param theDirection the String to test.
	 * @return Whether the direction is valid.
	 */
	private boolean verifyDirection(final String theDirection) {
		boolean result = false;
		for (String s: VALID_DIRECTIONS) {
			if (s.equals(theDirection)) {
				result = true;
				break;
			}
		}
		return result;
	}
}
