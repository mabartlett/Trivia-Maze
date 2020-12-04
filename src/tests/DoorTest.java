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
		final int[] a = {0, 0};
		final int[] b = {0, 1};
		myDoorTest = new Door(a, b);
	}
	
	/**
	 * Tests constructor.
	 */
	@Test
	public void constructorTest() {
		final int[] a = {0, 0};
		final int[] b = {0, 1};
		final Door door = new Door(a, b);
		assertEquals("Constructor method failed to initalize fields properly.",
				door, myDoorTest);
	}
	
	/**
	 * Tests the constructor for first illegal argument.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorArgTest1() {
		final int[] a = {0};
		final int[] b = {0, 1};
		myDoorTest = new Door(a, b);
	}
	
	/**
	 * Tests the constructor for second illegal argument.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorArgTest2() {
		final int[] a = {0};
		final int[] b = {0, 1};
		myDoorTest = new Door(b, a);
	}
	
	/**
	 * Tests the constructor when both arguments are equal.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorArgTest3() {
		final int[] a = {0, 0};
		final int[] b = {0, 0};
		myDoorTest = new Door(a, b);
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
		assertTrue("equals() fails to check for reflexivity.", 
				myDoorTest.equals(myDoorTest));
		assertFalse("equals() fails to check against null.", 
				myDoorTest.equals(null));
		final Object object = new Object();
		assertFalse("equals() fails to check against different classes.", 
				myDoorTest.equals(object));
		final int[] a = {0, 0};
		final int[] b = {0, 1};
		final Door door = new Door(a, b);
		assertTrue("equals() fails to compare all fields.", 
				myDoorTest.equals(door));
		final int[] c = {1, 1};
		final Door anotherDoor = new Door(b, c);
		assertFalse("equals() fails to compare all fields.", 
				myDoorTest.equals(anotherDoor));
	}
}
