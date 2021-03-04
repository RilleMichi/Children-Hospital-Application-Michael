package version1;

import java.time.LocalDate;

public class Checkup {
	private double weight;
	private int height;
	private double temperature;
	private boolean covid19;
	private LocalDate checkupDate;
	private Pediatrition pediatrition;

	// Konstruktor mit Automatischer Zeit
	public Checkup(double weight, int height, double temperature, boolean covid19, Pediatrition pediatrition) {
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.covid19 = covid19;
		// Gibt das aktuelle Datum an
		this.checkupDate = LocalDate.now();
		this.pediatrition = pediatrition;
	}

	// Konstruktor mit Manuelle Zeiteingabe
	public Checkup(double weight, int height, double temperature, boolean covid19, LocalDate localDate,
			Pediatrition pediatrition) {
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.covid19 = covid19;
		// Gibt das aktuelle Datum an
		this.checkupDate = localDate;
		this.pediatrition = pediatrition;
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

	public Pediatrition getPediatrition() {
		return pediatrition;
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
