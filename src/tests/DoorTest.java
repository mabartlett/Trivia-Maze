/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import trivia_maze.Door;

/**
 * This is the unit test class for Door.
 * @author Team 2
 * @version Autumn 2020
 */
public class DoorTest {
	
	/** The Door used for testing. **/
	private Door myDoorTest;
	
	/**
	 * Makes a door with fully legal arguments.
	 */
	@Before
	public void makeDoor() {
		myDoorTest = new Door();
	}
	
	/**
	 * Tests constructor.
	 */
	@Test
	public void constructorTest() {
		final Door door = new Door();
		assertEquals("Constructor method failed to initalize fields properly.",
				door, myDoorTest);
	}
	
	/**
	 * Verifies the setLocked() method.
	 */
	@Test
	public void setLockedTest() {
		myDoorTest.setLocked(true);
		assertTrue("setLocked() failed to set myLocked correctly.", 
				myDoorTest.isLocked());
	}
	
	/**
	 * Verifies the setPermaLocked() method.
	 */
	@Test 
	public void setPermaLockedTest() {
		 myDoorTest.setPermaLocked(true);
		 assertTrue("setLocked() failed to set myPermaLocked correctly.", 
				 myDoorTest.isPermaLocked());
	}
	
	/**
	 * Tests toString() 
	 */
	@Test
	public void toStringTest() {
		assertEquals("toString() returned wrong String.", " ", 
				myDoorTest.toString());
		myDoorTest.setPermaLocked(true);
		assertEquals("toString() returned wrong String.", "x", 
				myDoorTest.toString());
	}
	
	/**
	 * Tests equals()
	 */
	@Test
	public void equalsTest() {
		assertTrue("equals() failed to check for reflexivity.", 
				myDoorTest.equals(myDoorTest));
		assertFalse("equals() failed to check against null.", 
				myDoorTest.equals(null));
		final Object object = new Object();
		assertFalse("equals() failed to check against different classes.", 
				myDoorTest.equals(object));
		final Door door = new Door();
		assertTrue("equals() failed to compare same fields.", 
				myDoorTest.equals(door));
		door.setLocked(false);
		assertFalse("equals() failed to compare different fields.", 
				myDoorTest.equals(door));
	}
}
