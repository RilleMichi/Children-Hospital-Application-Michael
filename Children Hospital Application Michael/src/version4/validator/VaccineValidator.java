package version4.validator;

import version4.exception.InvalidInputException;
import version4.models.Vaccination;
import version4.models.Vaccination.Vaccine;

public class VaccineValidator implements Validator<Vaccine> {
	
	 public VaccineValidator() {}
	
	  public Vaccine validate(String string) throws InvalidInputException {
		    string = string.trim().toUpperCase(); // removes leading/trailing spaces
		    for (Vaccine vaccine : Vaccination.Vaccine.values()) {
		      if (vaccine.name().startsWith(string)) {
		        return vaccine;
		      }
		    }
		    throw new InvalidInputException();
		  }


//	public Vaccination.Vaccine validate (String string) {
//		string = string.trim();
//		if(string.toLowerCase().matches("measles")) {
//			return Vaccination.Vaccine.MEASLES;
//		}
//		else if (string.toLowerCase().matches("(covid-19|covid19|covid)")) {
//			return Vaccination.Vaccine.COVID;
//		}
//		else if (string.toLowerCase().matches("diphteria")) {
//			return Vaccination.Vaccine.DIPHTERIA;
//		}
//		else if (string.toLowerCase().matches("tetanus")) {
//			return Vaccination.Vaccine.TETANUS;
//		}
//		else if (string.toLowerCase().matches("polio")) {
//			return Vaccination.Vaccine.POLIO;
//		}
//		else {
//			return null;
//		}
//	}
	
}
