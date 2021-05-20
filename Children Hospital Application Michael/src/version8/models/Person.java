package version8.models;

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

	public Person(String firstName, String lastName, Gender gender) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public String getFullName() {
		return this.firstName + " " + this.lastName;
	}

	// toString überschreiben, damit man eine klaren Output hat
	@Override
	public String toString() {
		return this.firstName + " " + this.lastName + " " + this.gender;
	}
}
