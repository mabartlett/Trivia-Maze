import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Game implements Serializable {
	private int rows;
	
	private int columns;
	
	private static Room[][] myRooms;
	
	private Room myCurrentRoom;
	
	private String display = "";
	
//	private Theme myTheme;
	
	
	
	public Game(int theRows, int theColumns) {
		this.rows = theRows;
		this.columns = theColumns;
		myRooms = new Room[rows][columns];
		
		for (int i = 0; i < myRooms.length; i++) {
			for (int j = 0; j < myRooms[0].length; j++) {
				if (i == 0 && j == 0) {
					myRooms[i][j] = new Room("P   ");
				} else if ((i == rows - 1) && (j == columns - 1)) {
					myRooms[i][j] = new Room("D");
				} else if (j == columns - 1) {
					myRooms[i][j] = new Room("O");
				}	else {
					myRooms[i][j] = new Room("O   ");
				}
			}
		}
		
		for (int a = 0; a < myRooms.length; a++) {
			for (int b = 0; b < myRooms[0].length; b++) {
				display = display + myRooms[a][b].toString();
			}
			display = display + "\n";
		}
		
		System.out.println(display);
	}
	
	
	
	public int getRows() {
		return this.rows;
	}
	
	public int getColumns() {
		return this.columns;
	}
	
	public Room[][] getMaze() {
		return myRooms;
	}
	
	
	public int getRoomX() {
		return myCurrentRoom.getX();
	}
	
	public int getRoomY() {
		return myCurrentRoom.getY();
	}
	
	
	
	/**
	 * USED THIS FOR REFERENCE
	 * https://www.tutorialspoint.com/java/java_serialization.htm
	 */
	public static void saveGame() {
		try {
			FileOutputStream fileOut = new FileOutputStream("C:\\trivia-game\\savedGame.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(myRooms);
			out.close();
			fileOut.close();
			System.out.println("Serialized data is saved in /tmp/savedGame.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public static void loadGame() {
		try {
			Room[][] maze = null;
			
			FileInputStream fileIn = new FileInputStream("C:\\trivia-game\\savedGame.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			maze = (Room[][]) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Saved game file not found.");
			c.printStackTrace();
			return;
		}
	}
	
	public static void playGame(Scanner console) {
		System.out.println("\nPlease insert the number of rows and columns you want to play with: ");
		int rows = console.nextInt();
		int columns = console.nextInt();
		
		if (rows < 4 || columns < 4) {
			System.out.println("Please enter a minimum of 4 rows and 4 columns.");
			playGame(console);
		} else {
			Game mazeGame = new Game(rows, columns);
		}
	}
	
	public static void askQuestion(boolean result) {
		
	}
}
