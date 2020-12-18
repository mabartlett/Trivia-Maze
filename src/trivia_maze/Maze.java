/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package trivia_maze;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * This is the class for the maze itself.
 * @author Team 2
 * @version Autumn 2020
 */
public class Maze implements Serializable {
	/** The ID for serialization. */
	private static final long serialVersionUID = 1L;
	
	/** Represents the player. */
	public final String PLAYER_STRING = "P";
	
	/** Represents the exit of the maze. */
	public final String EXIT_STRING = "E";
	
	/** Represents each room. */
	public final String ROOM_STRING = "O";
	
	/** Represents each room. */
	public final String PASSEDROOM_STRING = "-";
	
	/** The number of rows in the Maze's 2D array of Rooms. */
	private int myRows;
	
	/** The number of columns in the Maze's 2D array of Rooms. */
	private int myColumns;
	
	/** The 2D array of Rooms that composes the maze. */
	private Room[][] myMaze;
	
	/** The Room in which the player currently is. */
	private Room myCurrentRoom;
	
	/** The next Room the player wants to move to. */
	private Room myNextRoom;
	
	/** The name of the save file. */
	private String mySavePath = "SavedGame.ser";
	
	/** The row in which the player currently is. */
	private int myPlayerRow;
	
	/** The column in which the player currently is. */
	private int myPlayerColumn;
	
	/** The row in which the exit is. */
	private int myExitRow;
	
	/** The column in which the exit is. */
	private int myExitColumn;
	
	/** The current result of the game. */
	private boolean myGameOver = false;
	
	/** The next Room's row. */
	private int myNextRoomRow;
	
	/** The next Room's column. */
	private int myNextRoomColumn;
	
	/** The user's input for the movement. */
	private static String myInput = "";
	
	/** Checks whether the user's answer to the question is correct. */
	private boolean myIncorrectAnswer;
	
	/**
	 * Constructs a Maze
	 * @param theRows the number of rows
	 * @param theColumns the number of columns
	 */
	public Maze(final int theRows, final int theColumns) {
		/*The input is guaranteed to be well-formed by the driver program, so 
		 * we have elected not to have input validation here. */
		myRows = theRows;
		myColumns = theColumns;
		myMaze = new Room[myRows][myColumns];
		myPlayerRow = 0;
		myPlayerColumn = 0;
		myExitRow = myRows - 1; 
		myExitColumn = myColumns - 1;
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		initializeMaze();
	}
	
	/**
	 * Constructs a Maze with an already made 2D array of Rooms.
	 * @param theRooms the 2D array of Rooms to make the maze out of
	 */
	public Maze(final Room[][] theRooms) {
		myMaze = theRooms;
	}
	
	/**
	 * Prints the Maze to the console.
	 */
	public void printMaze() {
		final StringBuilder sb = new StringBuilder();
		for (int a = 0; a < myMaze.length; a++) {
			for (int b = 0; b < myMaze[0].length; b++) {
				sb.append(myMaze[a][b].toString());
				if (b < myMaze[0].length - 1) {
					sb.append(myMaze[a][b].getDoors().get("e").toString());
				}
			}
			sb.append("\n");
			for (int b = 0; b < myMaze[0].length; b++) {
				if (a < myMaze.length - 1) {
					sb.append(myMaze[a][b].getDoors().get("s").toString());
					sb.append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	/** 
	 * @return the number of rows in the maze.
	 */
	public int getRows() {
		return myRows;
	}
	
	/**
	 * @return the number of columns in the maze.
	 */
	public int getColumns() {
		return myColumns;
	}
	
	/**
	 * @return the 2D array of Rooms itself.
	 */
	public Room[][] getMaze() {
		return myMaze;
	}
	
	/**
	 * @return the Room in which the player currently is
	 */
	public Room getCurrentRoom() {
		return myCurrentRoom;
	}
	
	/** @return the next room */
	public Room getNextRoom() {
		return myNextRoom;
	}
		
	/**
	 * Saves the game.
	 */
	public void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream(mySavePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
			System.out.println("Game data has been saved.");
		} catch (IOException i) {
			System.out.println("Game data could not be saved.");
			System.out.println(i);
		}
	}
	
	/**
	 * This method contains the main loop of the game.
	 */
	public void playGame() {
		while (myGameOver == false) {
			System.out.println("\nCurrent maze:");
			printMaze();
			
			if (myPlayerRow == myExitRow && myPlayerColumn == myExitColumn) {
				System.out.println("Congrats! You won the game!");
				myGameOver = true;
				break;
			}
			
			if (playerTrapped()) {
				System.out.println("\nYou are trapped! Game is now over.");
				break;
			}
			
			move(moveMenu());
		}
	}
	
	/**
	 * Prompts the player for an answer to a question
	 */
	public void askQuestion() {
		// TODO lock door when answered wrong.
		Scanner test = new Scanner(System.in);
		String answer = "y";
		String input2 = "";
		
		System.out.println("Question 1: Do you want to move to the next room?");
		input2 = test.next().toLowerCase();
		
		if (input2.equals(answer)) {
			System.out.println("Correct!");
			myIncorrectAnswer = false;
		} else {
			System.out.println("Incorrect answer. Door is now permanently locked.");
			myIncorrectAnswer = true;
		}
	}
	
	/**
	 * Displays the move menu and prompts for input
	 * @return what the player entered
	 */
	public String moveMenu() {
		Scanner test2 = new Scanner(System.in);
		// TODO Get rid of the scanners all over the place and make it a field.
		System.out.println("W) North");
		System.out.println("S) South");
		System.out.println("D) East");
		System.out.println("A) West");
		System.out.println("V) Save");
		System.out.println("Q) Quit");
		myInput = test2.next().toLowerCase();
		return myInput;
	}
	
	/**
	 * Displays a message about the indices being out of bound.
	 */
	public void outOfBounds() {
		System.out.println("Out of bounds. Please try again.\n");
		printMaze();
		move(moveMenu());
	}
	
	/**
	 * Sets the current room as passed, and changes the
	 * player's new room to the next room.
	 */
	public void setRooms() {
		myNextRoom.setText(PLAYER_STRING);
		myCurrentRoom.setText(PASSEDROOM_STRING);
	}
	
	/**
	 * Tries to move the player in a given direction
	 * @param theDirection the direction the player wants to go, either "n", 
	 * "s", "e", or "w".
	 */
	public void move(final String theDirection) {
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		if ("w".equals(theDirection)) {
			moveNorth();
		} else if ("s".equals(theDirection)) {
			moveSouth();
		} else if ("d".equals(theDirection)) {
			moveEast();
		} else if ("a".equals(theDirection)) {
			moveWest();
		} else if ("q".equals(theDirection)) {
			System.out.println("Sorry to see you go. Goodbye!");
			myGameOver = true;
		} else if ("v".equals(theDirection)) {
			saveGame();
		} else {
			System.out.println("Wrong input. Please try again.\n");
			printMaze();
			move(moveMenu());
		}
	}
	
	/**
	 * The player's attempt to move north.
	 */
	public void moveNorth() {
		myNextRoomRow = myPlayerRow - 1;
		if (myNextRoomRow < 0) {
			outOfBounds();
			return;
		}
		
		myNextRoom = myMaze[myPlayerRow - 1][myPlayerColumn];
		if (!myCurrentRoom.passThroughDoor("s")) {
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Checks if North room has been passed already
		if (myNextRoom.getText().equals(PASSEDROOM_STRING)) {
			myCurrentRoom.lockDoor("n");
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Asks a random question if the room is passable
		if (myCurrentRoom.passThroughDoor("n")) {
			askQuestion();
			if (myIncorrectAnswer == true) {
				myCurrentRoom.lockDoor("n");
				myNextRoom.lockDoor("s");
				return;
			}
		}
		
		// Changes the location of the player and marks the room as passed.
		setRooms();
		
		myPlayerRow -= 1;
				
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		myCurrentRoom.lockDoor("s");
	}
	
	/**
	 * The player's attempt to move south.
	 */
	public void moveSouth() {
		myNextRoomRow = myPlayerRow + 1;
		if (myNextRoomRow >= myRows) {
			outOfBounds();
			return;
		}
		
		myNextRoom = myMaze[myPlayerRow + 1][myPlayerColumn];
		if (!myCurrentRoom.passThroughDoor("s")) {
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Checks if South room has been passed already
		if (myNextRoom.getText().equals(PASSEDROOM_STRING)) {
			myCurrentRoom.lockDoor("s");
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Asks a random question if the room is passable
		if (myCurrentRoom.passThroughDoor("s")) {
			askQuestion();
			if (myIncorrectAnswer == true) {
				myCurrentRoom.lockDoor("s");
				myNextRoom.lockDoor("n");
				return;
			}
		}
		
		setRooms();
		
		myPlayerRow += 1;
		
		// Changes the location of the player and marks the room as passed.
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		myCurrentRoom.lockDoor("n");
	}
	
	/**
	 * The player's attempt to move east.
	 */
	public void moveEast() {		
		myNextRoomColumn = myPlayerColumn + 1;
		if (myNextRoomColumn >= myColumns) {
			outOfBounds();
			return;
		}
		
		myNextRoom = myMaze[myPlayerRow][myPlayerColumn + 1];
		if (!myCurrentRoom.passThroughDoor("e")) {
			System.out.println("This door is permanently locked.");
			return;
		}
		
		if (!myNextRoom.passThroughDoor("e")) {
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Checks if East room has been passed already
		if (myNextRoom.getText().equals(PASSEDROOM_STRING)) {
			myCurrentRoom.lockDoor("e");
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Asks a random question if the room is passable
		if (myCurrentRoom.passThroughDoor("e")) {
			askQuestion();
			if (myIncorrectAnswer == true) {
				myCurrentRoom.lockDoor("e");
				myNextRoom.lockDoor("w");
				return;
			}
		}
		
		setRooms();
		
		myPlayerColumn += 1;
		
		// Changes the location of the player and marks the room as passed.
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		myCurrentRoom.lockDoor("w");
	}
	
	/**
	 * The player's attempt to move west.
	 */
	public void moveWest() {
		myNextRoomColumn = myPlayerColumn - 1;
		if (myNextRoomColumn < 0) {
			outOfBounds();
			return;
		}
		
		myNextRoom = myMaze[myPlayerRow][myPlayerColumn - 1];
		if (!myCurrentRoom.passThroughDoor("w")) {
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Checks if West room has been passed already
		if (myNextRoom.getText().equals(PASSEDROOM_STRING)) {
			myCurrentRoom.lockDoor("w");
			System.out.println("This door is permanently locked.");
			return;
		}
		
		// Asks a random question if the room is passable
		if (myCurrentRoom.passThroughDoor("w")) {
			askQuestion();
			if (myIncorrectAnswer == true) {
				myCurrentRoom.lockDoor("w");
				myNextRoom.lockDoor("e");
				return;
			}
		}
		
		setRooms();
		
		myPlayerColumn -= 1;
		
		// Changes the location of the player and marks the room as passed.
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		myCurrentRoom.lockDoor("e");
	}
	
	/**
	 * Creates the actual maze that is composed of Rooms. Remember that a 
	 * Room's x coordinate is its column and a Room's y coordinate is its row.
	 */
	private void initializeMaze() {		
		String text;
		StringBuilder directions;
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[0].length; j++) {
				directions = new StringBuilder();
				if (i != 0) {
					directions.append("n");
				} 
				if (j != 0) {
					directions.append("w");
				}
				if (i != myMaze.length) {
					directions.append("s");
				}
				if (j != myMaze[0].length) {
					directions.append("e");
				}
				
				text = ROOM_STRING;
				if (i == 0 && j == 0) {
					text = PLAYER_STRING;
				} else if (i == myMaze.length - 1 && j == myMaze[0].length - 1) {
					text = EXIT_STRING;
				}
				// Remember that the x is its column and y is its row!
				myMaze[i][j] = new Room(i, j, directions.toString(), text);
			}
		}
	}
	
	/**
	 * Checks the player's location if they're trapped.
	 * @return gameOver -- returns true if the player's location is stuck, or false otherwise
	 */
	public boolean playerTrapped() {		
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		boolean stuck = true;
		
		for (String direction : myCurrentRoom.getDoors().keySet()) {
			if (!myCurrentRoom.getDoors().get(direction).isPermaLocked()) {
				stuck = false;
			}
		}
		myGameOver = stuck;
		
		return myGameOver;
	}
}