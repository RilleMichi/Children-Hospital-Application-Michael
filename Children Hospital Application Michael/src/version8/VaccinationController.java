package version8;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import version8.gson.LocalDateDeserializer;
import version8.gson.LocalDateSerializer;
import version8.models.Model;
import version8.models.Patient;
import version8.models.Pediatrician;
import version8.models.Vaccination;
import version8.models.Vaccination.Vaccine;

public class VaccinationController {

	private Model model;
	private Stage mainStage;
	private Scene mainScene;
	private Tab tab1;

	@FXML
	private ComboBox<Patient> patientComboBox;

	@FXML
	private ComboBox<Pediatrician> pediatricianComboBox;

	@FXML
	private ComboBox<Vaccine> vaccineComboBox;

	@FXML
	private Button cancel;

	@FXML
	private Button addVaccination;

	@FXML
	void handleAddVaccination(ActionEvent event) {
		Patient patient = this.patientComboBox.getValue();
		Pediatrician pediatrician = this.pediatricianComboBox.getValue();
		Vaccine vaccine = this.vaccineComboBox.getValue();
		Vaccination vaccination = new Vaccination(vaccine, pediatrician, patient.getNumber());
		patient.addVaccination(vaccination);

		//Implementierung für in Json
		this.model.writeVaccinations();
		
		// Zum Aktualisieren
		JavaFxApp.updateTab(this.tab1, this.model.getAllPatients());
		this.mainStage.setScene(this.mainScene);
	}

	@FXML
	void handleCancel(ActionEvent event) {
		this.mainStage.setScene(this.mainScene);
	}

	// Zum Ausfülllen der Comboboxen
	public void init(Model model, Stage mainStage, Tab tab1) {
		this.model = model;
		this.mainStage = mainStage;
		this.mainScene = mainStage.getScene();
		this.tab1 = tab1;

		for (Patient patient : this.model.getAllPatients()) {
			patientComboBox.getItems().add(patient);
		}
		for (Pediatrician pediatrician : this.model.getPediatricians()) {
			pediatricianComboBox.getItems().add(pediatrician);
		}
		for (Vaccine vaccine : Vaccine.values()) {
			vaccineComboBox.getItems().add(vaccine);
		}
	}

}
