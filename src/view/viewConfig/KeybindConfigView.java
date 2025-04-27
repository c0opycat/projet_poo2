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

public class KeybindConfigView extends GridPane {

  public KeybindConfigView(
    String labelText,
    String defaultKey,
    Keybinds keybinds
  ) {
    this.getStyleClass().add("keybind-row");
    this.setAlignment(Pos.CENTER);

    ColumnConstraints labelColumn = new ColumnConstraints();
    labelColumn.setMinWidth(100); // Largeur minimale pour les labels
    labelColumn.setPrefWidth(100); // Largeur préférée pour les labels
    labelColumn.setHgrow(Priority.NEVER); // Pas d'agrandissement horizontal

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

  public Button getkeybindButton() {
    return (Button) this.getChildren().get(1);
  }
}
