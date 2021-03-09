package version2;

public class RegexMismatchException extends Exception {

	private String string, regex;

	public RegexMismatchException(String string, String regex) {
		this.string = string;
		this.regex = regex;
	}

	@Override
	public String toString() {
		return this.string + " does not match " + this.regex;
	}
}
