package version8;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Scanner;

import version8.models.Checkup;
import version8.models.Model;
import version8.models.Patient;
import version8.models.Pediatrician;
import version8.models.Person.Gender;
import version8.models.Vaccination;
import version8.models.Vaccination.Vaccine;
import version8.validator.BooleanValidator;
import version8.validator.DateValidator;
import version8.validator.DoubleRangeValidator;
import version8.validator.IntRangeValidator;
import version8.validator.VaccineValidator;
import version8.validator.Validator;

public class ConsoleApp {

	// Damit man von überallzugreifen kann (in der Main, sowie auch in den Methoden)
	private static Scanner scanner = new Scanner(System.in);
	
	// Die model Klasse initaliseren
	public static Model model = new Model();

	public static void main(String[] args) {

		System.out.println("Welcome Children Hospital");

		//Hier lesen man die Daten von Personen ein
		model = Model.createTestModel();
		model.readCheckups();
		model.readVaccinations();

		// Die applikation soll so lange weiterlaufen, bis User "q" eingibt.
		String selection;

		String menu = Model.getMenu();

		do {
			// Patient suchen und in die variable patient1 abspeichern
			System.out.println(menu);
			selection = scanner.next();
			if (selection.equals("c")) {
				addCheckup();
			} else if (selection.equals("v")) {
				addVaccination();
			} else if (selection.equals("p")) {
				// Gibt alle Patienten aus
				printCollection("Patients", model.getAllPatients());

			} else if (selection.equals("r")) {
				// Gibt alle Eltern aus
				printCollection("Parents", model.getParents());

			} else if (selection.equals("s1")) {
				// Gibt Information vom Patienten aus, welches man nach der PID gesucht hat
				Patient patient = searchPatientByNumber();
				System.out.println(patient);

			} else if (selection.equals("s2")) {
				// Gibt Information vom Patienten aus, welches man nach den Namen gesucht hat
				Collection<Patient> patients = searchPatientByName();
				for (Patient patient : patients) {
					System.out.println(patient);
				}
			} else if (selection.equals("q1")) {
				printCollection("Vaccinated patients", model.getVaccinatedPatients(1));
			} else if (selection.equals("q2")) {
				System.out.println(model.getAmountOfCheckups());
			} else if (selection.equals("q3")) {
				System.out.println(model.getParentNames());
			} else if (selection.equals("q4")) {
				System.out.println(model.getChildrenOfLargestFamily());
			} else if (selection.equals("q5")) {
				System.out.println(model.areVaccinated(Gender.FEMALE, 6, Vaccine.POLIO));
			}
		} while (!selection.equals("q"));

		model.writeVaccinations();
		System.out.println("Bye!");
		scanner.close();
	}

	/********************** Methoden *************************/
	private static void addCheckup() {
		// Hier sucht man eine zufälligen Arzt
		Pediatrician pediatrician = model.getAvailablePediatrician();

		// Hier sucht man den Patienten nach der Nummer
		Patient patient = searchPatientByNumber();
		System.out.println(patient);

		// add check-up für patient
		Checkup checkup = createCheckup(pediatrician, patient.getNumber());
		patient.addCheckup(checkup);

		// In eine CSV Speichern
		String csv = checkup.toCSV();
		try {
			// True wird hier gebraucht um es hinten anzufügen
			Writer writer = new FileWriter("checkups.csv", true);
			writer.write(csv + "\n");
			writer.close();
		} catch (IOException e) {
			System.out.println("Writing checkup not possible!");
		}
	}

	private static void addVaccination() {
		// Hier sucht man eine zufälligen Arzt
		Pediatrician pediatrician = model.getAvailablePediatrician();

		// Hier sucht man den Patienten nach der Nummer
		Patient patient = searchPatientByNumber();
		System.out.println(patient);

		// Add Vaccinantion für patient
		Vaccination vaccination = createVaccination(pediatrician, patient.getNumber());
		patient.addVaccination(vaccination);
	}

	// Create Checkup
	private static Checkup createCheckup(Pediatrician pediatrician, int patientNumber) {

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
		return new Checkup(weight, height, temperature, covid, checkupDate, pediatrician, patientNumber);
	}

	// Create Vaccination
	private static Vaccination createVaccination(Pediatrician pediatrician, int patientNumber) {

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
		return new Vaccination(vaccine, vaccinationDate, pediatrician, patientNumber);
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
	// Das erste T steht nur dafür, das dies eine generishe MEthode ist
	// Das zweite T holt den generischen Daten/Objekttyp beispielsweise Patient oder
	// Parent heraus und speichert diese in T
	// DAs driite T ist nun also hier PAtient oder Parent, und man weist es den wert
	// in Element zu
	private static <T> void printCollection(String label, Collection<T> collection) {
		System.out.println(label.toUpperCase() + ":");
		if (collection.isEmpty()) {
			System.out.println("No elements available");
		} else {
			collection.forEach(System.out::println);
		}
	}

}
