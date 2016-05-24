package course2week2;

import edu.duke.*;
import java.io.*;

public class GeneAndWebLinks {

	public static void main(){
		
	}
	
	public String findProtein(String dna){
		int start = dna.indexOf("atg");
		if (start == -1){
			return "";
		}
		int stop = dna.indexOf("tag", start+3);
		if ((stop - start) % 3 == 0){//protein should be multiple of 3
			return dna.substring(start,stop+3);
		}
		else{
			return "";
		}
	}
	
	public void testing(){
		String a = "cccatggggtttaaataataataggagagagagagagagttt";
		String ap = "atggggtttaaataataatag";
		
		String result = findProtein(a);
		if (ap.equals(result)){
			System.out.println("Success for " + ap + " length " + ap.length());
		}
		else {
			System.out.println("mistake for input: " + a);
			System.out.println("got: " + result);
			System.out.println("not: " + ap);
		}
	}
	
	public void realTesting(){
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()){
			FileResource fr = new FileResource(f);
			String s = fr.asString();
			System.out.println("read " + s.length() + " characters");
		}
	}
}
