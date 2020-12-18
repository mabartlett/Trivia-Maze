package trivia_maze;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class TriviaMaze {
	/** The name of the save file. */
	private static String mySavePath = "SavedGame.ser";
	
	public static void main(String[] args) {
		Scanner console = new Scanner (System.in);
		
		intro();
		menu(console);
	}
	
	public static void intro() {
		System.out.println("Welcome to Marcus, Calvin, and Joey's TCSS 360 project!");
		System.out.println("This program is a game in the form of a trivia maze.");
	}
	
	/**
	 * Displays the 
	 * @param console
	 */
	public static void menu(Scanner console) {
		Maze mazeGame = null;
		
		System.out.print("\nPlease enter an option: (P)LAY -- (L)OAD FILE -- (H)ELP -- (Q)UIT\n");
		String input = console.nextLine().toLowerCase();
		
		if (input.equals("q")) {
			System.out.println("Sorry to see you go. Goodbye!");
		}
		
		while (!input.equals("q")) {
			if (input.equals("p")) {
				Maze.initMaze();
				break;
			} else if (input.equals("l")) {
				loadGame();
				break;
			} else if (input.equals("h")) {
				help(console);
			} else {
				System.out.println("Wrong input.");
			}
			
			System.out.print("\nPlease enter an option: (P)LAY -- (L)OAD FILE -- (H)ELP -- (Q)UIT\n");
			input = console.nextLine().toLowerCase();
			
			if (input.equals("q")) {
				System.out.println("Sorry to see you go. Goodbye!");
				break;
			}
		}
	}
	
	/**
	 * Loads the game.
	 */
	public static void loadGame() {
		Room[][] maze;
		try {
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
	 * Displays the help menu.
	 * @param console -- takes user input to navigate through the help menu.
	 */
	public static void help(Scanner console) {
		System.out.print("\nPlease enter an option: (A)BOUT -- (G)AMEPLAY INSTRUCTIONS -- (B)ACK\n");
		String input = console.nextLine().toLowerCase();
		
		while (!input.equals("b")) {
			if (input.equals("a")) {
				System.out.println("This game is a trivia maze.");
				System.out.println("1. You traverse through the map by answering questions.");
				System.out.println("2. If you answer a question wrong, the door to that pathway will be permanently locked.");
				System.out.println("3. If you answer it correctly, you progress onto the next room.\n");
			} else if (input.equals("g")) {
				System.out.println("1. To traverse a direction, enter N/E/S/W for North/East/South/West.");
				System.out.println("2. To answer a multiple choice question, enter a/b/c/d to select an option.");
				System.out.println("3. To answer a true/false, enter the respective choice.");
				System.out.println("4. To answer a short-response question, enter your answer.\n");
			} else {
				System.out.println("Wrong input.\n");
			}
				
			System.out.print("Please enter an option: (A)BOUT -- (G)AMEPLAY INSTRUCTIONS -- (B)ACK\n");
			input = console.nextLine().toLowerCase();
			
			if (input.equals("b")) {
				break;
			}
		}
	}
}