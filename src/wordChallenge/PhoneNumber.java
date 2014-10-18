package wordChallenge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

abstract public class PhoneNumber {

	//Set is used so that duplicate numbers can be avoided
	private Set<String> phoneNumbers;

	public PhoneNumber () throws IOException {
		phoneNumbers = new HashSet<String>();
	}

	public PhoneNumber (String strNumber) throws IOException {
		phoneNumbers.add(trimNumber(strNumber)); 
	}

	public String trimNumber(String strNumber) {
		return (strNumber.replaceAll("[^0-9]", ""));
	}
	
	public void addNumber(String strNumber) {
		phoneNumbers.add(trimNumber(strNumber));
	}
	
	public int getTotalNumbers() {
		return phoneNumbers.size();
	}
	
	public String getNumbersByIndex(int index) {
		return (phoneNumbers.toArray())[index].toString();
	}

	public void loadNumbersFromFile(String fileName) throws IOException
	{
		//File input stream
		InputStream  numberFile = new FileInputStream(fileName);

		//creating Buffer Reader
		BufferedReader lineBuff = new BufferedReader(new InputStreamReader(numberFile));
		
		String line = null;
		
		//Reading line by line and creating number database in the memory 
		while ((line = lineBuff.readLine()) != null) {

			//Removing all white spaces and punctuation
			addNumber(line);
		}

		//Closing BufferReader
		lineBuff.close();
		return;
	}
	abstract public void findMatchingCombinations();
	
}
