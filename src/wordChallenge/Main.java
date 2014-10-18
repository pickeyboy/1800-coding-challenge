package wordChallenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	


	public static void main(String[] arg) throws IOException {

		String userDictionaryFileName = "";

		//Write code to handle user input dictionary
		if (arg.length < 2)
		{
			System.out.println("Incorrect Argument.. Please use below arguments:");
			System.out.println("    -d <dictionary file>\n\n");
		}
		
		for (int i = 0 ; i < arg.length; i++)
		{
			if(arg[i].equals("-d") && i+1 <= arg.length)
			{
				userDictionaryFileName = arg[i+1];
				break;
			}
		}
		if(userDictionaryFileName.isEmpty())
		{
			System.out.println("Incorrect Argument.. Please use below arguments:");
			System.out.println("    -d <dictionary file>\n\n");
			System.exit(1);
		}
			
		
		KeypadDictionary defaultDictionary = new KeypadDictionary();
		try {
			defaultDictionary.makeDictionaryFromFile(userDictionaryFileName);
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Dictionary file: [" + userDictionaryFileName + "] not found.. Please check path\n");
//			e.printStackTrace();
			System.exit(1);
		}
		
		PhoneNumberOperation defaultNumbers = new PhoneNumberOperation(); 

		try{
			System.out.println("Please input phone number to map:\n");

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
			String input;
	 
			while((input=br.readLine())!=null){
				defaultNumbers.clearResults();
				defaultNumbers.findWordsFromDict(defaultDictionary, input);
				defaultNumbers.printResults(input);
				System.out.println("\npress CTRL-C to exit or input another number to map:\n");
				
			}
	 
		}catch(IOException io){
			io.printStackTrace();
			System.exit(1);
		}	
	}
	
}
