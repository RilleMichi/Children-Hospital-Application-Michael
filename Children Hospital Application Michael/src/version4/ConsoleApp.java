package version4;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

import version4.models.Checkup;
import version4.models.Insurance;
import version4.models.Model;
import version4.models.Parent;
import version4.models.Patient;
import version4.models.Pediatrician;
import version4.models.Person;
import version4.models.Vaccination;
import version4.models.Vaccination.Vaccine;
import version4.validator.BooleanValidator;
import version4.validator.DateValidator;
import version4.validator.DoubleRangeValidator;
import version4.validator.IntRangeValidator;
import version4.validator.VaccineValidator;
import version4.validator.Validator;

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
				System.out.println(patient);
				
				// add check-up für patient
				Checkup checkup = createCheckup(pediatrician);
				patient.addCheckup(checkup);

			} else if (selection.equals("v")) {
				// Hier sucht man eine zufälligen Arzt
				Pediatrician pediatrician = model.getAvailablePediatrician();

				// Hier sucht man den Patienten nach der Nummer
				Patient patient = searchPatientByNumber();
				System.out.println(patient);

				// Add Vaccinantion für patient
				Vaccination vaccination = createVaccination(pediatrician);
				patient.addVaccination(vaccination);
				
			} else if (selection.equals("p")) {
				//Gibt alle Patienten aus
				printCollection("Patients", model.getAllPatients());
				
			} else if (selection.equals("r")) {
				//Gibt alle Eltern aus
				printCollection("Parents", model.getParents());
				
			} else if (selection.equals("s")) {
				//Gibt Information vom Patienten aus, welches man nach der PID gesucht hat
				Patient patient = searchPatientByNumber();
				System.out.println(patient);
				
			} else if (selection.equals("n")) {
				//Gibt Information vom Patienten aus, welches man nach den Namen gesucht hat
				Collection<Patient> patients = searchPatientByName();
				for (Patient patient : patients) {
					System.out.println(patient);
				}
			} 
		} while (!selection.equals("q"));

		scanner.close();
	}

	/********************** Methoden *************************/
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

		// Ärzte erstellen und in Liste eintragen
		Pediatrician pediatrician1 = new Pediatrician("Adrian", "Casty", Person.Gender.MALE, Pediatrician.Title.Dr_Med);
		Pediatrician pediatrician2 = new Pediatrician("Heinni", "Hans", Person.Gender.MALE, Pediatrician.Title.Prof_Dr);
		model.addPediatrician(pediatrician1);
		model.addPediatrician(pediatrician2);
	}

	// Create Checkup
	private static Checkup createCheckup(Pediatrician pediatrician) {

		// Input weight
		Validator<Double> weightValidator = new DoubleRangeValidator(1, 600);
		ConsoleInput<Double> weightInput = new ConsoleInput<Double>(scanner, weightValidator, "Enter weight: ");
		double weight = weightInput.enterValue();

		// Input height
		Validator<Integer> heightValidator = new IntRangeValidator(1, 300);
		ConsoleInput<Integer> heightInput = new ConsoleInput<Integer>(scanner, heightValidator, "Enter height: ");
		int height = heightInput.enterValue();

		// Input Temperature
		Validator<Double> temperatureValidator = new DoubleRangeValidator(20, 50);
		ConsoleInput<Double> temepratureInput = new ConsoleInput<Double>(scanner, temperatureValidator,
				"Enter temperature: ");
		double temperature = temepratureInput.enterValue();

		// Input Covid true or false
		Validator<Boolean> booleanValidator = new BooleanValidator();
		ConsoleInput<Boolean> covidInput = new ConsoleInput<Boolean>(scanner, booleanValidator, "Enter Covid: ");
		boolean covid = covidInput.enterValue();

		// Input Datum
		Validator<LocalDate> dateValidator = new DateValidator();
		ConsoleInput<LocalDate> dateInput = new ConsoleInput<>(scanner, dateValidator,
				"Enter Checkup Date as dd:mm:yyyy: ");
		LocalDate checkupDate = dateInput.enterValue();

		// Return Checkup Object
		return new Checkup(weight, height, temperature, covid, checkupDate, pediatrician);
	}

	// Create Vaccination
	private static Vaccination createVaccination(Pediatrician pediatrician) {

		// Input Vaccine
		Validator<Vaccine> vaccineValidator = new VaccineValidator();
		ConsoleInput<Vaccine> vaccineInput = new ConsoleInput<>(scanner, vaccineValidator,
				"Enter Vaccine: [POLIO, MEASLES, COVID, DIPHTERIA, TETANUS]");
		Vaccine vaccine = vaccineInput.enterValue();

		// Input Datum
		Validator<LocalDate> dateValidator = new DateValidator();
		ConsoleInput<LocalDate> dateInput = new ConsoleInput<>(scanner, dateValidator,
				"Enter Checkup Date as dd:mm:yyyy: ");
		LocalDate vaccinationDate = dateInput.enterValue();

		// Return Vaccination Object
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

	// Gibt eine Collection zurück nach beliebier Typ
	//Das erste T steht nur dafür, das dies eine generishe MEthode ist
	//Das zweite T holt den generischen Daten/Objekttyp beispielsweise Patient oder Parent heraus und speichert diese in T
	//DAs driite T ist nun also hier PAtient oder Parent, und man weist es den wert in Element zu
	private static <T> void printCollection(String label, Collection<T> collection) {
		System.out.println(label.toUpperCase() + ":");
		// Gebe alle daten aus der collecion in element von Typ T aus.
		for (T element : collection) {
			System.out.println(element);
		}
	}

}
