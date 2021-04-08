package version4.validator;

import version4.exception.InvalidInputException;

public interface Validator<V> {

	//liefert einen Rückgabe wert vom Typ T zurück
	public V validate(String input) throws InvalidInputException;
}
