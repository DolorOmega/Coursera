package course3week1;

import edu.duke.FileResource;

public class TestCaesarCipherTwo {
	
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public String halfOfString(String message, int start){
		String result = "";
		
		for(int i = start; i < message.length(); i+=2){
			result = result + message.charAt(i);
		}
		
		return result;
	}
	
	public static int[] countLetters( String val ) {
		
		int[] counts = new int[26];
			
		for( int i = 0; i < val.length(); i++ ) {
			char ch = Character.toUpperCase( val.charAt( i ) );
			int index = alphabet.indexOf( ch );
			if( index != -1 ) {
				counts[index]++;
			}
		}
		
		return counts;
			
	}
	
	public int indexOfMax(int[] values){
		int maxIndex = 0;
		
		for (int i = 0; i < values.length; i++){
			if (values[i] < values[maxIndex]){
				maxIndex = i;
			}
		}
		
		return maxIndex;
	}
	
	public String sewStrings(String ... message){
		StringBuilder result = new StringBuilder();
		
		if( message != null && message.length > 0 ) {
			for( int i = 0; i < message[0].length(); i++ ) {
				for( int j = 0; j < message.length; j++ ) {
					if( message[j].length() > i ) {
						result.append( message[j].charAt( i ) );
					}
				}
			}
			
		}
		
		return result.toString();
	}
	
	public int getKey( String s ) {
		
		int[] frequencies = countLetters( s );
		
		int maxIndex = indexOfMax( frequencies );
		int dkey = maxIndex - 4;
		
		if( maxIndex < 4 ) {
			dkey = 26 - ( 4 - maxIndex );
		}
		
		return dkey;
		
	}
	
	public void breakCaesarCipher(String input){
		
	}
	
	public void simpleTests(){
		FileResource fr = new FileResource();
		String message = fr.asString();
		String encryptedStr = "";
		String decryptedStr = "";
		
		CaesarCipherTwo cct = new CaesarCipherTwo(17, 3);
		encryptedStr = cct.encrypt(message);
		System.out.println("Encrypted string is: " + encryptedStr);
		
		decryptedStr = cct.decrypt(encryptedStr);
		System.out.println("Decrypted string is: " + decryptedStr);
		
	}
	

}
