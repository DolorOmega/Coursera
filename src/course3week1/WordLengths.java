package course3week1;

import edu.duke.FileResource;

public class WordLengths {

	public static void main(String[] args) {
		WordLengths wl = new WordLengths();
		wl.testCountWordLengths();
	}
	
	public int[] countWordLengths(FileResource resource, int[] counts){
		for (String word : resource.words()){
			char firstChar = word.charAt(0);
			char lastChar = word.charAt(word.length()-1);
			//System.out.println(lastChar);
			
			if (!Character.isAlphabetic(firstChar)){
				//System.out.println("first char non alpha word: " + word);
				word.substring(1, word.length());
			}
			if (!Character.isAlphabetic(lastChar)){
				//System.out.println("last char non alpha word: " + word);
				word.substring(0, word.length()-1);
				//System.out.println("word after last char substring: " + word);
			}
			
			if (word.length() > 0){
				counts[word.length()-1]++;
				//System.out.println("Word length incremented" + counts[word.length()]);
			}else{
				counts[0]++;
			}
		}
		
		return counts;
	}
	
	public void testCountWordLengths(){
		FileResource fr = new FileResource();
		int maxCountIndex = 0;
		
		int[] counts = countWordLengths(fr, new int[31]);
		maxCountIndex = indexOfMax(counts);
		
		for (int i = 0; i < counts.length; i++){
			System.out.println("Words of length " + (i + 1) + ": " + counts[i]);
		}
		
		System.out.println(maxCountIndex);
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

}
