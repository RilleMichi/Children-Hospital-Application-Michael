package version3;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;
import java.util.Set;

import version3.exception.InvalidIntRangeException;
import version3.exception.RegexMismatchException;
import version3.models.Checkup;
import version3.models.Insurance;
import version3.models.Model;
import version3.models.Parent;
import version3.models.Patient;
import version3.models.Pediatrician;
import version3.models.Person;
import version3.models.Vaccination;
import version3.validator.BooleanValidator;
import version3.validator.DateValidator;
import version3.validator.DoubleRangeValidator;
import version3.validator.IntRangeValidator;

public class ConsoleApp {

	// Damit man von überallzugreifen kann (in der Main, sowie auch in den Methoden)
	private static Scanner scanner = new Scanner(System.in);
	// Die model Klasse initaliseren
	private static Model model = new Model();

	public static void main(String[] args) {

		System.out.println("Welcome Children Hospital");

		initData();

		// Die applikation soll so lange weiterlaufen, bis User "q" eingibt.
		String selection;
		do {
			// Patient suchen und in die variable patient1 abspeichern
			System.out.println(
					"Enter choice: [q=quit, c=createCheckup, v=createVaccination, p=printPatients, s=searchPatientsByNumber, n=searchPatientsByName, r=printParents]");
			selection = scanner.next();
			if (selection.equals("c")) {
				// Hier sucht man eine zufälligen Arzt
				Pediatrician pediatrician = model.getAvailablePediatrician();
				// Hier sucht man den Patienten nach der Nummer
				Patient patient = searchPatientByNumber();
				// add check-up für patient
				// 1. Variante mit Benutzereingabe
				Checkup checkup1 = createCheckup(pediatrician);
				// 2. Variante
				Checkup checkup2 = new Checkup(70.3, 175, 36.5, false, pediatrician);
				Checkup checkup3 = new Checkup(85.2, 185, 37.5, true, LocalDate.of(2019, 12, 15), pediatrician);
				patient.addCheckup(checkup1);
				patient.addCheckup(checkup2);
				patient.addCheckup(checkup3);
			} else if (selection.equals("v")) {
				Pediatrician pediatrician = model.getAvailablePediatrician();
				// Hier sucht man den Patienten nach Name
				// Patient patient = searchPatientByName();

				// Hier sucht man den Patienten nach der Nummer
				Patient patient = searchPatientByNumber();

				// Add Vaccinantion für patient
				// 1. Variante mit Benutzereingabe
				Vaccination vaccination1 = createVaccination(pediatrician);
				// 2. Variante
				Vaccination vaccination2 = new Vaccination(Vaccination.Vaccine.TETANUS, pediatrician);
				Vaccination vaccination3 = new Vaccination(Vaccination.Vaccine.COVID, pediatrician);
				patient.addVaccination(vaccination1);
				patient.addVaccination(vaccination2);
				patient.addVaccination(vaccination3);
			} else if (selection.equals("p")) {
				// Aufrufen der Methoden um alle PAtienten anzuzeigen
				printPatient();
			} else if (selection.equals("s")) {
				Patient patient = searchPatientByNumber();
				System.out.println(patient);
			} else if (selection.equals("n")) {
				Collection<Patient> patients= searchPatientByName();
				for (Patient patient : patients) {
					System.out.println(patient);
				}
			} else if (selection.equals("r")) {
				// Aufrufen der Methoden um alle PAtienten anzuzeigen
				printParents();
			}
		} while (!selection.equals("q"));

		scanner.close();
	}

	/********************** MEthoden *************************/
	// Daten einlesen
	private static void initData() {
		// Eltern erstellen und in Liste eintragen
		Parent parent1 = new Parent("Huu", "Nguyen", Person.Gender.MALE, "9876543210", "4537 Wiedlisbach");
		Parent parent2 = new Parent("Sarah", "Lobsiger", Person.Gender.FEMALE, "132456789", "3000 Bern");

		// Patienten erstellen und in Liste eintragen
		Patient patient1 = new Patient("Michael", "Nguyen", Person.Gender.MALE, LocalDate.of(1998, 06, 17), parent1,
				Insurance.ASSURA);
		Patient patient2 = new Patient("Lars", "Meyer", Person.Gender.MALE, LocalDate.of(2010, 12, 31), parent2,
				Insurance.KPT);
		Patient patient3 = new Patient("Lars", "Meyer", Person.Gender.MALE, LocalDate.of(2011, 12, 31), parent2,
				Insurance.KPT);

		model.addPatient(patient1);
		model.addPatient(patient2);
		model.addPatient(patient3);
		/*
		 * Ist eine ehemalige Methode (Wo man keine Model klasse hatte) in eine Liste
		 * einzufügen patients.add(patient1); patients.add(patient1);
		 * patientMap.put(patient1.getFullName(), patient1);p
		 * atientMap.put(patient2.getFullName(), patient2);
		 * 
		 */

		// Ärzte erstellen und in Liste eintragen
		Pediatrician pediatrician1 = new Pediatrician("Adrian", "Casty", Person.Gender.MALE, Pediatrician.Title.Dr_Med);
		Pediatrician pediatrician2 = new Pediatrician("Heinni", "Hans", Person.Gender.MALE, Pediatrician.Title.Prof_Dr);
		model.addPediatrician(pediatrician1);
		model.addPediatrician(pediatrician2);
	}

	// Create Checkup
	private static Checkup createCheckup(Pediatrician pediatrician) {
		// Input weight
		DoubleRangeValidator weightValidator = new DoubleRangeValidator(1, 600);
		// Initalisiere den Wert in weight, damit man später die Variable für das Objekt
		// gebrauchen kann
		double weight = -1;
		// solange der Wert nicht korrekt ist bzw. -1, frage den Benutzer ab
		do {
			try {
				System.out.println("Enter temperature: ");
				String input = scanner.next();
				weight = weightValidator.validate(input);
			} catch (Exception e) {
				System.err.println(e.getMessage() + " Try again!");
			}
		} while (weight == -1);

		// Input height
		IntRangeValidator heightValidator = new IntRangeValidator(1, 300);
		int height = -1;
		do {
			System.out.println("Enter height: ");
			String input = scanner.next();
			try {
				height = heightValidator.validate(input);
			} catch (InvalidIntRangeException | RegexMismatchException e) {
				System.err.println("Invalid input: " + e);
			}
		} while (height == -1);

		// Input Temperature
		DoubleRangeValidator temperatureValidator = new DoubleRangeValidator(20, 50);
		double temperature = -1;
		do {
			try {
				System.out.println("Enter temperature: ");
				String input = scanner.next();
				temperature = temperatureValidator.validate(input);
			} catch (Exception e) {
				System.err.println(e.getMessage() + " Try again!");
			}
		} while (temperature == -1);

		// Input Covid true or false
		Boolean covid = null;
		do {
			try {
				System.out.println("Enter Covid: ");
				covid = BooleanValidator.validate(scanner.next());
			} catch (Exception e) {
				System.err.println(e.getMessage() + " Try again!");
			}
		} while (covid == null);

		// input Date
		LocalDate checkupDate = null;
		do {
			try {
				System.out.println("Enter Checkup Date in dd:mm:yyyy");
				checkupDate = DateValidator.validate(scanner.next());
			} catch (Exception e) {
				System.err.println(e.getMessage() + " Try again!");
			}
		} while (checkupDate == null);

		// Return Checkup
		return new Checkup(weight, height, temperature, covid, checkupDate, pediatrician);
	}

	// Create Vaccination
	private static Vaccination createVaccination(Pediatrician pediatrician) {
		boolean validInput;
		Vaccination.Vaccine vaccine = null;
		do {
			// Vaccine
			System.out.println("Enter Vaccine: [POLIO, MEASLES, COVID, DIPHTERIA, TETANUS]");
			String str = scanner.next();
			try {
				// Uppercase um Fehler zu verhindern
				vaccine = Vaccination.Vaccine.valueOf(str.toUpperCase());
				validInput = true;
			} catch (IllegalArgumentException e) {
				validInput = false;
			}
		} while (!validInput);

		// Datum
		LocalDate vaccinationDate = null;
		do {
			try {
				System.out.println("Enter Checkup Date in dd:mm:yyyy");
				vaccinationDate = DateValidator.validate(scanner.next());
			} catch (Exception e) {
				System.err.println(e.getMessage() + " Try again!");
			}
		} while (vaccinationDate == null);

		// Return Checkup
		return new Vaccination(vaccine, vaccinationDate, pediatrician);
	}

	// Gibt die gesuchte Person aus, die der Benutzer eingibt
	private static Collection<Patient> searchPatientByName() {
		Collection<Patient> patientNumbers;

		System.out.println("Search Patient");
		System.out.println("Enter Firstname: ");
		String firstName = scanner.next();

		System.out.println("Enter Lastname: ");
		String lastName = scanner.next();
		// Geh zur Methode searchPatient. Man hat es getrennt, damit man die Methode
		// searchPatient auch "einzel" aufrufen kann
		patientNumbers = model.getPatients(firstName, lastName);

		return patientNumbers;
	}

	// Gibt die gesuchte Person aus, die der Benutzer eingibt. Aber diesesmal nach
	// der Nummer
	private static Patient searchPatientByNumber() {
		Patient searchPatient;
		do {
			System.out.print("Enter patient number: ");
			int patientNumber = scanner.nextInt();
			searchPatient = model.getPatient(patientNumber);
		} while (searchPatient == null);
		// Gibt Patienten zurück
		return searchPatient;
	}

	// Gibt alle Patienten aus. Zur Info: diese Methode hat nichts in der Model
	// Klasse zu suchen, da es Sachen in der KONSOLO ausgibt
	private static void printPatient() {
		for (Patient patient : model.getAllPatients()) {
			System.out.println(patient);
		}
	}
	
	private static void printParents() {
		for (Parent parent : model.getParents()) {
			System.out.println(parent);
		}
	}

}
