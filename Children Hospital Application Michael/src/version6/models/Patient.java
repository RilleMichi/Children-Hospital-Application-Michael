package version6.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import version6.models.Vaccination.Vaccine;



public class Patient extends Person {

	//Variable in der KLasse gebunden und existiert daher nur einmal. Diese Variable gilt für alle Instanzen. Man beginnt hier bei 1
	private static int nextNumber = 1; 
	
	private int number;
	private LocalDate birthDate;
	private Parent parent;
	private Insurance insurance;
	private ArrayList<Checkup> checkups;
	// List ist ein Interface
	private List<Vaccination> vaccinations;

	public Patient(String firstName, String lastName, Gender gender, LocalDate birthDate, Parent parent,
			Insurance insurance) {
		super(firstName, lastName, gender);
		this.number = nextNumber;
		//Damit die ID jedes mal um 1 erhöht
		nextNumber = nextNumber + 1;
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

	

	public int getNumber() {
		return number;
	}

	public void addCheckup(Checkup checkup) {
		checkups.add(checkup);
	}
	
	public List<Checkup> getCheckups() {
		return Collections.unmodifiableList(checkups);
	}

	public void addVaccination(Vaccination vaccination) {
		vaccinations.add(vaccination);
	}
	
	public List<Vaccination> getVaccinations() {
		//Man führt einen Schutz ein, damit er die Liste nicht verändern kann
		return Collections.unmodifiableList(vaccinations);
	}
	
	public boolean isVaccinated(Vaccine vaccine) {
		return vaccinations.stream()
				//Gibt ein Boolean zzrück, ob der "Patient" diese impfung hat
				.anyMatch(vaccination -> vaccination.getVaccine().equals(vaccine));
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
	public boolean isOlderThan(int years) {
		return this.getBirthDate().isBefore(LocalDate.now().minusYears(years));
	}

	public Parent getParent() {
		return parent;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	@Override
	public String toString() {
		// Mit super holt man das toString von der Klasse PErson, da es vererbt wurde
		return this.number + " - Patient: " + super.toString() + " " + this.birthDate + " " + this.insurance + "\nThe following investigation:" + this.checkups + "\nThe following Vaccinations: " + this.vaccinations;
	}
	
}
