package version1;

import java.time.LocalDate;

public class Vaccination {
	
	public enum Vaccine {POLIO, MEASLES, COVID, DIPHTERIA, TETANUS}
	
	private LocalDate date;
	private Vaccine vaccine;
	private Pediatrition pediatrition;
	
	public Vaccination(Vaccine vaccine, Pediatrition pediatrition) {
		this.date = LocalDate.now();
		this.vaccine = vaccine;
		this.pediatrition = pediatrition;
	}
	
	public Vaccination(Vaccine vaccine, LocalDate localDate,Pediatrition pediatrition) {
		this.date = localDate;
		this.vaccine = vaccine;
		this.pediatrition = pediatrition;
	}
	
	@Override
	public String toString() {
		return "Vaccionation: " + this.vaccine + "\n"; 
	}
	
	
}
