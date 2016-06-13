package course3week1;

public class CaesarCipherTwo {
	
	private String alphabet;
	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private int mainKey1;
	private int mainKey2;
	
	public CaesarCipherTwo(int key1, int key2){
		mainKey1 = key1;
		mainKey2 = key2;
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "";
		CaesarCipherTwo cc = new CaesarCipherTwo(21, 8);
		result = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
		System.out.println(result);

	}
	
	public String encrypt(String input){
		StringBuilder encrypted = new StringBuilder(input);
		
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            if(idx != -1){
            	if (i == 0 || (i & 1) == 0){
	                char newChar = shiftedAlphabet1.charAt(idx);
	                if(Character.isUpperCase(currChar)){
	                	encrypted.setCharAt(i, newChar);
	                }else{
	                	encrypted.setCharAt(i, Character.toLowerCase(newChar));
	                }
            	}else{
            		char newChar = shiftedAlphabet2.charAt(idx);
	                if(Character.isUpperCase(currChar)){
	                	encrypted.setCharAt(i, newChar);
	                }else{
	                	encrypted.setCharAt(i, Character.toLowerCase(newChar));
	                }
            	}
            }
        }
        return encrypted.toString();
	}
	
	public String decrypt(String input){
		CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
		
		return cct.encrypt(input);
	}
	
	public static String mergeMultipleStrings( String... strings ) {
		
		StringBuilder result = new StringBuilder();
		
		if( strings != null && strings.length > 0 ) {
			for( int i = 0; i < strings[0].length(); i++ ) {
				for( int j = 0; j < strings.length; j++ ) {
					if( strings[j].length() > i ) {
						result.append( strings[j].charAt( i ) );
					}
				}
			}
		}
		
		return result.toString();
		
	}

}
