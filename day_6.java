package xmas_lights;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	// This solution should work on Java 7 and up
	public static void main(String[] args) {
		
		String filePath = "/home/koby/workspace/xmas_lights/src/xmas_lights/input";	
		int[][] matrix = new int[1000][1000];
		
		// process input
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));) {			
			String line;
			while((line = reader.readLine())!=null) {
				
				String[] words = line.split(" ");				
				String[] to = words[words.length-1].split(",");
				String[] from = words[words.length-3].split(",");
				String cmd = words[words.length-4];
				
				int from_x = Integer.parseInt(from[0]);
				int from_y = Integer.parseInt(from[1]);
				int to_x = Integer.parseInt(to[0]);
				int to_y = Integer.parseInt(to[1]);
				
				for (int x=from_x; x<=to_x; x++) {
					for (int y=from_y; y<=to_y; y++) {
						switch(cmd) {
						case "on":
							++matrix[x][y];
							break;
						case "off":
							if (matrix[x][y]>0) {
								--matrix[x][y];
							}
							break;
						case "toggle":
							matrix[x][y]+=2;
							break;
						}
					}
				}
			}		
		} catch(IOException ioex) {
			ioex.printStackTrace();
		}
		
		// count
		long lit = 0;
		for (int x=0; x<1000; x++) {
			for (int y=0; y<1000; y++) {
				lit+=matrix[x][y];
			}
		}
		System.out.println("Total brightness: " + lit);		
	}
}
