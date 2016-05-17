package course3week1;

import edu.duke.*;
import java.lang.*;

public class WordPlay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WordPlay wp = new WordPlay();
		//Boolean vowelResult;
		//String replaceResult = "";
		String emphasis = "";
		//vowelResult = wp.isVowel('A');
		//System.out.println(vowelResult);
		
		//replaceResult = wp.replaceVowels("This is a test", '*');
		//System.out.println(replaceResult);
		
		emphasis = wp.emphasize("dna ctgaaactga", 'a');
		System.out.println(emphasis);
	}
	
	public Boolean isVowel(char ch){
		Boolean result = false;
		String vowels = "aeiou";
		ch = Character.toLowerCase(ch);
				
		for (int i = 0; i < vowels.length(); i++){
			if (ch == vowels.charAt(i)){
				result = true;
			}
		}
		
		return result;
	}
	
	public String replaceVowels(String phrase, char ch){
		StringBuilder replacedVowels = new StringBuilder(phrase);
		
		for (int i = 0; i < phrase.length(); i++){
			char examineChar = phrase.charAt(i);
			if(isVowel(examineChar)){
				replacedVowels.setCharAt(i, ch);
			}
		}
		
		return replacedVowels.toString();
	}
	
	public String emphasize(String phrase, char ch){
		StringBuilder emphasized = new StringBuilder(phrase);
		
		for(int i = 0; i < phrase.length(); i++){
			if (ch == emphasized.charAt(i)){
				if (i == 0 || (i & 2) == 0){
					emphasized.setCharAt(i, '+');
				}else {
					emphasized.setCharAt(i, '*');
				}
			}
		}
		
		return emphasized.toString();
	}

}
