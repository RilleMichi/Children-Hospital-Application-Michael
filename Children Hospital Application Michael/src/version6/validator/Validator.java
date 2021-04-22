package version6.validator;

import version6.exception.InvalidInputException;

public interface Validator<V> {

	//liefert einen Rückgabe wert vom Typ T zurück
	public V validate(String input) throws InvalidInputException;
}
