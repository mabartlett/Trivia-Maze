package Questions;

public abstract class Question {
	private String myPrompt;
	
	private String myCorrectAnswer;
		
	private boolean myAnswered; 
	
	public Question(String thePrompt, String theCorrectAnswer, boolean theAnswered) {
		myPrompt = thePrompt;
		myCorrectAnswer = theCorrectAnswer;
		myAnswered = theAnswered;
	}
	
	public String getPrompt() {
		return myPrompt;
	}
	
	public String getCorrectAnswer() {
		return myCorrectAnswer;
	}
	
	public boolean isAnswered() {
		return myAnswered;
	}
	
	public void setAnswered(boolean theAnswered) {
		myAnswered = theAnswered;
	}
	
	public void setPrompt(String thePrompt) {
		myPrompt = thePrompt;
	}
	
	public void setCorrectAnswer(String theCorrectAnswer) {
		myCorrectAnswer = theCorrectAnswer;
	}
	
	public abstract boolean isAnswerCorrect(String theAnswer);
	
	public abstract String toString();
}
