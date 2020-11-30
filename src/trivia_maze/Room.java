/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * This is the class for the individual rooms in the maze.
 * @author Team 2
 * @version Autumn 2020
 */
public class Room {
	/** The valid directions that the player can go. **/
	private final String[] VALID_DIRECTIONS = {"n", "s", "e", "w"};
	
	/** The x coordinate of the room, i.e., its column in the array. **/
	private int myX;
	
	/** The y coordinate of the room, i.e., its row in the array. **/
	private int myY;
	
	/** The array of Doors. A room will have 1-4 doors. **/
	private HashMap<String, Door> myDoors;
	
	/** The text that appears to represent each room. */
	private String myText;
	
	/**
	 * Constructs a Room.
	 * @param theX an int representing the room's x coordinate.
	 * @param theY an int representing the room's y coordinate.
	 * @param theDoors a HashMap of 1-4 Strings (directions) to Doors.
	 * @param theText a String of what the text is for the Room.
	 */
	public Room(final int theX, final int theY, 
			final HashMap<String, Door> theDoors, final String theText) {
		if (theX < 1 || theY < 1 || theDoors.keySet().size() < 1 || 
				theDoors.keySet().size() > 4 ) {
			throw new IllegalArgumentException();
		} else {
			// Verify theDoors has no invalid keys.
			final ArrayList<String> directions = new ArrayList<String>();
			for (String i: VALID_DIRECTIONS) {
				directions.add(i);
			}
			for (String i: theDoors.keySet()) {
				if (!directions.contains(i)) {
					throw new IllegalArgumentException();
				}
			}
			myX = Objects.requireNonNull(theX);
			myY = Objects.requireNonNull(theY);
			myDoors = Objects.requireNonNull(theDoors);
			myText = Objects.requireNonNull(theText);
		}
	}
	
	/**
	 * This method is called whenever the player tries to pass through a door. 
	 * It verifies there is a door in the direction and the door is not locked.
	 * @param theDirection a String of the direction, either "n", "s", "e", 
	 * or "w".
	 * @return the new Room the player arrives at by going through the Door if
	 * it can be passed through and null otherwise.
	 */
	public Room passThroughDoor(final String theDirection) {
		Room result = null;
		Door door = myDoors.get(theDirection);
		if (door != null && !door.isLocked()) {
			// Get the other room the door leads to.
			ArrayList<Room> rooms = door.getRooms();
			rooms.remove(this);
			result = rooms.get(0);
		}
		return result;
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
	 * @return the doors
	 */
	public HashMap<String, Door> getDoors() {
		return myDoors;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return myText;
	}
}
