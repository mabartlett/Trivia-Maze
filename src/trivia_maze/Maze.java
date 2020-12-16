package trivia_maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Maze implements Serializable {
	/** The ID for serialization. */
	public static final long serialVersionUID = 1L;
	
	/** Represents the player. */
	public static final String PLAYER_STRING = "P";
	
	/** Represents the exit of the maze. */
	public static final String EXIT_STRING = "E";
	
	/** Represents each room. */
	public static final String ROOM_STRING = "O";
	
	/** Represents each room. */
	public static final String PASSEDROOM_STRING = "M";
	
	/** Represents the minimum rows required for the maze. */
	public static final int MIN_ROWS = 4;
	
	/** Represents the minimum columns required for the maze. */
	public static final int MIN_COLUMNS = 4;
	
	/** The number of rows in the Maze's 2D array of Rooms. */
	private int myRows;
	
	/** The number of columns in the Maze's 2D array of Rooms. */
	private int myColumns;
	
	/** The 2D array of Rooms that composes the maze. */
	private static Room[][] myMaze;
	
	/** The Room in which the player currently is. */
	private Room myCurrentRoom;
	
	/** The next Room the player wants to move to. */
	private Room nextRoom;
	
	/** The Room in which the exit is. */
	private Room myExit;
	
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
	
	private static Maze mazeGame;
	
	private boolean gameOver = false;
	
	private int nextRoomRow;
	
	private int nextRoomColumn;
	
	private static String input = "";
	
	private boolean incorrectAnswer;
	
	/**
	 * Constructs a Maze
	 * @param theRows the number of rows
	 * @param theColumns the number of columns
	 */
	public Maze(final int theRows, final int theColumns) {
		myRows = theRows;
		myColumns = theColumns;
		myMaze = new Room[myRows][myColumns];
		initializeMaze();
		myPlayerRow = 0;
		myPlayerColumn = 0;
		myExitRow = myRows - 1;
		myExitColumn = myColumns - 1;
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		myExit = myMaze[myExitRow][myExitColumn];
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
	
	public Room getExitRoom() {
		return myExit;
	}
	
	/** @return the next room */
	public Room getNextRoom() {
		return nextRoom;
	}
		
	/**
	 * Saves the game.
	 */
	public void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream(mySavePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myMaze);
			out.close();
			fileOut.close();
			System.out.println("Game data has been saved.");
		} catch (IOException i) {
			System.out.println("Game data could not be saved.");
		}
	}
	
	/**
	 * Makes the maze based on input.
	 */
	public void initMaze() {
		Scanner console = new Scanner(System.in);
		System.out.println("\nPlease insert the number of rows and columns you want to play with: ");
		String r = console.next();
		while(!r.matches("\\d")) {
			System.out.println("You must enter an integer!");
			r = console.next();
		}
		int rows = Integer.valueOf(r);
		String c = console.next();
		while(!c.matches("\\d")) {
			System.out.println("You must enter an integer!");
			c = console.next();
		}
		int columns = Integer.valueOf(c);
		if (rows < MIN_ROWS || columns < MIN_COLUMNS) {
			System.out.println("Please enter a minimum of 4 rows and 4 columns.");
			initMaze();
		} else {
			mazeGame = new Maze(rows, columns);
			mazeGame.playGame();
		}
		console.close();
	}
	
	/**
	 * This method contains the main loop of the game.
	 */
	public void playGame() {
		while (gameOver == false) {
			System.out.println("\nCurrent maze:");
			printMaze();
			
			if (myPlayerRow == myExitRow && myPlayerColumn == myExitColumn) {
				System.out.println("Congrats! You won the game!");
				gameOver = true;
				break;
			}
			
			move(moveMenu());
		}
	}
	
	/**
	 * Prompts the player for an answer to a question
	 */
	public void askQuestion() {
		// TODO
		Scanner test = new Scanner(System.in);
		String answer = "yes";
		String input2 = "";
		
		System.out.println("Question 1: Do you want to move to the next room?");
		input2 = test.next().toLowerCase();
		
		if (input2.equals(answer)) {
			System.out.println("Correct!");
			incorrectAnswer = false;
		} else {
			System.out.println("Incorrect answer. Door is now locked.");
			incorrectAnswer = true;
		}
	}
	
	/**
	 * Displays the move menu and prompts for input
	 * @return what the player entered
	 */
	public String moveMenu() {
		Scanner test2 = new Scanner(System.in);
		
		System.out.println("W) North");
		System.out.println("S) South");
		System.out.println("D) East");
		System.out.println("A) West");
		System.out.println("V) Save");
		System.out.println("Q) Quit");
		input = test2.next().toLowerCase();
		
		return input;
	}
	
	public void outOfBounds() {
		System.out.println("Out of bounds. Please try again.\n");
		printMaze();
		move(moveMenu());
	}
	
	public void passedRoom() {
		System.out.println("Room has already been passed. Please try again.\n");
		printMaze();
		move(moveMenu());
	}
	
	public void setRooms() {
		nextRoom.setText(PLAYER_STRING + "  ");
		myCurrentRoom.setText(PASSEDROOM_STRING + "  ");
	}
	
	public void moveNorth() {
		nextRoomRow = myPlayerRow - 1;
		if (nextRoomRow < 0) {
			outOfBounds();
			return;
		}
		
		nextRoom = myMaze[myPlayerRow - 1][myPlayerColumn];
		
		askQuestion();
		if (incorrectAnswer == true) {
			nextRoom.lockDoor("s");
			return;
		}

		if (nextRoom.getText().equals(PASSEDROOM_STRING + "  ")) {
			passedRoom();
		} else {
			setRooms();
			myPlayerRow -= 1;
				
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		}
	}
	
	public void moveSouth() {
		nextRoomRow = myPlayerRow + 1;
		if (nextRoomRow >= myRows) {
			outOfBounds();
			return;
		}
		
		nextRoom = myMaze[myPlayerRow + 1][myPlayerColumn];
		
		askQuestion();
		if (incorrectAnswer == true) {
			nextRoom.lockDoor("n");
			return;
		}

		if (nextRoom.getText().equals(PASSEDROOM_STRING + "  ")) {
			passedRoom();
		} else {
			setRooms();
			myPlayerRow += 1;
				
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		}
	}
	
	public void moveEast() {
		nextRoomColumn = myPlayerColumn + 1;
		if (nextRoomColumn >= myColumns) {
			outOfBounds();
			return;
		}
		
		nextRoom = myMaze[myPlayerRow][myPlayerColumn + 1];
		
		askQuestion();
		if (incorrectAnswer == true) {
			nextRoom.lockDoor("w");
			return;
		}

		if (nextRoom.getText().equals(PASSEDROOM_STRING + "  ")) {
			passedRoom();
		} else {
			setRooms();
			myPlayerColumn += 1;
			
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		}
	}
	
	public void moveWest() {
		nextRoomColumn = myPlayerColumn - 1;
		if (nextRoomColumn < 0) {
			outOfBounds();
			return;
		}
		
		nextRoom = myMaze[myPlayerRow][myPlayerColumn - 1];
		
		askQuestion();
		if (incorrectAnswer == true) {
			nextRoom.lockDoor("e");
			return;
		}
		
		if (nextRoom.getText().equals(PASSEDROOM_STRING + "  ")) {
			passedRoom();
		} else {
			setRooms();
			myPlayerColumn -= 1;
			
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		}
	}
	
	/**
	 * Tries to the player in a given direction
	 * @param theDirection the direction the player wants to go, either "n", 
	 * "s", "e", or "w".
	 */
	public void move(final String theDirection) {
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		
		// TODO Check if Door is permaLocked by calling Room's passThroughDoor()
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
			gameOver = true;
		} else if ("v".equals(theDirection)) {
			saveGame();
		} else {
			System.out.println("Wrong input. Please try again.\n");
			printMaze();
			move(moveMenu());
		}
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
				if (i != myMaze.length - 1) {
					directions.append("s");
				}
				if (j != myMaze[0].length - 1) {
					directions.append("e");
				}
				text = ROOM_STRING + "  ";
				if (i == 0 && j == 0) {
					text = PLAYER_STRING + "  ";
				} else if (i == myMaze.length - 1 && j == myMaze[0].length - 1) {
					text = EXIT_STRING;
				}
				// Remember that the x is its column and y is its row!
				myMaze[i][j] = new Room(i, j, directions.toString(), text);
			}
		}
	}
}
