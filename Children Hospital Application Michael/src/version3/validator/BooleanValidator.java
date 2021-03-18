package version3.validator;

import version3.exception.InvalidInputException;

public class BooleanValidator {
	
	private static String regexTrue = "^(true|t|ok|yes)$";
	private static String regexFalse = "^(false|f|nok|no)$";
	
	//statisch, dadurch muss man nicht immer ein Objekt erstellen
	public static Boolean validate (String string) throws Exception {
		string = string.trim();
		if(string.toLowerCase().matches(regexTrue)) {
			return true;
		}
		else if (string.toLowerCase().matches(regexFalse)) {
			return false;
		}
		else {
			throw new InvalidInputException();
		}
	}

}
