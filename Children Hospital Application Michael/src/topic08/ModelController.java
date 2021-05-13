package topic08;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ModelController {

  private Model model;
  private List<String> unknownUsers;
  
  @FXML
  private TextField userName;
  
  @FXML
  private Button submitButton;

  public void init(Model model) {
    this.model = model;
    this.unknownUsers = new ArrayList<String>();
    this.userName.setOnKeyTyped(event -> {
      System.out.println(event.getCharacter());
    });
  }

  @FXML
  void handleSubmitButtonAction(ActionEvent event) {
    String userName = this.userName.getText();
    if (model.exists(userName)) {
      System.out.println("Hello " + userName + "!");
    } else {
      this.unknownUsers.add(userName);
      System.out.println("Unknown user!");
    }
  }

}
