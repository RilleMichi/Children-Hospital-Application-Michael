package version1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Add;


public class Patient extends Person {

	private LocalDate birthDate;
	private Parent parent;
	private Insurance insurance;
	private ArrayList<Checkup> checkups;
	// List ist ein Interface
	private List<Vaccination> vaccinations;

	public Patient(String firstName, String lastName, Gender gender, LocalDate birthDate, Parent parent,
			Insurance insurance) {
		super(firstName, lastName, gender);
		this.birthDate = birthDate;
		this.parent = parent;
		this.insurance = insurance;
		/*
		 * Hier erstellt man eine Liste bzw. man initalisiert es. Das heisst diese
		 * werden nicht vom User übergeben, sondern werden erstmals erstellt. Sie können
		 * es im Nachhinein noch mit Werten füllen
		 */
		this.checkups = new ArrayList<Checkup>();
		this.vaccinations = new ArrayList<Vaccination>();
		/* Um Redunanzen zu vermeiden, fügt man in der Liste Child von der Klasse Parent
		das Kind mit. */
		this.parent.addChild(this);
	}

	public void addCheckup(Checkup checkup) {
		checkups.add(checkup);
	}
	
	public void addVaccination(Vaccination vaccination) {
		vaccinations.add(vaccination);
	}
	
	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return "Patient: " + super.toString() + " " + this.birthDate + "\n" + this.checkups + "\n" + this.vaccinations;
	}
	
}
