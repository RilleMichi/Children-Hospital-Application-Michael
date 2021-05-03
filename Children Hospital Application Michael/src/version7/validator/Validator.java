package version7.validator;

import version7.exception.InvalidInputException;

public interface Validator<V> {

	//liefert einen R�ckgabe wert vom Typ T zur�ck
	public V validate(String input) throws InvalidInputException;
}
