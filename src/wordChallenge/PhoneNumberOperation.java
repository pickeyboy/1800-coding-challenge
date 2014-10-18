package wordChallenge;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Set;

/* 
 * This class contains the method to find combination of numbers
 * which has relevant matching in the dictionary
 * The implementation assumes that "1" and "0" are valid words individually
 * 
 * This class also inherits abstract class PhoneNumber and must implement
 * the method to find all combinations present in Set member of class PhoneNumber
 */

public class PhoneNumberOperation extends PhoneNumber{

	private LinkedList<PhoneNumberParser> runningParser = new LinkedList<PhoneNumberParser>();
	private LinkedList<PhoneNumberParser> finalParsedList = new LinkedList<PhoneNumberParser>();

	public PhoneNumberOperation () throws IOException {
	}
	
	public int getResultCount() {
		return finalParsedList.size();
	}

	/*
	 * This method parse the running list of node phone parser and if completed 
	 * copies the result into our final master list or else call the function
	 * to find the match again
	 * Input: 
	 * 	Dictionary: The dictionary database
	 */

	public void parseRunningParserList(Dictionary dictionary) {

		ListIterator<PhoneNumberParser> runningParserIt = runningParser.listIterator();
		
		while(runningParserIt.hasNext())
		{
			PhoneNumberParser node = runningParserIt.next(); 
			
			if(node != null && node.toConvert.length() > 0)
			{
				runningParserIt.remove();
				findWordCombinationsIteratively(dictionary, node.toConvert, node);
			}
			else
			{
				if(node.toConvert.length() == 0) //already parsed the whole number
				{
					finalParsedList.add(node); //Add result in final list
					runningParserIt.remove(); //remove from parser, we dont want to parse it again
				}
			}
				
		}
		
	}
	/*
	 * This function finds a match sub number from dictionary and add it to parset linked list
	 * Input: 
	 * 	Dicationary: The dictionary database
	 *  Word: The word you are gonna match in the dictionary
	 *  node: The parent node from where this is coming from
	 */
	private void findWordCombinationsIteratively(Dictionary dictionary, String word, PhoneNumberParser node) {

		PhoneNumberParser nodePNParser;
		
		for (int i = 1; i < word.length() + 1; i++)
		{
			Set<String> tmpStrSet;

			tmpStrSet = dictionary.lookupWords(word.substring(0, i)) ;
			
			if(tmpStrSet != null){

				Iterator <String> setIterator = tmpStrSet.iterator();
				
				while(setIterator.hasNext())
				{
					String tmp = setIterator.next();
					nodePNParser = new PhoneNumberParser();
					if(node != null)
						nodePNParser.addString(node.convertedStr + tmp, word.substring(tmp.length()));//Making new node
					else
						nodePNParser.addString(tmp, word.substring(tmp.length()));//Making new node
					
					runningParser.add(nodePNParser); //adding node to the list
				}
			}
		}

		//handling single digit replacement option
		//Since 0,1 is not present in Keypad and there is no
		//possible replacement for it, we consider this as a
		//valid word and keep as is
		
		nodePNParser = new PhoneNumberParser();
				
		if(node == null) {
			nodePNParser.addString(word.substring(0, 1), word.substring(1));//Making new node
			runningParser.add(nodePNParser); //adding node to the list
		}
		//else if(node.convertedStr.matches("^[01A-Z\\-]+$") || word.substring(0, 1).equals("0") || word.substring(0, 1).equals("1") ){

		else if(/*node.convertedStr.matches("^[09A-Z\\-]+$") &&*/ 
				!(Character.isDigit(node.convertedStr.charAt(node.convertedStr.length()-2)))){
			nodePNParser.addString(node.convertedStr + word.substring(0, 1), word.substring(1));//Making new node
			runningParser.add(nodePNParser); //adding node to the list
		}

		if(!runningParser.isEmpty())
			parseRunningParserList(dictionary);
	}


/*
 * This method is external interface to be invoked. 
 * It internally uses the method findWordCombinationsIteratively()
 * to find all combination of possible numbers which can be mapped
 * to the dictionary
 */
	public void findWordsFromDict(Dictionary dictionary, String strNumber) {
		
		if (null == dictionary || null == strNumber)
			return;
		
		strNumber = strNumber.replaceAll("[^a-zA-Z0-9]", "");
		if(strNumber.isEmpty())
			return;
		
		//Adding the number we are searching in our phone database
		addNumber(strNumber);
		
		findWordCombinationsIteratively(dictionary, strNumber, null);
	}
	
	/*
	 * This  method will print the results from master linklist
	 * to stdout.
	 */
	public void printResults(String input)
	{
		if (null == input)
			return;
		
		ListIterator<PhoneNumberParser> finalIt = finalParsedList.listIterator();
		
		System.out.println("The combinations found for [" + input + "] are as follows:\n");
		
		if(!finalIt.hasNext())
			System.out.println("Sorry no match found!!!\n\n");
		else
			while(finalIt.hasNext())
				System.out.println(finalIt.next().getConvertedString());
	}
	
	/*
	 *This method clears the master result data. 
	 *Invoking this function before starting next operation is necessary.  
	 */
	public void clearResults()
	{
		//removing all results from master list
		finalParsedList.clear();
	}
	
	/*
	 * (non-Javadoc)
	 * @see wordChallenge.PhoneNumber#findMatchingCombinations()
	 */
	public void findMatchingCombinations(){
		
	//Gosh implement this function if reading the numbers is required from the file. 
	// currently leaving it blank as there is no such requirement
		return;
	}
}
