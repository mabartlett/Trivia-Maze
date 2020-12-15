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
import trivia_maze.Maze;
import trivia_maze.Room;

/**
 * This class tests Maze.
 * @author Team 2
 * @version Autumn 2020
 */
public class MazeTest {
	/** A length for rows/cols that is valid. */
	public static final int VALID_LENGTH = 5;
	
	/** A length for rows/cols that is invalid. */
	public static final int INVALID_LENGTH = 3;
	
	/** This is where the player starts. */
	public static final int START_COORDINATE = 0;
	
	/** The Maze instance being tested. */
	private Maze myMaze;
	
	/**
	 * Creates a Maze class for testing.
	 */
	@Before
	public void setUp() {
		myMaze = new Maze(VALID_LENGTH, VALID_LENGTH);
	}

	/**
	 * Test method for {@link trivia_maze.Maze#Maze(int, int)}.
	 */
	@Test
	public void testMazeIntInt() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#Maze(trivia_maze.Room[][])}.
	 */
	@Test
	public void testMazeRoomArrayArray() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#printMaze()}.
	 */
	@Test
	public void testPrintMaze() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#getRows()}.
	 */
	@Test
	public void testGetRows() {
		assertEquals("getRows() returns wrong number of rows.", VALID_LENGTH, 
				myMaze.getRows());
	}

	/**
	 * Test method for {@link trivia_maze.Maze#getColumns()}.
	 */
	@Test
	public void testGetColumns() {
		assertEquals("getColumns() returns wrong number of columns.", 
				VALID_LENGTH, myMaze.getColumns());
	}

	/**
	 * Test method for {@link trivia_maze.Maze#getMaze()}.
	 */
	@Test
	public void testGetMaze() {
		// TODO
		fail("Not yet implemented");
//		boolean result = false;
//		final Room[][] testRooms = new Room[VALID_LENGTH][VALID_LENGTH];
//		for (int i = 0; i < VALID_LENGTH; i++) {
//			for (int j = 0; j < VALID_LENGTH; j++) {
//				result = myMaze.getMaze()[i][j].equals(testRooms[i][j]);
//			}
//		}
//		assertTrue("getMaze() returned wrong Rooms matrix.", result);
	}

	/**
	 * Test method for {@link trivia_maze.Maze#getCurrentRoom()}.
	 */
	@Test
	public void testGetCurrentRoom() {
		boolean result = myMaze.getCurrentRoom().getX() == START_COORDINATE;
		result = result && myMaze.getCurrentRoom().getY() == START_COORDINATE;
		System.out.println(myMaze.getCurrentRoom());
		assertTrue("getCurrentRoom() returned Room with wrong coordinates.", 
				result);
	}

	/**
	 * Test method for {@link trivia_maze.Maze#getExitRoom()}.
	 */
	@Test
	public void testGetExitRoom() {
		System.out.println(myMaze.getExitRoom());
		assertEquals("getExitRoom() returned the wrong room.", myMaze.getExitRoom(), 
				myMaze.getMaze()[VALID_LENGTH - 1][VALID_LENGTH - 1]);
	}

	/**
	 * Test method for {@link trivia_maze.Maze#saveGame()}.
	 */
	@Test
	public void testSaveGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#initMaze()}.
	 */
	@Test
	public void testInitMaze() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#playGame()}.
	 */
	@Test
	public void testPlayGame() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#askQuestion()}.
	 */
	@Test
	public void testAskQuestion() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveMenu()}.
	 */
	@Test
	public void testMoveMenu() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#outOfBounds()}.
	 */
	@Test
	public void testOutOfBounds() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#passedRoom()}.
	 */
	@Test
	public void testPassedRoom() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#setRooms()}.
	 */
	@Test
	public void testSetRooms() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveNorth()}.
	 */
	@Test
	public void testMoveNorth() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveSouth()}.
	 */
	@Test
	public void testMoveSouth() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveEast()}.
	 */
	@Test
	public void testMoveEast() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveWest()}.
	 */
	@Test
	public void testMoveWest() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for {@link trivia_maze.Maze#move(java.lang.String)}.
	 */
	@Test
	public void testMove() {
		fail("Not yet implemented"); // TODO
	}

}
