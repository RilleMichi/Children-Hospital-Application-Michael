package version7.validator;

import java.time.LocalDate;

import version7.exception.InvalidInputException;

public class DateValidator implements Validator<LocalDate>{
	
	public DateValidator() {
		
	}

	public LocalDate validate(String string) throws InvalidInputException {
		string = string.trim();
		if (checkSyntax(string)) {
			LocalDate date = map(string);
			if (checkSemantics(date)) {
				return date;
			}
		}
		throw new InvalidInputException();
	}

	private static boolean checkSyntax(String string) {
		/*
		 * ^(3[01]|[1-2][0-9]|0[1-9]) - Zahl mit 30 oder 31 - oder Zahl mit 10-29 - oder
		 * zahl mit 01-09
		 * 
		 * - \\. --> "."
		 * 
		 * (1[012]|0?[1-9]) - Zahl mit 10-12 - oder zahl mit 01-09
		 * 
		 * - \\. --> "."
		 * 
		 * ((19|20)[0-9]{2})$ - Zahl zu Beginn mit 19 oder 20 - [0-9]{2} --> muss 2
		 * Zahlen von 00-99 enthalten
		 */
		return string.matches("^(3[0-1]|[1-2][0-9]|0[1-9])\\:(1[0-2]|0[1-9])\\:((19|20)[0-9]{2})$");
	}

	private static LocalDate map(String string) {
		// Aufsplitten und zusammen für Datum
		String[] strings = string.split(":");
		int day = Integer.parseInt(strings[0]);
		int month = Integer.parseInt(strings[1]);
		int year = Integer.parseInt(strings[2]);
		return LocalDate.of(year, month, day);
	}

	private static boolean checkSemantics(LocalDate date) {
		return true;
	}
}
