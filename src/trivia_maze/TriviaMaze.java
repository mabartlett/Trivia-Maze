package trivia_maze;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.sqlite.SQLiteDataSource;

import Questions.MCQuestion;
import Questions.Question;
import Questions.SAQuestion;
import Questions.TFQuestion;

public class TriviaMaze {
	/** The name of the save file. */
	private static String mySavePath = "SavedGame.ser";
	
	/** Represents the minimum rows required for the maze. */
	public static final int MIN_ROWS = 4;
	
	/** Represents the minimum columns required for the maze. */
	public static final int MIN_COLUMNS = 4;
	
	/** The maximum number of allowed rows. */
	public static final int MAX_ROWS = 7;
	
	/** The maximum number of allowed columns. */
	public static final int MAX_COLUMNS = 7;
	
	/** The object of the Maze. */
	private static Maze myMazeGame;
	
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
	 * Displays the menu.
	 * @param console is the input scanner.
	 */
	public static void menu(Scanner console) {
		System.out.print("\nPlease enter an option: (P)LAY -- (L)OAD FILE -- (H)ELP -- (Q)UIT\n");
		String input = console.nextLine().toLowerCase();
		if (input.equals("q")) {
			System.out.println("Sorry to see you go. Goodbye!");
		}
		while (!input.equals("q")) {
			if (input.equals("p")) {
				initMaze();
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
	
	/** Accesses the database and makes questions. */
	private static ArrayList<Question> makeQuestions() {
		SQLiteDataSource ds = null;
        try {
            ds = new SQLiteDataSource();
            String url = "jdbc:sqlite:questions.db";
            ds.setUrl(url);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        String query = "SELECT * FROM TriviaQuestionsFinal";
        ArrayList<Question> arr = new ArrayList<Question>();
        try ( Connection conn = ds.getConnection();
               Statement stmt = conn.createStatement(); ) {
              ResultSet rs = stmt.executeQuery(query);
              while ( rs.next() ) {
            	  Question tempQuestion;
            	  String type = rs.getString( "type" );
            	  String prompt = rs.getString( "prompt" );
                  String correctAnswer = rs.getString( "correctAnswer");
                  boolean answered = false;
                  if(type.equals("MC")) {
                	  ArrayList<String> temp = new ArrayList<String>();
                	  temp.add(rs.getString( "correctAnswer" ));
                	  temp.add(rs.getString( "possibleAnswer1" ));
                	  temp.add(rs.getString( "possibleAnswer2" ));
                	  temp.add(rs.getString( "possibleAnswer3" ));
                	  tempQuestion = new MCQuestion(prompt, correctAnswer, answered, temp);
                      arr.add(tempQuestion);
                  } else if(type.equals("TF")) {
                	  tempQuestion = new TFQuestion(prompt, correctAnswer, answered);
                	  arr.add(tempQuestion);  
                  } else {
                	  tempQuestion = new SAQuestion(prompt, correctAnswer, answered);
                	  arr.add(tempQuestion);  
                  }
              }
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
        return arr;
	}

	/**
	 * Makes the maze based on input.
	 */
	public static void initMaze() {
		Scanner console = new Scanner(System.in);
		System.out.println("\nPlease insert the number of rows and columns you want to play with: ");
		String r = console.next();
		while (!r.matches("\\d")) {
			System.out.println("You must enter an integer!");
			r = console.next();
		}
		int rows = Integer.valueOf(r);
		
		String c = console.next();
		while (!c.matches("\\d")) {
			System.out.println("You must enter an integer!");
			c = console.next();
		}
		int columns = Integer.valueOf(c);
		
		if (rows < MIN_ROWS || columns < MIN_COLUMNS) {
			System.out.print("Please enter a minimum of ");
			System.out.print(MIN_ROWS);
			System.out.print(" rows and ");
			System.out.print(MIN_COLUMNS);
			System.out.print(" columns.");
			System.out.println();
			initMaze();
		} else if (rows > MAX_ROWS || columns > MAX_COLUMNS) {
			System.out.print("Please enter a maximum of ");
			System.out.print(MAX_ROWS);
			System.out.print(" rows and ");
			System.out.print(MAX_COLUMNS);
			System.out.print(" columns.");
			System.out.println();
			initMaze();
		} else {
			myMazeGame = new Maze(rows, columns, makeQuestions());
			myMazeGame.playGame();
		}
		console.close();
	}
	
	/**
	 * Loads the game.
	 */
	public static void loadGame() {
		Maze maze;
		try {
			FileInputStream fileIn = new FileInputStream(mySavePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			maze = (Maze) in.readObject();
			in.close();
			fileIn.close();
			System.out.println("Game data loaded successfully!");
			System.out.println("");
			maze.playGame();
		} catch (IOException i) {
			System.out.println("Game data could not be loaded.");
			System.out.println(i);
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