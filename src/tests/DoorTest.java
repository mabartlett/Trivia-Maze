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
	 * Sets up a Door for testing.
	 */
	@Before
	public void setUp() {
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
		final Door door = new Door(a, b);
		
	}
	
	/**
	 * Tests the constructor for second illegal argument.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorArgTest2() {
		final int[] a = {0};
		final int[] b = {0, 1};
		final Door door = new Door(b, a);
	}
	
	/**
	 * Tests the constructor when both arguments are equal.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void constructorArgTest3() {
		final int[] a = {0, 0};
		final int[] b = {0, 0};
		final Door door = new Door(a, a);
	}
}
