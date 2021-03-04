package version1;

public class Pediatrition extends Person {
	
	public enum Title {Prof_Dr, Dr_Med, Dr};
	private Title title;

	public Pediatrition(String afirstName, String alastName, Gender agender, Title title) {
		super(afirstName, alastName, agender);
		this.title = title;
		// TODO Auto-generated constructor stub
	}
	
	public Title getTitle() {
		return title;
	}

	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return "Pediatrition: " + this.title + " " + super.toString();
	}
}
