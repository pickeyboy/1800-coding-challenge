package wordChallenge;


public class PhoneNumberParser {
	
	public String convertedStr;
	public String toConvert;
	
	public PhoneNumberParser (){
		convertedStr="";
		toConvert="";
	}
	public String getConvertedString()
	{
		return convertedStr;
	}
	public void addString (String str1, String str2) {
		
		if(null == str1 || null == str2)
			return; 
		
		if(str2.isEmpty())
			convertedStr = convertedStr + str1;
		else
			convertedStr = convertedStr + str1 + "-";
		
		toConvert= str2;
	}

}
