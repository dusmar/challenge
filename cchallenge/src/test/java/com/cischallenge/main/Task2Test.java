package com.cischallenge.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cischallenge.graph.GNode;
import com.cischallenge.graph.GNodeImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Task 2.
 */
public class Task2Test extends TestCase {

	private GNode root;

	private List<List<GNode>> expectedResult;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		GNodeImpl nodeJ = new GNodeImpl("J", new GNodeImpl[0]);
		GNodeImpl nodeD = new GNodeImpl("D", new GNodeImpl[] { nodeJ });

		GNodeImpl nodeG = new GNodeImpl("G", new GNodeImpl[0]);
		GNodeImpl nodeH = new GNodeImpl("H", new GNodeImpl[0]);

		GNodeImpl nodeI = new GNodeImpl("I", new GNodeImpl[0]);

		GNodeImpl nodeC = new GNodeImpl("C", new GNodeImpl[] { nodeG, nodeH, nodeI });

		GNodeImpl nodeE = new GNodeImpl("E", new GNodeImpl[0]);
		GNodeImpl nodeF = new GNodeImpl("F", new GNodeImpl[0]);

		GNodeImpl nodeB = new GNodeImpl("B", new GNodeImpl[] { nodeE, nodeF });

		root = new GNodeImpl("A", new GNodeImpl[] { nodeB, nodeC, nodeD });

		expectedResult= new ArrayList<List<GNode>>();
		List<GNode> path1 = Arrays.asList(root, nodeB, nodeE);
		expectedResult.add(path1);

		List<GNode> path2 = Arrays.asList(root, nodeB, nodeF);
		expectedResult.add(path2);

		List<GNode> path3 = Arrays.asList(root, nodeC, nodeG);
		expectedResult.add(path3);

		List<GNode> path4 = Arrays.asList(root, nodeC, nodeH);
		expectedResult.add(path4);

		List<GNode> path5 = Arrays.asList(root, nodeC, nodeI);
		expectedResult.add(path5);

		List<GNode> path6 = Arrays.asList(root, nodeD, nodeJ);
		expectedResult.add(path6);

	}

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public Task2Test(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(Task2Test.class);
	}


	
	/**
     * input graph
     *	A
     *		B
     *  		E
     *  		F
     *		C	
     *  		G
     *  		H
     *  		I
     * 		D
     *  		J
	 *
     *
     */
	public void testPaths() {
		Task2 task2 = new Task2();
		List<List<GNode>> results = task2.paths(root);
		assertTrue(expectedResult.containsAll(results) && results.containsAll(expectedResult)
				&& results.size() == expectedResult.size());

	}

}
