package version2.validator;

import version2.models.Person;
import version2.models.Person.Gender;

public class GenderValidator {
	
	public static Person.Gender validate (String string) throws Exception {
		string = string.trim();
		if(string.toLowerCase().matches("(m|men")) {
			return Person.Gender.MALE;
		}
		else if (string.toLowerCase().matches("f|female")) {
			return Person.Gender.FEMALE;
		}
		else {
			throw new Exception("Invalid input String. Format: men or female");
		}
	}
}
