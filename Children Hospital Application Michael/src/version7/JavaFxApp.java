package version7;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Collection;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import version7.exception.InvalidInputException;
import version7.models.Checkup;
import version7.models.Model;
import version7.models.Patient;
import version7.models.Pediatrician;
import version7.validator.DateValidator;
import version7.validator.DoubleRangeValidator;
import version7.validator.IntRangeValidator;
import version7.validator.Validator;

public class JavaFxApp extends Application {

	private Model model;

	@Override
	public void start(Stage stage) throws Exception {

		model = Model.createTestModel();
		// liest die Checkups und Vaccinations
		model.readCheckups();
		model.readVaccinations();

		// Erstellen eines Tabs für Patients
		Tab tab1 = createTab("Patients", model.getAllPatients());
		// Erstellen eines Tabs für PArents
		Tab tab2 = createTab("Parents", model.getParents());
		// Erstellen eines Tabs für Pediatricians
		Tab tab3 = createTab("Pediatricians", model.getPediatricians());
		updateTab(tab1, this.model.getAllPatients());
		updateTab(tab2, this.model.getParents());
		updateTab(tab3, this.model.getPediatricians());

		// Erstellen eines tabPane
		TabPane tabPane = new TabPane();
		// hinzufügen der Tabs in die tabPane
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);

		// Menü
		MenuBar menuBar = new MenuBar();

		Menu fileMenu = new Menu("File");
		Menu inputMenu = new Menu("Input");
		Menu helpMenu = new Menu("Help");
		menuBar.getMenus().addAll(fileMenu, inputMenu, helpMenu);

		// Menüitem in Menu File
		MenuItem quitItem = new MenuItem("Quit");
		fileMenu.getItems().addAll(quitItem);

		// Menüitem in Menu Input
		MenuItem inputCheckupItem = new MenuItem("Checkup");
		MenuItem inputVaccinationItem = new MenuItem("Vaccination");
		inputMenu.getItems().addAll(inputCheckupItem, inputVaccinationItem);

		// Menüitem in Menu Help
		MenuItem aboutItem = new MenuItem("About");
		helpMenu.getItems().addAll(aboutItem);

		inputCheckupItem.setOnAction(event -> {
			//übergebe tab vom patient
			this.enterCheckup(stage, tab1);
		});

		// Eventhandling hinzufügen bei einer Aktion. Wichtig: funktioniert bei Menüitem
		aboutItem.setOnAction(event -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("About");
			alert.setContentText("This is a JavaFx Application in Version 7");
			// Man muss den Dialog schliessen bevor man zum Hauptfenster zurückgeht.
			alert.showAndWait();
		});

		// Eventhandling für quititem.
		quitItem.setOnAction(event -> {
			// Die hinzugefügten Vaccination werden noch in einem JSON hineingeschrieben.
			model.writeVaccinations();
			// Schliesst die Java Applikation
			System.exit(0);
		});

		// Die VBox ist die Wurzel
		VBox vBox = new VBox(menuBar, tabPane);

		// VBox wird in die Szene eingefügt
		Scene scene1 = new Scene(vBox);

		// die Szene wird dann in den Stage bzw. Fenster eingefügt
		stage.setScene(scene1);

		// Informationen bzw. Eigenschaften vom Fenster
		stage.setTitle("Children Hospital App");
		stage.setHeight(400);
		stage.setWidth(800);
		stage.show();
	}

	// -----Hilfsmethoden---------
	// Man erstellt hier eine Generic um den Datentyp herauszubekommen
	private static <T> Tab createTab(String label, Collection<T> collection) {
		TextArea textArea = new TextArea();
		// Anpassung im Textarea zu deaktivieren
		textArea.setEditable(false);
		// Man holt den Typ mithilfe des generischen Variable <T> und itiiert in diese
		// Colection. Man speichert jeweils es in die variable element.
		for (T element : collection) {
			textArea.appendText(element.toString() + "\n");
		}
		// Erstellen eines tab
		Tab tab = new Tab(label, textArea);
		tab.setClosable(false);

		return tab;
	}
	
	//Tabs aktualisieren
	private static <T> void updateTab(Tab tab, Collection<T> collection) {
		TextArea textArea = (TextArea) tab.getContent();
		//setze den Text wieder leer, damit es nicht doppelt angezeigt wird.
		textArea.setText("");
		for(T element : collection) {
			textArea.appendText(element.toString() + "\n");
		}
	}

	private void enterCheckup(Stage stage, Tab tab) {
		
		//MainScene Erstellen
		Scene mainScene = stage.getScene();
		
		//Grid hinzufügen
		GridPane gridPane = new GridPane();

		Label label0 = new Label("Patient");
		// Auswahl der Patienten
		ComboBox<Patient> comboBox0 = new ComboBox<>();
		for (Patient patient : this.model.getAllPatients()) {
			comboBox0.getItems().add(patient);
		}
		// Breite anppassen
		comboBox0.setMaxWidth(200);

		Label label1 = new Label("Pediatrician");
		// Auswahl der Patienten
		ComboBox<Pediatrician> comboBox1 = new ComboBox<>();
		for (Pediatrician pediatrician : this.model.getPediatricians()) {
			comboBox1.getItems().add(pediatrician);
		}
		// Breite anppassen
		comboBox1.setMaxWidth(200);

		//Erstellen von Labels
		Label label2 = new Label("Weight");
		TextField textField2 = new TextField();
		Label label3 = new Label("Height");
		TextField textField3 = new TextField();
		Label label4 = new Label("Temperature");
		TextField textField4 = new TextField();
		Label label5 = new Label("Date");
		TextField textField5 = new TextField();
		Label label6 = new Label("Covid");
		CheckBox checkBox6 = new CheckBox();

		Button addButton = new Button("Add Checkup");
		Button cancelButton = new Button("Cancel");
		
		Label invalidInputLabel = new Label("Invalid Input");
		invalidInputLabel.setTextFill(Color.RED);
		invalidInputLabel.setVisible(false);

		//Füge das in den GridPane hinzu X und X Koordinaten
		gridPane.add(label0, 0, 0);
		gridPane.add(comboBox0, 1, 0);
		gridPane.add(label1, 0, 1);
		gridPane.add(comboBox1, 1, 1);
		gridPane.add(label2, 0, 2);
		gridPane.add(textField2, 1, 2);
		gridPane.add(label3, 0, 3);
		gridPane.add(textField3, 1, 3);
		gridPane.add(label4, 0, 4);
		gridPane.add(textField4, 1, 4);
		gridPane.add(label5, 0, 5);
		gridPane.add(textField5, 1, 5);
		gridPane.add(label6, 0, 6);
		gridPane.add(checkBox6, 1, 6);
		gridPane.add(cancelButton, 0, 7);
		gridPane.add(addButton, 1, 7);
		gridPane.add(invalidInputLabel, 1, 8);

		// Setze den Grid in die Miite
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		gridPane.setAlignment(Pos.CENTER);

		//Blende den Fehler wieder aus nach Eingaben
		textField2.setOnKeyTyped(EventHandler -> {
			invalidInputLabel.setVisible(false);
		});
		textField3.setOnKeyTyped(EventHandler -> {
			invalidInputLabel.setVisible(false);
		});
		textField4.setOnKeyTyped(EventHandler -> {
			invalidInputLabel.setVisible(false);
		});
		textField5.setOnKeyTyped(EventHandler -> {
			invalidInputLabel.setVisible(false);
		});
		
		//Kommt zurück zum mainScene
		cancelButton.setOnAction(EventHandler -> {
			stage.setScene(mainScene);
		});
		
		addButton.setOnAction(event -> {
			try {
				Patient patient = comboBox0.getValue();
				Pediatrician pediatrician = comboBox1.getValue();

				String weightStr = textField2.getText();
				Validator<Double> weightValidator = new DoubleRangeValidator(1, 600);
				double weight = weightValidator.validate(weightStr);

				String heightStr = textField3.getText();
				Validator<Integer> heightValidator = new IntRangeValidator(1, 300);
				int height = heightValidator.validate(heightStr);

				String temperatureStr = textField4.getText();
				Validator<Double> temperatureValidator = new DoubleRangeValidator(20, 50);
				double temperature = temperatureValidator.validate(temperatureStr);

				String dateStr = textField5.getText();
				Validator<LocalDate> dateValidator = new DateValidator();
				LocalDate checkupDate = dateValidator.validate(dateStr);

				boolean covidTest = checkBox6.isSelected();

				//Create Checkup
				Checkup checkup = new Checkup(weight, height, temperature, covidTest, checkupDate, pediatrician,
						patient.getNumber());
				patient.addCheckup(checkup);
				
				// In eine CSV Speichern
				String csv = checkup.toCSV();
				try {
					// True wird hier gebraucht um es hinten anzufügen
					Writer writer = new FileWriter("checkups.csv", true);
					writer.write(csv + "\n");
					writer.close();
				} catch (IOException e) {
					System.out.println("Writing checkup not possible!");
				}
				
				//update scene
				updateTab(tab, model.getAllPatients());
				// set scene
				stage.setScene(mainScene);
				
			} catch (InvalidInputException e) {
				invalidInputLabel.setVisible(true);
				e.printStackTrace();
			}
		});

		// Wird in die Szene eingefügt.
		Scene scene = new Scene(gridPane);
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
