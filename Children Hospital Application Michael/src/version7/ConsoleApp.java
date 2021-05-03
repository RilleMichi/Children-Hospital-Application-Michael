package version7;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import version7.models.Checkup;
import version7.models.Insurance;
import version7.models.Model;
import version7.models.Parent;
import version7.models.Patient;
import version7.models.Pediatrician;
import version7.models.Person;
import version7.models.Person.Gender;
import version7.models.Vaccination;
import version7.models.Vaccination.Vaccine;
import version7.validator.BooleanValidator;
import version7.validator.DateValidator;
import version7.validator.DoubleRangeValidator;
import version7.validator.IntRangeValidator;
import version7.validator.LocalDateDeserializer;
import version7.validator.LocalDateSerializer;
import version7.validator.VaccineValidator;
import version7.validator.Validator;

public class ConsoleApp {

	// Damit man von überallzugreifen kann (in der Main, sowie auch in den Methoden)
	private static Scanner scanner = new Scanner(System.in);
	// Die model Klasse initaliseren
	private static Model model = new Model();

	public static void main(String[] args) {

		System.out.println("Welcome Children Hospital");

		initData();
		readCheckups();
		readVaccinations();

		// Die applikation soll so lange weiterlaufen, bis User "q" eingibt.
		String selection;

		String menu = getMenu();

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

		writeVaccinations();
		System.out.println("Bye!");
		scanner.close();
	}

	private static void readVaccinations() {		
		//gsonBuilder erstellen
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
		
		//Gson erstellen
		Gson gson = gsonBuilder.setPrettyPrinting().create();

		try {
			//Reader klasse liest ein Json File
			Reader reader = new FileReader("vaccinations.json");
			
			//Mithilfe des gsonBuilder werden die Daten richtig formatiert. Alle Vaccination klassen werden vorest in einer Liste abgespeichert
			Vaccination[] vaccinations = gson.fromJson(reader, Vaccination[].class);
			//Sie werden einzeln ausgebenen UND ANSCHLIESSEND ZUM RICHTIGEN PATIENT erstellt
			for (Vaccination vaccination : vaccinations) {
				int patientNumber = vaccination.getPatientNumber();
				Patient patient = model.getPatient(patientNumber);
				patient.addVaccination(vaccination);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Reading vaccinations not possible!");
		}
	}

	private static void writeVaccinations() {
		//Man holt alle Vaccinations heraus
		List<Vaccination> vaccinations = model.getVaccinations();
		
		//Man erstellt eine Klasse gsonbuilder
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
		gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

		//Gson wird erstellt
		Gson gson = gsonBuilder.setPrettyPrinting().create();

		try {
			//Man erstellt falls noch nicht vorhanden ein neues vaccination.json File
			Writer writer = new FileWriter("vaccinations.json");
			//Diese werden eingetragen
			gson.toJson(vaccinations, writer);
			writer.close();
		} catch (IOException e) {
			System.out.println("Writing vaccinations not possible!");
		}

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

	private static String getMenu() {
		String menu = "";
		String sep = "";
		try {
			Reader fileReader = new FileReader("menu.txt");
			Scanner scanner = new Scanner(fileReader);
			// Man liest von Zeile zu Zeile
			while (scanner.hasNextLine()) {
				// speichert die Zeile in die Variable line
				String line = scanner.nextLine();
				// Wird im Menü hinten noch angefügt
				menu = menu + sep + line;
				sep = ", ";
			}
			scanner.close();
			fileReader.close();
		} catch (IOException e) {
			System.out.println("Menu not found!");
			// Hier gibt man einen Leeren Strng zurück, wenn man den Menu nicht findet
			return "";
		}
		return menu;
	}

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

	private static void readCheckups() {
		try {
			Reader reader = new FileReader("checkups.csv");
			Scanner scanner = new Scanner(reader);
			while (scanner.hasNextLine()) {
				// speichert die zeile in csv
				String csv = scanner.nextLine();
				// erstellt ein Checkup für jede Zeile indem sie Methode fromCSV aufruft
				Checkup checkup = Checkup.fromCSV(csv, model);

				// Von diesem Checkup holt man die Patientnummer
				int patientNumber = checkup.getPatientNumber();
				// Danach holt man den Patient anhand dieser PAtientennummer
				Patient patient = model.getPatient(patientNumber);
				// Der Checkup wird zum PAtienten hinzugefügt.
				patient.addCheckup(checkup);
			}
			scanner.close();
			reader.close();
		} catch (IOException e) {
			System.out.println("Checkup file not found!");
		}
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
