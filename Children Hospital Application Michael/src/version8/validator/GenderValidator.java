package version8.validator;

import version8.exception.InvalidInputException;
import version8.models.Person.Gender;

public class GenderValidator implements Validator<Gender> {
	
	  public GenderValidator() {
		// TODO Auto-generated constructor stub
	}

	  public Gender validate(String string) throws InvalidInputException {
	    string = string.trim().toUpperCase(); // removes leading/trailing spaces
	    for (Gender vaccine : Gender.values()) {
	      if (vaccine.name().startsWith(string)) {
	        return vaccine;
	      }
	    }
	    throw new InvalidInputException();
	  }
	
//	public static Person.Gender validate (String string) throws Exception {
//		string = string.trim();
//		if(string.toLowerCase().matches("(m|men")) {
//			return Person.Gender.MALE;
//		}
//		else if (string.toLowerCase().matches("f|female")) {
//			return Person.Gender.FEMALE;
//		}
//		else {
//			throw new Exception("Invalid input String. Format: men or female");
//		}
//	}
}
