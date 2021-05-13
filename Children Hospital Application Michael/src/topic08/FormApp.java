package topic08;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FormApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    URL url = this.getClass().getResource("form.fxml");
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();
    
    primaryStage.setScene(new Scene(root));
    primaryStage.setTitle("Registration Form");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
