package version2.exception;

@SuppressWarnings("serial")
public class InvalidDoubleRangeException extends InvalidInputException {

	private double value, min, max;
	
	public InvalidDoubleRangeException (double value, double min, double max) {
		this.max = max;
		this.min = min;
		this.value = value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Invalid input " + this.value + ". Must in Range: " + this.min + " and " + this.max;
	}
}
