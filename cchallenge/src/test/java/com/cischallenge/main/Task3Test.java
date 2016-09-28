package com.cischallenge.main;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Task 3.
 */
public class Task3Test extends TestCase {

	private Map<String, Long> expectedResults;

	@Override
	protected void setUp() throws Exception {
		expectedResults = new HashMap<String, Long>();

		expectedResults.put("waiting", 1L);
		expectedResults.put("scanning", 1L);
		expectedResults.put("for", 1L);
		expectedResults.put("recognized", 1L);
		expectedResults.put("used", 1L);
		expectedResults.put("input", 1L);
		expectedResults.put("default", 2L);
		expectedResults.put("previously", 1L);
		expectedResults.put("delimiter", 3L);
		expectedResults.put("whether", 1L);
		expectedResults.put("by", 2L);
		expectedResults.put("of", 2L);
		expectedResults.put("scanner", 1L);
		expectedResults.put("changed", 1L);
		expectedResults.put("block", 1L);
		expectedResults.put("value", 1L);
		expectedResults.put("regardless", 1L);
		expectedResults.put("a", 2L);
		expectedResults.put("method", 1L);
		expectedResults.put("may", 1L);
		expectedResults.put("will", 1L);
		expectedResults.put("was", 1L);
		expectedResults.put("character.iswhitespace", 1L);
		expectedResults.put("is", 1L);
		expectedResults.put("scanner's", 1L);
		expectedResults.put("it", 1L);
		expectedResults.put("the", 5L);
		expectedResults.put("as", 1L);
		expectedResults.put("reset()", 1L);
		expectedResults.put("reset", 1L);
		expectedResults.put("to", 1L);
		expectedResults.put("whitespace", 2L);
		expectedResults.put("operation", 1L);

	}

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public Task3Test(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(Task3Test.class);
	}

	public void testWords() {
		Task3 task3 = new Task3();
		Map<String, Long> words = task3.words(new File(getClass().getResource("/words.txt").getFile()));
		assertEquals(expectedResults, words);

	}

}
