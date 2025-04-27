package view.viewGame;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

public class CharacterChoiceView extends BorderWithButtons {

  public CharacterChoiceView() {
    super();
    this.addContent();
  }

  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());

    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);

    buttons.add(quit);

    this.addButtons(buttons);
  }

  private void addContent() {
    VBox box = new VBox();

    String lang = GameView.loadLanguage();

    Label nameLabel = new Label();
    Label classLabel = new Label();
    Button start = new Button();

    if (lang.toUpperCase() == "EN") {
      nameLabel.setText("Name: ");
      classLabel.setText("Class: ");
      start.setText("Start");
    } else {
      nameLabel.setText("Nom : ");
      classLabel.setText("Classe : ");
      start.setText("Commencer");
    }
    // TextArea nameTA

    box.getChildren().addAll(nameLabel, classLabel, start);

    this.setContent(box);

    start.setOnAction(e -> {
      GameView gameView = new GameView("Hihi", "MEDIC");
      this.setContent(gameView);
    });
  }
}
