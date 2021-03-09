package version2;

public class VaccineValidator {

	public Vaccination.Vaccine validate (String string) {
		string = string.trim();
		if(string.toLowerCase().matches("measles")) {
			return Vaccination.Vaccine.MEASLES;
		}
		else if (string.toLowerCase().matches("(covid-19|covid19|covid)")) {
			return Vaccination.Vaccine.COVID;
		}
		else if (string.toLowerCase().matches("diphteria")) {
			return Vaccination.Vaccine.DIPHTERIA;
		}
		else if (string.toLowerCase().matches("tetanus")) {
			return Vaccination.Vaccine.TETANUS;
		}
		else if (string.toLowerCase().matches("polio")) {
			return Vaccination.Vaccine.POLIO;
		}
		else {
			return null;
		}
	}
	
}
