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
	static final String[] LETTERS = {"a", "b", "c", "d"};
	
	/**
	 * the ArrayList to store all multplie choice options.
	 */
	private ArrayList<String> myPossibleAnswers;
	
	/** A map of a b c or d to the possible answers. */
	private HashMap<String, String> myAnswersMap;
	
	/** The letter a b c or d that is correct. */
	private String myCorrectLetter;
	
	/**
	 * Constructs a MCQuestion, has one more parameter than other classes to store 
	 * possible answers.
	 * @param thePrompt the prompt of the question.
	 * @param theCorrectAnswer the correct answer of the question
	 * @param theAnswered whether or not question is answered.
	 * @param thePossibleAnswers the list of possible answers for multplie choice. 
	 */
	public MCQuestion(String thePrompt, String theCorrectAnswer, boolean theAnswered, 
			ArrayList<String> thePossibleAnswers) {
		super(thePrompt, theCorrectAnswer, theAnswered);
		myPossibleAnswers = new ArrayList<String>();
		for(int i = 0; i < thePossibleAnswers.size(); i++) {
			myPossibleAnswers.add(thePossibleAnswers.get(i));
		}
		Collections.shuffle(myPossibleAnswers);
		myAnswersMap = new HashMap<String, String>();
		for (int i = 0; i < myPossibleAnswers.size(); i++) {
			myAnswersMap.put(LETTERS[i], myPossibleAnswers.get(i));
			if (myPossibleAnswers.get(i).equals(myCorrectAnswer)) {
				myCorrectLetter = LETTERS[i];
			}
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
	 * This gets the letter of the answer, not the answer itself.
	 * @return a b c or d 
	 */
	@Override
	public String getCorrectAnswer() {
		return myCorrectLetter;
	}
	
	/**
	 * return the toString of this object. 
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
		sb.append("Enter a, b, c, or d.\n");
		for (String letter: myAnswersMap.keySet()) {
			sb.append(letter + ". ");
			sb.append(myAnswersMap.get(letter));
			sb.append("\n");
		}
		return sb.toString();
	}
}
