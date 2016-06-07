package course3week4;

import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
	
	public static void main (String args[]){
		VigenereBreaker vb = new VigenereBreaker();
		String result = "";
		
		result = vb.sliceString("abcdefghijklm", 0, 4);
		System.out.println(result);
	}
	
    public String sliceString(String message, int whichSlice, int totalSlices) {
    	StringBuilder modify = new StringBuilder();
    	char slice = '\0';
    	    	
    	for (int i = whichSlice; i < message.length(); i += totalSlices){
    		slice = message.charAt(i);
    		modify.append(slice);
    	}
    	
        return modify.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
    }
    
}
