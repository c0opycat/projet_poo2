package view.viewGame.viewCommand.viewInteractCommand;

import controller.controllerGame.controllerCommand.controllerInterractCommand.TakeButtonController;
import java.awt.Point;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

public class TakeButtonView extends Button {

  private Lang lang = new Lang();
  private final TakeButtonController takeController;

  public TakeButtonView(GameView gameView, String elem, int ind) {
    super();
    this.takeController = new TakeButtonController(gameView, this);
    this.lang.setButtonLang(this, "Prendre", "Take");
    this.getStyleClass().add("button-Commande");

    int containerX = gameView.getContainerView().getX();
    int containerY = gameView.getContainerView().getY();
    Point containerPoint = new Point(containerX, containerY);

    this.setOnAction(e -> {
        System.out.println(containerPoint);
        this.getTakeController().setTakeModel(String.valueOf(ind));
        if (this.getTakeController().execute(containerPoint)) {
          this.getTakeController().removeItemContainerView(ind);
        }
      });
  }

  public TakeButtonController getTakeController() {
    return this.takeController;
  }
}
