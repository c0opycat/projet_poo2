package view.viewConfig;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import view.Keybinds;

/**
 * A view component for configuring keybinds in the application.
 * Displays a label and a button for each keybind, allowing the user to rebind keys.
 * @author L. Cooper
 */
public class KeybindConfigView extends GridPane {

  /**
   * Constructs a KeybindConfigView with a label, a default key, and a Keybinds object.
   *
   * @param labelText The text to display as the label for the keybind.
   * @param defaultKey The default key to display on the button.
   * @param keybinds The Keybinds object that manages the application's keybinds.
   */
  public KeybindConfigView(
    String labelText,
    String defaultKey,
    Keybinds keybinds
  ) {
    this.getStyleClass().add("keybind-row");
    this.setAlignment(Pos.CENTER);

    ColumnConstraints labelColumn = new ColumnConstraints();
    labelColumn.setMinWidth(100);
    labelColumn.setPrefWidth(100);
    labelColumn.setHgrow(Priority.NEVER);

    this.getColumnConstraints().addAll(labelColumn);

    Label keybindLabel = new Label(labelText + ": ");
    keybindLabel.setAlignment(Pos.CENTER_LEFT);

    Button keybindButton = new Button(defaultKey);
    keybindButton.setAlignment(Pos.CENTER);

    keybindButton.setOnAction(e -> {
      String key = keybinds.getSpecKeybind(labelText.toLowerCase());
      Alert configAlert = new Alert(AlertType.CONFIRMATION);
      configAlert.setTitle("Keybinds configuration");
      configAlert.setHeaderText(
        "Please press the key that you want to bind to " + labelText
      );
      configAlert.setContentText("The current key is " + key);

      configAlert
        .getDialogPane()
        .addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
          String newKey = ke.getCode().getName();

          keybindButton.setText(newKey);
          configAlert.setContentText("The current key is " + newKey);

          ke.consume();
        });

      configAlert.showAndWait();

      e.consume();
    });

    this.add(keybindLabel, 0, 0);
    this.add(keybindButton, 1, 0);
  }

  /**
   * Retrieves the button associated with the keybind.
   *
   * @return The Button used to configure the keybind.
   */
  public Button getkeybindButton() {
    return (Button) this.getChildren().get(1);
  }
}
