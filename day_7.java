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

	static HashMap<String, Integer> board = new HashMap<>();

	public static void main(String[] args) {

		String path = "/home/koby/workspace/some_asm/src/some_asm/input";
		
		System.out.println("Processing ..");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {

			String line;
			while ((line = reader.readLine()) != null) {
				String[] input = line.split(" ");
				if (input.length==3 && input[1].equals("->")) {
					PROVIDE(input[0], input[2]);
				} else if (input[0].equals("NOT")) {
					NOT(input[1], input[3]);
				} else {
					switch (input[1]) {
					case "AND":
						AND(input[0], input[2], input[4]);
						break;
					case "OR":
						OR(input[0], input[2], input[4]);
						break;
					case "LSHIFT":
						LSHIFT(input[0], input[2], input[4]);
						break;
					case "RSHIFT":
						RSHIFT(input[0], input[2], input[4]);
						break;
					}
				}

			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		
		printMap();
	}
	
	public static void initMap() {
		for (char i='a'; i<='z'; i++) {
			board.put(""+i, 0);
		}
		for (char i='a'; i<='z'; i++) {
			for (char j='a'; j<='z'; j++) {
				board.put(""+i + j, 0);
			}
		}
	}
	
	// gates logic
	
	public static void PROVIDE(String a, String target) {		
		int x=0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a)!=null) {
				x = board.get(a);
			} else return;
		}
		board.put(target, x);
	}

	public static void AND(String a, String b, String target){
		int x=0, y=0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a)!=null) {
				x = board.get(a);
			} else return;
		}
		
		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b)!=null) {
				y = board.get(b);
			} else return;
		}
		board.put(target, x&y);
	}

	public static void OR(String a, String b, String target) {
		int x=0, y=0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a)!=null) {
				x = board.get(a);
			} else return;
		}
		
		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b)!=null) {
				y = board.get(b);
			} else return;
		}
		board.put(target, x|y);
	}

	public static void LSHIFT(String a, String b, String target) {
		int x=0, y=0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a)!=null) {
				x = board.get(a);
			} else return;
		}
		
		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b)!=null) {
				y = board.get(b);
			} else return;
		}
		board.put(target, x<<y);
	}

	public static void RSHIFT(String a, String b, String target) {
		int x=0, y=0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a)!=null) {
				x = board.get(a);
			} else return;
		}
		
		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b)!=null) {
				y = board.get(b);
			} else return;
		}
		board.put(target, x>>y);
	}

	public static void NOT(String a, String target) {
		int x=0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a)!=null) {
				x = board.get(a);
			} else return;
		}
		board.put(target, 65535 - x);
	}
	
	// utility methods

	public static boolean isNumber(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}
	
	public static void printMap() {
		for(String obj:board.keySet()) {
			System.out.println(obj + " : " + board.get(obj));
		}
	}

}
