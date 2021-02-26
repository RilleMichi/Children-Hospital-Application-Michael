package Version1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Patient extends Person {

	private LocalDate birthDate;
	private Parent parent;
	private Insurance insurance;
	private ArrayList<Checkup> checkups;
	// List ist ein Interface
	private List<Vaccination> vaccinations;

	public Patient(String afirstName, String alastName, Gender agender, LocalDate abirthDate, Parent aparent,
			Insurance ainsurance) {
		super(afirstName, alastName, agender);
		this.birthDate = abirthDate;
		this.parent = aparent;
		this.insurance = ainsurance;
		/*
		 * Hier erstellt man eine Liste bzw. man initalisiert es. Das heisst diese
		 * werden nicht vom User �bergeben, sondern werden erstmals erstellt. Sie k�nnen
		 * es im Nachhinein noch mit Werten f�llen
		 */
		this.checkups = new ArrayList<Checkup>();
		this.vaccinations = new ArrayList<Vaccination>();
		/* Um Redunanzen zu vermeiden, f�gt man in der Liste Child von der Klasse Parent
		das Kind mit. */
		this.parent.addChild(this);
	}

	public void addCheckup(Checkup checkup) {
		checkups.add(checkup);
	}
	
	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return "Patient: " + super.toString() + " " + this.birthDate + "\n" + this.checkups;
	}
	
}
