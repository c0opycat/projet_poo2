package view.viewGame;

import controller.controllerGame.GameController;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.BorderWithButtons;
import view.ButtonMenu;
import view.ButtonQuit;
import view.Lang;

public class CharacterChoiceView extends BorderWithButtons {

  private Lang lang = new Lang();

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
    VBox box = new VBox(20);
    box.setId("character-choice");

    HBox nameBox = new HBox(5);
    HBox jobBox = new HBox(5);
    Label nameLabel = new Label();

    Label jobLabel = new Label();
    Button start = new Button();

    lang.setLabelLang(nameLabel, "Entre ton nom :", "Enter your name:");
    lang.setLabelLang(jobLabel, "Choisis ta classe :", "Choose your class:");
    lang.setButtonLang(start, "Commencer", "Start");

    TextField nameTF = new TextField();
    nameTF.setMaxWidth(300);

    ComboBox<String> jobList = new ComboBox<>();
    jobList.setStyle("-fx-font-size: 16px");

    for (String job : GameController.getJobs()) {
      jobList.getItems().add(job);
    }

    nameBox.getChildren().addAll(nameLabel, nameTF);
    jobBox.getChildren().addAll(jobLabel, jobList);
    box.getChildren().addAll(nameBox, jobBox, start);

    nameBox.setAlignment(Pos.CENTER);
    jobBox.setAlignment(Pos.CENTER);
    box.setAlignment(Pos.CENTER);

    this.setContent(box);

    start.setOnAction(e -> {
      GameView gameView = new GameView("Hihi", "MEDIC");
      this.getMainScene().setContent(gameView);
      gameView.updateCurrentLocation();
      gameView.setButtons();
      // gameView
      //   .getCurrentLocationView()
      //   .setCommandsView(gameView.getCommandsView());
      gameView.addHandlers(gameView.getMainScene());
    });
  }
}
