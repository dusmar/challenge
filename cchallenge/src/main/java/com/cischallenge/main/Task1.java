package com.cischallenge.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.cischallenge.graph.GNode;

public class Task1 {

	/**
	 *  method is using  breadth first traversal algorithm to return all nodes in graph.
	 * 
	 * @param node root node
	 * @return ArrayList containing every GNode in the graph. Each node should appear
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
