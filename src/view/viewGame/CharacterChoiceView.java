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

/**
 * View class for the character selection screen.
 * Allows the user to input a name and select a job before starting the game.
 */
public class CharacterChoiceView extends BorderWithButtons {

  private Lang lang = new Lang();

  /**
   * Constructs a CharacterChoiceView and initializes its content.
   */
  public CharacterChoiceView() {
    super();
    this.addContent();
  }

  /**
   * Sets up the buttons for the character selection screen, including menu and quit buttons.
   */
  public void setButtons() {
    ArrayList<Button> buttons = new ArrayList<>();

    ButtonMenu menu = new ButtonMenu(this.getMainScene());

    ButtonQuit quit = new ButtonQuit();

    buttons.add(menu);

    buttons.add(quit);

    this.addButtons(buttons);
  }

  /**
   * Adds the content for the character selection screen, including input fields for name and job.
   * Also sets up the "Start" button to initialize the game.
   */
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
    jobList.getSelectionModel().selectFirst();

    nameBox.getChildren().addAll(nameLabel, nameTF);
    jobBox.getChildren().addAll(jobLabel, jobList);
    box.getChildren().addAll(nameBox, jobBox, start);

    nameBox.setAlignment(Pos.CENTER);
    jobBox.setAlignment(Pos.CENTER);
    box.setAlignment(Pos.CENTER);

    this.setContent(box);

    start.setOnAction(e -> {
      GameView gameView = new GameView(this.getUsername(), this.getJob());
      this.getMainScene().setContent(gameView);
      gameView.updateCurrentLocation();
      gameView.setButtons();
      gameView.addHandlers(gameView.getMainScene());
    });
  }

  /**
   * Gets the username entered by the user.
   * If the input is blank, defaults to "anonymous".
   *
   * @return the username entered by the user
   */
  private String getUsername() {
    TextField nameTF =
      (TextField) ((HBox) ((VBox) this.getCenter()).getChildren()
          .get(0)).getChildren()
        .get(1);

    return nameTF.getText().isBlank() ? "anonymous" : nameTF.getText().trim();
  }

  /**
   * Gets the job selected by the user.
   * If no job is selected, defaults to the first job in the list.
   *
   * @return the selected job
   */
  private String getJob() {
    @SuppressWarnings("unchecked")
    ComboBox<String> jobList = (ComboBox<
        String
      >) ((HBox) ((VBox) this.getCenter()).getChildren().get(1)).getChildren()
      .get(1);

    if (jobList.getSelectionModel().getSelectedItem() == null) {
      jobList.getSelectionModel().selectFirst();
    }

    return jobList.getSelectionModel().getSelectedItem();
  }
}
