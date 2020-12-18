/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package tests;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import trivia_maze.Maze;

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
	
	/** The answer to the mock question. */
	public static final String QUESTION_ANSWER = "yes";
	
	/** The answer to the move menu prompt. */
	public static final String RESPONSE = "q";
	
	/** An invalid response to the move menu prompt. */
	public static final String INVALID_RESPONSE = "grok";
	
	/** The Maze instance being tested. */
	private Maze myMaze;
	
	/**
	 * Creates a Maze class for testing.
	 */
	@Before
	public void setUp() {
		myMaze = new Maze(VALID_LENGTH, VALID_LENGTH, null);
	}

	/**
	 * Test method for {@link trivia_maze.Maze#printMaze()}.
	 */
	@Test
	public void testPrintMaze() {
		myMaze.printMaze();
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
		assertTrue("getCurrentRoom() returned Room with wrong coordinates.", 
				result);
	}

	/**
	 * Test method for {@link trivia_maze.Maze#getExitRoom()}.
	 */
	@Test
	public void testGetExitRoom() {
//		assertEquals("getExitRoom() returned the wrong room.", myMaze.getExitRoom(), 
//				myMaze.getMaze()[VALID_LENGTH - 1][VALID_LENGTH - 1]);
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
	// Don't look at the line directly below. It's... it's too horrible!
	@Test(expected = NoSuchElementException.class)
	public void testInitMaze() {
		setIn("x 3 y 3 5 5 q");
//		myMaze.initMaze();
	}

	/**
	 * Test method for {@link trivia_maze.Maze#playGame()}.
	 */
	@Test
	public void testPlayGame() {
		setIn(RESPONSE);
		myMaze.playGame();
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
		setIn(RESPONSE);
		myMaze.moveMenu();
	}

	/**
	 * Test method for {@link trivia_maze.Maze#outOfBounds()}.
	 */
	@Test
	public void testOutOfBounds() {
		setIn(RESPONSE);
		myMaze.outOfBounds();
	}

	/**
	 * Test method for {@link trivia_maze.Maze#passedRoom()}.
	 */
	@Test
	public void testPassedRoom() {
		setIn(RESPONSE);
//		myMaze.passedRoom();
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
		setIn(RESPONSE);
		myMaze.moveNorth();
		setIn(QUESTION_ANSWER + "\n" + RESPONSE);
		myMaze.moveSouth();
		setIn(RESPONSE);
		myMaze.moveNorth();
		
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveSouth()}.
	 */
	@Test
	public void testMoveSouth() {
		setIn(QUESTION_ANSWER + "\n" + RESPONSE);
		myMaze.moveSouth();
		myMaze.getCurrentRoom().equals(
				myMaze.getMaze()[1][0]);
		for (int i = 0; i < VALID_LENGTH - 2; i++) {
			setIn(QUESTION_ANSWER + "\n" + RESPONSE);
			myMaze.moveSouth();
		}
		setIn(RESPONSE);
		myMaze.moveSouth();
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveEast()}.
	 */
	@Test
	public void testMoveEast() {
		setIn(QUESTION_ANSWER + "\n" + RESPONSE);
		myMaze.moveEast();
		myMaze.getCurrentRoom().equals(
				myMaze.getMaze()[0][1]);
		setIn(RESPONSE);
		myMaze.moveWest();
		setIn(RESPONSE);
		myMaze.moveEast();
	}

	/**
	 * Test method for {@link trivia_maze.Maze#moveWest()}.
	 */
	@Test
	public void testMoveWest() {
		setIn(RESPONSE);
		myMaze.moveWest();
		setIn(QUESTION_ANSWER + "\n" + RESPONSE);
		myMaze.moveEast();
		setIn(RESPONSE);
		myMaze.moveWest();
	}

	/**
	 * Test method for {@link trivia_maze.Maze#move(java.lang.String)}.
	 */
	@Test
	public void testMove() {
		setIn(RESPONSE);
		myMaze.move("w");
		setIn(QUESTION_ANSWER + "\n" + RESPONSE);
		myMaze.move("s");
		setIn(RESPONSE);
		myMaze.move("a");
		setIn(QUESTION_ANSWER + "\n" + RESPONSE);
		myMaze.move("d");
		myMaze.move("q");
		myMaze.move("v");
		setIn(RESPONSE);
		myMaze.move(INVALID_RESPONSE);
	}
	
	/**
	 * Sets a given string as input to System. I used this page for reference: 
	 * https://stackoverflow.com/questions/6415728
	 * By the way, this is super cool and I didn't know you could do this!
	 * @param theString the string which is to be used as input
	 */
	private void setIn(final String theString) {
		ByteArrayInputStream bais = new ByteArrayInputStream(
				(theString).getBytes());
		System.setIn(bais);
	}

}
