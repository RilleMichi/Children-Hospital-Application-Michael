package version2;

import java.util.ArrayList;
import java.util.List;

public class Parent extends Person {

	private String phoneNumber;
	private String address;
	private List<Patient> children;

	// firstName, lastName und Gender muss man mitgeben, da es von Person erbt
	// super gibt den Input an die Klasse Person
	public Parent(String firstName, String lastName, Gender gender, String phoneNumber, String address) {
		super(firstName, lastName, gender);
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.children = new ArrayList<Patient>();
	}

	//fügt ein Kind in die Patient ArrayList ein
	public void addChild(Patient child) {
		children.add(child);
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return "Parent: " + super.toString();
	}

}
