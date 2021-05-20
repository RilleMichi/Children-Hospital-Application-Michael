package version8.validator;

import version8.exception.InvalidInputException;

public class BooleanValidator implements Validator<Boolean> {
	
	public BooleanValidator() {}
	
	private static String regexTrue = "^(true|t|ok|yes)$";
	private static String regexFalse = "^(false|f|nok|no)$";
	
	//statisch, dadurch muss man nicht immer ein Objekt erstellen
	public Boolean validate (String string) throws InvalidInputException {
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
