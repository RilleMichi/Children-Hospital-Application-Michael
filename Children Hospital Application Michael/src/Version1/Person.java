package Version1;

public class Person {

	/*
	 * Es ist eine Klasse Gender mit 2 Objekten Male und Female. (Man kann auch eine
	 * eigene Klasse erstellen). Man kann keine Zahlen darin abspeichern. Zuweisung
	 * im Main bseispielsweise mit "Gender gender = Gender.MALE;" Hier macht es kein
	 * Sinn die Klasse als private zu deklarieren, sonst können die anderen von
	 * aussen nicht mehr darauf zugreifen
	 */
	public enum Gender {
		MALE, FEMALE, OTHER
	};

	// Die instanzvariablen sollen "immer" als private deklarieren,
	// sonst kann man von aussen darauf zugreifen.
	private String firstName;
	private String lastName;
	private Gender gender;

	public Person(String afirstName, String alastName, Gender agender) {
		this.firstName = afirstName;
		this.lastName = alastName;
		this.gender = agender;
	}

	// toString überschreiben, damit man eine klaren Output hat
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + " " + this.gender;
	}
}
