package version2.models;

public class Pediatrician extends Person {
	
	public enum Title {Prof_Dr, Dr_Med, Dr};
	private Title title;

	public Pediatrician(String firstName, String lastName, Gender gender, Title title) {
		super(firstName, lastName, gender);
		this.title = title;
		// TODO Auto-generated constructor stub
	}
	
	public Title getTitle() {
		return title;
	}

	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return "Pediatrician: " + this.title + " " + super.toString();
	}
}
