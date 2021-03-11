package version2.validator;

public class PhoneNumberValidator {

	public static String validate(String string) throws Exception {
		string = string.trim();
		//e.g. 0XX XXX XX XX or +41 XX XXX XXXX)
		if (string.toLowerCase()
				.matches("^((0[0-9]{2}\\s[0-9]{3}\\s[0-9]{2}\\s[0-9]{2})|(\\+41\\s[0-9]{2}\\s[0-9]{2}\\s[0-9]{4}))$")) {
			return string;
		}

		throw new Exception("Invalid input String. Format: 0XX XXX XX XX or +41 XX XXX XXXX");
	}
}
