package Questions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.sqlite.SQLiteDataSource;

public class QuestionDriver {

	public static void main(String[] args) {
		
		SQLiteDataSource ds = null;

        try {
            ds = new SQLiteDataSource();
            // C:\\Databases\\questions.db
            String url = "jdbc:sqlite:questions.db";
            ds.setUrl(url);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        
        System.out.println("Connected to database");
        String query = "SELECT * FROM TriviaQuestionsFinal";
        ArrayList<Question> arr = new ArrayList<Question>();
        
        try ( Connection conn = ds.getConnection();
               Statement stmt = conn.createStatement(); ) {
              ResultSet rs = stmt.executeQuery(query);
              while ( rs.next() ) {
            	  Question tempQuestion;
            	  String type = rs.getString( "type" );
            	  String prompt = rs.getString( "prompt" );
                  String correctAnswer = rs.getString( "correctAnswer");
                  boolean answered = false;
                  if(type.equals("MC")) {
                	  ArrayList<String> temp = new ArrayList<String>();
                	  temp.add(rs.getString( "correctAnswer" ));
                	  temp.add(rs.getString( "possibleAnswer1" ));
                	  temp.add(rs.getString( "possibleAnswer2" ));
                	  temp.add(rs.getString( "possibleAnswer3" ));
                	  tempQuestion = new MCQuestion(prompt, correctAnswer, answered, temp);
                      arr.add(tempQuestion);
                  } else if(type.equals("TF")) {
                	  tempQuestion = new TFQuestion(prompt, correctAnswer, answered);
                	  arr.add(tempQuestion);  
                  } else {
                	  tempQuestion = new SAQuestion(prompt, correctAnswer, answered);
                	  arr.add(tempQuestion);  
                  }
              }
          } catch ( SQLException e ) {
              e.printStackTrace();
              System.exit( 0 );
          }
        	

        System.out.println(arr.get(0).toString());
		
		// TODO Auto-generated method stub
//		String prompt = "The color of the sky is blue?";
//		String correct = "true";
//		boolean answered = false;
//		Question test = new TFQuestion(prompt, correct, answered);
//		System.out.println(test.toString());
//		
//		String prompt1 = "What is the name of the first President of the United States?";
//		String correct1 = "George Washington";
//		boolean answered1 = false;
//		Question test1 = new SAQuestion(prompt1, correct1, answered1);
//		System.out.println(test1.toString());
//		
//		String prompt2 = "What coding language does UW Tacoma mostly teach?";
//		String[] answers = {"Ruby", "Python", "Java", "Javascript"};
//		String correct2 = "Java";
//		boolean answered2 = false;
//		Question test2 = new MCQuestion(prompt2, correct2, answered2, answers);
//		System.out.println(test2.toString());
	}

}
