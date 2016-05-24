package course2week4;

import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.File;
import java.util.*;


public class MiniProjectBabyNames {

	public static void main (String [] args) {
		MiniProjectBabyNames miniProj = new MiniProjectBabyNames();
		miniProj.runSuite(6);
		//miniProj.testSuite();
		
		//initialize variables as static final to be reused throughout the code for easier readablilty
		//parse the data once into objects within the code instead of having re-read it every time
	}

//Utility method to run various methods quickly for the quiz/////////////////////////////////////////
	public void runSuite(int methodToRun){
		switch(methodToRun){
			case 1: FileResource fr = new FileResource("week4/data/yob1905.csv");
					totalBirths(fr);
					break;
			case 2: int rankResult = getRank(1971, "Frank", "m");
					System.out.println(rankResult);
					break;
			case 3: String nameResult = getName(1982, 450, "m");
					System.out.println(nameResult);
					break;
			case 4: whatIsNameInYear("Owen", 1974, 2014, "m");
					break;
			case 5: int highRankResult = yearOfHighestRank("Mich", "m");
					System.out.println(highRankResult);
					break;
			case 6: double avgResult = getAverageRank("Jacob", "m");
					System.out.println(avgResult);
					break;
			case 7: int higherBirthresult = getTotalBirthsRankedHigher(1990, "Drew", "m");
					System.out.println(higherBirthresult);
					break;
		}
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames = 0;
		Set<String> uniqueBoyNames = new HashSet<String>();
		Set<String> uniqueGirlNames = new HashSet<String>();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			totalNames += 1;
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				uniqueBoyNames.add(rec.get(0));
			}
			else {
				totalGirls += numBorn;
				uniqueGirlNames.add(rec.get(0));
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("total names = " + totalNames);
		System.out.println("total unique boy names = " + uniqueBoyNames.size());
		System.out.println("total unique girl names = " + uniqueGirlNames.size());
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////		
	public int getRank(int year, String name, String gender) {
		int maleRank = 0;
        int femaleRank = 0;
        Boolean foundName = false;
        
        FileResource fr = new FileResource("week4/data/yob" + year + ".csv");
        
        for(CSVRecord rec : fr.getCSVParser(false)){            
            if(rec.get(1).equalsIgnoreCase("F")){
                femaleRank++;
            }else if(rec.get(1).equalsIgnoreCase("M")){
                maleRank++;
            }
            
            if(rec.get(0).equalsIgnoreCase(name) && rec.get(1).equalsIgnoreCase(gender)){
                foundName = true;
                break;
            }
        }
        
        while(foundName == true){
	        if(gender.equalsIgnoreCase("M")){
	            return maleRank;
	        }else if(gender.equalsIgnoreCase("F")){
	            return femaleRank;
	        }
        }
        return -1;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public String getName (int year, int rank, String gender){
		int maleRank = 0;
        int femaleRank = 0;
        String resultName = "";
        Boolean foundName = false;
        
        FileResource fr = new FileResource("week4/data/yob" + year + ".csv");
        
        for(CSVRecord rec : fr.getCSVParser(false)){            
            if(rec.get(1).equalsIgnoreCase("F")){
                femaleRank++;
            }else if(rec.get(1).equalsIgnoreCase("M")){
                maleRank++;
            }
            
            if((femaleRank == rank || maleRank == rank) && rec.get(1).equalsIgnoreCase(gender)){
                foundName = true;
                resultName = rec.get(0);
                break;
            }
        }
        
        //init resultName with the default value and only return the resultName instead of the nearly infinite while loop
        while (foundName == true){
        	return resultName;
        }
        return "NO NAME";
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public void whatIsNameInYear (String name, int year, int newYear, String gender){
		int resultRank = getRank(year, name, gender);
		String resultName = getName(newYear, resultRank, gender);
		String genderNoun = "";
		
		if(gender.equalsIgnoreCase("M")){
            genderNoun = "he";
        }else if(gender.equalsIgnoreCase("F")){
            genderNoun = "she";
        }
		
		System.out.println(name + " born in " + year + " would be " + resultName + " if " + genderNoun + " was born in " + newYear);
	}
		
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public int yearOfHighestRank (String name, String gender){
	    int yearOfHighestRank = -1;
	    int compare = 0;
	    String year = "";
	    DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            
        	year = f.getName().substring(3, 7);
            int currentRank = getRank(Integer.parseInt(year),name,gender);
            if(currentRank != -1 && compare == 0){
            	compare = currentRank;
                yearOfHighestRank = Integer.parseInt(year);
            }
            if(currentRank < compare && currentRank != -1){
            	compare = currentRank;
                yearOfHighestRank = Integer.parseInt(year);
            }
        }
	    return yearOfHighestRank;
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public double getAverageRank(String name, String gender){         
		double averageRank = -1.0;
		double totalRank = 0;
		int counter = 0;
		String year = "";
		DirectoryResource dr = new DirectoryResource();
		 
		for (File f : dr.selectedFiles()) {
		    year = f.getName().substring(3, 7);
			 int currentRank = getRank(Integer.parseInt(year),name,gender);
			 counter++;
		         if(currentRank != -1){
		        	totalRank += currentRank; 
		         }
		     }
		if (totalRank != 0){
			averageRank=totalRank/counter;
			return averageRank;
		}else{ 
			return averageRank;
		}
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////	
	public int getTotalBirthsRankedHigher(int year, String name, String gender){
		int totalHigherBirths = 0;
		int currentRec = 0;
		FileResource fr = new FileResource("week4/data/yob" + year + ".csv");
		int rank = getRank(year, name, gender);
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equalsIgnoreCase(gender)){
				currentRec++;
				if(currentRec < rank && rec.get(1).equalsIgnoreCase(gender)){
					totalHigherBirths += Integer.parseInt(rec.get(2));
				}
			}
		}
		return totalHigherBirths;
	}
	
	//Utility functions to run preset tests for each method/////////////////////////////////////////////////
	//TODO: add assertions to complete test cases
		public void testSuite(){
			testTotalBirths();
			System.out.println("---------------------------------\n");
			testGetRank();
			System.out.println("---------------------------------\n");
			testGetName();
			System.out.println("---------------------------------\n");
			testWhatIsNameInYear();
			System.out.println("---------------------------------\n");
			testYearOfHighestRank();
			System.out.println("---------------------------------\n");
			testGetAverageRank();
			System.out.println("---------------------------------\n");
			testGetTotalBirthsRankedHigher();
		}
		
		public void testTotalBirths(){
			String fileString = "week4/data/yob1905.csv";
			
			FileResource fr = new FileResource(fileString);
			totalBirths(fr);
		}
		
		public void testGetRank(){
			int year = 2012;
			String name = "Mason";
			String gender = "m";
					
			int rankResult = getRank(year, name, gender);
			System.out.println(rankResult);
		}
		
		public void testGetName(){
			int year = 2012;
			int rank = 2;
			String gender = "m";
			
			String nameResult = getName(year, rank, gender);
			System.out.println(nameResult);
		}
		
		public void testWhatIsNameInYear(){
			String name = "Mason";
			int birthYear = 2014;
			int compareYear = 2012;
			String gender = "m";
			
			whatIsNameInYear(name, birthYear, compareYear, gender);
		}
		
		public void testYearOfHighestRank(){
			String name = "Ethan";
			String gender = "m";
			
			int highRankResult = yearOfHighestRank(name, gender);
			System.out.println(highRankResult);
		}
		
		public void testGetAverageRank(){
			String name = "Mason";
			String gender = "m";
			
			double avgResult = getAverageRank(name, gender);
			System.out.println(avgResult);
		}
		
		public void testGetTotalBirthsRankedHigher(){
			int year = 2012;
			String name = "William";
			String gender = "m";
			
			int higherBirthresult = getTotalBirthsRankedHigher(year, name, gender);
			System.out.println(higherBirthresult);
		}


}
