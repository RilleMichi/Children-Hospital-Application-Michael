package version1;

import java.time.LocalDate;

public class Vaccination {

	public enum Vaccine {
		// Zusatzinformationen
		// Man k�nnte auch eine Klasse erstellen, aber der Aufwand ist in diesme Projekt zu gross.
		POLIO(10, true), MEASLES(5, true), COVID(-1, false), DIPHTERIA(8, true), TETANUS(9, true);

		private int protectionYears;
		private boolean recommended;

		private Vaccine(int protectionYears, boolean recommended) {
			this.protectionYears = protectionYears;
			this.recommended = recommended;
		}

		//Damit man die protectionyears und empefehlung aufrufen kann
		public int getProtectionYears() {
			return protectionYears;
		}

		public boolean isRecommended() {
			return recommended;
		}

	}

	private LocalDate date;
	private Vaccine vaccine;
	private Pediatrition pediatrition;

	public Vaccination(Vaccine vaccine, Pediatrition pediatrition) {
		this.date = LocalDate.now();
		this.vaccine = vaccine;
		this.pediatrition = pediatrition;
	}

	public Vaccination(Vaccine vaccine, LocalDate localDate, Pediatrition pediatrition) {
		this.date = localDate;
		this.vaccine = vaccine;
		this.pediatrition = pediatrition;
	}

	public LocalDate getDate() {
		return date;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}

	public Pediatrition getPediatrition() {
		return pediatrition;
	}

	@Override
	public String toString() {
		return String.format("{Date: %s, Vaccine: %s}", this.date, this.vaccine);
	}

}
