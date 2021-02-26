package Version1;

import java.time.LocalDate;

public class Vaccination {
	
	public enum Vaccine {POLIO, MEASLES, COVID}
	
	private LocalDate date;
	private Vaccine vaccine;
	private Pediatrition pediatrition;
	
	public Vaccination(Vaccine vaccine, Pediatrition pediatrition) {
		this.date = LocalDate.now();
		this.vaccine = vaccine;
		this.pediatrition = pediatrition;
	}
	
	
}
