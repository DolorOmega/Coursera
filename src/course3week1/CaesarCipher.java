package course3week1;

import edu.duke.*;
import java.lang.*;

public class CaesarCipher {

	public static void main(String[] args) {
		String result = "";
		
		CaesarCipher cc = new CaesarCipher();
		result = cc.encryptTwoKeys("First Legion", 23, 17);
		System.out.println(result);
	}
	
	public String encrypt (String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0,key);

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            Boolean a = Character.isUpperCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1){
                char newChar = shiftedAlphabet.charAt(idx);
                if(Character.isUpperCase(currChar)){
                	encrypted.setCharAt(i, newChar);
                }else{
                	encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }
            }
        }
        return encrypted.toString();
	}
	
	public void testCaesar (){
		FileResource fr = new FileResource();
		String message = fr.asString();
		int key = 23;
		String encrypted = encrypt(message, key);
		System.out.println("key is " + key + "\n" + encrypted);
	}
	
	public String encryptTwoKeys (String input, int key1, int key2) {
		Boolean altBool = true;
		StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0,key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0,key2);

        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            Boolean a = Character.isUpperCase(currChar);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1){
            	if (i == 0 || (i & 2) == 0){
	                char newChar = shiftedAlphabet1.charAt(idx);
	                if(Character.isUpperCase(currChar)){
	                	encrypted.setCharAt(i, newChar);
	                }else{
	                	encrypted.setCharAt(i, Character.toLowerCase(newChar));
	                }
	                altBool = false;
            	}else{
            		char newChar = shiftedAlphabet2.charAt(idx);
	                if(Character.isUpperCase(currChar)){
	                	encrypted.setCharAt(i, newChar);
	                }else{
	                	encrypted.setCharAt(i, Character.toLowerCase(newChar));
	                }
	                altBool = true;
            	}
            }
        }
        return encrypted.toString();
	}

}
