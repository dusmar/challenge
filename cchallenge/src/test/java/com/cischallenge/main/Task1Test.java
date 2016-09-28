package com.cischallenge.main;

import java.util.ArrayList;
import java.util.List;

import com.cischallenge.graph.GNode;
import com.cischallenge.graph.GNodeImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Task 1.
 */
public class Task1Test extends TestCase {
	
	private GNode root;
	
	private List<GNode> expectedResult;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
   
        expectedResult = new ArrayList<GNode>();
 		GNodeImpl nodeJ =  new GNodeImpl("J", new GNodeImpl[0]);	
 		expectedResult.add(nodeJ);
		GNodeImpl nodeD =  new GNodeImpl("D", new GNodeImpl[]{nodeJ});	
 		expectedResult.add(nodeD);

		
		GNodeImpl nodeG =  new GNodeImpl("G", new GNodeImpl[0]);	
 		expectedResult.add(nodeG);
		GNodeImpl nodeH =  new GNodeImpl("H", new GNodeImpl[0]);	
 		expectedResult.add(nodeH);
		GNodeImpl nodeI =  new GNodeImpl("I", new GNodeImpl[0]);	
 		expectedResult.add(nodeI);

		GNodeImpl nodeC =  new GNodeImpl("C", new GNodeImpl[]{nodeG, nodeH, nodeI});	
 		expectedResult.add(nodeC);
    	
		
		GNodeImpl nodeE =  new GNodeImpl("E", new GNodeImpl[0]);	
 		expectedResult.add(nodeE);
		GNodeImpl nodeF =  new GNodeImpl("F", new GNodeImpl[0]);	
 		expectedResult.add(nodeF);

		GNodeImpl nodeB =  new GNodeImpl("B", new GNodeImpl[]{nodeE, nodeF});	
 		expectedResult.add(nodeB);
	

		root =  new GNodeImpl("A", new GNodeImpl[]{nodeB, nodeC, nodeD});	
 		expectedResult.add(root);
	
    
    }
	
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public Task1Test(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(Task1Test.class);
	}

	
	/**
     * 
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

	
	public void testWalkGraph() {
		Task1 task1 = new Task1();
		List<GNode> results = task1.walkGraph(root);
		assertTrue(expectedResult.containsAll(results) && results.containsAll(expectedResult) && results.size()==expectedResult.size());
	}


}
