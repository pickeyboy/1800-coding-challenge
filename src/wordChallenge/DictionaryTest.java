/*
 * This is TestNG unit testcases to test methods written
 * 
 */

package wordChallenge;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;


public class DictionaryTest{

	@Test
	public void testAdd2Dictionary() throws IOException{
			KeypadDictionary defaultDictionary = new KeypadDictionary();
			defaultDictionary.add2Dictionary("Hello");
			defaultDictionary.add2Dictionary("Hello");
			defaultDictionary.add2Dictionary("Hello");
			defaultDictionary.add2Dictionary("World");
			defaultDictionary.add2Dictionary("World");
			Assert.assertEquals(2, defaultDictionary.getTotalNumberOfWords());
		}
	
	//makeDictionaryFromFile(String filename)
	@Test
	public void TestLoadingDictionaryFromFileLoadingTimeCheck() throws IOException{
			KeypadDictionary defaultDictionary = new KeypadDictionary();
			defaultDictionary.makeDictionaryFromFile("./data/Dictionary.txt");
			Assert.assertTrue(defaultDictionary.getTotalNumberOfWords() > 0);
			//Improvement required on loading time
		}
	
	@Test
	public void TestLookupWordsforInvalidnumbers() throws IOException{
			KeypadDictionary defaultDictionary = new KeypadDictionary();
			defaultDictionary.add2Dictionary("CALL");
			defaultDictionary.add2Dictionary("ME");
			defaultDictionary.add2Dictionary("MF");
			Assert.assertEquals(3, defaultDictionary.getTotalNumberOfWords());
			
			Assert.assertEquals(null, defaultDictionary.lookupWords("hello"));
			
			Assert.assertEquals(1, defaultDictionary.lookupWords("2255").size());
			Assert.assertEquals(2, defaultDictionary.lookupWords("63").size());
		}

	@Test
	public void TestLookupWordsifMatchesCorrectly() throws IOException{
			KeypadDictionary defaultDictionary = new KeypadDictionary();
			defaultDictionary.add2Dictionary("A");
			defaultDictionary.add2Dictionary("B");
			defaultDictionary.add2Dictionary("C");
			Assert.assertEquals(3, defaultDictionary.getTotalNumberOfWords());
			Assert.assertEquals(null, defaultDictionary.lookupWords("63"));
			Assert.assertEquals(null, defaultDictionary.lookupWords("hello"));
			Assert.assertEquals(3, defaultDictionary.lookupWords("2").size());

			Assert.assertEquals(true, defaultDictionary.lookupWords("2").contains("A"));
			Assert.assertEquals(true, defaultDictionary.lookupWords("2").contains("B"));
			Assert.assertEquals(true, defaultDictionary.lookupWords("2").contains("C"));

		}
	@Test
	public void TestLookupWordsifhandlesverylonginput() throws IOException{
		KeypadDictionary defaultDictionary = new KeypadDictionary();
		defaultDictionary.add2Dictionary("A");
		defaultDictionary.add2Dictionary("B");
		defaultDictionary.add2Dictionary("C");
		Assert.assertNull(defaultDictionary.lookupWords("1234567890123456792"));
	}
	
	@Test
	public void Testconver2KayPadNumbersFunctionalities() throws IOException{
		KeypadDictionary defaultDictionary = new KeypadDictionary();
		Assert.assertEquals(0, defaultDictionary.convert2KeypadNumber("012345").length());
		Assert.assertEquals(0, defaultDictionary.convert2KeypadNumber("12345").length());
		Assert.assertEquals("2222222", defaultDictionary.convert2KeypadNumber("AAAAAAA"));
		Assert.assertEquals("222222222222222222222222", 
				defaultDictionary.convert2KeypadNumber("ABABABABABABABABABABABAB"));
	}
	
	@Test
	public void TestfindWordCombinationsIterativelyfortiming() throws IOException{
		KeypadDictionary defaultDictionary = new KeypadDictionary();
		defaultDictionary.add2Dictionary("CALL");
		defaultDictionary.add2Dictionary("ALLME");
		defaultDictionary.add2Dictionary("ME");
		
		PhoneNumberOperation testPNO = new PhoneNumberOperation();
		testPNO.findWordsFromDict(defaultDictionary, "225563");
		Assert.assertEquals(2, testPNO.getResultCount());
	}
	@Test
	public void TestfindWordForcornerCasesof10() throws IOException{
		KeypadDictionary defaultDictionary = new KeypadDictionary();
		defaultDictionary.add2Dictionary("CALL");
		defaultDictionary.add2Dictionary("ALLME");
		defaultDictionary.add2Dictionary("ME");
		
		PhoneNumberOperation testPNO = new PhoneNumberOperation();
		testPNO.findWordsFromDict(defaultDictionary, "10");
		Assert.assertEquals(1, testPNO.getResultCount());
		testPNO.clearResults();
		
		testPNO.findWordsFromDict(defaultDictionary, "100");
		Assert.assertEquals(1, testPNO.getResultCount());
		testPNO.clearResults();

		testPNO.findWordsFromDict(defaultDictionary, "100");
		Assert.assertEquals(1, testPNO.getResultCount());
		testPNO.clearResults();
	}
	@Test
	public void TestPhonParserAddStringNullCheck() {
		PhoneNumberParser pp = new PhoneNumberParser();
		pp.addString(null, "test");
		Assert.assertEquals(0, pp.convertedStr.length());

		pp.addString(null, "test");
		Assert.assertEquals(0, pp.toConvert.length());
		
		pp.addString("Hello", "test");
		Assert.assertEquals(6, pp.convertedStr.length());
		Assert.assertEquals(4, pp.toConvert.length());

	}
	//findWordsFromDict(Dictionary
	@Test
	public void TestNullCheckFindWordsfromDict() throws IOException{
		PhoneNumberOperation defaultNumbers = new PhoneNumberOperation(); 

		defaultNumbers.findWordsFromDict(null, null);
		Assert.assertEquals(0, defaultNumbers.getResultCount());
		
	}

}



