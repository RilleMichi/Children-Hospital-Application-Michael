package version4;

import java.util.Scanner;
import version4.exception.InvalidInputException;
import version4.validator.Validator;

public class ConsoleInput<V> {
	
	private Scanner scanner;
	private Validator<V> validator;
	private String userPrompt;
	
	//Konstruktor, welche die werte übergibt
	public ConsoleInput(Scanner scanner, Validator<V> validator, String userPrompt) {
	this.scanner = scanner;
	this.validator = validator;
	this.userPrompt = userPrompt;
	}
	
	//Die enterValue
	public V enterValue() {
		boolean validInput;
		V value = null;
		
		do {
			System.out.println(userPrompt);
			String input = scanner.next();
			try {				
				value = validator.validate(input);
				validInput = true;
			} catch (InvalidInputException e) {
				System.err.println("Invalid Input: " + e);
				validInput = false;
			}
		} while (!validInput);
		return value;
	}

}
