package Questions;

public class QuestionDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prompt = "The color of the sky is blue?";
		String correct = "true";
		boolean answered = false;
		Question test = new TFQuestion(prompt, correct, answered);
		System.out.println(test.toString());
		
		String prompt1 = "What is the name of the first President of the United States?";
		String correct1 = "George Washington";
		boolean answered1 = false;
		Question test1 = new SAQuestion(prompt1, correct1, answered1);
		System.out.println(test1.toString());
		
		String prompt2 = "What coding language does UW Tacoma mostly teach?";
		String[] answers = {"Ruby", "Python", "Java", "Javascript"};
		String correct2 = "Java";
		boolean answered2 = false;
		Question test2 = new MCQuestion(prompt2, correct2, answered2, answers);
		System.out.println(test2.toString());
	}

}
