package view.viewConfig;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import view.Keybinds;

public class KeybindConfigView extends HBox {

  public KeybindConfigView(
    String labelText,
    String defaultKey,
    Keybinds keybinds
  ) {
    HBox keybindRow = new HBox();
    keybindRow.getStyleClass().add("keybind-row");

    Label keybindLabel = new Label(labelText + ": ");
    Button keybindButton = new Button(defaultKey);
    keybindRow.getChildren().addAll(keybindLabel, keybindButton);

    // Le style est à refaire, ce qui serait bien c que les boutons soient alignés à droite
    // Et aussi jsp pq tout est super à gauche wtf ? mdrrr tu verras qd tu ouvriras config ds l'app
    // T fier de moi pr mon beau travail ?
    keybindRow.setAlignment(Pos.CENTER);
    keybindLabel.setAlignment(Pos.CENTER);
    keybindButton.setAlignment(Pos.CENTER_RIGHT);

    keybindButton.setOnAction(e -> {
      Alert configAlert = new Alert(AlertType.CONFIRMATION);
      configAlert.setTitle("Keybinds configuration");
      configAlert.setHeaderText(
        "Please press the key that you want to bind to " + labelText
      );
      configAlert.setContentText("The current key is " + defaultKey);

      configAlert
        .getDialogPane()
        .addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
          String newKey = ke.getCode().getName();

          keybindButton.setText(newKey);
          configAlert.setContentText("The current key is " + newKey);

          ke.consume();
        });

      //   this.removeEventHandler(KeyEvent.KEY_PRESSED, ke -> {
      //       System.out.println(ke.getCode().getName());
      //       System.out.println(ke.getText());
      //       if (!keybinds.isKeybindPresent(ke.getText())) {
      //         keybindButton.setText(ke.getText());
      //         configAlert.setContentText("The current key is " + ke.getText());
      //       } else {
      //         configAlert.setContentText(
      //           "This key is already binded. The current key is " + ke.getText()
      //         );
      //       }
      //       ke.consume();
      //     });

      configAlert.showAndWait();

      e.consume();
    });

    this.getChildren().addAll(keybindLabel, keybindButton);
  }

  public Button getkeybindButton() {
    return (Button) this.getChildren().get(1);
  }
}
