package version7;

import java.util.Collection;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import version7.models.Model;

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

		// Erstellen eines tabPane
		TabPane tabPane = new TabPane();
		// hinzufügen der Tabs in die tabPane
		tabPane.getTabs().add(tab1);
		tabPane.getTabs().add(tab2);
		tabPane.getTabs().add(tab3);
		

		//Menü
		MenuBar menuBar = new MenuBar(); 
	    
	    Menu fileMenu = new Menu("File");
	    Menu inputMenu = new Menu("Input");
	    Menu helpMenu = new Menu("Help");
	    menuBar.getMenus().addAll(fileMenu, inputMenu, helpMenu);

	    //Menüitem in Menu File
	    MenuItem quitItem = new MenuItem("Quit");
	    fileMenu.getItems().addAll(quitItem);
	    
	    //Menüitem in Menu Input
	    MenuItem inputCheckupItem = new MenuItem("Checkup");
	    MenuItem inputVaccinationItem = new MenuItem("Vaccination");
	    inputMenu.getItems().addAll(inputCheckupItem, inputVaccinationItem);
	    
	    //Menüitem in Menu Help
	    MenuItem aboutItem = new MenuItem("About");
	    helpMenu.getItems().addAll(aboutItem);
	    
	    //Eventhandling hinzufügen bei einer Aktion. Wichtig: funktioniert bei Menüitem
	    aboutItem.setOnAction(event -> {
	    	Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("About");
	    	alert.setContentText("This is a JavaFx Application in Version 7");
	    	//Man muss den Dialog schliessen bevor man zum Hauptfenster zurückgeht.
	    	alert.showAndWait();
	    });
	    
	    //Eventhandling für quititem. 
	    quitItem.setOnAction(event -> {
	    	//Die hinzugefügten Vaccination werden noch in einem JSON hineingeschrieben.
	    	model.writeVaccinations();
	    	//Schliesst die Java Applikation
	    	System.exit(0);
	    });
		
		//Die VBox ist die Wurzel
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

	//-----Hilfsmethoden---------
	//Man erstellt hier eine Generic um den Datentyp herauszubekommen
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

	public static void main(String[] args) {
		Application.launch(args);
	}
}
