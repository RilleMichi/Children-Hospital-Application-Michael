package version4.models;

import java.time.LocalDate;

public class Checkup {public Checkup() {
	// TODO Auto-generated constructor stub
}
	private double weight;
	private int height;
	private double temperature;
	private boolean covid19;
	private LocalDate checkupDate;
	private Pediatrician pediatrician;

	// Konstruktor mit Automatischer Zeit
	public Checkup(double weight, int height, double temperature, boolean covid19, Pediatrician pediatrician) {
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.covid19 = covid19;
		// Gibt das aktuelle Datum an
		this.checkupDate = LocalDate.now();
		this.pediatrician = pediatrician;
	}

	// Konstruktor mit Manuelle Zeiteingabe
	public Checkup(double weight, int height, double temperature, boolean covid19, LocalDate localDate,
			Pediatrician pediatrician) {
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.covid19 = covid19;
		// Gibt das aktuelle Datum an
		this.checkupDate = localDate;
		this.pediatrician = pediatrician;
	}

	public double getWeight() {
		return weight;
	}

	public int getHeight() {
		return height;
	}

	public double getTemperature() {
		return temperature;
	}

	public boolean isCovid19() {
		return covid19;
	}

	public LocalDate getCheckupDate() {
		return checkupDate;
	}

	public Pediatrician getPediatrition() {
		return pediatrician;
	}

	@Override
	public String toString() {
		// %s --> string
		return String.format("{Date: %s, Weight: %skg, Height: %scm, Temp.: %s Celsius, Covid-19: %s}", this.checkupDate, this.weight,
				this.height, this.temperature, this.covid19);
		/*
		 * return "The following investigation: \n-Date: " + this.checkupDate +
		 * "\n-Weight: " + this.weight + "kg \n-Height: " + this.height +
		 * "cm \n-Temperature: " + this.temperature + "degree \n-Covid-19: " +
		 * this.covid19 + "\n-" + this.pediatrition.toString() + "\n";
		 */
	}

}
