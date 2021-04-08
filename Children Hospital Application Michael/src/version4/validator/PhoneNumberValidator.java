package version4.validator;

import version4.exception.InvalidInputException;

public class PhoneNumberValidator implements Validator<String>{
	
	public PhoneNumberValidator () {
		
	}

	public String validate(String string) throws InvalidInputException {
		string = string.trim();
		//e.g. 0XX XXX XX XX or +41 XX XXX XXXX)
		if (string.toLowerCase()
				.matches("^((0[0-9]{2}\\s[0-9]{3}\\s[0-9]{2}\\s[0-9]{2})|(\\+41\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{4}))$")) {
			return string;
		}

		throw new InvalidInputException();
	}
}
