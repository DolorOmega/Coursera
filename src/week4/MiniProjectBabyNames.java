package week4;

import org.apache.commons.csv.CSVRecord;
import edu.duke.*;

import java.io.File;
import java.util.*;


public class MiniProjectBabyNames {

	public static void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalNames = 0;
		HashSet uniqueBoyNames = new HashSet();
		HashSet uniqueGirlNames = new HashSet();
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
	
	
	public static int getRank(int year, String name, String gender) {
		int maleRank = 0;
        int femaleRank = 0;
        Boolean foundName = false;
        
        FileResource fr = new FileResource("week4/data/yob" + year + "short.csv");
        
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
        
        if(gender.equalsIgnoreCase("M") && foundName == true){
            return maleRank;
        }else if(gender.equalsIgnoreCase("F") && foundName == true){
            return femaleRank;
        }else{
            return -1;
        }
	}
	
	
	public static String getName (int year, int rank, String gender){
		int maleRank = 0;
        int femaleRank = 0;
        String resultName = "";
        Boolean foundName = false;
        
        FileResource fr = new FileResource("week4/data/yob" + year + "short.csv");
        
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
        
        if(gender.equalsIgnoreCase("M") && foundName == true){
            return resultName;
        }else if(gender.equalsIgnoreCase("F") && foundName == true){
            return resultName;
        }else{
            return "NO NAME";
        }
	}
	
	public static void whatIsNameInYear (String name, int year, int newYear, String gender){
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
	
	public static int yearOfHighestRank (String name, String gender){
		DirectoryResource dr = new DirectoryResource();
		String fileName = "";
		String yearToInt = "";
		int year = 0;
		int topRankYear = 0;
		int topRank = 0;
		int currentRank = 0;
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			fileName = f.getName();
			yearToInt = fileName.substring(3, 7);
			year = Integer.parseInt(yearToInt);
			//If topRankYear is nothing
			if (topRankYear == 0) {
				topRankYear = year;
			}
			//Otherwise
			else {
				currentRank = getRank(year, name, gender);
				topRank = getRank(topRankYear, name, gender);
				//Check if currentRow's temperature > largestSoFar's
				if (currentRank < topRank) {
					//If so update largestSoFar to currentRow
					topRankYear = year;
				}
			}
		}
		
		if (topRankYear != -1){
			return topRankYear;
		}
		else{
			return -1;
		}
	
	}
	
	public static void main (String [] args) {
		//FileResource fr = new FileResource();
		//FileResource fr = new FileResource("/C:/Users/Jeremy Foster/Eclipse/workspace/Coursera/src/week4/data/yob2014short.csv");
		//totalBirths(fr);
		//int result = getRank(2014, "Mason", "m");
		//String result = getName(2014, 3, "m");
		//System.out.println(result);
		//whatIsNameInYear("Mason", 2014, 2012, "m");
		int result = yearOfHighestRank("Mason", "m");
		System.out.println(result);
	}
	
}
