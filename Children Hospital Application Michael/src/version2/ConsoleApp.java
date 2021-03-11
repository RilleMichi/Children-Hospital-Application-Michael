package version2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import version2.exception.InvalidIntRangeException;
import version2.exception.RegexMismatchException;
import version2.models.Checkup;
import version2.models.Insurance;
import version2.models.Parent;
import version2.models.Patient;
import version2.models.Pediatrician;
import version2.models.Person;
import version2.models.Vaccination;
import version2.models.Pediatrician.Title;
import version2.models.Person.Gender;
import version2.models.Vaccination.Vaccine;
import version2.validator.BooleanValidator;
import version2.validator.DateValidator;
import version2.validator.DoubleRangeValidator;
import version2.validator.IntRangeValidator;

public class ConsoleApp {

	// Damit man von überallzugreifen kann (in der Main, sowie auch in den Methoden)
	private static Scanner scanner = new Scanner(System.in);
	// Man erstellt eine PAtients liste, wo man alle Patienten manuell einfügen
	// Würde auch mit Person funktionieren
	// Kann von überallzugegriffen werden (in der Main, sowie auch in den Methoden)
	private static List<Patient> patients = new ArrayList<>();
	private static List<Pediatrician> pediatricians = new ArrayList<>();
	private static List<Parent> parents = new ArrayList<>();

	public static void main(String[] args) {

		initData();

		// Ärzte aus einer Liste in einer Variablen speichern
		Pediatrician pediatrician1 = pediatricians.get(0);
		Pediatrician pediatrician2 = pediatricians.get(1);

		// Die applikation soll so lange weiterlaufen, bis User "q" eingibt.
		String selection;

		do {
			// Patient suchen und in die variable patient1 abspeichern
			System.out.println("Enter choice: [q=quit, c=createCheckup, v=createVaccination, p=printPatients]");
			selection = scanner.next();
			if (selection.equals("c")) {
				Patient patient = enterPatient();
				// add check-up für patient
				// 1. Variante mit Benutzereingabe
				Checkup checkup1 = createCheckup(pediatrician1);
				// 2. Variante
				Checkup checkup2 = new Checkup(70.3, 175, 36.5, false, pediatrician1);
				Checkup checkup3 = new Checkup(85.2, 185, 37.5, true, LocalDate.of(2019, 12, 15), pediatrician2);
				patient.addCheckup(checkup1);
				patient.addCheckup(checkup2);
				patient.addCheckup(checkup3);
			} else if (selection.equals("v")) {
				Patient patient = enterPatient();
				// Add Vaccinantion für patient
				// 1. Variante mit Benutzereingabe
				Vaccination vaccination1 = createVaccination(pediatrician1);
				// 2. Variante
				Vaccination vaccination2 = new Vaccination(Vaccination.Vaccine.TETANUS, pediatrician1);
				Vaccination vaccination3 = new Vaccination(Vaccination.Vaccine.COVID, pediatrician2);
				patient.addVaccination(vaccination1);
				patient.addVaccination(vaccination2);
				patient.addVaccination(vaccination3);
			} else if (selection.equals("p")) {
				// Aufrufen der Methoden um alle PAtienten anzuzeigen
				printPatient();
			}
		} while (!selection.equals("q"));

		scanner.close();
	}

	/********************** MEthoden *************************/
	// Daten einlesen
	private static void initData() {
		// Eltern erstellen und in Liste eintragen
		Parent parent1 = new Parent("Huu", "Nguyen", Person.Gender.MALE, "9876543210", "4537 Wiedlisbach");
		parents.add(parent1);
		Parent parent2 = new Parent("Sarah", "Lobsiger", Person.Gender.FEMALE, "132456789", "3000 Bern");
		parents.add(parent2);

		// Patienten erstellen und in Liste eintragen
		Patient patient1 = new Patient("Michael", "Nguyen", Person.Gender.MALE, LocalDate.of(1998, 06, 17), parent1,
				Insurance.ASSURA);
		patients.add(patient1);
		Patient patient2 = new Patient("Lars", "Meyer", Person.Gender.MALE, LocalDate.of(2010, 12, 31), parent2,
				Insurance.KPT);
		patients.add(patient2);

		// Ärzte erstellen und in Liste eintragen
		Pediatrician pediatrician1 = new Pediatrician("Adrian", "Casty", Person.Gender.MALE, Pediatrician.Title.Dr_Med);
		pediatricians.add(pediatrician1);
		Pediatrician pediatrician2 = new Pediatrician("Heinni", "Hans", Person.Gender.MALE, Pediatrician.Title.Prof_Dr);
		pediatricians.add(pediatrician2);

	}

	// Create Checkup
	private static Checkup createCheckup(Pediatrician pediatrician) {
		// Input weight
		DoubleRangeValidator weightValidator = new DoubleRangeValidator(1, 600);
		//Initalisiere den Wert in weight, damit man später die Variable für das Objekt gebrauchen kann
		double weight = -1;
		//solange der Wert nicht korrekt ist bzw. -1, frage den Benutzer ab
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
			}
			catch(InvalidIntRangeException|RegexMismatchException e){
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

		// Vaccine
		System.out.println("Enter Vaccine: [POLIO, MEASLES, COVID, DIPHTERIA, TETANUS]");
		String str = scanner.next();
		// Uppercase um Fehler zu verhindern
		Vaccination.Vaccine vaccine = Vaccination.Vaccine.valueOf(str.toUpperCase());

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

	/*************** Patient ****************/
	// Gibt die gesuchte Person aus, die der Benutzer eingibt
	private static Patient enterPatient() {
		Patient searchPatient = null;
		do {
			System.out.println("Search Patient");
			System.out.println("Enter Firstname: ");
			String firstName = scanner.next();

			System.out.println("Enter Lastname: ");
			String lastName = scanner.next();
			// Geh zur Methode searchPatient. Man hat es getrennt, damit man die Methode
			// searchPatient auch "einzel" aufrufen kann
			searchPatient = searchPatient(firstName, lastName);

		} while (searchPatient == null);
		// Gibt PAtienten zurück
		return searchPatient;
	}

	private static Patient searchPatient(String firstName, String lastName) {
		// Überprüfe jede Patienten nach Name
		for (Patient patient : patients) {
			// Bei einen Treffer speiche diese in Variable searchPAtient
			if (patient.getFirstName().equals(firstName) && patient.getLastName().equals(lastName)) {
				return patient;
			}
		}
		return null;
	}

	// Gibt alle Patienten aus
	private static void printPatient() {
		for (Person patient : patients) {
			System.out.println(patient);
			System.out.println();
		}
	}

}
