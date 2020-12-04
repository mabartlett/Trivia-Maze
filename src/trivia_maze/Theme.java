package trivia_maze;

/**
 * 
 * @author Team 2
 * @version 
 */
public class Theme {
	
	private String myName; 
	
	private Question[] myQuestions;
	
	public Theme(String theName, Question[] theQuestions) { // TODO: use arraylist or array???
		myName = theName;
		for (int i=0; i < theQuestions.length; i++) {
			myQuestions[i] = theQuestions[i];
		}
	}
	
	public String getName() {
		return myName;
	}
	
	public Question[] getQuestions() {
		return myQuestions;
	}
	
	public void setName(String theName) {
		myName = theName;
	}
	
	
	
}
