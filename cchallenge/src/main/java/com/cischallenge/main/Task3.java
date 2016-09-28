package com.cischallenge.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {

	/**
	 * 
	 * method counts words in input file. There is some basic normalization in place ( '.' or ',' are removed if they are last characters in word
	 * 
	 * 
	 * @param inputFile input file object
	 * @return map where key is word and value is number of occurrences in inputFile
	 */
	public Map<String, Long> words(File inputFile) {
		Map<String, Long> words = new HashMap<String, Long>();
		Scanner input;
		try {
			input = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			// DM TODO error handling
			e.printStackTrace();
			return null;
		}
		while (input.hasNextLine()) {
			if (input.hasNext()) {
				String word = normalize(input.next());
				long count = 0;
				if (words.containsKey(word)) {
					count = words.get(word);
				}
				words.put(word, count + 1);
			}
		}
		input.close();
		print(words);
		return words;
	}

	private String normalize(String next) {
		if (next.endsWith(".") || next.endsWith(",")) { // remove comma or dot
														// if it is last
														// character in the word
			next = next.substring(0, next.length() - 1);
		}
		return next.toLowerCase(); // lower
	}

	private void print(Map<String, Long> words) {
		for (String word : words.keySet()) {
			System.out.print(words.get(word));
			System.out.print(" ");
			System.out.print(word);
			System.out.println();

		}

	}

}
