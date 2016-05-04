package week4;

import org.apache.commons.csv.*;
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
	    int yearOfHighestRank=-1;
	    int temp=0;
	    String filename="";
	    DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            
            filename = f.getPath().substring(f.getPath().indexOf("yob")+3,f.getPath().indexOf("yob")+7);
            int currentRank = getRank(Integer.parseInt(filename),name,gender);
            if(currentRank != -1 && temp == 0){
                temp=currentRank;
                yearOfHighestRank=Integer.parseInt(filename);
                
            }
            if(currentRank<temp && currentRank != -1){
                
                temp = currentRank;
                yearOfHighestRank = Integer.parseInt(filename);
            }
        }
	    return yearOfHighestRank;
	}
	
	public static double getAverageRank(String name, String gender){         
		double averageRank = -1.0;
		int temp=0;
		int count = 1;
		String filename="";
		DirectoryResource dr = new DirectoryResource();
		 
		for (File f : dr.selectedFiles()) {
		    filename = f.getPath().substring(f.getPath().indexOf("yob")+3,f.getPath().indexOf("yob")+7);
			 int currentRank = getRank(Integer.parseInt(filename),name,gender);
		         if(currentRank != -1 ){
		            temp += currentRank; 
		            averageRank=(double)temp/count;
		            count++;
		         }
		     }
		     
		   return averageRank;
    
	}
	
	
	public static int getTotalBirthsRankedHigher(int year, String name, String gender){
		int totalHigherBirths = 0;
		String resultName = "";
		FileResource fr = new FileResource("week4/data/yob" + year + "short.csv");
		int rank = getRank(year, name, gender);
		for (int i = (rank-1); i > 0; i--){
			resultName = getName(year, i, gender);
			for (CSVRecord rec : fr.getCSVParser(false)) {
				if(rec.get(0).equals(resultName)){
					totalHigherBirths += Integer.parseInt(rec.get(2));
				}
			}
		}
		return totalHigherBirths;
	}
	
	public static void main (String [] args) {
		//FileResource fr = new FileResource();
		//FileResource fr = new FileResource("week4/data/yob1905.csv");
		//totalBirths(fr);
		//int result = getRank(1971, "Frank", "m");
		//String result = getName(1982, 450, "m");
		//System.out.println(result);
		//whatIsNameInYear("Owen", 1974, 2014, "m");
		//int result = yearOfHighestRank("Mich", "m");
		//System.out.println(result);
		//double result = getAverageRank("Robert", "m");
		//System.out.println(result);
		int result = getTotalBirthsRankedHigher(2014, "Ava", "f");
		System.out.println(result);
	}
	
}
