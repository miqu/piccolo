package org.piccolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class will be used to read the barcodes on a Rasperry PI
 */
public class ReaderController {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public String readCode() throws IOException{
		String line = reader.readLine();
		return line;
	}

}
