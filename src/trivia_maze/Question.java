package trivia_maze;

/**
 * Class that stores everything a question would need.
 * @author Team 2
 * @version 11/30/20
 */
public class Question {
	
	private String myPrompt;
	
	private String myCorrectAnswer;
	
	private String[] myWrongAnswers;
	
	private boolean myAnswered; 
	
	public Question(String thePrompt, String theCorrectAnswer, 
			String[] theWrongAnswers, boolean theAnswered) {
		myPrompt = thePrompt;
		myCorrectAnswer = theCorrectAnswer;
		myAnswered = theAnswered;
		// TODO use enhanced for loop.
		for (int i=0; i < theWrongAnswers.length; i++) {
			myWrongAnswers[i] = theWrongAnswers[i];
		}
	}
	
	public String getPrompt() {
		return myPrompt;
	}
	
	public String getCorrectAnswer() {
		return myCorrectAnswer;
	}
	
	public boolean getAnswer() {
		return myAnswered;
	}
	
	/**
	 * Check if the answer from user is correct. 3 different cases, True/False 
	 * case, Multplie choice, short answer.
	 * @param theAnswer User Answer
	 * @return if the answer is correct
	 */
	public boolean answerIsCorrect(String theAnswer) {
		return false;
	}
	
	/**
	 * This will display a question to 
	 * @param theQuestion
	 * @return the question to be displayed to the user
	 */
	public String displayQuestion(Question theQuestion) {
		return "";
	}
}
