package view.viewGameOver;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import view.Lang;
import view.viewGame.GameView;

public class EndGameInfos extends VBox {

  private final GameView gameView;
  private Lang lang = new Lang();

  public EndGameInfos(GameView gameView) {
    super();
    this.gameView = gameView;

    this.addElements();
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public Lang getLang() {
    return this.lang;
  }

  private void addElements() {
    String endText = "";

    TextArea endDescription = new TextArea();
    endDescription.setEditable(false);
    endDescription.setWrapText(true);

    if (this.getGameView().getGameController().isWon()) {
      endText = lang.getCurr_lang().equals("EN")
        ? "You win !"
        : "Vous avez gagné !";
      endDescription.setText(
        this.getGameView().getGameController().getGameWonMessage()
      );
    } else {
      endText = lang.getCurr_lang().equals("EN")
        ? "You died !"
        : "Vous êtes mort !";
      endDescription.setText(
        this.getGameView().getGameController().getGameLostMessage()
      );
    }

    Label endLabel = new Label(endText);

    Region spring = new Region();

    //Get the score
    long score = this.getGameView().getGameController().getScore();
    Label scoreLabel = new Label(
      lang.getCurr_lang().equals("EN")
        ? "Your Score : " + score
        : "Votre score : " + score
    );

    this.getChildren().addAll(endLabel, scoreLabel, spring, endDescription);

    //Style
    spring.setMaxHeight(100);
    VBox.setVgrow(spring, Priority.ALWAYS);
    endLabel.setStyle(
      "-fx-font-weight: bold; -fx-font-size: 50px; -fx-text-fill: #380e0e"
    );
    scoreLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25px");
    endLabel.setAlignment(Pos.CENTER);
    scoreLabel.setAlignment(Pos.CENTER);
    VBox.setMargin(endLabel, new Insets(30));
    VBox.setMargin(scoreLabel, new Insets(20));
  }
}
