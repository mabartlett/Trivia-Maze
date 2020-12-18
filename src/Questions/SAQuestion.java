package Questions;


/**
 * Class that stores everything a question would need.
 * @author Joseph Bode
 * @version 12/15/20
 */
public class SAQuestion extends Question {
	
	/**
	 * constructs an SAQuestion with given info.
	 * @param thePrompt actual question
	 * @param theCorrectAnswer the correct answer.
	 * @param theAnswered whether the question is answered or not. 
	 */
	public SAQuestion(String thePrompt, String theCorrectAnswer, boolean theAnswered) {
		super(thePrompt, theCorrectAnswer, theAnswered);
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
	 * Display an SA Question. 
	 * @return returns SA Question String
	 */
	public String toString() { // only displays question prompt right now. 
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
		sb.append("Please give a one word answer to the above prompt");
		return sb.toString();
	}
}
