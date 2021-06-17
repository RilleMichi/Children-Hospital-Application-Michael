package version8.models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Checkup {public Checkup() {
	// TODO Auto-generated constructor stub
}
	private double weight;
	private int height;
	private double temperature;
	private boolean covid19;
	private LocalDate checkupDate;
	private Pediatrician pediatrician;
	
	private int patientNumber;

	// Konstruktor mit Automatischer Zeit
	public Checkup(double weight, int height, double temperature, boolean covid19, Pediatrician pediatrician, int patientNumber) {
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.covid19 = covid19;
		// Gibt das aktuelle Datum an
		this.checkupDate = LocalDate.now();
		this.pediatrician = pediatrician;
		this.patientNumber = patientNumber;
	}

	// Konstruktor mit Manuelle Zeiteingabe
	public Checkup(double weight, int height, double temperature, boolean covid19, LocalDate localDate,
			Pediatrician pediatrician, int patientNumber) {
		this.weight = weight;
		this.height = height;
		this.temperature = temperature;
		this.covid19 = covid19;
		// Gibt das aktuelle Datum an
		this.checkupDate = localDate;
		this.pediatrician = pediatrician;
		this.patientNumber = patientNumber;
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

	public int getPatientNumber() {
		return patientNumber;
	}

	@Override
	public String toString() {
		// %s --> string
		// this.pediatrician == null ? "" :  this.pediatrician.getFullName() --> Falls pediatrician leer gib die Variable leer aus
		return String.format("{Date: %s, Weight: %skg, Height: %scm, Temp.: %s Celsius, Covid-19: %s, Pediatrician: %s}", this.checkupDate, this.weight,
				this.height, this.temperature, this.covid19, this.pediatrician == null ? "" : this.pediatrician.getFullName());
		/*
		 * return "The following investigation: \n-Date: " + this.checkupDate +
		 * "\n-Weight: " + this.weight + "kg \n-Height: " + this.height +
		 * "cm \n-Temperature: " + this.temperature + "degree \n-Covid-19: " +
		 * this.covid19 + "\n-" + this.pediatrition.toString() + "\n";
		 */
	}
	
	  
	  public String toCSV() {
	    return this.checkupDate + "," + this.height + "," + this.weight + "," + this.temperature + "," + this.covid19 + "," + this.pediatrician.getFullName() + "," + this.patientNumber;
	  }
	  
	  public static Checkup fromCSV(String csv, Model model) {
	    String[] fields = csv.split(",");
	    
	    String[] yyyymmdd = fields[0].split("-");
	    int year = Integer.parseInt(yyyymmdd[0]);
	    int month = Integer.parseInt(yyyymmdd[1]);
	    int day = Integer.parseInt(yyyymmdd[2]);
	    LocalDate checkupDate = LocalDate.of(year, month, day);
	    
	    int height = Integer.parseInt(fields[1]);
	    double weight = Double.parseDouble(fields[2]);
	    double temperature = Double.parseDouble(fields[3]);
	    
	    boolean covidTest = Boolean.parseBoolean(fields[4]);
	    
	    String pediatricianName = fields[5];
	    //Man muss ein Pediatricion Objekt zurückgeben
	    Pediatrician pediatrician = model.getPediatrician(pediatricianName);
	    
	    int patientNumber = Integer.parseInt(fields[6]);
	    
	    
	    //Null für Pediatricion
	    return new Checkup(weight, height, temperature, covidTest, checkupDate, pediatrician ,patientNumber);
	  }

}
