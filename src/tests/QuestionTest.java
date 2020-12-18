/**
 * Team 2: Calvin Nguyen, Joseph Bode, and Marcus Bartlett.
 * TCSS 360
 * Trivia Maze
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Questions.Question;
import Questions.TFQuestion;

/**
 * This class tests all the methods of the abstract Question class.
 * @author Team 2
 * @version Autumn 2020
 */
public class QuestionTest {
	
	/** The prompt for the question. */
	public static final String VALID_PROMPT = "The color of the sky is blue?";
	
	/** Another valid prompt for the question. */
	public static final String OTHER_VALID_PROMPT = "This statement is false.";
	
	/** The correct answer for the question. */
	public static final String VALID_ANSWER = "true";
	
	/** Another valid answer for the question. */
	public static final String OTHER_VALID_ANSWER = "false";
	
	/** The question object used for testing. */
	private Question myQuestion;

	/**
	 * Test method for 
	 * {@link Questions.Question#Question(java.lang.String, 
	 * java.lang.String, boolean)}.
	 */
	@Before
	public void testQuestion() {
		myQuestion = new TFQuestion(VALID_PROMPT, VALID_ANSWER, false);
	}

	/**
	 * Test method for {@link Questions.Question#getPrompt()}.
	 */
	@Test
	public void testGetPrompt() {
		assertEquals("getPrompt() does not return correct prompt.", 
				myQuestion.getPrompt(), VALID_PROMPT);
	}

	/**
	 * Test method for {@link Questions.Question#getCorrectAnswer()}.
	 */
	@Test
	public void testGetCorrectAnswer() {
		assertEquals("getCorrectAnswer() does not return correct answer.", 
				myQuestion.getCorrectAnswer(), VALID_ANSWER);
	}

	/**
	 * Test method for {@link Questions.Question#isAnswered()}.
	 */
	@Test
	public void testIsAnswered() {
		assertFalse("isAnswered() does not return myAnswered correctly.", 
				myQuestion.isAnswered());
	}

	/**
	 * Test method for {@link Questions.Question#setAnswered(boolean)}.
	 */
	@Test
	public void testSetAnswered() {
		myQuestion.setAnswered(true);
		assertTrue("setAnswered() does not set myAnswered() correctly.", 
				myQuestion.isAnswered());
	}

	/**
	 * Test method for {@link Questions.Question#setPrompt(java.lang.String)}.
	 */
	@Test
	public void testSetPrompt() {
		myQuestion.setPrompt(OTHER_VALID_PROMPT);
		assertEquals("setPrompt() does not set the prompt correctly.", 
				myQuestion.getPrompt(), OTHER_VALID_PROMPT);
	}

	/**
	 * Test method for 
	 * {@link Questions.Question#setCorrectAnswer(java.lang.String)}.
	 */
	@Test
	public void testSetCorrectAnswer() {
		myQuestion.setCorrectAnswer(OTHER_VALID_ANSWER);
		assertEquals("setCorrectAnswer() does not set the answer correctly.", 
				myQuestion.getCorrectAnswer(), OTHER_VALID_ANSWER);
	}

}
