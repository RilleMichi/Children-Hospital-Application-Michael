package version2;

public class BooleanValidator {
	
	public static Boolean validate (String string) throws Exception {
		string = string.trim();
		if(string.toLowerCase().matches("(true|t|ok|yes)")) {
			return true;
		}
		else if (string.toLowerCase().matches("false|f|nok|no")) {
			return false;
		}
		else {
			throw new Exception("Invalid input String. Format: true or false");
		}
	}

}
