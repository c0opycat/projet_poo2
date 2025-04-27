package view.viewGame;

import controller.controllerGame.GameController;
import java.awt.Label;
import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;

public class CharacterChoiceView extends BorderWithButtons {

  public CharacterChoiceView() {
    super();
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

    String lang = GameController.getLang();

    Label nameLabel = new Label();
    Label classLabel = new Label();

    if (lang.toUpperCase() == "EN") {
      nameLabel.setText("Name: ");
      classLabel.setText("Class: ");
    } else {
      nameLabel.setText("Nom : ");
      classLabel.setText("Classe : ");
    }
    // TextArea nameTA
  }
}
