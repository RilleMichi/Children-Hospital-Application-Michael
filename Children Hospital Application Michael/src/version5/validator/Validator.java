package version5.validator;

import version5.exception.InvalidInputException;

public interface Validator<V> {

	//liefert einen R�ckgabe wert vom Typ T zur�ck
	public V validate(String input) throws InvalidInputException;
}
