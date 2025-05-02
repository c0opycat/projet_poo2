package view;

import java.util.ArrayList;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Utility class for displaying various types of alerts in the application.
 * Provides methods for showing information, confirmation, and choice alerts.
 * @author L. Cooper
 * @author A. Bertrand-Bernard
 */
public class MyAlert {

  private final String TITLE;
  private final String HEADER;
  private final String CONTENT;

  /**
   * Constructs a MyAlert instance with the specified title, header, and content.
   *
   * @param title the title of the alert
   * @param header the header text of the alert
   * @param content the content text of the alert
   */
  public MyAlert(String title, String header, String content) {
    this.TITLE = title;
    this.HEADER = header;
    this.CONTENT = content;
  }

  /**
   * Displays an information alert with the specified title, header, and content.
   */
  public void showInformation() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle(TITLE);
    alert.setHeaderText(HEADER);
    alert.setContentText(CONTENT);
    alert.showAndWait();
  }

  /**
   * Displays a confirmation alert with "OK" and "Cancel" options.
   *
   * @return true if the user selects "OK", false otherwise
   */
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

  /**
   * Displays a choice alert with the specified button types.
   *
   * @param buttonTypes a list of ButtonType objects representing the choices
   * @return the text of the button selected by the user
   */
  public String showChoiceAlert(ArrayList<ButtonType> buttonTypes) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(TITLE);
    alert.setHeaderText(HEADER);
    alert.setContentText(CONTENT);

    alert.getButtonTypes().setAll(buttonTypes);

    Optional<ButtonType> choices = alert.showAndWait();

    return choices.isPresent() ? choices.get().getText() : null;
  }

  /**
   * Displays a choice alert with the specified button types that can't be closed.
   *
   * @param buttonTypes a list of ButtonType objects representing the choices
   * @return the text of the button selected by the user
   */
  public String showChoiceAlertNotCloseable(ArrayList<ButtonType> buttonTypes) {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle(TITLE);
    alert.setHeaderText(HEADER);
    alert.setContentText(CONTENT);

    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();

    stage.setOnCloseRequest(event -> {
      event.consume();
    });

    alert.getButtonTypes().setAll(buttonTypes);

    Optional<ButtonType> choices = alert.showAndWait();

    return choices.isPresent() ? choices.get().getText() : null;
  }

  /**
   * Creates a list of ButtonType objects from a list of button text strings.
   *
   * @param buttonTextList a list of strings representing button labels
   * @return a list of ButtonType objects
   */
  public ArrayList<ButtonType> createButtonTypes(
    ArrayList<String> buttonTextList
  ) {
    ArrayList<ButtonType> buttonTypes = new ArrayList<>();

    for (String buttonText : buttonTextList) {
      buttonTypes.add(new ButtonType(buttonText));
    }

    return buttonTypes;
  }
}
