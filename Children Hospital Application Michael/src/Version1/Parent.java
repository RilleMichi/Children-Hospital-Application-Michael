package Version1;

import java.util.ArrayList;
import java.util.List;

public class Parent extends Person {

	private String phoneNumber;
	private String address;
	private List<Patient> children;

	// firstName, lastName und Gender muss man mitgeben, da es von Person erbt
	// super gibt den Input an die Klasse Person
	public Parent(String afirstName, String alastName, Gender agender, String phoneNumber, String address) {
		super(afirstName, alastName, agender);
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.children = new ArrayList<Patient>();
	}

	//f�gt ein Kind in die Patient ArrayList ein
	public void addChild(Patient child) {
		children.add(child);
	}
	
	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return "Parent: " + super.toString();
	}

}
