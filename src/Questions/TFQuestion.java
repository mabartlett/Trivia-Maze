package Questions;

public class TFQuestion extends Question {
	
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
	
	public String toString() { // only displays question prompt right now. 
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
		sb.append("Please answer True or False.");
		return sb.toString();
	}
	
}
