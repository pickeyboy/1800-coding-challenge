package wordChallenge;

import java.io.IOException;

public class KeypadDictionary extends Dictionary {
	
	public KeypadDictionary() throws IOException {
	}

	
	private static final char[] NUMBER_MAP_TABLE = {
		'2', '2', '2', '3', '3', '3', '4', '4', '4', 
		'5', '5', '5', '6', '6', '6','7', '7', '7', 
		'7', '8', '8', '8', '9', '9', '9',	'9'
	};

	public String convert2KeypadNumber(String word) {
	
	int count = 0;
	StringBuffer strEquivNum = new StringBuffer(word);
	
	if(!word.matches("^[A-Z]+$"))
		return "";
	
	//Replacing all chars to equivalent number
	for (count = 0; count < word.length() ; count++)
	{
		strEquivNum.setCharAt(count, NUMBER_MAP_TABLE[word.charAt(count) - 'A']);
	}
	
	//Converting to string for storage
	return strEquivNum.toString();
	}

}
