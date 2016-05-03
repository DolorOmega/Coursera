package week2;

public class PracticeQuiz1 {

	public static void findAbc(String input) {
	    int index = input.indexOf("abc");
	    System.out.println("ind1: " + (index+1) + " ind2: " + (index+4));
	    while (true) {
	        if (index == -1) {
	            break;
	        }
	        if (index > input.length() - 3){
	        	break;
	        }
	        String found = input.substring(index+1, index+4);
	        System.out.println(found);
	        index = input.indexOf("abc", index+4);
	    }
	}
	   public static void main(String [] args) {
	    //no code yet
		//findAbc("abcd");
		   findAbc("abcdabc");
	}
	   
}
