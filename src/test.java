import java.util.Scanner;


public class test {
	public static void main(String[] args) {
		Scanner console = new Scanner (System.in);
		
		intro();
		menu(console);
	}
	
	public static void intro() {
		System.out.println("Welcome to Marcus, Calvin, and Joey's TCSS 360 project!");
		System.out.println("This program is a game in the form of a trivia maze.");
	}
	
	public static void menu(Scanner console) {
		System.out.print("\nPlease enter an option: START -- FILE -- HELP -- END\n");
		String input = console.nextLine().toLowerCase();
		
		if (input.equals("end")) {
			System.out.println("Sorry to see you go. Goodbye!");
		}
		
		while (!input.equals("end")) {
			if (input.equals("start")) {
				createMaze();
				break;
//				System.out.println("startgame method");
			} else if (input.equals("file")) {
				System.out.println("load/save method");
			} else if (input.equals("help")) {
				help(console);
			} else {
				System.out.println("Wrong input.");
			}
			
			System.out.print("\nPlease enter an option: START -- FILE -- HELP -- END\n");
			input = console.nextLine().toLowerCase();
			
			if (input.equals("end")) {
				System.out.println("Sorry to see you go. Goodbye!");
				break;
			}
		}
	}
	
	public static void help(Scanner console) {
		System.out.print("\nPlease enter an option: ABOUT -- GAMEPLAY INSTRUCTIONS -- BACK\n");
		String input = console.nextLine().toLowerCase();
		
		while (!input.equals("back")) {
			if (input.equals("about")) {
				System.out.println("This game is a trivia maze.");
				System.out.println("You must traverse through the map from the beginning");
				System.out.println("to the end by answering questions. If you answer a");
				System.out.println("question wrong, the door to that pathway will be");
				System.out.println("permanently locked. If you answer it correctly, you");
				System.out.println("progress onto the next room.\n");
			} else if (input.equals("gameplay instructions")) {
				System.out.println("To traverse a direction, enter N/S/E/W for");
				System.out.println("North/South/East/West. To answer a multiple choice");
				System.out.println("question, enter a/b/c/d to select an option.");
				System.out.println("To answer a true/false, enter the respective choice.");
				System.out.println("To answer a short-response question, enter your answer.\n");
			} else {
				System.out.println("Wrong input.\n");
			}
				
			System.out.print("Please enter an option: ABOUT -- GAMEPLAY INSTRUCTIONS -- BACK\n");
			input = console.nextLine().toLowerCase();
			
			if (input.equals("back")) {
				break;
			}
		}
	}
	
	public static void createMaze() {
		Scanner input = new Scanner(System.in);
		String[][] maze = null;
		String display = "";
		
		System.out.println("\nPlease enter the amount of rows and columns:");
		
		int rows = input.nextInt();
		int columns = input.nextInt();
		
		if (rows < 4 || columns < 4) {
			System.out.println("A minimum of 4 rows and 4 columns are required.");
			createMaze();
		} else {
			maze = new String[rows][columns];
			for (int i = 0; i < maze.length; i++) {
				for (int j = 0; j < maze[0].length; j++) {
					if (i == 0 && j == 0) {
						maze[i][j] = "P  ";
					} else if (i == (rows - 1) && j == (columns - 1)) {
						maze[i][j] = "D";
					} else {
						maze[i][j] = "O  ";
					}
				}
			}
			
			for (int a = 0; a < maze.length; a++) {
				for (int b = 0; b < maze[0].length; b++) {
					display = display + maze[a][b];
				}
				display = display + "\n";
			}
			
			System.out.println(display);
		}
	}
}
