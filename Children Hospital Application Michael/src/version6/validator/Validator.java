package version6.validator;

import version6.exception.InvalidInputException;

public interface Validator<V> {

	//liefert einen R�ckgabe wert vom Typ T zur�ck
	public V validate(String input) throws InvalidInputException;
}
