package Questions;

/**
 * This class handles the behavior of a TFQuestion. 
 * @author Joseph Bode
 * @version 12/15/2020
 */

public class TFQuestion extends Question {
	
	/**
	 * Construct a True false question. 
	 * @param thePrompt the question itself
	 * @param theCorrectAnswer correct answer to the question.
	 * @param theAnswered whether the question is answered or not. 
	 */
	public TFQuestion(String thePrompt, String theCorrectAnswer, boolean theAnswered) {
		super(thePrompt, theCorrectAnswer, theAnswered);
	}
		
	/**
	 * Check if the answer from user is correct. Short answer form input. 
	 * @param theAnswer User Answer
	 * @return if the answer is correct
	 */
	public boolean isAnswerCorrect(String theAnswer) {
		boolean user;
		if (theAnswer.equalsIgnoreCase(theAnswer)) {
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
	 * 
	 * @return toString of a true false question.
	 */
	public String toString() { // only displays question prompt right now. 
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
		sb.append("Please answer True or False.");
		return sb.toString();
	}
	
}
