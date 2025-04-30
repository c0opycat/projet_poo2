package view.viewGame.viewCommand.viewItemCommand;

import controller.controllerGame.controllerCommand.controllerItemCommand.EquipController;
import java.awt.Point;
import javafx.scene.control.Button;
import view.Lang;
import view.viewGame.GameView;

public class EquipView {

  private final Lang lang = new Lang();
  private final EquipController equipController;
  private final GameView gameView;

  public EquipView(GameView gameView) {
    this.gameView = gameView;
    this.equipController = new EquipController(this, gameView);
  }

  public Lang getLang() {
    return this.lang;
  }

  public EquipController getEquipController() {
    return this.equipController;
  }

  public GameView getGameView() {
    return this.gameView;
  }

  public Button equipButton(boolean isBackpack, String elem, int ind) {
    Button equipButton = new Button();
    this.getLang().setButtonLang(equipButton, "Equiper", "Equip");
    equipButton.getStyleClass().add("button-Commande");

    int containerX = this.getGameView().getContainerView().getX();
    int containerY = this.getGameView().getContainerView().getY();
    Point containerPoint = new Point(containerX, containerY);

    equipButton.setOnAction(e -> {
      if (isBackpack) {
        this.getEquipController().setEquipModel(1, String.valueOf(ind));
        if (this.getEquipController().execute(containerPoint)) {
          this.getGameView().getContainerView().updateContainerView(true);
          this.getGameView()
            .getHeroView()
            .getHeroController()
            .updateDescription();
        }
      } else {
        this.getEquipController().setEquipModel(3, String.valueOf(ind));
        if (this.getEquipController().execute(containerPoint)) {
          System.out.println("execute retourne true");
          this.getGameView().getContainerView().updateContainerView(false);
          this.getGameView()
            .getHeroView()
            .getHeroController()
            .updateDescription();
        }
      }
    });

    return equipButton;
  }
}
