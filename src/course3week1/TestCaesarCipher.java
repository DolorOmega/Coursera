package course3week1;

import edu.duke.FileResource;

public class TestCaesarCipher {
	
	private static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	
	public void breakCaesarCipher (String input){
		int key = getKey(input);
		String result = "";
		
		CaesarCipherOO cc = new CaesarCipherOO(key);
		result = cc.decrypt(input);
		System.out.println(result);
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
	
	public void simpleTests (){
		FileResource fr = new FileResource();
        String message = fr.asString();
        String encryptedStr = "";
        String decryptedStr = "";
        
        CaesarCipherOO cc = new CaesarCipherOO(18);
        encryptedStr = cc.encrypt(message);
        System.out.println("Encrypted string is: " + encryptedStr);
        
        decryptedStr = cc.decrypt(encryptedStr);
        System.out.println("Decrypted string is: " + decryptedStr);
        
        System.out.println("Calling breaker method.\n");
        breakCaesarCipher(encryptedStr);
	}

}
