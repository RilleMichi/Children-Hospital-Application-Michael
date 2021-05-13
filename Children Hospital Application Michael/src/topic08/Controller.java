package topic08;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

  @FXML
  private TextField userName;

  @FXML
  private Button submitButton;

  @FXML
  void handleSubmitButtonAction(ActionEvent event) {
    System.out.println("Username = " + this.userName.getText());
  }

}
