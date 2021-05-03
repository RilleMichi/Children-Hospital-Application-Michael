package version7.exception;

//Warnung ausschalten
@SuppressWarnings("serial")
public class RegexMismatchException extends InvalidInputException {

	private String string, regex;

	public RegexMismatchException(String string, String regex) {
		this.string = string;
		this.regex = regex;
	}
	
	public String getString() {
		return string;
	}
	
	public String getRegex() {
		return regex;
	}


	@Override
	public String toString() {
		return this.string + " does not match " + this.regex;
	}
}
