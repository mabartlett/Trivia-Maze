package trivia_maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
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

	private int rows;
	
	private int columns;
	
	private static Room[][] maze;
	
	private Room myCurrentRoom;
	
	private Room exit;
	
	private String printMaze = "";
	
	private String saveFile = "SavedGame.ser";
	
	private int playerRow;
	
	private int playerColumn;
	
	private int exitRow;
	
	private int exitColumn;
	
	private String input;
	
//	private Theme myTheme;
	
	public Maze(int theRows, int theColumns) {
		this.rows = theRows;
		this.columns = theColumns;
		maze = new Room[rows][columns];
		playerRow = 0;
		playerColumn = 0;
		exitRow = rows - 1;
		exitColumn = columns - 1;
		myCurrentRoom = maze[playerRow][playerColumn];
		exit = maze[exitRow][exitColumn];
		initializeMaze();
	}
	
	public void printMaze() {
		for (int a = 0; a < maze.length; a++) {
			for (int b = 0; b < maze[0].length; b++) {
				printMaze = printMaze + maze[a][b].toString();
			}
			printMaze = printMaze + "\n";
		}
		
		System.out.println(printMaze);
	}
	
	public Maze(Room[][] rooms) {
		maze = rooms;
	}
	
	
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public Room[][] getMaze() {
		return maze;
	}
	
	
	public Room getCurrentRoom() {
		return this.myCurrentRoom;
	}
	
	
	
	/**
	 * USED THIS FOR REFERENCE
	 * https://www.tutorialspoint.com/java/java_serialization.htm
	 */
	public void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream(saveFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(maze);
			out.close();
			fileOut.close();
			System.out.println("Game data has been saved.");
		} catch (IOException i) {
			System.out.println("Game data could not be saved.");
		}
	}
	
	public void loadGame() {
		try {
			Room[][] maze = null;
			
			FileInputStream fileIn = new FileInputStream(saveFile);
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
	
	public static void initMaze() {
		Scanner console = new Scanner(System.in);
		System.out.println("\nPlease insert the number of rows and columns you want to play with: ");
		int rows = console.nextInt();
		int columns = console.nextInt();
		
		if (rows < 4 || columns < 4) {
			System.out.println("Please enter a minimum of 4 rows and 4 columns.");
			initMaze();
		} else {
			Maze mazeGame = new Maze(rows, columns);
			mazeGame.playGame();
		}
	}
	
	public void playGame() {
		while (!gameIsOver()) {
			System.out.println("\nCurrent maze:");
			printMaze();
			moveMenu();
		}
	}
	
	public boolean gameIsOver() {
		boolean gameOver = false;
		
		return gameOver;
	}
	
	
	public static void askQuestion(boolean result) {
		
	}
	
	public String moveMenu() {
		Scanner test = new Scanner(System.in);
		input = "";
		
		while (!input.equals("n") && !input.equals("s") && !input.equals("e") && !input.equals("w")
				&& !input.equals("q")) {
			System.out.println("N) North");
			System.out.println("E) East");
			System.out.println("S) South");
			System.out.println("W) West");
			System.out.println("Q) Quit");
			
			input = test.next().toLowerCase();
		}
		
		return input;
	}
	
	public void move() {
		if (input.equals("n")) {
			moveNorth();
			//myCurrentRoom = new Room("P   ");
		} else {
			System.out.println("North door is locked.");
		}
		
		if (input.equals("e")) {
			moveEast();
		} else {
			System.out.println("East door is locked.");
		}
	}
	
	/**
	 *  && northRoom().isPassable()
	 */
	public void moveNorth() {
		// TODO Check if door is locked.
		if (maze[playerRow - 1][playerColumn] != null) {
			myCurrentRoom = maze[playerRow - 1][playerColumn];
		}
	}
	
	public void moveEast() {
		// TODO Check if door is locked.
		if (maze[playerRow][playerColumn + 1] != null) {
			myCurrentRoom = maze[playerRow][playerColumn + 1];
		}
	}
	
	public void moveSouth() {
		// TODO Check if door is locked.
		if (maze[playerRow + 1][playerColumn] != null) {
			myCurrentRoom = maze[playerRow + 1][playerColumn];
		}
	}
	
	public void moveWest() {
		// TODO Check if door is locked.
		if (maze[playerRow][playerColumn - 1] != null) {
			myCurrentRoom = maze[playerRow][playerColumn - 1];
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
		HashMap<String, Door> doorMap = new HashMap<String, Door>();
		String text;
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
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
				if (i != maze.length - 1) {
					doorMap.put("s", southDoor);
				}
				if (j != maze[0].length - 1) {
					doorMap.put("e", eastDoor);
				}
				text = ROOM_STRING + "  ";
				if (i == 0 && j == 0) {
					text = PLAYER_STRING + "  ";
				} else if (i == maze.length - 1 && j == maze[0].length - 1) {
					text = EXIT_STRING;
				}
				// Remember that the x is its column and y is its row!
				maze[i][j] = new Room(j, i, doorMap, text);
			}
		}
	}
}
