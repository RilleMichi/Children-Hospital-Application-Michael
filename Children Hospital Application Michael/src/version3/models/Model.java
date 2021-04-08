package version3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import version3.models.Patient;
import version3.models.Pediatrician;

public class Model {

	// Man erstellt eine PAtients liste, wo man alle Patienten manuell einfügen
	// Würde auch mit Person funktionieren
	// Kann von überallzugegriffen werden (in der Main, sowie auch in den Methoden)
	private List<Patient> patients;
	// Map um keine lineare suche zu haben
	private Map<String, Patient> patientMap;
	
	private List<Pediatrician> pediatricians;
	
	public Model() {
		this.patients = new ArrayList<>();
		this.patientMap  = new HashMap<>();
		this.pediatricians = new ArrayList<>();
	}
	
	//Fügt hier in die Map und ArrayListe die Patient ein
	public void addPatient (Patient patient) {
		this.patients.add(patient);
		this.patientMap.put(patient.getFullName(), patient);
	}
	
	public Patient getPatient(String firstName, String lastName) {
		//Hier sucht man den PAtient mit der Map Collections da es schnelle geht
		String fullName = firstName + " " + lastName;
		Patient patient = this.patientMap.get(fullName);
		//Zur Info gibt Null zurück, wenn es keinen PAtient findet
		return patient;

		// Überprüfe jede Patienten nach Name
		// for (Patient patient : patients) {
		// Bei einen Treffer speiche diese in Variable searchPAtient
		// if (patient.getFirstName().equals(firstName) &&
		// patient.getLastName().equals(lastName)) {
		// return patient;
		// }
		// }
		// return null;
	}
	
	//Diese Methode ist aber gefährlich, da man die Liste nach draussen gibt
	//Man kann aber entweder eine Kopie erstellen und nicht eine Unmodifzierte Liste ausgeben
	//Was hier anschliessend gemacht wurde
	public List<Patient> getAllPatients(){
		return Collections.unmodifiableList(this.patients);
	}
	
	public void addPediatrician(Pediatrician pediatrician) {
		this.pediatricians.add(pediatrician);
	}

	public Pediatrician getAvailablePediatrician() {
		Random random = new Random();
		//Man bekommt hier einen zufälligen Wert von der Ärzte liste 
		int index = random.nextInt(this.pediatricians.size());
		return this.pediatricians.get(index);
	}

}
