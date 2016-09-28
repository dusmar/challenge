package com.cischallenge.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.cischallenge.graph.GNode;

public class Task1 {

	/**
	 *  breadth first traversal. node are stored to arraylist and then simple returned
	 * 
	 * @param root
	 *            node
	 * @return list containing every GNode in the graph. Each node should appear
	 *         in the ArrayList exactly once (i.e. no duplicates).
	 */
	public List<GNode> walkGraph(GNode node) {
		Queue<GNode> queue = new LinkedList<GNode>();
		List<GNode> result = new ArrayList<GNode>();
		queue.add(node);
		while (!queue.isEmpty()) {
			GNode curNode = queue.poll();
			result.add(curNode);
			for (GNode lnode : curNode.getChildren()) {
				queue.add(lnode);
			}
		}
		return result;

	}

}
