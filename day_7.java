package some_asm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;

public class Main {

	static HashMap<String, Integer> board = new HashMap<>();

	public static void main(String[] args) {

		String path = "/home/koby/workspace/some_asm/src/some_asm/input";
		int fileLength = 0;
		try (BufferedReader nr = new BufferedReader(new FileReader(new File(
				path)))) {
			while (nr.readLine() != null) {
				++fileLength;
			}
		} catch (IOException ioex) {
			ioex.printStackTrace();
		}
		boolean[] commands = new boolean[fileLength];

		System.out.println("Processing (" + fileLength + ")..");

		String line;
		boolean res = false;

		for (int k=0; k<fileLength; k++) {

			try (BufferedReader reader = new BufferedReader(new FileReader(new File(path)))) {

				res = false;
				int pos = 0;
				while ((line = reader.readLine()) != null) {
					String[] input = line.split(" ");
					if (input.length == 3 && input[1].equals("->")) {
						res = PROVIDE(input[0], input[2]);
					} else if (input[0].equals("NOT")) {
						res = NOT(input[1], input[3]);
					} else {
						switch (input[1]) {
						case "AND":
							res = AND(input[0], input[2], input[4]);
							break;
						case "OR":
							res = OR(input[0], input[2], input[4]);
							break;
						case "LSHIFT":
							res = LSHIFT(input[0], input[2], input[4]);
							break;
						case "RSHIFT":
							res = RSHIFT(input[0], input[2], input[4]);
							break;
						}
					}
					if (res) {
						commands[pos] = res;
					}
					++pos;
				}
			} catch (IOException ioex) {
				ioex.printStackTrace();
			}
		}
		printMap();
	}

	// gates logic

	public static boolean PROVIDE(String a, String target) {
		int x = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a) != null) {
				x = board.get(a);
			} else
				return false;
		}
		board.put(target, x);
		return true;
	}

	public static boolean AND(String a, String b, String target) {
		int x = 0, y = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a) != null) {
				x = board.get(a);
			} else
				return false;
		}

		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b) != null) {
				y = board.get(b);
			} else
				return false;
		}
		board.put(target, x & y);
		return true;
	}

	public static boolean OR(String a, String b, String target) {
		int x = 0, y = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a) != null) {
				x = board.get(a);
			} else
				return false;
		}

		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b) != null) {
				y = board.get(b);
			} else
				return false;
		}
		board.put(target, x | y);
		return true;
	}

	public static boolean LSHIFT(String a, String b, String target) {
		int x = 0, y = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a) != null) {
				x = board.get(a);
			} else
				return false;
		}

		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b) != null) {
				y = board.get(b);
			} else
				return false;
		}
		board.put(target, x << y);
		return true;
	}

	public static boolean RSHIFT(String a, String b, String target) {
		int x = 0, y = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a) != null) {
				x = board.get(a);
			} else
				return false;
		}

		if (isNumber(b)) {
			y = Integer.parseInt(b);
		} else {
			if (board.get(b) != null) {
				y = board.get(b);
			} else
				return false;
		}
		board.put(target, x >> y);
		return true;
	}

	public static boolean NOT(String a, String target) {
		int x = 0;
		if (isNumber(a)) {
			x = Integer.parseInt(a);
		} else {
			if (board.get(a) != null) {
				x = board.get(a);
			} else
				return false;
		}
		board.put(target, 65535 - x);
		return true;
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
		for (String obj : board.keySet()) {
			System.out.println(obj + " : " + board.get(obj));
		}
	}

}
