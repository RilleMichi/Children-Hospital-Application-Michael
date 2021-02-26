package Version1;

import java.time.LocalDate;

import Version1.Pediatrition.Title;
import Version1.Person.Gender;

public class ConsoleApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parent parent1 = new Parent("Huu", "Nguyen", Gender.MALE, "9876543210", "4537 Wiedlisbach");
		System.out.println(parent1);
		Person patient1 = new Patient ("Michael", "Nguyen", Gender.MALE, LocalDate.of(1998, 06, 17), parent1, Insurance.ASSURA);
		System.out.println(patient1);

		Parent parent2 = new Parent("Sarah", "Lobsiger", Gender.FEMALE, "132456789", "3000 Bern");
		Patient patient2 = new Patient("Lars", "Meyer", Gender.MALE, LocalDate.of(2010, 12, 31), parent2, Insurance.KPT);
		
		Pediatrition pediatrition1 = new Pediatrition("Adrian", "Casty", Gender.MALE, Title.Dr_Med);
		System.out.println(pediatrition1);
		Pediatrition pediatrition2 = new Pediatrition("Heinni", "Hans", Gender.MALE, Title.Prof_Dr);
		
		Checkup checkup1 = new Checkup(70.3, 175, 36.5, false, pediatrition1);
		Checkup checkup2 = new Checkup(85.2, 185, 37.5, true, pediatrition2);
		System.out.println(checkup1);
		
	}

}
