package com.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;

import org.apache.commons.lang3.RandomStringUtils;

public class TextFileManipulator {
	
	public String defaultFinanceTestDataFilePath = Paths.get(System.getProperty("user.dir"), "src", "Test File Finance.txt").toString();
	
	static int noOfLines = 0;
	
	public String writeFinanceDataOntoTextFile(int noOfLinesRequiredInTxtFile){
		String fileGenerationStatusString = "";
		noOfLines = noOfLinesRequiredInTxtFile;
		//Code for writing the String Array onto file
		try {
            FileOutputStream outputStream = new FileOutputStream(defaultFinanceTestDataFilePath);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            
            for(int i = 0; i < noOfLinesRequiredInTxtFile; i++) {
    			int randomizer = generateRandomNumber(60, 105);
    			String randomStringGenerated = "";
    			
    			//Code to Write the Random String Generated to text file
    			while(randomStringGenerated.length() <= randomizer) {
    				int randomNumber = generateRandomNumber(1, 100);
    				if((randomNumber % 4) == 1)
            			randomStringGenerated = randomStringGenerated + "finance" + generateRandomString(generateRandomNumber(1, 8));
            		else if((randomNumber % 4) == 2)
            			randomStringGenerated = randomStringGenerated + "finance" + generateRandomString(1) + "finance";
            		else
            			randomStringGenerated = randomStringGenerated + generateRandomString(-1);
    				
    				if((generateRandomNumber(1, 100) % 6) == 0)
    					randomStringGenerated = randomStringGenerated + "\t";
    				else
    					randomStringGenerated = randomStringGenerated + " ";
    			}
    			if(i == noOfLinesRequiredInTxtFile - 2)
    				bufferedWriter.write("Footer Text Sample Company" + "\r");
    			else if(i == noOfLinesRequiredInTxtFile - 1)
    				bufferedWriter.write("<EOF>");
    			else
    				bufferedWriter.write(randomStringGenerated + "\r");
    		}             
            bufferedWriter.close();
            fileGenerationStatusString = "File Successfully Generated";
        } catch (IOException e) {
            e.printStackTrace();
        }
		return fileGenerationStatusString;
	}
	
	public String readFinanceDataFromTextFile(int lineIndex) {
		//Code for reading String from file based on lineIndex
		try {
            FileInputStream inputStream = new FileInputStream(defaultFinanceTestDataFilePath);
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-16");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String character;
            int initialIndex = 0;
            
            if(lineIndex == -1)
            	lineIndex = noOfLines - 1;
 
            while ((character = bufferedReader.readLine()) != null) {
                System.out.print(character);
                initialIndex++;
                if(initialIndex == lineIndex) {
                	break;
                }
            }
            reader.close();
            return character;
 
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public String generateRandomString(int size) {
		//Code for Generating the Random String
		String randomString = "";
		int randomNumber = ((size == -1) ? generateRandomNumber(1, 15) : size);
		randomString = RandomStringUtils.random(randomNumber, 0, 20, true, true,
                "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
        System.out.println("random = " + randomString);
		
		return randomString;
	}
	
	public int generateRandomNumber(int min, int max) {
		return (int) (Math.random()*(max-min+1)+min);
	}

}
