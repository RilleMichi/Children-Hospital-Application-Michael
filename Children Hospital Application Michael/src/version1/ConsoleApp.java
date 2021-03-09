package version1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
		//Ärzte aus einer Liste in einer Variablen speichern
		Pediatrician pediatrician1 = pediatricians.get(0);
	    Pediatrician pediatrician2 = pediatricians.get(1);

	    //Patient suchen und in die variable patient1 abspeichern
		Patient patient1 = enterPatient();
		
		// add check-up für patient1
		//1. Variante
		Checkup checkup1 = createCheckup(pediatrician1);
		//2. Variante
		Checkup checkup2 = new Checkup(70.3, 175, 36.5, false, pediatrician1);
		Checkup checkup3 = new Checkup(85.2, 185, 37.5, true, LocalDate.of(2019, 12, 15), pediatrician2);
		patient1.addCheckup(checkup1);
		patient1.addCheckup(checkup2);
		patient1.addCheckup(checkup3);

		
		// Add Vaccinantion für patient2
		Vaccination vaccination1 = new Vaccination(Vaccination.Vaccine.TETANUS, pediatrician1);
		Vaccination vaccination2 = new Vaccination(Vaccination.Vaccine.COVID, pediatrician2);
		patient1.addVaccination(vaccination1);
		patient1.addVaccination(vaccination2);
		

		// Checkup eingabe
		Checkup ckeckup3 = createCheckup(pediatrician1);
		patient1.addCheckup(ckeckup3);

		// Aufrufen der Methoden um alle PAtienten anzuzeigen
		printPatient();
		scanner.close();
	}

	/**********************MEthoden*************************/
	
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
		Pediatrician pediatrician2 = new Pediatrician("Heinni", "Hans", Person.Gender.MALE, Pediatrician.Title.Prof_Dr);

		
	}

	private static Checkup createCheckup(Pediatrician pediatrician) {
		// Example kg
		System.out.println("Enter weight in kg");
		// Gibe solange ein bis es ein double ist
		while (!scanner.hasNextDouble()) {
			System.out.println("Thats not a double");
			scanner.next();
		}
		// Speichere wert in variable weight
		double weight = scanner.nextDouble();

		System.out.println("Enter height in cm");
		while (!scanner.hasNextInt()) {
			System.out.println("Thats not an Integer");
			scanner.next();
		}
		int height = scanner.nextInt();

		System.out.println("Enter temperature");
		while (!scanner.hasNextDouble()) {
			System.out.println("Thats not a double");
			scanner.next();
		}
		double temperature = scanner.nextDouble();

		System.out.println("Enter Covid as True or False");
		while (!scanner.hasNextBoolean()) {
			System.out.println("Thats not a Boolean");
			scanner.next();
		}
		boolean covid = scanner.nextBoolean();

		// Return Checkup
		return new Checkup(weight, height, temperature, covid, pediatrician);
	}

	/***************Patient****************/
	// Gibt die gesuchte Person aus, die der Benutzer eingibt
	private static Patient enterPatient() {
		Patient searchPatient = null;
		do {
			System.out.println("Search Patient");
			System.out.println("Enter Firstname: ");
			String firstName = scanner.next();

			System.out.println("Enter Lastname: ");
			String lastName = scanner.next();
			//Geh zur Methode searchPatient. Man hat es getrennt, damit man die Methode searchPatient auch "einzel" aufrufen kann
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
