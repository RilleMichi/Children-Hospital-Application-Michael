package version2.exception;

@SuppressWarnings("serial")
public class InvalidIntRangeException extends InvalidInputException {

	private int value, min, max;
	
	public InvalidIntRangeException (int value, int min, int max) {
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
