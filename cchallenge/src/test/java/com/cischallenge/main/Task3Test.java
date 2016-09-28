package com.cischallenge.main;

import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Task 2.
 */
public class Task3Test extends TestCase {

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
		Map<String, Long> words = task3.words("words.txt");

	}

}
