package version1;

import java.time.LocalDate;
import java.util.ArrayList;

import version1.Pediatrition.Title;
import version1.Person.Gender;
import version1.Vaccination.Vaccine;

public class ConsoleApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parent parent1 = new Parent("Huu", "Nguyen", Gender.MALE, "9876543210", "4537 Wiedlisbach");
		Patient patient1 = new Patient ("Michael", "Nguyen", Gender.MALE, LocalDate.of(1998, 06, 17), parent1, Insurance.ASSURA);

		Parent parent2 = new Parent("Sarah", "Lobsiger", Gender.FEMALE, "132456789", "3000 Bern");
		Patient patient2 = new Patient("Lars", "Meyer", Gender.MALE, LocalDate.of(2010, 12, 31), parent2, Insurance.KPT);
		
		Pediatrition pediatrition1 = new Pediatrition("Adrian", "Casty", Gender.MALE, Title.Dr_Med);
		Pediatrition pediatrition2 = new Pediatrition("Heinni", "Hans", Gender.MALE, Title.Prof_Dr);
		
		Checkup checkup1 = new Checkup(70.3, 175, 36.5, false, pediatrition1);
		Checkup checkup2 = new Checkup(85.2, 185, 37.5, true, LocalDate.of(2019, 12, 15), pediatrition2);
		
		Vaccination vaccination1 = new Vaccination(Vaccine.TETANUS, pediatrition1);
		Vaccination vaccination2 = new Vaccination(Vaccine.COVID, pediatrition2);
		
		patient1.addCheckup(checkup1);
		patient1.addCheckup(checkup2);
		
		patient1.addVaccination(vaccination1);
		patient1.addVaccination(vaccination2);
		
		
		ArrayList<Person> persons = new ArrayList<>();
		persons.add(patient1);
		persons.add(patient2);
		System.out.println(persons);
	}

}
