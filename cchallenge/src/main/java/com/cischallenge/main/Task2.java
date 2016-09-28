package com.cischallenge.main;

import java.util.ArrayList;
import java.util.List;

import com.cischallenge.graph.GNode;

public class Task2 {

	public void dfs(GNode node, List<GNode> currentPath, List<List<GNode>> allPaths) {
		System.out.println(node.getName());
		// for every child
		currentPath.add(node);
		if (node.getChildren().length==0) {
			allPaths.add(currentPath);
		}
		for (GNode n : node.getChildren()) {
			dfs(n, new ArrayList<GNode>(currentPath),allPaths);
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public List<List<GNode>> paths(GNode node) {
		List<List<GNode>> allPaths = new ArrayList<List<GNode>>();
		dfs(node, new ArrayList<GNode>(), allPaths);
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
