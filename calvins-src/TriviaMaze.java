import java.util.Scanner;

public class TriviaMaze {
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
		Game mazeGame = null;
		
		System.out.print("\nPlease enter an option: (P)LAY -- (L)OAD FILE -- (H)ELP -- (Q)UIT\n");
		String input = console.nextLine().toLowerCase();
		
		if (input.equals("q")) {
			System.out.println("Sorry to see you go. Goodbye!");
		}
		
		while (!input.equals("q")) {
			if (input.equals("p")) {
				mazeGame.playGame(console);
				break;
			} else if (input.equals("l")) {
				mazeGame.loadGame();
				break;
			} else if (input.equals("h")) {
				help(console);
			} else if (input.equals("save")) {
				mazeGame.saveGame();
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
	
	public static void help(Scanner console) {
		System.out.print("\nPlease enter an option: (A)BOUT -- (G)AMEPLAY INSTRUCTIONS -- (B)ACK\n");
		String input = console.nextLine().toLowerCase();
		
		while (!input.equals("b")) {
			if (input.equals("a")) {
				System.out.println("This game is a trivia maze.");
				System.out.println("You must traverse through the map from the beginning");
				System.out.println("to the end by answering questions. If you answer a");
				System.out.println("question wrong, the door to that pathway will be");
				System.out.println("permanently locked. If you answer it correctly, you");
				System.out.println("progress onto the next room.\n");
			} else if (input.equals("g")) {
				System.out.println("To traverse a direction, enter N/S/E/W for");
				System.out.println("North/South/East/West. To answer a multiple choice");
				System.out.println("question, enter a/b/c/d to select an option.");
				System.out.println("To answer a true/false, enter the respective choice.");
				System.out.println("To answer a short-response quEestion, enter your answer.\n");
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
