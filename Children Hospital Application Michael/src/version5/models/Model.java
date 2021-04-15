package version5.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import version5.models.Person.Gender;
import version5.models.Vaccination.Vaccine;

public class Model {

	// Man erstellt eine PAtients liste, wo man alle Patienten manuell einfügen
	// Würde auch mit Person funktionieren
	// Kann von überallzugegriffen werden (in der Main, sowie auch in den Methoden)
	private List<Patient> patients;

	// Hier speichert MEngen von Patientennummer ab, statt eines PAtient
	private Map<String, Set<Integer>> patientNumberMap;

	// Eine Map mit Inter bzw. ID. Diese sind eindeutiger als die vorherige
	private Map<Integer, Patient> patientMap;

	private List<Pediatrician> pediatricians;

	public Model() {
		this.patients = new ArrayList<>();
		this.patientNumberMap = new HashMap<>();
		this.patientMap = new HashMap<>();
		this.pediatricians = new ArrayList<>();
	}

	// Fügt hier in die Map und ArrayListe den Patient ein
	public void addPatient(Patient patient) {
		int patientNumber = patient.getNumber();
		String name = patient.getFullName();
		this.patients.add(patient);
		this.patientMap.put(patientNumber, patient);

		/*
		 * Es kann vorkommen, dass Patienten den gleichen Namen haben. Dies wird mit
		 * dieser Collections überprüft. In der PatientNumberMap collections ist der
		 * Name der KEY und die Nummer den Wert
		 *
		 * Die Nummer ist eine Menge! Das heisst bei Namen, die mehrmals vorkommen ist
		 * der Wert eine Menge mit unterschiedlichen Patientennummer z.B.
		 * "Michael Nguyen",{1, 3,9}
		 * 
		 * Wenn man einen Patienten einfügt, geht man in der patientNumberMap und sucht
		 * den Namen Anschliessend wird es unter patientNumbers gespeichert
		 * 
		 * Falls man den Namen nicht findet, muss man für patientNumbers zuerst eine
		 * leere Menge erstellen (wird vorgegeben) Danach fügt man in dieser
		 * patientNumbers den jeweiligen Nummer Zu guter letzt speichert man diese in
		 * der patientNumberMap
		 * 
		 * Falls aber der Name vorhanden. Das heisst zum Beispiel der Name Michael
		 * existiert bereits dann wird in der Menge patientNumbers eine weitere
		 * PatientenNummerID mitgegeben
		 */
		Set<Integer> patientNumbers = this.patientNumberMap.get(name);
		// Wenn es keinen Eintrag gibt
		if (patientNumbers == null) {
			// man erstellt eine leere Menge
			patientNumbers = new HashSet<>();
			// Man fügt die Patientennummer in der leeren Menge hinzu
			patientNumbers.add(patientNumber);
			this.patientNumberMap.put(name, patientNumbers);
		}
		// Man fügt in der Menge einen weiteren Patientennumme hinzu.
		else {
			patientNumbers.add(patientNumber);
			/*
			 * diese Zeile ist nicht nötig. patientNumbers ist zwar einelokale Variable, sie
			 * ist aber eine Referenz auf den bereits existierenden Eintrag in der Map. Wenn
			 * Sie also diese put-Operation ausführen, wird die existierende Referenz
			 * ersetzt durch sich selbst. Das funktioniert zwar, ist aber nicht nötig.
			 */
			// this.patientNumberMap.put(name, patientNumbers);
		}
	}

	// Gibt eine Liste von den PatientenNUMMER zurück, wo diesen Namen besitzen
	// Hier werden alle PAtientenNummer aus der PatientNumberMap herausgegegeben
	// Das heisst zum Beispiel Wenn man nach den Namen Michael sucht und es 2
	// Michaels gibt werden beide IDNummer als eine Menge zurückgegeben
	public Set<Integer> getPatientNumbers(String firstName, String lastName) {
		String fullName = firstName + " " + lastName;
		// Holt den Wert bzw. Menge mithilfe des Namen
		Set<Integer> patientNumbers = this.patientNumberMap.get(fullName);
		// Falls man den Namen nicht findet wird eine leere menge zurückgegeben.
		if (patientNumbers == null) {
			return new HashSet<>();
		} else {
			return Collections.unmodifiableSet(patientNumbers);
		}
	}

	// Gibt eine Liste mit PAtienten zurück, wo man nach der PID gesucht hat
	public Patient getPatient(int number) {
		return this.patientMap.get(number);
	}

	// Gibt eine Liste mit allen Patienten zurück
	// Diese Methode ist aber gefährlich, da man die Liste nach draussen gibt
	// Man kann aber entweder eine Kopie erstellen und nicht eine Unmodifzierte
	// Liste ausgeben
	// Was hier anschliessend gemacht wurde
	public List<Patient> getAllPatients() {
		return Collections.unmodifiableList(this.patients);
	}

	public void addPediatrician(Pediatrician pediatrician) {
		this.pediatricians.add(pediatrician);
	}

	public Pediatrician getAvailablePediatrician() {
		Random random = new Random();
		// Man bekommt hier einen zufälligen Wert von der Ärzte liste
		int index = random.nextInt(this.pediatricians.size());
		return this.pediatricians.get(index);
	}

	// Man holt hier alle PatientenNummer und gibt diese PAtientena aus
	public Collection<Patient> getPatients(String firstName, String lastName) {
		// Man erstellt eine Array, damit man bei mehreren Patienten diese zurückgeben
		// kann
		List<Patient> patients = new ArrayList<Patient>();
		// diese Ruft die getPatientNumbers Methode auf und gibt alle Patienten zurück
		// mit den gleichen Namen
		Set<Integer> patientNumbers = this.getPatientNumbers(firstName, lastName);
//		patientNumbers.forEach(patientNumber -> {
//			Patient patient = this.getPatient(patientNumber);
//			patients.add(patient);
//		});
		for (int patientNumber : patientNumbers) {
			// werden dann in der array gespeichert und wieder ausgegeben
			Patient patient = this.getPatient(patientNumber);
			patients.add(patient);
		}
		// hier ist kein unmodifiable, da dies bereits geändert wurde
		return patients;
	}

	// Man holt eine Menge von Eltern
	public Collection<Parent> getParents() {
		// Hier erstellt man eine Leere Menge, da man die Eltern nur EINMAL ausgeben
		// möchte
		Set<Parent> parents = new HashSet<>();
		for (Patient patient : patients) {
			Parent parent = patient.getParent();
			parents.add(parent);
		}
		// Kein umodified, da es intern bereits abgeändert wurde
		return parents;
	}

	public Collection<Parent> getParents(String firstName, String lastName) {
		// Quelle
		return this.patients.stream()
				// Hole alle Eltern, indem er für jeden einzelnen Objekts erzeugt und dann die getPArent Methode aufruft 
				.map(patient -> patient.getParent())
				// Suche alle nach dem Namen mit dem Vor und Nachnamen
				.filter(parent -> parent.getFirstName().equals(firstName) && parent.getLastName().equals(lastName))
				// Dient dazu gleiche ellemente zu löschen
				// .distinct()
				// Danach gibt man eine Menge zurück, damit es keine Duplikate entstehen
				.collect(Collectors.toSet());
	}

	public int getNumberOfPatients() {
		return this.patients.size();
	}

	public Set<Patient> getVaccinatedPatients(int amount) {
		// Quelle in einem Stream umwandeln
		// Man holt Objekte von Patient!
		return this.patients.stream()
				// info patient ist ein frei definierbarer Name
				// Man benötigt hier keine Map, da man hier sowieso PAtient zurückgeben möchte
				// Überprüfen ob die Vaccination liste grösser als 0 ist.
				.filter(patient -> patient.getVaccinations().size() >= amount)
				// Ausgabe alle Patienten, die mehr als 1 Impfung haben in eine MEnge
				.collect(Collectors.toSet());
	}

	public int getAmountOfCheckups() {
		return this.patients.stream()
				// Hole mir die Liste alle Checkups derjenigen Patienten
				.mapToInt(patient -> patient.getCheckups().size())
				// Gib dann die Summe als Integer
				.sum();
	}

	public String getParentNames() {
		return this.patients.stream()
				// hole die Liste der Eltern wie ".map(patient -> patient.getParent())"
				.map(Patient::getParent)
				// Gib die Eltern nur einmal aus
				.distinct()
				// Hole den Namen des Elterns wie ".map(parent -> parent.getFullName())"
				.map(Parent::getFullName)
				// sortiere
				.sorted()
				// Setze die als EINEN String zusammen und trenne diese mit ,
				.collect(Collectors.joining(", "));
	}
	
	
	public boolean areVaccinated(Gender gender,int age, Vaccine vaccine) {
		return this.patients.stream()
				//Hole alle Frauen heraus
				.filter(patient -> patient.getGender().equals(gender))
				//Hole alle Frauen, die älter sind als 6 Jahren
				.filter(patient -> patient.isOlderThan(age))
				//Hole alle Frauen heraus, die mit Polio geimpft sind.
				.allMatch(patient -> patient.isVaccinated(vaccine));						
	}
	
	
}