package Questions;

public class MCQuestion extends Question {
	
	String[] myPossibleAnswers;
	
	public MCQuestion(String thePrompt, String theCorrectAnswer, boolean theAnswered, String[] thePossibleAnswers) {
		super(thePrompt, theCorrectAnswer, theAnswered);
		myPossibleAnswers = thePossibleAnswers.clone();
	}
	
	public String[] getPossibleAnswers() {
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
	
	public String toString() { // only displays question prompt right now. 
		StringBuilder sb = new StringBuilder();
		sb.append("Question: " + this.getPrompt());
		sb.append("\n");
		sb.append("a. " + myPossibleAnswers[0]);
		sb.append("\n");
		sb.append("b. " + myPossibleAnswers[1]);
		sb.append("\n");
		sb.append("c. " + myPossibleAnswers[2]);	
		sb.append("\n");
		sb.append("d. " + myPossibleAnswers[3]);
		return sb.toString();
	}
}
