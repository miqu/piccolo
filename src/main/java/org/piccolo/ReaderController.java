package org.piccolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * This class will be used to read the barcodes on a Rasperry PI
 */
public class ReaderController {
	
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public String readId() throws IOException{
		String line = reader.readLine();
		return line;
	}

    public static void main(String ... args){
        ReaderController rc = new ReaderController();
        String luettu="";
        while (!"lopeta".equals(luettu)){

            try {
                luettu=rc.readId();
                if(luettu==null){
                    Thread.sleep(100);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(luettu);
        }
    }
}