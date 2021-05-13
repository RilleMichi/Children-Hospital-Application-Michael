package topic08;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CSSApp extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    URL fxmlUrl = this.getClass().getResource("form.fxml");
    URL cssUrl = this.getClass().getResource("style.css");

    FXMLLoader loader = new FXMLLoader(fxmlUrl);
    Parent root = loader.load();
    
    Scene scene = new Scene(root);
    scene.getStylesheets().add(cssUrl.toExternalForm());    
    
    primaryStage.setScene(scene);
    primaryStage.setTitle("Registration Form");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
