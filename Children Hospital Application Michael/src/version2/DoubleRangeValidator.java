package version2;

public class DoubleRangeValidator {
	
	  private int min, max;

	  public DoubleRangeValidator(int min, int max) {
	    this.min = min;
	    this.max = max;
	  }


	public double validate(String string, double min, double max) throws Exception {
		string = string.trim(); // entfernt im String vorne und hinten alle  Leerschläge
		/*
		 * - ^[0-9]+ --> Es muss mit 0-9 beginnen und es muss mind. 1x vorkommen (+)
		 * - ((.|,)?[0-9]+)?$ --> Bei einer Kommazahl muss es mind. eine Zahl nach dem Punkt/Komma kommen.
		 * --(.....)?$ --> Das ? bedeutet, dass es einmal vorkommen kann, muss es aber nicht. Das $ Bedeutet das Ende eines String
		 * --(.|,) Es muss ein Punkt oder Komma stehen
		 * --[0-9]+ Es muss mind eine Zahl vorkommen.
		*/
		if (string.matches("^[0-9]+((.|,)[0-9]+)?$")) { 
			double x = Double.parseDouble(string);
			if (x >= this.min && x <= this.max) { 
				return x;
			}
		}
		throw new Exception("Invalid input String. Must in Range: " + this.min + " and " + this.max);
	}
}
