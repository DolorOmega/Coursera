package course3week1;

import java.util.Arrays;

public class CaesarBreaker {

	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CaesarBreaker cb = new CaesarBreaker();
		String result = "";
		
		//result = cb.halfOfString("Qbkm Zgis", 0);
		result = cb.decrypt("Top ncmy qkff vi vguv vbg ycpx");
		System.out.println(result);
	}

	public String decrypt(String encrypted){
		
		int key = getKey(encrypted);
		
		return new CaesarCipher().encrypt("test", 2);
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
	
	public static int[] countLetters( String val ) {
			
		int[] counts = new int[26];
			
		for( int i = 0; i < val.length(); i++ ) {
			char ch = Character.toUpperCase( val.charAt( i ) );
			int index = ALPHABET.indexOf( ch );
			if( index != -1 ) {
				counts[index]++;
			}
		}
		
		return counts;
			
	}
	
	public void testDecrypt(String encrypted){
		String result = "";
		
		result = decrypt(encrypted);
		
		System.out.println(result);
	}
	
	public String[] splitStrings (String stringToSplit, int numOfStrings){
		String[] result = new String[numOfStrings];
		Arrays.fill( result, "" );
		
		for( int i = 0; i < stringToSplit.length(); i++ ) {
			int index = ( i % numOfStrings );
			result[index] += stringToSplit.charAt( i );
		}
		
		return result;
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
	
	public String halfOfString (String message, int start){
		StringBuilder splitString = new StringBuilder();
		
		for(int i = 0; i < message.length(); i++){
			if (start == 0 && (i == 0 || (i & 1) == 0)){
				splitString.append(message.charAt(i));
			}else {
				splitString.append(message.charAt(i));
			}
		}
		
		return splitString.toString();
		
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
	
	public String decryptTwoKeys(String encrypted){
		String[] splitStrings = splitStrings(encrypted, 2);
		
		String decrypted1 = decrypt(splitStrings[0]);
		String decrypted2 = decrypt(splitStrings[1]);
		
		return sewStrings(decrypted1, decrypted2);
	}
}
