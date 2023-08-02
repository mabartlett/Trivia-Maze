/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import trivia_maze.Door;
import trivia_maze.Room;

/**
 * Tests Room.
 * @author Team 2
 * @version Autumn 2020
 */
public class RoomTest {
	
	/** A valid number for the coordinates. */
	public static final int VALID_COORDINATE = 0;
	
	/** An invalid number for coordinates. */
	public static final int INVALID_COORDINATE = -1;
	
	/** Valid directions. */
	public static final String VALID_DIRECTIONS = "nse";
	
	/** Invalid directions. */
	public static final String INVALID_DIRECTIONS = "qx";
	
	/** A null string. */
	public static final String EMPTY_STRING = "";
	
	/** A valid string for myText. */
	public static final String VALID_STRING = "O";
	
	/** Another valid string for myText. */
	public static final String ANOTHER_VALID_STRING = "X";
	
	/** The Room object used for testing. */
	private Room myRoom;
	
	/** Constructs a valid Room */
	@Before
	public void makeRoom() {
		myRoom = new Room(VALID_COORDINATE, VALID_COORDINATE, VALID_DIRECTIONS,
				VALID_STRING);
	}

	/** Tests the constructor with invalid X. */
	@Test(expected = IllegalArgumentException.class)
	public void constructorXTest() {
		myRoom = new Room(INVALID_COORDINATE, VALID_COORDINATE,
				VALID_DIRECTIONS, VALID_STRING);
	}
	
	/** Tests the constructor with an invalid Y.*/
	@Test(expected = IllegalArgumentException.class)
	public void constructorYTest() {
		myRoom = new Room(VALID_COORDINATE, INVALID_COORDINATE, 
				VALID_DIRECTIONS, VALID_STRING);
	}
	
	/** Tests the constructor with an invalid direction. */
	@Test(expected = IllegalArgumentException.class)
	public void constructorDirectionTest() {
		myRoom = new Room(VALID_COORDINATE, VALID_COORDINATE,
				INVALID_DIRECTIONS, VALID_STRING);
	}
	
	/** Tests the constructor with null directions. */
	@Test(expected = IllegalArgumentException.class)
	public void constructorDirectionNullTest() {
		myRoom = new Room(VALID_COORDINATE, VALID_COORDINATE, EMPTY_STRING, 
				VALID_STRING);
	}
	
	/** Tests the constructor with a null string for theText. */
	@Test(expected = NullPointerException.class)
	public void constructorNullTextTest() {
		myRoom = new Room(VALID_COORDINATE, VALID_COORDINATE, VALID_DIRECTIONS,
				null);
	}
	
	/** Tests the constructor with a null for x coordinate. */
	@Test(expected = NullPointerException.class)
	public void constructorNullDirectionsTest() {
		myRoom = new Room(VALID_COORDINATE, VALID_COORDINATE, 
				null, VALID_STRING);
	}
	
	/** Makes sure that the myDoors field is not null. */
	@Test
	public void myDoorsInitializeNullTest() {
		assertFalse("Constructor left myDoors null.", 
				myRoom.getDoors().equals(null));
	}
	
	/** Makes sure that myDoors is accurate. */
	@Test
	public void myDoorsInitializeTest() {
		HashMap<String, Door> doors = new HashMap<String, Door>();
		for (int i = 0; i < VALID_DIRECTIONS.length(); i++) {
			doors.put(VALID_DIRECTIONS.substring(i, i + 1), new Door());
		}
		assertEquals("Constructor failed to initialize myDoors.", 
				myRoom.getDoors(), doors);
	}
	
	/** Tests passThroughDoor when the door is unlocked. */
	@Test
	public void passThroughDoorTestUnlocked() {
		assertTrue("passThroughDoor() doesn't work right.", 
				myRoom.passThroughDoor(VALID_DIRECTIONS.substring(0, 1)));
	}
	
	/** Tests passThroughDoor when the door is locked. */
	@Test
	public void passThroughDoorTestLocked() {
		myRoom.lockDoor(VALID_DIRECTIONS.substring(0, 1));
		assertFalse("passThroughDoor() fails when trying locked door.",
				myRoom.passThroughDoor(VALID_DIRECTIONS.substring(0, 1)));
	}
	
	/** Tests passThroughDoor when an invalid direction is passed. */
	@Test(expected = IllegalArgumentException.class) 
	public void passThroughDoorTestInvalidDirection() {
		myRoom.passThroughDoor(INVALID_DIRECTIONS.substring(0, 1));
	}
	
	/** Tests lockDoor with invalid input. */
	@Test(expected = IllegalArgumentException.class)
	public void lockDoorTestInvalidArgument() {
		myRoom.lockDoor(INVALID_DIRECTIONS.substring(0, 1));
	}
	
	/** Tests getX */
	@Test
	public void getXTest() {
		assertEquals("getX() failed.", myRoom.getX(), VALID_COORDINATE);
	}
	
	/** Tests getY */
	@Test
	public void getYTest() {
		assertEquals("getY() failed.", myRoom.getY(), VALID_COORDINATE);
	}
	
	/** Tests getDirections() */
	@Test
	public void getDirectionsTest() {
		assertEquals("getDirections() failed.", myRoom.getDirections(), 
				VALID_DIRECTIONS);
	}
	
	/** Tests getText() */
	@Test
	public void getTextTest() {
		assertEquals("getText() failed.", myRoom.getText(), VALID_STRING);
	}
	
	/** Tests setText() */
	@Test
	public void setTextTest() {
		myRoom.setText(ANOTHER_VALID_STRING);
		assertEquals("setText() failed.", myRoom.getText(), 
				ANOTHER_VALID_STRING);
	}
	
	/** Tests setText() with input that's too long. */
	@Test(expected = IllegalArgumentException.class)
	public void setTextTooLongTest() {
		final StringBuilder sb = new StringBuilder();
		sb.append(VALID_STRING);
		for (int i = 0; i < Room.MAX_TEXT_LENGTH; i++) {
			sb.append(VALID_STRING);
		}
		myRoom.setText(sb.toString());
	}
	
	/** Tests setText() with an empty String. */
	@Test(expected = IllegalArgumentException.class)
	public void setTextEmptyTest() {
		myRoom.setText(EMPTY_STRING);
	}
	
	/** Tests toString(). */
	@Test
	public void toStringTest() {
		assertEquals("toString() returned wrong string representation.", 
				myRoom.toString(), VALID_STRING);
	}
	
	/** Tests equals for negative response. */
	@Test
	public void equalsFalseTest() {
		assertFalse("equals() fails check against null.", myRoom.equals(null));
		assertFalse("equals() fails check against different class.", 
				myRoom.equals(new Object()));
		assertFalse("equals() fails check for different x-coordinate.", 
				myRoom.equals(new Room(VALID_COORDINATE + 1, VALID_COORDINATE, 
						VALID_DIRECTIONS, VALID_STRING)));
		assertFalse("equals() fails check for different y-coordinate.", 
				myRoom.equals(new Room(VALID_COORDINATE, VALID_COORDINATE + 1, 
						VALID_DIRECTIONS, VALID_STRING)));
		assertFalse("equals() fails check against different directions.", 
				myRoom.equals(new Room(VALID_COORDINATE, VALID_COORDINATE, 
						VALID_DIRECTIONS.substring(0, 1), VALID_STRING)));
		assertFalse("equals() fails check against different text.", 
				myRoom.equals(new Room(VALID_COORDINATE, VALID_COORDINATE, 
						VALID_DIRECTIONS, ANOTHER_VALID_STRING)));
		final Room r = new Room(VALID_COORDINATE, VALID_COORDINATE, 
				VALID_DIRECTIONS, VALID_STRING);
		r.lockDoor(VALID_DIRECTIONS.substring(0, 1));
		assertFalse("equals() fails check against different Doors.", 
				myRoom.equals(r));
	}
	
	/** Tests equal for positive response. */
	@Test
	public void equalsTrueTest() {
		assertTrue("equals() fails check against self.", myRoom.equals(myRoom));
		final Room r = new Room(VALID_COORDINATE, VALID_COORDINATE, 
				VALID_DIRECTIONS, VALID_STRING);
		assertTrue("equals() fails check against fields.", myRoom.equals(r));
	}
	
	
}
