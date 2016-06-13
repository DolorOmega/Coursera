package course3week1;

public class CaesarCipherOO {
	
	private String alphabet;
	private String shiftedAlphabet;
	private int mainKey = 23;
	
	public CaesarCipherOO(int key){
		mainKey = key;
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(mainKey) + alphabet.substring(0,mainKey);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result = "";
		CaesarCipherOO cc = new CaesarCipherOO(15);
		result = cc.encrypt("Can you imagine life WITHOUT the internet AND computers in your pocket?");
		System.out.println(result);
	}
	
	public String encrypt (String input){
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
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
	
	public String decrypt (String input){
		CaesarCipherOO cc = new CaesarCipherOO(26 - mainKey);
		
		return cc.encrypt(input);
	}

}
