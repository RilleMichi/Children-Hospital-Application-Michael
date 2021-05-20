package version8.validator;

import version8.exception.*;

public class IntRangeValidator implements Validator<Integer> {

	private static String regex = "^[0-9]+$";

	// Bei Error (z.B. -1 ist die Körpergrösse unkorrekt)
	// Entweder kann man hier mit einem Error arbeiten
	// Um sauberer arbeiten zu können, sollte man mit Exeptions arbeiten wie bei
	// DoubleRangeValidator
	private int min, max;

	public IntRangeValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public Integer validate(String string) throws InvalidIntRangeException, RegexMismatchException {
		string = string.trim(); // removes leading/trailing spaces
		if (string.matches(regex)) {
			int x = Integer.parseInt(string);
			if (x >= this.min && x <= this.max) {
				return x;
			} else {
				throw new InvalidIntRangeException(x, this.min, this.max);
			}
		} else {
			throw new RegexMismatchException(string, regex);
			// throw new Exception("Invalid input String. Must in Range: " + this.min + "
			// and " + this.max);
		}
	}
}
