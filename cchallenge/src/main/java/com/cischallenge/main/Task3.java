package com.cischallenge.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task3 {

	public Map<String, Long> words(String inputFile) {
		Map<String, Long> words = new HashMap<String, Long>();
		Scanner input;
		try {
			input = new Scanner(new File(getClass().getResource("/" + inputFile).getFile()));
		} catch (FileNotFoundException e) {
			// DM TODO error handling
			e.printStackTrace();
			return null;
		}
		while (input.hasNextLine()) {
			if (input.hasNext()) {
				String word = input.next();
				long count = 1;
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

	private void print(Map<String, Long> words) {
		for (String word : words.keySet()) {
			System.out.print(words.get(word));
			System.out.print(" ");
			System.out.print(word);
			System.out.println();

		}

	}

}
