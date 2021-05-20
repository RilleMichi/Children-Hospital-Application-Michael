package version8.validator;

import version8.exception.InvalidInputException;

public interface Validator<V> {

	//liefert einen R�ckgabe wert vom Typ T zur�ck
	public V validate(String input) throws InvalidInputException;
}
