package Questions;

/**
 * This class defines behavior for a basic question and since it is abstract,
 * you can create the actual types of question in the hierarchy. 
 * @author Joseph Bode
 * @version 12/15/20
 */

public abstract class Question {
	
	/**
	 * the prompt to give to the user.
	 */
	private String myPrompt;
	
	/**
	 * the correct answer of a question.
	 */
	private String myCorrectAnswer;
	
	/**
	 * whether the question is answered or not. 
	 */
	private boolean myAnswered; 
	
	/**
	 * This constructs a Question object with the previous 
	 * fields mentioned. 
	 * @param thePrompt the prompt of a question.
	 * @param theCorrectAnswer the correct answer of a question.
	 * @param theAnswered whether the question is answered or not. 
	 */
	public Question(String thePrompt, String theCorrectAnswer, boolean theAnswered) {
		myPrompt = thePrompt;
		myCorrectAnswer = theCorrectAnswer;
		myAnswered = theAnswered;
	}
	
	/**
	 * method to get the prompt of the question.
	 * @return prompt
	 */
	public String getPrompt() {
		return myPrompt;
	}
	
	/**
	 * method to get the correct of answer of the question. 
	 * @return the correct answer
	 */
	public String getCorrectAnswer() {
		return myCorrectAnswer;
	}
	
	/**
	 * Return myAnswered value.
	 * @return boolean value if the question is answered or not. 
	 */
	public boolean isAnswered() {
		return myAnswered;
	}
	
	/**
	 * set the boolean value of the myAnswered.
	 * @param theAnswered the value to change to. 
	 */
	public void setAnswered(boolean theAnswered) {
		myAnswered = theAnswered;
	}
	
	/**
	 * Set the prompt (will likely never be needed, just functionality.
	 * @param thePrompt set the prompt given para
	 */
	public void setPrompt(String thePrompt) {
		myPrompt = thePrompt;
	}
	
	/**
	 * Set the correct answer. (will likely never be needed, just functionality.
	 * @param theCorrectAnswer set the correct answer.
	 */
	public void setCorrectAnswer(String theCorrectAnswer) {
		myCorrectAnswer = theCorrectAnswer;
	}
	
	// DEFINED IN OTHER CLASSES AS ABSTRACT DICTATES. 
	public abstract boolean isAnswerCorrect(String theAnswer);
	
	public abstract String toString();
}
