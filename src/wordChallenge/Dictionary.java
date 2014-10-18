package wordChallenge;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * This class is dictionary data base
 * It stores the words in Key value pair has where 
 * key is the respect keymap number and value is set of words 
 * matching with the number
 */

public abstract class Dictionary {

	private Map<String, Set<String>> wordsStorage = new HashMap<>();

	//This is added for Unit test
	private int totalWordCount = 0;
	
	
	public Dictionary() throws IOException {
	}
	
	
	public int getTotalNumberOfWords(){
		return totalWordCount;
	}

	abstract public String convert2KeypadNumber(String word);
	
	/*
	 * add2Dictionary will add with following information
	 *  - Remove all punctuation and non alphabetic chars
	 *  - Adds words only in caps
	 *  - Generate equivalent number from defined keypad
	 */
	public void add2Dictionary(String word){
		
		//Removing all non alpha numeric characters
		word = word.replaceAll("[^a-zA-Z0-9]", "");

		//converting to upper case
		String upperCaseWord = word.toUpperCase();
	
		//converting word to keypad number
		String equivNumber = convert2KeypadNumber(upperCaseWord);
		
		if (equivNumber.length() == 0)
			return;
		
		Set<String> strList = wordsStorage.get(equivNumber);
		
		if (strList == null){
			strList = new HashSet<>();	
			wordsStorage.put(equivNumber, strList);
		}
		if(strList.add(upperCaseWord))
			totalWordCount++;
	}
	
	public Set<String> lookupWords(String strNumber) {
		return wordsStorage.get(strNumber);
	}
	
	/*
	 * This method validate a word and returns true
	 * if is a valid word otherwiser false.
	 */
	
	private static boolean validateWord(String word) {
		
		//If dictionary word has special character return flase.
		boolean hasSpecialChar= word.matches("^.*[^a-zA-Z0-9 ].*$");
				
		return !hasSpecialChar;
	}

	/*
	 * This method reads from a file and prepares
	 * Dictionary database
	 */
	
	public int makeDictionaryFromFile(String filename) throws IOException{
	
		//File input stream
		InputStream  dictFile = new FileInputStream(filename);

		//creating Buffer Reader
		BufferedReader lineBuff = new BufferedReader(new InputStreamReader(dictFile));
		
		String line = null;
		
		//Reading line by line and creating dictionary in memory in Hashmap format
		while ((line = lineBuff.readLine()) != null) {
			
			//Removing all non alpha numeric characters
			line = line.replaceAll("[^a-zA-Z0-9]", "");

			//converting to upper case
			String upperCaseWord = line.toUpperCase();

			//Adding word to the data structure
			add2Dictionary(upperCaseWord);
		}
		
		//Closing buffer reader
		lineBuff.close();
		return 1;
		
	}
}
