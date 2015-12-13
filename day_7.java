package some_asm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {
	/*
	 * Work in Progress
	 */
	
	HashMap<String, Integer> board = new HashMap<>();

	public static void main(String[] args) {
		
		String path = "/home/koby/workspace/some_asm/src/some_asm/input";
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {
			
			String line;
			while((line = reader.readLine())!=null) {
				String[] input = line.split(" ");
				
				// todo: process the input
				
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
	}
	
	public void AND(String a, String b, String target) {
		int x = 0, y = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			board.get(a);
		}
		if (isNumber(b)) {
			y = Integer.parseInt(a);
		} else {
			board.get(b);
		}
		board.put(target, x&y);
	}
	
	public boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

}
