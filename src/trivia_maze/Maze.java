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
	public static final String PASSEDROOM_STRING = "X";
	
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
	
	private boolean gameOver;
	
	HashMap<String, Door> doorMap = new HashMap<String, Door>();
	
	/** The currently selected theme for the questions. */
//	private Theme myTheme;
	
	/**
	 * Constructs a Maze
	 * @param theRows the number of rows
	 * @param theColumns the number of columns
	 */
	public Maze(final int theRows, final int theColumns) {
		myRows = theRows;
		myColumns = theColumns;
		myMaze = new Room[myRows][myColumns];
		myPlayerRow = 0;
		myPlayerColumn = 0;
		myExitRow = myRows - 1;
		myExitColumn = myColumns - 1;
		myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		myExit = myMaze[myExitRow][myExitColumn];
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
		
	/**
	 * USED THIS FOR REFERENCE
	 * https://www.tutorialspoint.com/java/java_serialization.htm
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
	 * Loads the game.
	 */
	public void loadGame() {
		try {
			Room[][] maze = null;
			FileInputStream fileIn = new FileInputStream(mySavePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			maze = (Room[][]) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Game data loaded successfully!");
			Maze game = new Maze(maze);
			game.playGame();
		} catch (IOException i) {
			System.out.println("Game data could not be loaded.");
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Saved game file not found.");
			c.printStackTrace();
			return;
		}
	}
	
	/**
	 * Makes the maze based on input.
	 */
	public static void initMaze() {
		Scanner console = new Scanner(System.in);
		System.out.println("\nPlease insert the number of rows and columns you want to play with: ");
		int rows = console.nextInt();
		int columns = console.nextInt();
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
			move(moveMenu());
		}
	}
	
	/**
	 * @return whether the game is over
	 */
	public boolean gameIsOver() {
		// TODO
		gameOver = false;
		
		if (myPlayerRow == myExitRow && myPlayerColumn == myExitColumn) {
			System.out.println("Congrats! You won the game!");
			gameOver = true;
		} else if (moveMenu().equals("q")) {
			System.out.println("Sorry to see you go. Goodbye!");
			gameOver = true;
		}
		
		return gameOver;
		
	}
	
	/**
	 * Prompts the player for an answer to a question
	 */
	public static void askQuestion() {
		// TODO
	}
	
	/**
	 * Displays the move menu and prompts for input
	 * @return what the player entered
	 */
	public String moveMenu() {
		Scanner test = new Scanner(System.in);
		String input = "";
		
		System.out.println("W) North");
		System.out.println("S) South");
		System.out.println("D) East");
		System.out.println("A) West");
		System.out.println("V) Save");
		System.out.println("Q) Quit");
		input = test.next().toLowerCase();
		
		return input;
	}
	
	/**
	 * Tries to the player in a given direction
	 * @param theDirection the direction the player wants to go, either "n", 
	 * "s", "e", or "w".
	 */
	public void move(final String theDirection) {
		final Room nextRoom;
		// TODO Check if such a Room exists, i.e., is not out of bounds
		// TODO Check if Door is permaLocked by calling Room's passThroughDoor()
		if ("w".equals(theDirection)) {
			// TODO
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PASSEDROOM_STRING + "  ");
			
			myPlayerRow -= 1;
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PLAYER_STRING + "  ");
			
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		} else if ("s".equals(theDirection)) {
			// TODO
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PASSEDROOM_STRING + "  ");
			
			myPlayerRow += 1;
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PLAYER_STRING + "  ");
			
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		} else if ("d".equals(theDirection)) {
			// TODO
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PASSEDROOM_STRING + "  ");
			
			myPlayerColumn += 1;
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PLAYER_STRING + "  ");
			
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		} else if ("a".equals(theDirection)) {
			// TODO
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PASSEDROOM_STRING + "  ");
			
			myPlayerColumn -= 1;
			myMaze[myPlayerRow][myPlayerColumn] = new Room(myPlayerRow, myPlayerColumn, doorMap, PLAYER_STRING + "  ");
			
			myCurrentRoom = myMaze[myPlayerRow][myPlayerColumn];
		} else if ("q".equals(theDirection)) {
			gameIsOver();
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
		Door northDoor;
		Door southDoor;
		Door eastDoor;
		Door westDoor;
//		HashMap<String, Door> doorMap = new HashMap<String, Door>();
		String text;
		for (int i = 0; i < myMaze.length; i++) {
			for (int j = 0; j < myMaze[0].length; j++) {
				doorMap.clear();
				northDoor = new Door(new int[] {i, j}, new int[] {i - 1, j});
				southDoor = new Door(new int[] {i, j}, new int[] {i + 1, j});
				eastDoor = new Door(new int[] {i, j}, new int[] {i, j + 1});
				westDoor = new Door(new int[] {i, j}, new int[] {i, j - 1});
				if (i != 0) {
					doorMap.put("n", northDoor);
				} 
				if (j != 0) {
					doorMap.put("w", westDoor);
				}
				if (i != myMaze.length - 1) {
					doorMap.put("s", southDoor);
				}
				if (j != myMaze[0].length - 1) {
					doorMap.put("e", eastDoor);
				}
				text = ROOM_STRING + "  ";
				if (i == 0 && j == 0) {
					text = PLAYER_STRING + "  ";
				} else if (i == myMaze.length - 1 && j == myMaze[0].length - 1) {
					text = EXIT_STRING;
				}
				// Remember that the x is its column and y is its row!
				myMaze[i][j] = new Room(j, i, doorMap, text);
			}
		}
	}
}
