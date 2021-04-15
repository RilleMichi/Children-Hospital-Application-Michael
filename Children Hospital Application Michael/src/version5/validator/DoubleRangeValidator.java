package version5.validator;

import version5.exception.*;


public class DoubleRangeValidator implements Validator<Double> {

	/*
	 * - ^[0-9]+ --> Es muss mit 0-9 beginnen und es muss mind. 1x vorkommen (+) -
	 * ((\\.|,)?[0-9]+)?$ --> Bei einer Kommazahl muss es mind. eine Zahl nach dem
	 * Punkt/Komma kommen. --(.....)?$ --> Das ? bedeutet, dass es einmal vorkommen
	 * kann, muss es aber nicht. Das $ Bedeutet das Ende eines String --(.|,) Es
	 * muss ein Punkt oder Komma stehen --[0-9]+ Es muss mind eine Zahl vorkommen.
	 */
	private static String regex = "^[+|-]?[0-9]+((\\.|,)[0-9]+)?$";

	private double min, max;

	public DoubleRangeValidator(int min, int max) {
		this.min = min;
		this.max = max;
	}

	//throws, da sonst die Exception nicht behadnelt werden
	//Entweder man gibt hier drinnen ein Try and Catch
	//Oder man gibt hier die Info welche Exceptions es existieren, wenn man diese Methode aufruft, dass man DORT ein try catch ausübt.
	@Override
	public Double validate(String string) throws RegexMismatchException, InvalidDoubleRangeException{
		string = string.trim(); // entfernt im String vorne und hinten alle Leerschläge

		if (string.matches(regex)) {
			double x = Double.parseDouble(string);
			if (x >= this.min && x <= this.max) {
				return x;
			} else {
				//Falls es über den min oder max ist.
				throw new InvalidDoubleRangeException(x, this.min, this.max);
			}
		} else {
			//Z.B. wenn man hier einen String eingibt anstatt ein double
			throw new RegexMismatchException(string, regex);
			// throw new Exception("Invalid input String. Must in Range: " + this.min + "
			// and " + this.max);
		}
		}
		
}
