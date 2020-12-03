package trivia_maze;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Maze implements Serializable {
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
		
		// Initial maze printed
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				if (i == 0 && j == 0) {
					maze[i][j] = new Room("P   ");
				} else if ((i == rows - 1) && (j == columns - 1)) {
					maze[i][j] = new Room("E");
				} else if (j == columns - 1) {
					maze[i][j] = new Room("O");
				}	else {
					maze[i][j] = new Room("O   ");
				}
			}
		}
		
		
//		for (int a = 0; a < maze.length; a++) {
//			for (int b = 0; b < maze[0].length; b++) {
//				printMaze = printMaze + maze[a][b].toString();
//			}
//			printMaze = printMaze + "\n";
//		}
	}
	
//	public void printMaze() {
//		for (int a = 0; a < maze.length; a++) {
//			for (int b = 0; b < maze[0].length; b++) {
//				printMaze = printMaze + maze[a][b].toString();
//			}
//			printMaze = printMaze + "\n";
//		}
//		
//		System.out.println(printMaze);
//	}
	
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
			myCurrentRoom = new Room("P   ");
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
		if (!northRoom().isLocked() && northRoom() != null) {
			myCurrentRoom = northRoom();
		}
	}
	
	public void moveEast() {
		if (!eastRoom().isLocked() && eastRoom() != null) {
			myCurrentRoom = eastRoom();
		}
	}
	
	public void moveSouth() {
		if (!southRoom().isLocked() && southRoom() != null) {
			myCurrentRoom = southRoom();
		}
	}
	
	public void moveWest() {
		if (!westRoom().isLocked() && westRoom() != null) {
			myCurrentRoom = westRoom();
		}
	}
	
	public Room northRoom() {
		return maze[playerRow - 1][playerColumn];
	}
	
	public Room eastRoom() {
		return maze[playerRow][playerColumn + 1];
	}
	
	public Room southRoom() {
		return maze[playerRow + 1][playerColumn];
	}
	
	public Room westRoom() {
		return maze[playerRow][playerColumn - 1];
	}
}
