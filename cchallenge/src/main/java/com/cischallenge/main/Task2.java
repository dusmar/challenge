package com.cischallenge.main;

import java.util.ArrayList;
import java.util.List;

import com.cischallenge.graph.GNode;

public class Task2 {

	
	private void pathsInner(GNode node, List<GNode> currentPath, List<List<GNode>> allPaths) {
		// for every child
		currentPath.add(node);
		if (node.getChildren().length==0) {
			allPaths.add(currentPath);
		}
		for (GNode n : node.getChildren()) {
			pathsInner(n, new ArrayList<GNode>(currentPath),allPaths);
		}
	}

	/**
	 * Method is using depth first traversal algorithm to return all possible paths started at 'node'
	 * @param start of paths
	 * @return ArrayList of ArrayLists, representing all  possible paths through the graph starting at 'node'. 
	 */
	public List<List<GNode>> paths(GNode node) {
		List<List<GNode>> allPaths = new ArrayList<List<GNode>>();
		pathsInner(node, new ArrayList<GNode>(), allPaths);
		print(allPaths);
		return allPaths;
	}
	
	
	private void print(List<List<GNode>> paths){
		for (List<GNode> path: paths) {
			for (GNode node:path) {
				System.out.print(node.getName());
				System.out.print(" ");
			}
			System.out.println("");
		}
		
	}

}
