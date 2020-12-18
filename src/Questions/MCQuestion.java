package Questions;

import java.util.ArrayList;
import java.util.Collections;


/**
 * 
 * @author joseph bode
 * @version 12/16/20
 */
public class MCQuestion extends Question {
	
	/**
	 * the ArrayList to store all multplie choice options.
	 */
	ArrayList<String> myPossibleAnswers;
	
	/**
	 * Constructs a MCQuestion, has one more parameter than other classes to store possible answers.
	 * @param thePrompt the prompt of the question.
	 * @param theCorrectAnswer the correct answer of the question
	 * @param theAnswered whether or not question is answered.
	 * @param thePossibleAnswers the list of possible answers for multplie choice. 
	 */
	public MCQuestion(String thePrompt, String theCorrectAnswer, boolean theAnswered, ArrayList<String> thePossibleAnswers) {
		super(thePrompt, theCorrectAnswer, theAnswered);
		myPossibleAnswers = new ArrayList<String>();
		for(int i = 0; i < thePossibleAnswers.size(); i++) {
			myPossibleAnswers.add(thePossibleAnswers.get(i));
		}
	}
	
	/**
	 * returns the list of possible answers.
	 * @return the possible answers.
	 */
	public ArrayList<String> getPossibleAnswers() {
		return myPossibleAnswers;
	}
	
	/**
	 * Check if the answer from user is correct. Short answer form input. 
	 * @param theAnswer User Answer
	 * @return if the answer is correct
	 */
	public boolean isAnswerCorrect(String theAnswer) {
		boolean user;
		if (theAnswer.equalsIgnoreCase(this.getCorrectAnswer())) {
			user = true;
			this.setAnswered(true); // come back later
			System.out.println("Correct!"); // DELETE THIS LATER, ERROR CHECKING.
		}
		else {
			user = false;
			this.setAnswered(false); // come back later, logic issue possibly. 
			System.out.println("Incorrect! The Answer was :" + this.getCorrectAnswer()); // DELETE THIS LATER, ERROR CHECKING
		}
		return user; 
	}
	
	/**
	 * return the toString of this object. 
	 */
	public String toString() { // only displays question prompt right now. 
		StringBuilder sb = new StringBuilder();
		Collections.shuffle(myPossibleAnswers);
//		ArrayList<String> helper = new ArrayList<String>();
//		for(int i = 0; i < myPossibleAnswers.size(); i++) {
//			helper.add(myPossibleAnswers.get(i));
//		}
//		Random rand = new Random();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
//		int index = helper.indexOf(helper.get(rand.nextInt(helper.size())));
		sb.append("a. " + myPossibleAnswers.get(0));
		sb.append("\n");
		sb.append("b. " + myPossibleAnswers.get(1));
		sb.append("\n");
		sb.append("c. " + myPossibleAnswers.get(2));	
		sb.append("\n");
		sb.append("d. " + myPossibleAnswers.get(3));
		return sb.toString();
	}
}
