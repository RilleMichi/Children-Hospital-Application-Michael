package topic08;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModelControllerApp extends Application {

  private Model model = new Model();

  @Override
  public void start(Stage primaryStage) throws Exception {
    
    URL url = this.getClass().getResource("modelcontrollerform.fxml");
    FXMLLoader loader = new FXMLLoader(url);
    Parent root = loader.load();
    
    ModelController controller = loader.getController();
    controller.init(this.model); // self-defined init method
    
    primaryStage.setScene(new Scene(root));
    primaryStage.setTitle("Registration Form");
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
