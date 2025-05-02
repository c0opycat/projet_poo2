package view.viewGame.viewCommand.viewMenuCommand;

import controller.controllerGame.controllerCommand.controllerMenuCommand.PauseController;
import java.util.ArrayList;
import javafx.scene.control.Button;
import view.Lang;
import view.MyAlert;
import view.viewGame.GameView;

public class PauseView extends Button {

  private final PauseController pauseController;
  private final GameView gameView;
  private final Lang lang = new Lang();

  public PauseView(GameView gameView) {
    super();
    this.gameView = gameView;
    this.pauseController = new PauseController(gameView, this);

    this.setText("Pause");
    this.setOnAction(e -> {
        this.getPauseController().execute();
        this.showPauseAlert();
        e.consume();
      });
  }

  public PauseController getPauseController() {
    return this.pauseController;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public Lang getLang() {
    return this.lang;
  }

  public void showPauseAlert() {
    String title = this.getLang().getCurr_lang().equals("EN")
      ? "Game paused"
      : "Jeu en pause";
    String header = this.getLang().getCurr_lang().equals("EN")
      ? "The game is paused."
      : "Le jeu est en pause.";
    MyAlert pauseAlert = new MyAlert(title, header, null);

    ArrayList<String> optionList = new ArrayList<>();

    String resumeOption = this.getLang().getCurr_lang().equals("EN")
      ? "Resume"
      : "Reprendre";

    optionList.add(resumeOption);

    String choice = pauseAlert.showChoiceAlertNotCloseable(
      pauseAlert.createButtonTypes(optionList)
    );

    if (choice != null) {
      this.getPauseController().execute();
    }
  }
}
