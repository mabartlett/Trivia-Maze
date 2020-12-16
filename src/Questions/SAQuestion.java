package Questions;

/**
 * Class that stores everything a question would need.
 * @author Team 2
 * @version 11/30/20
 */
public class SAQuestion extends Question {
		
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
	
	public String toString() { // only displays question prompt right now. 
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
		sb.append("Please give a one word answer to the above prompt");
		return sb.toString();
	}
}
