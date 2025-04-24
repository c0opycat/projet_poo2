package view;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class MyAlert {

  private final String TITLE;
  private final String HEADER;
  private final String CONTENT;

  public MyAlert(String title, String header, String content) {
    this.TITLE = title;
    this.HEADER = header;
    this.CONTENT = content;
  }

  public void showInformation() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(TITLE);
    alert.setHeaderText(HEADER);
    alert.setContentText(CONTENT);
    alert.showAndWait();
  }

  public boolean showConfirmation() {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(TITLE);
    alert.setHeaderText(HEADER);
    alert.setContentText(CONTENT);

    ButtonType bt1 = new ButtonType("OK");
    ButtonType bt2 = new ButtonType("Cancel");

    alert.getButtonTypes().setAll(bt1, bt2);

    Optional<ButtonType> choices = alert.showAndWait();

    if (choices.get() == bt1) {
      return true;
    }

    return false;
  }
}
