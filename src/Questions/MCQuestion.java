package Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


/**
 * 
 * @author joseph bode and Marcus Bartlett
 * @version 12/16/20
 */
public class MCQuestion extends Question {
	
	/** The possible letters. */
	public static final String[] LETTERS = {"a", "b", "c", "d"};
	
	/**
	 * the ArrayList to store all multplie choice options.
	 */
	ArrayList<String> myPossibleAnswers;
	
	/** A map of a b c or d to the possible answers. */
	HashMap<String, String> myAnswersMap;
	
	/** The letter a b c or d that is correct. */
	String myCorrectLetter;
	
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
		Collections.shuffle(myPossibleAnswers);
		int i = 0;
		for (String answer: myPossibleAnswers) {
			myAnswersMap.put(LETTERS[i], answer);
			if (answer.equals(this.getCorrectAnswer())) {
				myCorrectLetter = LETTERS[i];
			}
			i++;
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
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("Enter a, b, c, or d.\n");
		for (String letter: myAnswersMap.keySet()) {
			sb.append(letter + ". ");
			sb.append(myAnswersMap.get(letter));
			sb.append("\n");
		}
		return sb.toString();
	}
}
